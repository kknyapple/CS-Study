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

WAS(Web Application Server)에서 커넥션 풀과 스레드 풀의 커넥션과 스레드 수는 메모리와 직접적으로 관련이 있다.  
커넥션과 스레드 수를 많이 설정하면 메모리를 많이 차지하고, 적게 설명하면 처리하기 못하는 대기 요청이 많아지게 된다.

## 참고

https://tech-interview.tistory.com/218  
https://d2.naver.com/helloworld/5102792
