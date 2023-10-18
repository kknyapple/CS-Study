# JPA(Java Persistance API)

### ORM

Object-Relational Mapping의 약자로, 애플리케이션 Class와 관계형 데이터베이스의 테이블을 매핑한다는 뜻이며, 기술적으로는 애플리케이션의 객체를 RDB 테이블에 자동으로 영속화 해주는 것이다.

종류로는 Hibernate, EclipseLink, DataNucleus 등이 있다.

**장점** 

- SQL문이 아닌 Method를 통해 DB를 조작할 수 있어, 개발자는 객체 모델을 이용하여 비즈니스 로직을 구성하는데만 집중할 수 있다.
- Query와 같이 필요한 선언문, 할당 등의 부수적인 코드가 줄어, 코드의 가독성을 높일 수 있다.

**단점**

- 프로젝트의 규모가 크고 복잡하여 설계가 잘못된 경우, 속도 저하 및 일관성을 무너뜨리는 문제가 생길 수 있다.
- 복잡하고 무거운 Query는 속도를 위해 결국 SQL문을 사용해야할 수 있다.

## JPA

자바 진영에서 ORM(Object-Relational Mapping) 기술 표준으로 사용되는 인터페이스의 모음으로, 자바 어플리케이션에서 관계형 데이터베이스를 사용하는 방식을 정의한 인터페이스이다.

이는 인터페이스이기 때문에 Hibernate, OpenJPA 등이 JPA를 구현한다.

![image](https://github.com/Ahrang777/CS-Study/assets/100702397/d69b1f7e-63a8-4c45-857d-39cfd30601a6)


JPA는 애플리케이션과 JDBC 사이에서 동작하며, 개발자가 JPA를 활용했을 때 JDBC API를 통해 SQL을 호출하여 데이터베이스와 호출하는 전개가 이루어진다.

즉, 개발자는 JPA의 활용법만 익히면 DB 쿼리를 직접 구현하지 않아도 데이터베이스를 관리할 수 있다.

### JPA 특징

1. **객체 중심 개발 가능**
    
    JAVA에서 객체 중심으로 개발한 뒤 이를 DB에 객체 형태로 저장하고 싶을 때 JPA를 통해 이 문제를 해결할 수 있다.
    
2. **생산성 증가**
    
    CRUD를 간단하게 구현할 수 있다.
    
    ```sql
    // CRUD 코드
    
    // 저장
    jpa.persist(member)
    
    // 조회
    Member member = jpa.fiind(memberId)
    
    // 수정
    member.setName("변경할 이름")
    
    // 삭제
    jpa.remove(member)
    ```
    
3. **유지보수 용이**
    
    SQL을 직접 다루면 엔티티에 필드를 하나만 추가해도 그에 해당하는 SQL과 결과를 매핑하기 위한 JDBC API 코드를 모두 변경해야하지만, JPA를 사용하면 이러한 과정을 JPA가 대신 처리해준다.
    
4. **패러다임의 불일치 해결**
    
    JAVA에서는 부모클래스와 자식클래스와의 관계인 **상속 관계**가 존재하는데, 데이터베이스에서는 이러한 객체의 상속관계를 지원하지 않는다.
    
    이러한 문제를 JPA는, 아래와 같이 테이블에서 상속과 비슷한 ‘슈퍼타입, 서브타입 관계’를 이용하여 해결한다.
    
    ![image](https://github.com/Ahrang777/CS-Study/assets/100702397/1e3e977c-f919-459f-bdcc-dcdb221865f5)

    
    개발자는 자바 컬렉션에 저장하듯이  JPA에게 객체를 저장해주기만 하면 된다.
    
    예를 들어, 앨범을 저장하고 싶다면 다음과 같이 한 줄만 입력하면 된다.
    
    ```java
    jpa.persist(album);
    ```
    
    그러면 JPA는 스스로 상속관계임을 알고, 위 코드를 아래의 쿼리와 같이 변환하여 실행한다.
    
    ```sql
    INSERT INTO ITEM (ID, NAME, PRICE) .....
    INSERT INTO ALBUM (ARTIST) .....
    ```
    
5. **성능 증가**
    
    ```sql
    String memberId = "testId"
    
    Member member1 = jpa.find(memberId); // 조회
    Member member2 = jap.find(memberId); // 조회
    ```
    
    위는 같은 회원을 두 번 조회하는 코드이다.
    
    JPA를 사용하지 않았을 때는 두 번의 SELECT 쿼리가 수행되지만, JPA를 사용하면 SELECT 쿼리가 한 번만 수행되고, 그 다음은 이미 조회한 회원 객체를 재사용하면 되기 때문에 성능이 좋아질 수 있다.
    

### 제약사항

JPA는 복잡한 쿼리보다는 실시간 쿼리에 최적화 되어 있다. 복잡한 작업이 필요한 경우에는 기존의 MyBatis와 같은 Mapper 방식이 더 효율적일 수 있다.

따라서 Spring에서는 JPA와 MyBatis를 같이 사용할 수 있기 때문에 상황에 맞는 방식을 택하여 개발하면 된다.

---

### 참고

https://dbjh.tistory.com/77

https://devfunny.tistory.com/813

[https://junny00.tistory.com/entry/JPA-JPA-동작-과정과-장점](https://junny00.tistory.com/entry/JPA-JPA-%EB%8F%99%EC%9E%91-%EA%B3%BC%EC%A0%95%EA%B3%BC-%EC%9E%A5%EC%A0%90)
