# 트랜잭션 격리 수준

## 개념

- 동시에 여러 트랜잭션이 처리될 때, 트랜잭션끼리 얼마나 서로 고립되어 있는지를 나타내는 수준
- 즉, 특정 트랜잭션이 다른 트랜잭션에서 변경하거나 조회하는 데이터를 볼 수 있도록 허용할지 말지를 결정하는 것

## 종류

![Untitled](https://github.com/Ahrang777/CS-Study/assets/60701386/c03bd555-c9e2-4bae-8b4e-824263c8e1cb)

- 아래로 내려갈 수록 고립 정도가 높아지며, 성능이 떨어지는 것이 일반적임

<br>

### READ UNCOMMITTED (커밋되지 않은 읽기)

- 어떤 트랜잭션 변경 내용이 COMMIT이나 ROLLBACK과 상관없이 다른 트랜잭션에서 보여짐
- 정합성에 문제가 많은 격리 수준이기 때문에 사용하지 않는 것을 권장함
- **DIRTY READ 발생**

<br>

**[DIRTY READ 발생]**

트랜잭션이 작업이 완료되지 않았는데도 다른 트랜잭션에서 볼 수 있게 되는 현상

![Untitled (1)](https://github.com/Ahrang777/CS-Study/assets/60701386/05201f22-6300-4b0b-a293-d8942e4f3a82)

사용자 A는 `emp_no = 50000` , `first_name = 'JuBal'` 인 새로운 사원을 삽입하고 있다. 

그리고 사용자 B는 사용자 A가 변경한 내용을 커밋하기도 전에 `emp_no = 50000` 인 사원을 검색하고 있다. 

이때 사용자 B의 SELECT 쿼리 결과에서는 사용자 A가 삽입한 커밋되지 않은 새로운 사원이 조회된다. 

여기서 문제는 만약 사용자 A가 작업 도중 문제가 발생하여 삽입한 내용을 롤백해도 사용자 B는 JuBal이 정상적인 사원이라 판단하고 계속해서 처리하게 된다.

<br>

### **READ COMMITTED (커밋된 읽기)**

- COMMIT 이 된 데이터만 읽음
- RDB에서 대부분 기본적으로 사용되고 있는 격리 수준
- Dirty Read와 같은 현상은 발생하지 않지만 **NON-REPEATABLE READ 발생**

<br>

**[DIRTY READ 해결]**

![Untitled (2)](https://github.com/Ahrang777/CS-Study/assets/60701386/78586246-c9c1-46ba-8edc-6c1464d2de40)

사용자 A는 `emp_no = 50000` 인 사원의 `first_name` 을 JuBal에서 Toto로 수정하였는데, 이때 새로운 값인 Toto는 `employees` 테이블에 즉시 기록되고 이전 값인 JuBal은 `Undo` 영역으로 백업이 된다.

따라서 사용자 A가 이러한 변경 내역을 커밋하기 전에 사용자 B가 `emp_no = 50000` 인 사원을 조회하면 결과 값은 Toto가 아닌, 이전 값인 JuBal이 조회된다. 여기서 사용자 B의 SELECT 쿼리 결과는 `employees` 테이블이 아닌 `Undo` 영역의 백업된 레코드에서 가져온 결과이다.

READ COMMITTED 격리 수준에서는 어떤 트랜잭션에서 변경한 내용이 커밋되기 전까지는 다른 트랜잭션에서 그러한 변경 내역을 조회할 수 없다. 최종적으로 사용자 A가 변경된 내용을 커밋하면 그때부터는 다른 트랜잭션에서도 백업된 `Undo` 영역의 데이터인 JuBal이 아닌, 새롭게 변경된 Toto 값을 참조할 수 있게 된다.

**# Undo 영역**

- 언두 영역은 UPDATE 문장이나 DELETE와 같은 질의문에 의해 데이터를 변경했을 때 변경되기 전의 데이터를 보관하는 곳이다.
- 크게 두 가지로 용도로 사용한다.
    - 트랜잭션의 롤백 대비용
    - 트랜잭션의 격리 수준을 유지하면서 높은 동시성을 제공
<br>

**[NON-REPEATABLE READ 발생]**

하나의 트랜잭션 내에서 동일한 SELECT 쿼리를 실행했을 때 항상 같은 결과를 보장해야 한다는 REPEATABLE READ 정합성에 어긋나는 것

![Untitled (3)](https://github.com/Ahrang777/CS-Study/assets/60701386/b2a8f9e2-d38b-486f-9f8c-616635e562c3)

사용자 B는 BEGIN 명령으로 트랜잭션을 시작하고 `first_name = 'Toto'` 인 사원을 조회하면 일치하는 데이터가 존재하지 않는다. 

하지만 이후에 사용자 A가 `emp_no = 50000` 인 사원의 이름을 Toto로 수정하고 커밋한 후 사용자 B가 똑같은 SELECT 쿼리로 조회하면 이번에는 1건의 결과가 조회된다. 

이는 별다른 문제는 없어 보이지만, 사용자 B가 하나의 트랜잭션 내에서 동일한 SELECT 쿼리를 실행했을 때 항상 같은 결과를 보장해야 한다는 REPEATABLE READ 정합성에 어긋나게 된다.

이러한 부정합 현상은 일반적인 웹 애플리케이션에서는 크게 문제가 되지 않지만, 하나의 트랜잭션에서 동일한 데이터를 여러 번 읽고 변경하는 작업이 금전적인 처리와 연결되면 문제가 될 수 있다. 예를 들어, 다른 트랜잭션에서 입금과 출금 처리를 계속 진행하고 있을 때 다른 트랜잭션에서 오늘 입금된 금액의 총합을 조회한다고 가정해 보자. 이때 READ COMMITTED 격리 수준을 사용한다면 REPEATABLE READ가 보장되지 않으므로 총합을 계산하는 SELECT 쿼리를 실행할 때마다 다른 결과를 가져오는 큰 문제가 발생할 수 있다.

<br>

### **REPEATABLE READ (반복 가능한 읽기)**

- 자신의 트랜잭션이 생성되기 이전의 트랜잭션에서 COMMIT 이 된 데이터만 읽습니다.
- MySQL과 MariaDB 가 기본으로 사용하는 격리 수준
- MySQL에서는 트랜잭션마다 트랜잭션 ID를 부여하여 트랜잭션 ID보다 작은 트랜잭션 번호에서 변경한 것만 읽게 된다.
- **PHANTOM READ 발생**

<br>

**[NON-REPEATABLE READ 해결]**

언두 영역에 백업된 모든 데이터에는 변경을 발생한 트랜잭션의 번호가 포함되어 있는데, REPEATABLE READ 격리 수준에서는 실행 중인 트랜잭션보다 작은 트랜잭션에서 변경한 데이터만 보게 하여 NON-REPEATABLE READ 문제를 해결한다.

![Untitled (4)](https://github.com/Ahrang777/CS-Study/assets/60701386/8f11ddc1-9dda-4c05-b0fc-eeac35759ef2)

위 그림은 사용자 A가 `emp_no = 50000` 인 사원의 이름을 변경하는 과정에서 사용자 B가 `emp_no = 50000` 인 사원을 SELECT할 때 REPEATABLE READ 격리 수준이 작동하는 방식을 보여준다. 먼저 `employees` 테이블은 번호가 6인 트랜잭션에 의해 JuBal 사원이 삽입되었다고 가정하자.

사용자 A의 트랜잭션 번호는 12이고, 사용자 B의 트랜잭션 번호는 10이다. 이때 사용자 A는 사원의 이름을 Toto로 변경하고 커밋을 수행한다. 이때 사용자 B는 `emp_no = 50000` 인 사원을 A 트랜잭션이 변경을 실행하기 전과 실행한 후 각각 조회를 하였지만, 데이터는 항상 동일한 JuBal라는 결과가 나온다.

그 이유는 사용자 B가 BEGIN 명령으로 트랜잭션을 시작하면서 10번이라는 트랜잭션 번호를 부여 받았는데, 사용자 B의 10번 트랜잭션 안에서 실행되는 모든 SELECT 쿼리는 자신의 트랜잭션인 10번보다 작은 트랜잭션 번호에서 변경한 데이터만 볼 수 있기 때문이다. 그래서 사용자 A의 12번 트랜잭션에서 변경한 데이터는 열람할 수 없다.

<br>

**[PHANTOM READ 발생]**

PHANTOM READ란, `SELECT ... FOR UPDATE` 쿼리와 같은 쓰기 잠금을 거는 경우 다른 트랜잭션에서 수행한 변경 작업에 의해 레코드가 보였다가 안 보였다가 하는 현상을 말한다.

![Untitled (5)](https://github.com/Ahrang777/CS-Study/assets/60701386/a77944ce-84ba-4841-98d6-dce3846e8a3a)

위 그림은 사용자 A가 `employees` 테이블에 INSERT를 실행하기 전과 후에 사용자 B가 `SELECT ... FOR UPDATE` 쿼리로 `employees` 테이블을 조회했을 때의 결과이다.

NON-REPEATABLE READ 문제 해결에서 설명한 것처럼 동일한 트랜잭션 내에서의 동일한 쿼리는 항상 같은 결과를 출력해야 한다. 

그러나 위 그림에서 두 번의 `SELECT ... FOR UPDATE` 는 다른 결과를 보여 주고 있다. 그 이유는 `SELECT ... FOR UPDATE` 쿼리의 경우 SELECT하는 레코드에 쓰기 잠금을 걸어야 하는데, 언두 영역에는 잠금을 걸 수 없기 때문이다. 

따라서 어쩔 수 없이 `SELECT ... FOR UPDATE` 나 `SELECT ... LOCK IN SHARE MODE` 로 조회되는 레코드는 언두 영역의 변경 전 데이터를 가져오는 것이 아니라 현재 레코드의 값을 가져온다.

<br>

### **SERIALIZABLE (직렬화 가능)**

- 가장 단순한 격리 수준이지만 가장 엄격한 격리 수준
- 데이터를 접근할 때, 항상 Lock을 걸고 데이터를 조회
- SERIALIZABLE에서는 PHANTOM READ가 발생하지 않음 (모든 부정합 문제 발생 X) → 동시 처리 거의 불가능
- 성능 문제로 데이터베이스에서 거의 사용되지 않음

![Untitled (6)](https://github.com/Ahrang777/CS-Study/assets/60701386/7b87b5f4-63e1-4239-8bae-5295ac8bfe53)

## 참고

[https://velog.io/@yujiniii/데이터베이스-트랜잭션-격리-수준](https://velog.io/@yujiniii/%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4-%ED%8A%B8%EB%9E%9C%EC%9E%AD%EC%85%98-%EA%B2%A9%EB%A6%AC-%EC%88%98%EC%A4%80)

https://steady-coding.tistory.com/562
