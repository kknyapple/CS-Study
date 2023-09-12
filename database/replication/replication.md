### Replication 배경

![image](https://github.com/kknyapple/CS-Study/assets/72698829/be50e4aa-dfa4-44f9-ab8e-ec62b4cb8a43)

- 단순한 Database를 구성할 때는 아래의 그림처럼 하나의 서버와 하나의 Database를 구성하게 된다.
- 사용자는 점점 많아지고 Database는 많은 Query를 처리하기엔 너무 힘들어진다.
- 데이터베이스 리플리케이션은 기본적으로 데이터 안정성을 위함이다.
- 어떠한 원인으로 인해 데이터가 손상되었을 때, 가장 기초적인 대처는 가장 최신의 백업본을 복구하여 사용하는 것이다. 그러나 데이터 백업을 주기적이고 자동으로 되도록 해놓았다고 하더라도 백업된 시간과 장애가 발생한 시간 사이의 데이터 변경 사항들은 모두 소실되게 된다.
- Replication을 사용하게 되면 간의 딜레이가 있긴 하지만 거의 실시간으로 마스터 서버와 동일한 데이터를 갖고 있기 때문에, 장애 복구 시 데이터 소실이 최소화된다.

### **Replication**

- 여러 개의 DB를 권한에 따라 수직적인 구조(Master-Slave)로 구축하는 방식이다.
- 리플리케이션에서 Master Node는 쓰기 작업 만을 처리하며 Slave Node는 읽기 작업 만을 처리한다.
- 리플리케이션은 비동기 방식으로 노드들 간의 데이터를 동기화한다.

### **Replication 처리 방식**

![image](https://github.com/kknyapple/CS-Study/assets/72698829/287fd115-bcf8-4e7a-9116-f80d4512c6f8)

- Master DBMS 에는 데이터의 수정사항을 반영만 하고 Replication을 하여 Slave DBMS에 실제 데이터를 복사한다.

### MySQL의 Replication

![image](https://github.com/kknyapple/CS-Study/assets/72698829/b4c52d9d-ef7d-4dea-b3e9-787900375e28)

- MySQL의 Replication 방식
1. Master 노드에 쓰기 트랜잭션이 수행된다.
2. Master 노드는 데이터를 저장하고 트랜잭션에 대한 로그를 파일에 기록한다.(BIN LOG)
3. Slave 노드의 IO Thread는 Master 노드의 로그 파일(BIN LOG)를 파일(Replay Log)에 복사한다.
4. Slave 노드의 SQL Thread는 파일(Replay Log)를 한 줄씩 읽으며 데이터를 저장한다.
- 리플리케이션은 Master와 Slave간의 데이터 무결성 검사(데이터가 일치하는지)를 하지 않는 비동기방식으로 데이터를 동기화한다.

### **Replication 장단점**

- 장점
    - DB 요청의 60~80% 정도가 읽기 작업이기 때문에 Replication만으로도 충분히 성능을 높일 수 있다.
    - 비동기 방식으로 운영되어 지연 시간이 거의 없다.
- 단점
    - 노드들 간의 데이터 동기화가 보장되지 않아 일관성 있는 데이터를 얻지 못할 수 있다.
    - Master 노드가 다운되면 복구 및 대처가 까다롭다.
