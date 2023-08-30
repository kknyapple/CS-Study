# DBCP (DataBase Connetion Pool)

## Connection Pool

> 클라이언트의 요청에 따라 각 애플리케이션의 스레드에서 데이터베이스에 접근하기 위해서는 Connection이 필요하다.

<img src="https://github.com/Ahrang777/CS-Study/assets/72875528/4610a1f2-0105-476d-bc2a-d967e7459e43" width="90%" height="50%"/>

- DB Connection Pool Manager가 일정의 Connection을 여러 개 생성해 두어 저장해 높은 캐시(공간)
- 이 공간의 Connection을 필요할 때 꺼내 쓰고 반환하는 기법

## 데이터베이스 접근 과정

<img src="https://github.com/Ahrang777/CS-Study/assets/72875528/884e751a-a224-469b-9f85-31506a0b9775" width="90%" height="50%"/>

1. 웹 컨테이너가 실행되면 데이터베이스와 연결된 Connection 객체들을 미리 생성해 Pool에 저장한다.
2. 클라이언트 요청 시 Pool에서 Connection 객체를 가져와 데이터베이스에 접근한다.
3. 요청 처리가 끝나면 사용된 Connection 객체를 다시 Pool에 반환한다.
4. 남은 Connection이 없다면 해당 클라이언트는 대기 상태로 전환시키고, Pool에 Connection이 반환되면 대기 상태에 있는 클라이언트에게 순차적으로 제공한다.

## 장점

- 매 연결마다 Connection 객체를 생성하고 소멸시키는 비용을 줄일 수 있다.
- 미리 생성된 Connection 객체를 사용하기 때문에 DB 접근 시간이 단축된다.
- DB에 접근하는 Connection의 수를 제한하여, 메모리와 DB에 걸리는 부하를 조정할 수 있다.

## 커넥션 풀과 스레드 풀

### Thread Pool
> 매 요청마다 요청을 처리할 Thread를 만드는것이 아닌, 미리 생성한 pool 내의 Thread를 소멸시키지 않고 재사용하여 효율적으로 자원을 활용하는 기법

- WAS(Web Application Server)에서 Thread pool과 Connection pool내의 Thread와 Connection의 수는 직접적으로 메모리와 관련이 있기 때문에 많이 사용하면 할 수록 메모리를 많이 점유하게 된다. 그렇다고 반대로 메모리를 위해 적게 지정한다면 서버에서는 많은 요청을 처리하지 못하고 대기 할 수 밖에 없다.
- 보통 WAS의 Thread의 수가 Conncetion의 수보다 많은 것이 좋은데, 그 이유는 모든 요청이 DB에 접근하는 작업이 아니기 때문이다.

## 참고

https://tech-interview.tistory.com/218  
https://d2.naver.com/helloworld/5102792
