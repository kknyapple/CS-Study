# 목차
1. [TCP/IP란](#TCP/IP란)
2. [OSI 7계층 VS TCP/IP 4계층](#OSI-7계층-VS-TCP/IP-4계층)
3. [사용 이유](#사용-이유)
4. [계층](#계층)
5. [참고](#참고)

# TCP/IP 4계층

## TCP/IP란

### IP **(Internet Protocol)**

- OSI 3계층(네트워크)에서 사용하는 프로토콜
- 서로 다른 운영체제 등을 쓰는 다양한 컴퓨터들이 서로 통신할 때 사용하는 약속된 데이터 이동 간의 규약
- 패킷 데이터들을 최대한 빨리 특정 목적지 주소로 보내는 프로토콜

**특징**

1. 비연결성 : 패킷을 받을 대상이 없거나, 서비스 불능 상태여도 패킷 전송
2. 비신뢰성
    1. 에러제어와 흐름제어 하지 않음
    2. 패킷 전달 여부를 보증하지 않음
    3. 패킷을 보낸 순서와 받는 순서 다를 수 있음

### TCP **(Transmission Control Protocol)**

- OSI 4계층(전송)에서 사용하는 프로토콜
- 패킷을 정상적으로 받을 수 있도록 하는 프로토콜

**특징**

1. 연결형 : 일단 연결을 하고 데이터 전송 (3-way handshake, 4-way handshake)
2. 순서 보장 : 순서가 잘못되어 도착한 경우 수신 호스트가 송신 호스트에게 다시 전송 요청
3. 데이터 전달 보증 : 메시지 누락 알 수 있음, 데이터 전송 후 수신 호스트가 잘 받았다고 응답
4. 신뢰할 수 있는 프로토콜
5. 흐름제어 및 혼잡제어

## OSI 7계층 VS TCP/IP 4계층

![Untitled (8)](https://github.com/RIN-1011/RIN-1011/assets/60701386/380ccbfa-36b4-4e80-bc51-4352ad305998)

- OSI 7계층이 네트워크 전송의 데이터 표준을 정립했다면 TCP/IP 4계층은 이를 실제로 사용하는 인터넷 표준
- TCP/IP 프로토콜 통신 과정에 초점을 맞추어, OSI 7계층을 좀 더 단순화 시킨 계층

## 사용 이유

- 실제 인터넷 통신을 반영하기에 현실적
- 인터넷 개발 이후 계속 표준화 되어 신뢰성 우수

## 계층

### 네트워크 인터페이스 계층 **(Network Interface or Network Access Layer)**

- OSI 1,2계층에 해당. TCP/IP 패킷을 네트워크 매체로 송수신하는 과정을 담당한다.
- 데이터 단위는 프레임, 전송 주소는 MAC 주소를 참조한다.
- 물리적인 네트워크 통신을 정의하며, 흐름 제어(Flow Control)는 Header(MAC)에서 수행한다.
- 에러 검출과 패킷의 프레임화 역할을 담당하며, 에러 제어(Error Control)는 Tailer(CRC)에서 수행한다.

### 인터넷 계층(Internet Layer)

- OSI 3계층에 해당하며, Addressing, Packaging, Routing 기능을 제공한다.
- 데이터 단위는 패킷(세그먼트로부터), 전송 주소는 IP 주소를 참조한다.
- 논리적 주소인 IP를 이용한 노드간 전송과 라우팅 기능을 처리하며, 네트워크상 목적지까지 연결성을 제공한다.
- 패킷은 Segment를 목적지에 전송하기 위해 시작&목적지의 논리주소를 붙인 단위이다.
- 핵심 프로토콜은 IP, ARP, ICMP, IGMP 등이 있다.

### 전송 계층(Transport Layer)

- OSI 3,4계층에 해당하며, 통신 노드 간의 연결제어 및 자료의 송수신을 담당한다.
- 데이터 단위는 세그먼트(실질적인 데이터 전송단위), 전송 주소는 Port 를 참조한다.
- 애플리케이션 계층의 세션과 데이터그램 통신 서비스를 제공한다.
- TCP, UDP가 핵심 프로토콜이다. TCP, UDP에 대한 구분을 하고 데이터에 대한 제어 정보가 포함된다.
- Segment(세그먼트)는 전송을 위해 데이터를 일정 크기로 나누며, 발신, 수신, 포트주소, 오류검출코드가 첨부된다.

### 응용 프로그램 계층(Application Layer)

- OSI 5,6,7계층에 해당하며, 다른 계층의 서비스에 접근할 수 있는 응용 프로그램 서비스를 제공한다.
- 데이터 단위는 Data/Message 자체이다.
- 사용자가 소프트웨어 응용 프로그램을 사용하는 가장 가까운 계층이며, 이 인터페이스를 제공한다.
- 응용 프로그램 간의 데이터 통신을 위해 사용하는 프로토콜을 정의한다. (HTTP, FTP, SSH, Telnet 등)

## 참고

https://wooono.tistory.com/507

[https://velog.io/@yoonvelog/네트워크-인터넷-통신](https://velog.io/@yoonvelog/%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC-%EC%9D%B8%ED%84%B0%EB%84%B7-%ED%86%B5%EC%8B%A0)

https://velog.io/@rosewwross/TCPIP

[https://abangpa1ace.tistory.com/entry/Web-OSI-7계층](https://abangpa1ace.tistory.com/entry/Web-OSI-7%EA%B3%84%EC%B8%B5)
