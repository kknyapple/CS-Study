# TCP

## TCP (Transmission Control Protocol)

> 장치들 사이에 논리적인 접속을 성립(establish)하기 위하여 연결을 설정하여 신뢰성을 보장하는 연결형 서비스

## 용어

### State 정보

- CLOSED : 포트가 닫힌 상태
- LISTEN : 포트가 열린 상태로 연결 요청 대기 중
- SYN_RECV : SYNC 요청을 받고 상대방의 응답을 기다리는 중
- ESTABLISHED : 포트 연결 상태
- TIME-WAIT : Client는 Server로부터 FIN을 수신하더라도 일정시간(default : 240초)동안 세션을 남겨놓고 잉여 패킷을 기다리는 과정

### Flag 정보

> TCP Header에는 CONTROL BIT(플래그 비트, 6bit)가 존재
> 각각의 bit는 "URG-ACK-PSH-RST-SYN-FIN"의 의미를 가진다.

- SYN(Synchronize Sequence Number)
  - 연결 설정
  - Sequence Number를 랜덤으로 설정하여 세션을 연결하는데 사용
  - 초기에 Sequence Number 전송
  - Connection을 생성할 때 사용하는 flag
- ACK(Acknowledgement)
  - 응답 확인
  - 패킷을 받았다는 것을 의미하는 flag
  - Acknowledgement Number 필드가 유용한지 나타낸다.
  - 양단 프로세스가 쉬지 않고 데이터를 전송한다고 가정하면 최초 연결 설정 과정에서 전송되는 첫 번째 세그먼트를 제외한 모든 세그먼트의 ACK비트는 1로 지정된다고 생각할 수 있다.
- FIN(Finish)
  - 연결 해제
  - 세션 연결을 종료시킬때 사용
  - 더 이상 전송할 데이터가 없음을 의미
  - 4-Way Handshake에서 사용

## 3-Way Handshake

> TCP 통신을 이용하여 데이터를 전송하기 위해 네트워크 연결을 설정(Connection Establish) 하는 과정

### 과정

<img src="https://github.com/Ahrang777/CS-Study/assets/72875528/74b22cc9-faf4-4479-8d83-2610dae754f5" width="80%" height="50%"/>

#### Step1

> Client -> SYN -> Server

- Client가 Server에게 접속을 요청하는 SYN 플래그를 보낸다.

#### Step2

> Server -> SYN + ACK -> Client

- Server는 Listen상태에서 SYN이 들어온 것을 확인하고 SYN_RECV 상태로 바뀌어 SYN + ACK 플래그를 Client에게 전송한다.
- 그 후 Server는 다시 ACK 플래그를 받기 위해 대기상태로 변경된다.

#### Step3

> Client -> ACK -> Server

- SYN + ACK 상태를 확인한 Client는 서버에게 ACK를 보내고 연결 성립된다.

## 4-Way Handshake

> TCP의 연결을 해제(Connection Termination) 하는 과정

### 과정

<img src="https://github.com/Ahrang777/CS-Study/assets/72875528/f7a926d0-6185-46fd-af93-4ffdbbc27287" width="80%" height="50%"/>

#### Step1

> Client -> FIN -> Server

- Client가 연결을 종료하겠다는 FIN 플래그를 전송한다.
- 보낸 후에 FIN-WAIT-1 상태로 변한다.

#### Step2

> Server -> ACK -> Client

- FIN 플래그를 받은 Server는 확인 메시지인 ACK를 Client에게 보내준다.
- 그 후 CLOSE-WAIT 상태로 변한다.
- Client도 마찬가지로 Server에서 종료될 준비가 됐다는 FIN을 받기위해 FIN-WAIT-2상태가 된다.

#### Step3

> Server -> FIN -> Client

- Close 준비가 다 된 후 Server는 Client에게 FIN 플래그를 전송한다.

#### Step4

> Client -> ACK -> Server

- Client는 해지 준비가 되었다는 정상 응답인 ACK를 Server에게 보내준다.
- 이때, Client는 TIME-WAIT 상태로 변경된다.

## Question

### Q1. TCP 연결 설졍 과정과 연결 종료 과정의 단계가 차이나는 이유는?

- Client가 데이터 전송을 마쳤다고 하더라도 Server는 아직 보낼 데이터가 남아있을 수 있다.
- 그렇기 때문에 일단 FIN에 대한 ACK만 보내고, 데이터를 모두 전송한 후 FIN을 보낸다.

### Q2. 만약 Server에서 FIN 플래그 전송하기 전에 전송한 패킷이 Routing 지연이나 패킷 유실로 인한 재전송 등으로 FIN 패킷보다 늦게 도착하는 상황이 발생하면 어떻게 될까?

- TIME-WAIT
- 이러한 현상에 대비하여 Client는 Server로부터 FIN을 수신하더라도 일정시간(default : 240초)동안 세션을 남겨놓고 잉여 패킷을 기다리는 과정을 거친다.


### Q3. 초기 Sequence Number인 ISN을 0부터 시작하지 않고 난수를 생성해서 설정하는 이유는?

- Connection을 맺을 때 사용하는 Port는 유한 범위 내에서 사용하고 시간이 지남에 따라 재사용된다.
- 따라서 두 통신 호스트가 과거에 사용한 포트 번호 쌍을 사용하는 가능성이 존재한다.
- 서버에서는 패킷의 SYN을 보고 패킷을 구분하게 되는데 난수가 아닌 순차적인 Number가 전송된다면 이전의 Connection에서 오는 패킷을 인식할 수 있다.
- 이러한 문제 발생 가능성을 줄이기 위해 난수로 ISN을 설정한다.

## 참고

https://jeongkyun-it.tistory.com/180  
https://github.com/WeareSoft/tech-interview/blob/master/contents/network.md#tcp%EC%9D%98-3-way-handshake%EC%99%80-4-way-handshake
