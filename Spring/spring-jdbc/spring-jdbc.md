# Spring JDBC를 이용한 데이터 접근

## JDBC(Java Database Connectivity)

> DB에 접근할 수 있도록 Java에서 제공하는 API

- JDBC는 데이터베이스에서 자료를 쿼리하거나 업데이트하는 방법을 제공한다.

### Plain JDBC API의 문제점

- 쿼리를 실행하기 전과 후에 많은 코드를 작성해야한다.
  - EX) 연결 생성, 명령문, ResultSet 닫기, 연결 등
- 데이터베이스 로직에서 예외 처리 코드를 수행해야 한다.
- 트랜잭션을 처리해야 한다.
- 이러한 모든 코드를 반복하는 것으로, 시간이 낭비된다.

## JDBC Tepmplate

- Spring JDBC 접근 방법 중 하나이다.
- 내부적으로 Plain JDBC API를 사용하지만 위와 같은 문제점들을 제거한 형태의 Spring에서 제공하는 class이다.

### Spring JDBC가 하는 일

1. Connection 열기와 닫기
2. Statement 준비와 닫기
3. Statement 실행
4. ResultSet Loop 처리
5. Exeption 처리와 반환
6. Transaction 처리

### Spring JDBC에서 개발자가 할 일

1. datasource 설정
- gradle
   
```
jdbc.driver = com.mysql.jdbc.Driver
jdbc.url = jdbc:mysql//localhost:3306/databaseName
jdbc.username = root
jdbc.password = password
https://gmlwjd9405.github.io/2018/05/15/setting-for-db-programming.html
```

- maven

```
<context:property-placeholder location="com/spring/props/jdbc.properties"/>

<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
      <property name="driverClassName" value="${jdbc.driverClassName}" />
      <property name="url" value="${jdbc.url}" />
      <property name="username" value="${jdbc.username}" />
      <property name="password" value="${jdbc.password}" />
</bean>
```
2. sql문 작성
3. 결과 처리

### Spring JDBC 접근 방법

1. JdbcTemplate
2. NamedParameterJdbcTemplate
3. SimpleJdbcTemplate
4. SimpleJdbcInsert 및 SimpleJdbcCall

### JdbcTemplate이 제공하는 기능

- 실행 : insert나 update 같이 DB의 데이터에 변경이 일어나는 쿼리를 수행하는 작업
- 조회 : select를 이용해 데이터를 조회하는 작업
- 배치 : 여러 개의 쿼리를 한 번에 수행해냐 하는 작업

## 참고

https://gmlwjd9405.github.io/2018/05/15/setting-for-db-programming.html

https://gmlwjd9405.github.io/2018/12/19/jdbctemplate-usage.html
