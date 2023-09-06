# [DB] Statement와 PreparedStatement

## Statement와 PreparedStatement

- SQL문을 실행하고 결과를 반환하는 기능들을 캡슐화한 인터페이스
- PreparedStatement는 Statement를 상속 받음
- 가장 큰 차이점은 캐시 사용 여부

## SQL 실행 단계

1. 쿼리 문장 분석(Query의 문법적, 의미적 오류 체크, 쿼리 실행 계획 수립 등)
2. 컴파일
3. 실행

## Statement

```java
String sql= " Select * From TEST where name='" + name + "' ";

Statement stmt = conn.createStatement()

ResultSet rs = stmt.executeQuery(sql);
```

- 매번 컴파일을 수행해야 한다.
- 쿼리에 파라미터 값이 없고 `executeQuery()` 메소드에 쿼리를 파라미터 값으로 넣고 있기 때문에 실행 전까지는 무슨 쿼리를 실행하는지 알지 못한다.
- 따라서 쿼리문을 실행할 때마다 생성하기 때문에 반복 실행되는 경우에 효율이 떨어진다.

### 장점

- 테이블, 칼럼에 대한 동적 쿼리 작성이 가능하다. (`CREATE`, `DROP`, `ALTER`과 같은 DDL 작성에 적합함)
- 쿼리 실행문을 직접 확인할 수 있어 쿼리 분석이 쉽다.

### 단점

- SQL 수행 과정 1번을 매 요청마다 수행하기 때문에 Query 처리 비용이 더 든다.
- SQL문을 수행하는 과정에서 매번 컴파일을 하기 때문에 성능상 이슈가 발생함
- SQL Injection으로 인한 공격에 노출된다.

## PreparedStatement

```java
String sql="Select * From TEST where name=?";

PreparedStatement psmt=conn.preparedStatement(sql);

psmt.setString(1,"yoon");

ResultSet rs=psmt.executeQuery();
```

- parse 과정을 최초 1회만 수행하고, parse를 거친 후 생성된 결과는 메모리 어딘가에 저장해두고 필요할 때마다 사용한다.
- Statement와 다르게 conn.preparedStatement(sql)을 통해 쿼리문을 미리 생성하기 때문에, 실행할 때 executeQuery() 메소드에 파라미터를 넣지 않고 수행한다.
- 따라서 실행시 쿼리를 생성하지 않고 이미 미리 생성되어있는 상태이기 때문에 Statement에 비해 반복 실행시 속도가 훨씬 빠르다.

### 장점

- SQL 과정의 1번은 건너뛰고 2번부터 처리하기 때문에 처리 시간이 빠르다.
- 자주 변경되는 부분을 변수를 선언하고 매번 다룬 값을 대입(바인딩)하여 사용하는데, 이때 바인딩 데이터는 SQL문법이 아닌 내부의 인터프리터나 컴파일 언어로 처리하기 때문에 문법적인 의미를 가질 수 없어, 바인딩 변수에 SQL 공격 쿼리를 입력할지라도 의미있는 쿼리로 동작하지 않아 **SQL Injection을 예방**할 수 있다.

### 단점

- 실행된 쿼리를 확인하기 어렵고, 쿼리에 오류가 생긴 경우 분석하기 어렵다.
- 바인드 변수 `?` 는 일부 허용된 위치에서만 사용할 수 있기 때문에 동적 쿼리 작성이 힘들다.
  - 변수를 활용해 동적으로 테이블을 변경하는 쿼리를 작성해야 하는 경우 PreparedStatement로 처리할 수 없다.

---

https://iksflow.tistory.com/127

[https://dheldh77.tistory.com/entry/데이터베이스-Statement와-Prepared-Statement](https://dheldh77.tistory.com/entry/%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4-Statement%EC%99%80-Prepared-Statement)

https://webstone.tistory.com/50
