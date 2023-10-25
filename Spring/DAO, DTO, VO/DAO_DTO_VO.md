# DAO, DTO, VO 차이

## DAO

- 실제로 DB의 data에 접근하기 위한 객체
    - 실제로 DB에 접근하여 data를 삽입, 삭제, 조회, 수정 등 CRUD 기능을 수행한다.
    - DataBase 접근을 하기 위한 로직과 비지니스 로직을 분리하기 위해 사용한다.
    - DAO의 경우는 DB와 연결할 Connection 까지 설정되어 있는 경우가 많다.
    - Service와 DB를 연결하는 고리 역할을 한다.
    - Repository package와 비슷하게 사용되는 것이 DAO이다.
- 예시

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDao {

    public void add(TestDto dto) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "root");

        PreparedStatement preparedStatement = connection.prepareStatement("insert into users(id,name,password) value(?,?,?)");

        preparedStatement.setString(1, dto.getName());
        preparedStatement.setInt(2, dto.getValue());
        preparedStatement.setString(3, dto.getData());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        
        connection.close();

    }
}
```

## DTO

![Untitled (1)](https://github.com/RIN-1011/RIN-1011/assets/60701386/9fbf8d75-337d-485e-aacd-ea2dfc444f89)

- DTO는 계층 간 데이터 교환을 하기 위해 사용하는 객체로, DTO는 로직을 가지지 않는 순수한 데이터 객체(Java Beans)이다.
    - DTO는 즉, getter/setter 메서드만 가진 클래스를 의미한다.
    - DB에서 데이터를 얻어서 Service나 Controller 등으로 보낼 때 사용한다.
    - 엔티티를 DTO 형태로 변환한 후 사용한다.
- Entity 클래스는 데이터베이스와 매핑 되어있는 핵심 클래스이므로 절대 요청이나 응답 값을 전달하는 클래스로 사용하면 안된다.
    - view는 비즈니스 요구사항에 따라 자주 변경됨 → 만약 Entity 클래스를 요청 및 응답 값을 전달하는 클래스로 사용한다면 뷰 변경 시마다 Entity 클래스 변경됨 → Entity 클래스와 얽혀있는 많은 클래스들에게 영향을 끼침 → 따라서 view의 변경에 따라 영향을 끼치지 않고 자유로운 변경이 가능한 DTO 사용함
- 예시
    
    **Entity**
    
    ```java
    @Entity
    @Getter
    @Setter
    public class Member {
        @Id
        @GeneratedValue
        private Long id;
    
        private String name;
    		private int age;
    }
    ```
    
    **DTO**
    
    ```java
    @Getter
    @Setter
    static class ResponseDto {
        private String name;
        private String result = "결과입니다.";
    
        public ResponseDto(Member member) {
            name = member.getName();
        }
    }
    ```
    
    **Controller**
    
    ```java
    @PostMapping("api/useDTO")
    @ResponseBody
    public ResponseDto useDTO(@RequestParam String name, @RequestParam Long id) {
    				Member findMember = memberService.findOne(id);
            return new ResponseDto(findMember);
    }
        
    @PostMapping("api/notUseDTO")
    @ResponseBody
    public FindMember notUseDTO(@RequestParam String name, @RequestParam Long id) {
            Member findMember = memberService.findOne(id);
            return new FindMember(findMember);
    }
    ```
    
    → useDTO 메서드는 DTO로 원하는 값만 반환
    
    → notUseDTO 메서드는 Member 엔티티의 모든 정보 반환
    

## VO

- VO는 DTO와 달리 Read-Only속성을 지닌 값 오브젝트이다.
- DTO는 setter를 가지고 있어서 값이 변할 수 있지만 VO의 경우에는 getter만 가지고 있어서 수정이 불가능하다.
- 예시
    
    ```java
    public class UserDTO {
    
        private String name;
        private String id;
    
        public UserDTO(String name, String id) {
            this.name = name;
            this.id = id;
        }
    
        public String getName() {
            return name;
        }
    
        public String getId() {
            return id;
        }
    
    }
    ```
    
- VO 생성 시 제약조건
    - 불변성
        - setter와 가변 로직이 없는 불변상태여야 함
        - 이 덕분에 VO를 호출하는 쪽에서는 값 변경에 대한 걱정을 할 필요가 없음
    - 동등성
        - 생성된 여러 VO가 실제 다른 객체더라도 ‘값’이 같다면 동등한 객체로 판단
        - 따라서 equals & hashCode 재정의 해야 함
    - 자가 유효성 검사
        - 원시 타입 사용할 시, 사용하는 모든 곳에서 유효성 검사 진행해야 하는 문제점 존재
        - VO를 사용하면 사용하는 쪽에서는 유효성 검사가 보장되어 있으므로 안전하게 사용 가능
    - 코드
        
        ```java
        public class ShapeProperty {
        		
            // 불변성 (Immutable)
            private final int width;
            private final int height;
        
            public Shape(final int width, final int height) {
                // 자가 유효성 검사 (Self-Validation)
                validateWidth(width);
                validateHeight(height);
                
                this.width = width;
                this.height = height;
            }
        
            // 동등성 (Equality)
            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                ShapeProperty that = (ShapeProperty) o;
                return width == that.width && height == that.height;
            }
        
            @Override
            public int hashCode() {
                return Objects.hash(width, height);
            }
        }
        ```
        
- VO의 필요성 : https://ksh-coding.tistory.com/83

## DAO vs DTO vs VO

### DAO vs DTO

- DAO : 실제 데이터베이스에 접근하기 위한 객체
- DTO : 데이터 교환을 위해 사용하는 객체

### DTO vs VO

- DTO = 데이터 전달용
- VO = 값 표현

| 종류 | 용도 | 동등 결정 | 가변 / 불변 | 로직 |
| --- | --- | --- | --- | --- |
| DTO | 계층 간 데이터 전달 | 속성값이 모두 같아도 같은 객체가 아닐 수 있음 | - setter 존재 시 가변<br>- setter 비 존재시 불변 | getter/setter 이외의 로직이 불필요함 |
| VO | 값 자체를 표현 | 속성값이 모두 같으면 같은 객체 | 불변 | getter/setter 이외의 로직을 가질 수 있음 |

## 참고

[https://velog.io/@ha0kim/DAO-DTO-VO-차이](https://velog.io/@ha0kim/DAO-DTO-VO-%EC%B0%A8%EC%9D%B4)

[https://maenco.tistory.com/entry/Java-DTO와-VO의-차이](https://maenco.tistory.com/entry/Java-DTO%EC%99%80-VO%EC%9D%98-%EC%B0%A8%EC%9D%B4)

https://dkswnkk.tistory.com/500

https://lemontia.tistory.com/591

https://kbwplace.tistory.com/162

https://80000coding.oopy.io/dcd0f224-053f-456c-af22-d7e0946fa868

https://syoun602.tistory.com/6
