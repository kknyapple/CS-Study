# JDBC

## 개념

![Untitled (7)](https://github.com/RIN-1011/RIN-1011/assets/60701386/86ec3536-986d-4675-b8c6-a4904d338b01)

- DB에 접근할 수 있도록 Java에서 제공하는 API이다.
- JDBC는 관계형 데이터베이스에 사용되는 SQL문을 실행하기 위해 자바로 작성된 클래스와 인터페이스로 구성되어 있다.
- 특정 데이터베이스나 특정 데이터베이스 매커니즘에 구애받지 않는 독립적인 인터페이스를 통해 다양한 데이터베이스에 접근하는 코드를 구현할 수 있도록 제공하는 자바 클래스의 표준 집합이다.
- JDBC 클래스는 자바 패키지 `java.sql`나 `javax.sql`에 포함되어 있다.

## 필요성

- DB마다 사용법이 달라, DB 변경 시 애플리케이션 코드를 변경해야 했음
    - 애플리케이션은 JDBC 인터페이스에 의존하여 공통된 메서드를 이용해 접근할 수 있음 → 다른 종류의 DB로 변경해도 애플리케이션 코드 변경할 필요 없음
- DB마다 사용법이 달라, DB 변경 시 각 DB 사용을 위한 사용법을 새로 학습해야 했음
    - 개발자는 JDBC 인터페이스 사용법만 공부하고 나머지는 DB 구현체가 직접 해줌

## 동작 흐름

![Untitled (8)](https://github.com/RIN-1011/RIN-1011/assets/60701386/32c43826-a2b1-45ea-b47e-89b503d26162)

- JDBC는 Java 애플리케이션 내에서 JDBC API를 사용하여 데이터베이스에 접근하는 구조이다.
- JDBC API를 사용하기 위해서는 JDBC 드라이버를 먼저 로딩한 후 데이터베이스와 연결하게 된다.

### JDBC 드라이버

- 데이터베이스와의 통신을 담당하는 인터페이스
- Oracle, MS SQL, MySQL 등과 같은 데이터베이스에 알맞은 JDBC 드라이버를 구현하여 제공
- JDBC 드라이버의 구현체를 이용해서 특정 벤더의 데이터베이스에 접근할 수 있음

## JDBC API 사용흐름

![Untitled (9)](https://github.com/RIN-1011/RIN-1011/assets/60701386/180b93f5-3278-4989-ae1b-1b0aa08b22e1)
<br>
![Untitled (10)](https://github.com/RIN-1011/RIN-1011/assets/60701386/781152df-b289-4562-87c4-8e99cf304bfd)

1. JDBC 드라이버 로딩 : 사용하고자 하는 JDBC 드라이버를 로딩한다. JDBC 드라이버는 DriverManager 클래스를 통해 로딩된다.
2. Connection 객체 생성 : JDBC 드라이버가 정상적으로 로딩되면 DriverManager를 통해 데이터베이스와 연결되는 세션(Session)인 Connection 객체를 생성한다.
3. Statement 객체 생성 : Statement 객체는 작성된 SQL 쿼리문을 실행하기 위한 객체로 정적 SQL 쿼리 문자열을 입력으로 가진다.
4. Query 실행 : 생성된 Statement 객체를 이용하여 입력한 SQL 쿼리를 실행한다.
5. ResultSet 객체로부터 데이터 조회 : 실행된 SQL 쿼리문에 대한 결과 데이터 셋이다.
6. ResultSet, Statement, Connection 객체들의 Close : JDBC API를 통해 사용된 객체들은 생성된 객체들을 사용한 순서의 역순으로 Close 한다.

### JDBC 사용 객체
|객체|설명|
|-----|-----|
| DriverManager | JDBC 드라이버를 통해서 커넥션을 만드는 역할<br>Class.forName() 메소드를 통해 생성, 예외처리 필수<br>getConnection()메소드를 호출하여 Connection객체를 반환받음 |
| Connection | 특정 데이터 원본과 연결된 객체<br>DB의 연결정보를 담고 있는 객체 (ip주소, port번호, 계정명, 비밀번호) |
| Statement | 해당 DB에 SQL문을 전달하고 실행한 후 결과를 받아내는 객체<br>→ 특징: 완성된 SQL문을 실행할 수 있는 객체임<br>Connection객체에 의해 프로그램에 구현되는 일종의 메소드 집합<br>Connection클래스의 createStatement()를 호출하여 객체 생성<br>Statement객체로 SQL문을 String객체로 담아 인자값으로 전달하여 질의 수행 |
| Result Set | SELECT문을 사용한 질의 성공시 반환되는 객체<br>(만일 실행한 SQL문이 SELECT문일 경우 조회된 결과가 result set객체에 들어감)<br>SQL 질의에 해당하는 결과를 담고 있으며 '커서(CURSOR)'를 이용하여  특정 행에 대한 참조를 조작 |

## JDBC 사용 코드 예시

### 0.환경구성

- JDK 설치
- JDBC 드라이버 설치Maven에 다음과 같은 의존성을 추가한다. MySQL사이트에서 다운로드한다. 즉, Maven에서 이용할 때는 이런 의존성을 추가해주면 된다.

```java
<dependency>
  <groupld>mysql</groupld>
  <artifactld>mysql-connector-java</artifactls>
  <version>5.1.45<version>
<dependency>
```

### 1. IMPORT

```java
import java.sql.*;
```

- 자바코드에서 나랑 다른 패키지에 있는 클래스를 사용하기 위해서 반드시 import해줘야 한다.

### 2. 드라이버 로드

```java
Class.forName("com.mysql.jdbc.Driver");
```

- 이는 드라이버를 로드하는 코드인데 각각 DB벤더에서 제공하는 객체이다. `jdbc.Driver`는 `패키지명.클래스명`이다. 해당 객체를 Class라는 클래스가 갖고 있는 forName이라는 메소드를 이용하면 해당 객체가 메모리에 올라간다.

### 3. Connection 얻기

```java
String dburl = "jdbc:mysql://localhost/dbName";
Connection con = DriverManager.getConnection(dburl,ID,PWD);
```

- connection 객체를 얻어낼때 사용하는 객체가 `DriverManager`라는 객체이다. 그래서 `DriverManager`가 갖고 있는 `getConnection`을 이용하면 어떤 URL에 접속할거냐(DB가 있는 IP주소), 즉 DB가 있는 URL이다. 그리고 DB의 user, password를 입력해주면 연결할 수 있다.이때 dburl을 정하는 방식도 DB에 따라 다른데, 각각 DB벤더에서 알려준다.
- connection 객체 생성 = DB에 접속하겠다. connection객체는 db가 접속됐을 때 얻어내줄 수 있는 객체이다. connection 객체라곤 하지만 connection은 인터페이스이고, 이 객체는 각각의 벤더가 구현하고 있는 객체여야 한다. 그러려면 벤터가 제공해주는 라이브버리를 사용할 수 있어야 하는데 이것을 사용할 수 있게 해주는 것이 로딩하는 것이다. 즉 드라이버를 로드하는 것이다. 그래서 로드먼저 해야 한다.

**예제**

```
public static Connection getConnection() throws Exception{
	//오라클 사용시 정의되는 URL 형식
	String url = "jdbc:oracle:thin:@117.16.46.111:1521:xe";
	String user = "smu"; //DB의 user
	String password = "smu";//DB의 password
	Connection conn = null;
    //오라클 벤더가 제공하고 있는 클래스 이름
	Class.forName("oracle.jdbc.driver.OracleDriver");
	conn = DriverManager.getConnection(url, user, password);
	return conn;
}
```
### 4. Statement 생성 및 질의수행

```java
//Statement 생성
Statement stmt = con.createStatement();

//질의 수행
ResultSet rs = stmt.executeQuery("select no from user");
```

- 명령생성으로 실제 쿼리(select,insert..등)를 사용하면 된다. 이런 쿼리를 사용하기 위해서는 반드시 Statement 객체를 얻어야 한다.

```java
//any SQL
stmt.execute("query");

//Select
stmt.executeQuery("query");

//Insert,Update,Delete
stmt.executeUpdate("query");
```

- Statement 객체에 쿼리문을 작성하고 실행해달라는 메서드(execute)를 사용한다. 해당 메서드는 실행할 쿼리가 Select냐 Insert냐 Update냐 Delete냐 에 따라서 메서드가 달라진다.
- 그리고 statement객체를 이용해서 ResultSet을 얻어낼 수 있다.
- statement객체가 하는 일은 select..하고 만드는 것이다. 즉, 쿼리문을 생성하고 수행하도록 도와주는 것이다. 이 statement도 인터페이스이고, 실제로 db벤더가 무엇이냐에 따라서 이 statement객체를 구현한 객체가 리턴이 될 것이다.

### 5. ResultSet으로 결과 받기

```java
ResultSet rs = stmt.executeQuery("select no from user");
while(rs.next()) //레코드상에서 다음것이 있는 동안에
  System.out.println(rs.getInt("no"));
  //가리키고 있는 거에서 칼럼명이 no인애 값을 하나 꺼내와라
```

- 현재 ResultSet은 DB쪽에 있고, 이 DB ResultSet에 레퍼런스(rs)를 얻어온 것이고, 이를 가지고 실행해야 한다.
- 클라이언트가 우리 프로그램이라고 했을 때, 실행해달라 했을때 결과를 준다 했는데 이 결과셋은 DB가 가지고 있는 것이고 이 결과값을 가리킬 수 있는 레퍼런스 변수만 온다.
- 즉, ResultSet에서 시작해서 하나씩 꺼내온다.
- 그런 다음에 데이터베이스가 준 결과값을 가지고 결과를 꺼내올 수 있는데, 만약 SQL문에 결과물이 있으면 ResultSet객체를 생성한다. 그래서 쿼리문이 뭐냐에 따라 조금 다르게 실행된다.

### 6. Close

```
rs.close();
stmt.close();
con.close();
```

- 그리고 모든 객체를 닫아야 한다. 단, 닫는 순서가 반드시 거꾸로여야 한다.
- 가장 마지막에 열린 ResultSet(rs)를 가장 먼저 닫아줘야 한다.

## 참고

[https://velog.io/@jungnoeun/JDBC란](https://velog.io/@jungnoeun/JDBC%EB%9E%80)

https://ittrue.tistory.com/250

https://mundol-colynn.tistory.com/46

[https://velog.io/@ragnarok_code/DataBase-JDBC란](https://velog.io/@ragnarok_code/DataBase-JDBC%EB%9E%80)

[https://velog.io/@jungnoeun/JDBC란](https://velog.io/@jungnoeun/JDBC%EB%9E%80)

https://ojt90902.tistory.com/878
