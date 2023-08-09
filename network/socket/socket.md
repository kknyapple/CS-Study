# 목차
1. [개념](#개념)
2. [특징](#특징)
3. [소켓 통신](#소켓-통신)
4. [소켓 종류](#소켓-종류)
5. [차이점](#socketio와-websocket의-차이)
<br/>
<br/>

# 소켓

## 개념

![Untitled](https://github.com/RIN-1011/RIN-1011/assets/60701386/a0ea0080-fd51-4d73-a1cd-6fd57977bbf4)

- TCP/IP 기반 네트워크 통신에서 데이터 송수신의 마지막 접점 (양방향 통신 끝점)
- OSI 7계층 중 응용 계층에 속하는 프로세스들은 데이터 송수신을 위해 반드시 소켓을 거쳐 전송 계층으로 데이터를 전달해야 함
- 전송 계층과 응용 프로그램 사이의 인터페이스 역할을 하며 떨어져 있는 두 호스트를 연결해줌

## 특징

1. **5-Tuple**
    
    통신을 통해 전달되는 모든 데이터 포맷은 5-tuple이라는 규격에 맞추어 흐르게 됨. 소켓 역시 이 정보를 가짐
    
    1) 프로토콜 (Protocol)
    
    2) 호스트 IP 주소 (source IP address)
    
    3) 호스트 port 번호 (source port nunber)
    
    4) 목적지 IP 주소 (destination IP address)
    
    5) 목적지 port 번호 (destination port number
    
    ---
    - IP 주소 : 호스트들을 식별할 수 있는 고유한 주소
    - port 번호 : 호스트 내의 프로세스들을 식별할 수 있는 번호
    
    -> 1개의 프로세스가 1개 이상의 소켓을 사용한다면 소켓에 주어지는 소켓 번호로 소켓 식별
    
2. **운영체제나 언어에 종속적임**
    1. 소켓을 구현하는 방법은 프로그램이 언어마다, 운영체제 마다 다름
3. **실시간 데이터 송수신에 적합**
    1. 서버와 클라이언트가 특정 포트를 통해 실시간으로 양방향 통신을 하기 때문

## 소켓 통신

### 개념

- 소켓을 통해 서버-클라이언트간 데이터를 주고 받는 양방향 연결 지향성 통신
- 지속적으로 연결을 유지하면서 실시간으로 데이터를 주고 받아야 하는 경우에 사용됨

### 소켓 통신의 흐름

![Untitled (1)](https://github.com/RIN-1011/RIN-1011/assets/60701386/8e41e1c6-99cd-458a-b4df-730a1db6d8e3)

- 소켓은 클라이언트 소켓과 서버 소켓으로 구분됨
- 서버 소켓
    - 구현 코드
        
        ```java
        1) 서버소켓 생성
            ServerSocket serverSocket = new ServerSocket(8000);  // 포트번호
        
        2) 클라이언트 접속 대기
        // 클라이언트로부터 연결 요청이 들어오면 연결을 맺고 클라이언트 소켓을 생성해 리턴함
            Socket socket = serverSocket.accept( );
        
        3) 데이터 송수신을 위한 input/output 스트림 생성 
            InputStream in = socket.getInputStream( );
            OutputStream out = socket.getOutputStream( );
        
        4) input 스트림을 통한 데이터 수신 (클라이언트 → 서버)
            byte[ ] inputData = new byte[100];
            int length = in.read(inputData);
            String inputMessage = new String(inputData, 0, length);
        
        5) output 스트림을 통한 데이터 송신 (서버 → 클라이언트) 
            String outputMessage = "보낼메시지";
            out.write(outputMessage.getBytes( ));
            out.flush( );
        
        6) 통신 종료
            socket.close( );
            serverSocket.close( );
        ```
        
    - 서버 프로그램에서만 사용하는 소켓으로 클라이언트로부터 연결 요청이 오기를 기다렸다가 연결 요청이 들어오면 클라이언트와 연결을 맺고 다른 소켓을 만드는 일을 함
    1. **`socket()`** : 소켓 생성
    2) **`bind()`** : 사용할 IP address와 Port number 등록
    3) **`listen()`** : 연결 되지 않은 소켓을 요청 수신 대기 모드로 전환(Block 상태)
    4) **`accept()`** : client의 요청 수락 후 실질적인 소켓 연결 → 통신을 위한 새로운 소켓 생성
    5) **`send()`**, **`recv()`**: Client는 처음에 생성한 소켓으로, Server는 새로 반환(생성)된 소켓으로 client와 server간에 데이터 송수신
    6)  **`close()`**: 소켓을 닫음
       
- 클라이언트 소켓
    - 구현 코드
        
        ```java
        1) 클라이언트 소켓 생성을 통한 서버접속
            Socket socket = new Socket("127.0.0.1", 8000);  // IP주소, 포트번호
        
        2) 데이터 송수신을 위한 input/output 스트림 생성 
            InputStream in = socket.getInputStream( );
            OutputStream out = socket.getOutputStream( );
        
        3) output 스트림을 통한 데이터 송신 (클라이언트 → 서버)
            String outputMessage = "보낼메시지";
            out.write(outputMessage.getBytes( ));
            out.flush( );
        
        4) input 스트림을 통한 데이터 수신 (서버 → 클라이언트)
            byte[ ] inputData = new byte[100];
            int length = in.read(inputData); //수신된 데이터를 읽어서 리턴
            String inputMessage = new String(inputData, 0, length);
        
        5) 통신 종료
            socket.close( );
        ```
        
    - 기다릴 필요 없이 바로 클라이언트 소켓을 생성함. 클라이언트 소켓은 서버 프로그램으로 연결 요청을 하는 것과 데이터 전송을 하는 일을 함
    1) **`socket()`** : 소켓 생성
    2) **`connect()`** : Client에서 Server와 연결하기 위해 소켓과 목적지 IP address, Port number 지정 (Block 상태)
    3) **`send()`**, **`recv()`**: Client는 처음에 생성한 소켓으로, Server는 새로 반환(생성)된 소켓으로 client와 server간에 데이터 송수신
    4) **`close()`**: 소켓을 닫음

## 소켓 종류

### 스트림 소켓(**Stream sockets**) - TCP

![Untitled (2)](https://github.com/RIN-1011/RIN-1011/assets/60701386/19c09b37-1d35-4486-8611-967ad994e8ec)

- TCP를 사용하므로 연결 지향형 소켓
- 신뢰성 보장
- 데이터 순서대로 송수신
- 점대점 연결

### 데이터그램 소켓(**Datagram sockets**) - UDP

![Untitled (3)](https://github.com/RIN-1011/RIN-1011/assets/60701386/6d744d7f-fd2f-403a-bced-6a6709d68229)

- UDP를 사용하므로 비 연결형 소켓
- 신뢰성을 보장할 수 없음
- 데이터가 순서대로 송수신될 지 보장 못함
- 점대점 연결뿐 아니라 일대다도 가능
    - **`connect()`** 과정이 필요 없기 때문에 소켓을 생성한 후 바로 데이터 전송 → 따라서 주로 일 대 다 통신에 많이 쓰임

## Socket.io와 WebSocket의 차이

### WebSocket  : 양방향 소통을 위한 프로토콜

- HTML5 웹 표준 기술
- 매우 빠르게 작동하며 통신할 때 아주 적은 데이터를 이용함
- 이벤트를 단순히 듣고, 보내는 것만 가능함

### Socket.io : 양방향 통신을 위해 웹소켓 기술을 활용하는 라이브러리

- 표준 기술이 아니며, 라이브러리임
- 소켓 연결 실패 시 fallback을 통해 다른 방식으로 알아서 해당 클라이언트와 연결을 시도함
- 방 개념을 이용해 일부 클라이언트에게만 데이터를 전송하는 브로드캐스팅이 가능함

### 어떤 걸 사용해야 하나?

- WebSocket
    - 데이터 전송이 많은 경우에는 빠르고 비용이 적은 표준 웹소켓 사용
    - ex) 가상화폐 거래소
- Socket.io
    - 서버에서 연결된 소켓들을 세밀하게 관리해야 하는 서비스인 경우 Broadcasting 기능이 있는 socket.io을 쓰는게 유지보수 측면에서 이득

## **HTTP 통신과 소켓 통신의 차이**

### HTTP 통신

- 클라이언트의 요청이 있을 때만 서버가 응답
- JSON, HTML, Image 등 다양한 데이터를 주고 받을 수 있음
- 서버가 응답한 후 연결을 바로 종료하는 단방향 통신이지만 Keep Alive 옵션을 주어 일정 시간 동안 커넥션을 유지할 수 있음
- 실시간 연결이 아닌 데이터 전달이 필요한 경우에만 요청을 보내는 상황에 유리

### 소켓 통신

- 클라이언트와 서버가 특정 포트를 통해 양방향 통신을 하는 방식
- 데이터 전달 후 연결이 끊어지는 것이 아니라 계속해서 연결을 유지 → HTTP에 비해 더 많은 리소스 소모
- 클라이언트와 서버가 실시간으로 계속하여 데이터를 주고 받아야 하는 경우에 유리
- 실시간 동영상 스트리밍이나 온라인 게임 등에 사용

## 참고

[https://velog.io/@newdana01/소켓이란-종류-통신-흐름-HTTP통신과의-차이](https://velog.io/@newdana01/%EC%86%8C%EC%BC%93%EC%9D%B4%EB%9E%80-%EC%A2%85%EB%A5%98-%ED%86%B5%EC%8B%A0-%ED%9D%90%EB%A6%84-HTTP%ED%86%B5%EC%8B%A0%EA%B3%BC%EC%9D%98-%EC%B0%A8%EC%9D%B4)

[https://on1ystar.github.io/socket programming/2021/03/16/socket-1/](https://on1ystar.github.io/socket%20programming/2021/03/16/socket-1/)

https://velog.io/@seungrok-yoon/how-we-travel-internet-2

https://kadosholy.tistory.com/125

https://juyoung-1008.tistory.com/19

https://wildeveloperetrain.tistory.com/122
