# SQL Injection

SQL Injection이란 악의적인 사용자가 보안상의 취약점을 이용하여, 임의의 SQL문을 주입하고 실행되게 하여 데이터베이스가 비정상적인 동작을 하도록 조작하는 행위입니다. Injection공격은 OWASP Top10 에 속해 있으며, 공격이 비교적 쉬운 편이고 공격에 성공할 경우 큰 피해를 입힐 수 있는 공격입니다. 



# 공격 종류 및 방법

## 1. Error based SQL Injection

논리적 에러를 이용한 SQL Injection 입니다. SQL 공격 기법 중 논리적 에러를 이용한 SQL Injection은 가장 많이 쓰이고, 대중적인 공격 기법입니다.

![sql_injection1](https://github.com/Ahrang777/Ahrang777.github.io/assets/59478159/4dbd3fa7-39ad-4dd1-baa9-9f89132f3dd2)



위 사진에서 보이는 쿼리문은 일반적으로 로그인에서 많이 사용되는 쿼리문입니다. 해당 구문에서 입력값에 대한 검증이 없음을 확인하고, 악의적인 사용자가 임의의 SQL 구문을 주입하였습니다. 주입된 내용을 살펴보면 id값과 상관없이 true가 되도록 OR 1=1 과 같은 참이 되는 조건은 삽입했습니다. 또한 \-- (Oracle 등에서 한줄 주석에 사용된다. \--가 아니더라도 주석처리 기능을 적용하는 형태로 이용)를 통해 WHERE절의 AND password 이하 부분을 주석 처리 해주었습니다. 매우 간단한 구문이지만, 결론적으로 Users 테이블에 있는 모든 정보를 조회하게 됩니다.



## 2. Union based SQL Injection

Union 명령어를 이용한 SQL Injection

SQL 에서 Union 키워드는 두 개의 쿼리문에 대한 결과를 통합해서 하나의 테이블로 보여주게 하는 키워드 입니다. 정상적인 쿼리문에 Union 키워드를 사용하여 인젝션에 성공하면, 원하는 쿼리문을 실행할 수 있게 됩니다. Union Injection을 성공하기 위해서는 두 가지의 조건이 있습니다. 하나는 Union 하는 두 테이블의 컬럼 수가 같아야 하고, 데이터 형이 같아야 합니다. 

![sql_injection2](https://github.com/Ahrang777/Ahrang777.github.io/assets/59478159/da74ae4c-8407-49aa-a11b-4a6b93e4be19)

위의 사진에서 보이는 쿼리문은 Board 라는 테이블에서 게시글을 검색하는 쿼리문입니다. 입력값을 title 과 contents 컬럼의 데이터랑 비교한 뒤 비슷한 글자가 있는 게시글을 출력합니다. 여기서 입력값으로 Union 키워드와 함께 컬럼 수를 맞춰서 SELECT 구문을 넣어주게 되면 두 쿼리문이 합쳐서서 하나의 테이블로 보여지게 됩니다. 현재 인젝션 한 구문은 사용자의 id와 passwd를 요청하는 쿼리문 입니다. 인젝션이 성공하게 되면, 사용자의 개인정보가 게시글과 함께 화면에 보여지게 됩니다. 

 물론 패스워드를 평문으로 데이터베이스에 저장하지는 않겠지만 인젝션이 가능하다는 점에서 이미 그 이상의 보안위험에 노출되어 있습니다. 이 공격도 역시 입력값에 대한 검증이 없기 때문에 발생하게 되었습니다.



## Blind SQL Injection

### Boolean based SQL

Blind SQL Injection은 데이터베이스로부터 특정한 값이나 데이터를 전달받지 않고, 단순히 참과 거짓의 정보만 알 수 있을 때 사용합니다. 로그인 폼에 SQL Injection이 가능하다고 가정 했을 때, 서버가 응답하는 로그인 성공과 로그인 실패 메시지를 이용하여, DB의 테이블 정보 등을 추출해 낼 수 있습니다.

![sql_injection3](https://github.com/Ahrang777/Ahrang777.github.io/assets/59478159/f440ce38-886a-4930-a0b5-998bf44935de)

위의 그림은 Blind Injection을 이용하여 데이터베이스의 테이블 명을 알아내는 방법입니다. (MySQL) 인젝션이 가능한 로그인 폼을 통하여 악의적인 사용자는 임의로 가입한 abc123 이라는 아이디와 함께 abc123’ and ASCII(SUBSTR(SELECT name From information_schema.tables WHERE table_type=’base table’ limit 0,1)1,1)) > 100 -- 이라는 구문을 주입합니다.

 해당구문은 MySQL 에서 테이블 명을 조회하는 구문으로 limit 키워드를 통해 하나의 테이블만 조회하고, SUBSTR 함수로 첫 글자만, 그리고 마지막으로 ASCII 를 통해서 ascii 값으로 변환해줍니다. 만약에 조회되는 테이블 명이 Users 라면 ‘U’ 자가 ascii 값으로 조회가 될 것이고, 뒤의 100 이라는 숫자 값과 비교를 하게 됩니다. 거짓이면 로그인 실패가 될 것이고, 참이 될 때까지 뒤의 100이라는 숫자를 변경해 가면서 비교를 하면 됩니다. 공격자는 이 프로세스를 자동화 스크립트를 통하여 단기간 내에 테이블 명을 알아 낼 수 있습니다.



### Time based SQL











# 대응방안

## 1. 입력 값에 대한 검증



## 2. Prepared Statement 구문사용



## 3. Error Message 노출 금지



### 4. 웹 방화벽 사용







참고

[참고1](https://noirstar.tistory.com/264)
