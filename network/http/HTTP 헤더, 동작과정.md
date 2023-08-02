## HTTP

### HTTP

- HyperText Transfer Protocol
- 웹 상에서 클라이언트와 서버 간에 요청/응답으로 정보를 주고 받을 수 있는 프로토콜
- 주로 HTML 문서를 주고 받는데 쓰인다.

### HTTP의 특징

- Connectionless(비연결성)
    - 클라이언트가 요청을 서버에 보내고 서버가 적절한 응답을 클라이언트에 보내면 바로 연결이 끊긴다.
    - 트래픽이 많지 않고 최소한의 자원으로 서버 유지 가능하다.
    - TCP/IP 연결을 새로 맺어야 하므로 3 way handshake 시간이 추가된다.
    - HTTP 1.1에서 지속 연결이 가능해졌다.
- Statesless(무상태성)
    - 연결을 끊는 순간 클라이언트와 서버의 통신은 끝나며 상태 정보를 유지하지 않는다.
    - 브라우저 쿠키, 서버 세션, 토큰 등을 이용해 상태를 유지할 수 있다.

## HTTP 헤더

### 일반 헤더(General Header)

- 메세지에 대한 기본적인 정보를 제공한다.
- Date
    - HTTP 메세지를 생성한 일시이다.
    - Date: (day-name), (day) (month) (year) (hour):(minute):(econd) GMT
- Connection
    - 클라이언트와 서버 간 연결에 대한 옵션 설정
    - Connection: close → 메세지 교환 후 TCP 연결 종료
    - Connection: Keep-Alive → 메세지 교환 후 TCP 연결 유지
- (HTTP/1.0) Cache-Control

### 요청 헤더(Request Header)

- 요청이 최초 발생한 곳에서 누가 혹은 무엇이 그 요청을 보냈는 지에 대한 정보나 클라이언트의 정보를 제공한다.
- Host
    - 요청하는 자의 호스트명, 포트 번호를 포함하고 있다.
    - Host: [developer.mozilla.org](http://developer.mozilla.org/)
- User-Agent
    - 요청자의 소프트웨어 정보를 표현한다.
- Accept
    - 요청자가 원하는 미디어의 타입 및 우선순위를 표현한다.
- cookie
    - 서버에 의해서 이전에 저장된 쿠키를 포함시키는 속성이다.
- Referer
    - Referer 요청 헤더는 현재 요청을 보낸 페이지의 절대 혹은 부분 주소를 포함한다.

### 응답 헤더(Respones Header)

- 클라이언트가 응답을 원활하게 할 수 있게 하고 나중에 더 나은 요청을 할 수 있도록 도와주는 헤더이다.
- Server
    - 서버 소프트웨어의 정보를 표현한다.
- content-encoding
    - 응답하는 내용의 인코딩 포맷을 표현한다.
- content-type
    - 응답하는 내용의 타입과 문자 포맷을 표현한다.
- cache-control
    - 캐시 관리에 대한 정보를 표현한다.
- date
    - 응답 메세지가 생성된 시간을 표현한다.
- vary
    - 캐시된 응답을 향후의 응답에 사용할 기준을 표현한다.
- Set-Cookie
    - 서버에서 사용자에게 세션 쿠키 정보를 전달한다.
- Age
    - max-age내에서 캐시가 얼마나 지났는지 초 단위로 표현한다.

### 엔터티 헤더(Entity Header)

- 본문의 대한 헤더이다.
- Allow, Location 등이 있다.

## HTTP 동작 과정

### HTTP 동작 과정

- 서버 접속 -> 클라이언트 -> 요청 -> 서버 -> 응답 -> 클라이언트 -> 연결 종료
- 사용자가 웹 브라우저에 URL 주소 입력
- DNS 서버에  도메인 이름 검색
    - IP는 사람이 알아볼 수 있는 문자로 변환되어 DNS 서버에서 등록되어 있다.
    - [www.ssafy.com](http://www.ssafy.com/) 에 접속하기 위해서는 IP 주소를 입력하여 매번 접속을 시도해야 되지만 해당 주소에 대해서 DNS 서버에 도메인 이름으로 [www.ssafy.com](http://www.ssafy.com/) 을 등록해두었기 때문에 IP를 알지 못하여도 접속이 가능하다
    - DNS 서버에서 사용자가 적은 URL 주소에 도메인 네임 부분에 대한 IP 주소를 찾아낸다
- 클라이언트-서버 TCP 연결 시도
    - 3-way handshake : 클라이언트 - 서버간에 신뢰성 있는 연결을 위해 3번의 패킷 교환 과정
        - Client -> Server : 접속 요청하는 SYN 패킷 전송
        - Server -> Client : 접속 요청을 수락했고, 접속 요청 하는 쪽에도 포트를 열어달라는 SYN, ACK 패킷 전송
        - Client -> Server : 연락 정상적으로 이루어졌다는 ACK 패킷 전송
- 클라이언트가 서버에게 요청
    
    ![image](https://github.com/kknyapple/CS-Study/assets/72698829/3cc32ca7-7902-4057-a348-5c2a39a4c5da)

    
    - Header, Body 구조로 이루어진 HTTP 메시지를 요청한다
    - HTTP Request Message = Request Header + 빈 줄 + Request Body
    - Request Header
        - Host : 요청이 전송되는 target 호스트 URL 주소
        - User-Agent : 요청을 보내는 웹 브라우저의 정보
        - Aceept : 응답 Body 데이터 타입 정보
        - Connetion : 해당 요청이 끝난 후에 계속 연결을 유지 할 것인지 끊을 것인지 정보
            - keep-alive : 한번 연결 후 일정 시간안에 계속 요청이 있을 시 끊지 않고 유지함
            - close : 한번 연결 후 바로 끊음
        - Content-Type : HTTP 요청 보내는 메시지 Body 타입
        - Content-Length : 요청 보내는 메시지 Body의 총 사이즈
    - 빈 줄
        - 요청에 대한 모든 메타 정보가 전송되었음을 알리는 용도
    - Request Body
        - HTTP 요청 또는 응답에 전송할 데이터를 담는 부분
- 서버가 클라이언트에게 데이터 응답
    
    ![image](https://github.com/kknyapple/CS-Study/assets/72698829/b03bf744-f906-434f-8ffd-0ea4c729bc92)
    
    - 서버에서 HTTP 메시지를 받게 되면 어떤 메소드 요청인지, 어떤 리소스를 찾는지 확인한 후 찾아서 해당 리소스와 응답코드를 함께 클라이언트에게 전달하게 된다.
    - HTTP Version : HTTP 버젼
    - Status code : 응답 상태 코드
    - Statue Text : 응답 상태 부가 설명
- 서버 클라이언트 간 연결 종료
    - 4-way handshake : TCP 연결을 해제하기 위해 수행되는 절차
        - Client -> Server : 연결을 종료하겠다는 FIN 패킷 전송
        - Server -> Client : 연결 종료를 확인했다는 ACK 전송, 자신의 통신이 끝날때까지 기다림
        - Server -> Client : 통신이 끝났을 경우 FIN 패킷 전송
        - Client -> Server : 통신 해지를 확인했다는 ACK 패킷 전송
- 웹 브라우저가 웹 문서 출력
    - 웹 브라우저 화면에 응답 받은 메시지의 Body 부분에 있는 데이터를 나타낸다.

## 참고

- [https://velog.io/@wlwl99/HTTP의-특징](https://velog.io/@wlwl99/HTTP%EC%9D%98-%ED%8A%B9%EC%A7%95)
- [https://velog.io/@jkijki12/HTTP-Header-정리](https://velog.io/@jkijki12/HTTP-Header-%EC%A0%95%EB%A6%AC)
- https://velog.io/@ahngj96/HTTP-0.9-1.0-1.1-2.0
- https://mangchhe.github.io/web/2021/02/19/HttpActionProcess/
- HTTP 완벽 가이드 3.5 헤더
