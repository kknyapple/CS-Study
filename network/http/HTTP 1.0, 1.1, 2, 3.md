## HTTP

### HTTP

![image](https://github.com/kknyapple/CS-Study/assets/72698829/03918570-aa5d-4403-a312-63ccca8839e6)

- 하이퍼 텍스트 전송 프로토콜로 인터넷에서 데이터를 주고 받을 수 있는 프로토콜이다.
- 웹 서비스 통신에 사용된다.
- stateless → 과거 요청/응답에 대해 알지 못한다. (쿠키, 세션, localStorage로 보완)

## HTTP/1.0

### HTTP/1.0

- 커넥션당 하나의 요청과 응답만 처리한다.
- 패킷 왕복 시간인 RTT가 증가한다.

## HTTP/1.1

### HTTP/1.1

![image](https://github.com/kknyapple/CS-Study/assets/72698829/538a6962-ba1d-4225-ba9d-eb288629eaff)

- 매번 TCP를 연결하지 않고 한 번 TCP를 초기화하고 여러 개의 파일을 송수신 할 수 있게 한다. 한 번에 모든 요청을 순차적으로 전송하고, 순차적으로 응답을 받는다. 다음 요청을 보내기 위해 이전 요청의 응답을 기다리지 않는다. 하지만 앞의 응답이 지연되면 나머지 응답도 지연된다.(pipelining)
- TCP 3 way handshake가 한 번 발생하면 그 다음부터 발생하지 않는다. (persistent connection)

### 문제점

- HOL(Head-of-Line) Blocking
- 무거운 헤더 구조

## HTTP/2

### HTTP/2

- HTTP/1 보다 지연 시간을 줄이고 응답 시간을 더 빠르게 할 수 있다.
- 멀티플렉싱, 중복 헤더 제거, 헤더 압축, 서버 푸시, 요청의 우선순위 처리를 지원한다.

### 멀티플렉싱

- 하나의 연결 안에서 여러 응답과 요청을 동시에 처리한다.
- 스트림을 생성하여 요청과 응답 메세지를 전송한다.
- 스트림은 서버와 클라이언트 사이의 양방향 통신 채널을 의미한다.
- 서버와 클라이언트는 각각 다수의 스트림을 만드어 병렬적으로 요청과 응답을 전송한다.

### 서버 푸시

- 하나의 요청에 대해 추가적인 리소스를 전송이 가능하다.

## HTTPS

### HTTPS

- HTTP/2는 HTTPS 위에서 작동한다.
- 애플리케이션 계층과 전송 계층 사이의 SSL/TLS 계층을 넣은 신뢰할 수 있는 HTTP 요청이다.
- 이를 통해 통신 암호화를 한다.

### SSL/TLS

- 전송 계층에서 보안을 제공하는 프로토콜이다.
- 클라이언트와 서버가 통신할 때 메세지를 가로채지 못하게 한다.’

## HTTP/3

### HTTP/3

- QUIC 계층 위에서 동작하며 UDP 기반으로 돌아간다.
- TCP를 사용하지 않기 때문에 TCP 3 way handshake 과정을 거치지 않아도 된다.

https://dkrnfls.tistory.com/289

https://lietenant-k.tistory.com/m/120

[https://velog.io/@cjh8746/HTTP-Keep-Alive-와-pipelining-그리고-Multiplexed-Streams을-공부하다가-알게된-버전열-HTTP0.9-2.0-정리](https://velog.io/@cjh8746/HTTP-Keep-Alive-%EC%99%80-pipelining-%EA%B7%B8%EB%A6%AC%EA%B3%A0-Multiplexed-Streams%EC%9D%84-%EA%B3%B5%EB%B6%80%ED%95%98%EB%8B%A4%EA%B0%80-%EC%95%8C%EA%B2%8C%EB%90%9C-%EB%B2%84%EC%A0%84%EC%97%B4-HTTP0.9-2.0-%EC%A0%95%EB%A6%AC)
