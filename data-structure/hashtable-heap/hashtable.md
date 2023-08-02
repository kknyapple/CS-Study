# Hash Table

## 해시 함수 (Hash Function)

> 임의의 길이를 갖는 데이터를 고정된 길이의 값으로 매핑하는 함수

- 임의 크기의 데이터가 해시 함수를 통해 고정 크기 값인 해시로 매핑된다.

  > 임의의 길이를 갖는 데이터 (Key) => 해시 함수 => 고정된 길이의 값(해시 값)  
  >  ex) SHA265 https://www.convertstring.com/ko/Hash/SHA256  
  >  ![image](https://github.com/Ahrang777/CS-Study/assets/72875528/06764d88-dcd9-4c7a-8860-45bac6b78c23)
  > 체크섬(Checksum), 손실 압축, 무작위화 함수(Randomization Function), 암호 등과 관련 깊다.

1. 나눗셈법
2. 곱셈법
3. Universal Hasing
4. MD5(Message - Digest Algorithm)
5. SHA(Secure Hash Algorithm)

## 해싱 (Hashing)

> 해시 테이블을 인덱싱하기 위해 해시 함수를 사용하는 것

## 해시 테이블 (Hash Table)

### 해시 테이블이란?

> Key와 Value를 1:1로 연관 지어 저장하는 자료구조

### 구조 및 동작 과정

<img src="https://github.com/Ahrang777/CS-Study/assets/72875528/94d081f2-0289-4857-a167-1bac462d6b29" width="60%" height="50%"/>

### 구조

1. Key
2. 해시 함수 (Hash Function)
3. Bucket 또는 Slot
   - 값이 저장되는 장소

### 동작 과정(나눗셈법 기준)

1. 해시 함수를 이용해서 키를 해시 값으로 매핑한다.
2. 해시 값을 버킷의 크기로 나누고 나온 나머지를 버킷의 인덱스로 가진다.

## 로드 팩터 (Load Factor)

> 해시 테이블에 저장된 데이터 개수 n을 버킷의 개수 k로 나눈 것

- 로드 팩터 비율에 따라서 해시 함수를 재작성해야 될지 또는 해시 테이블의 크기를 조정해야 할지 결정한다.
- 로드 팩터가 증가할수록 해시 테이블의 성능은 점점 감소하며, Java10의 경우 0.75를 넘어설 경우 동적 배열처럼 해시 테이블의 공간을 재할당한다.

## 기능

- 연관배열(Associative Array)과 동일한 기능을 지원한다.
- Key, Value를 저장한다.
- Key가 주어졌을 때, 해당 Key와 연관된 Value 조회한다.
- 기존 Key에 새로운 Value가 주어졌을 때, 기존 Value를 새로운 Value로 대체한다.
- Key가 주어졌을 때, 해당 Key에 연관된 Value 제거한다.

## 특징

- 순차적으로 데이터를 저장하지 않는다.
- Key를 통해서 Value 값을 얻을 수 있다.
- Key는 중복이 허용되지 않고, Value는 중복이 허용된다.
- 수정 가능하다.(Mutable)

## 시간 복잡도

- 평균 : O(1)

- 최악 : O(N)
  - 충돌 발생 시, 개별 체이닝의 경우 연결된 리스트까지 검색해야 하므로 O(N)까지 시간 복잡도가 증가할 수 있다.
  - 만약 테이블이 꽉 찬 경우, 테이블을 확장해 주어야 하는데 이는 심각한 성능 저하를 불러오기 때문에 가급적이면 확장하지 않도록 테이블을 설계해야 한다.
  - 해시 테이블에서 자주 사용하게 되는 데이터를 Cache에 적용하면 효율을 높일 수 있다.

## 충돌

### 해시 충돌이란?

> 해시 함수가 서로 다른 두 개의 입력값에 대해 동일한 출력값을 내는 상황을 의미

- 해시 충돌은 해시 함수를 이용한 자료구조나 알고리즘의 효율적을 떨어뜨리기 때문에, 해시 함수는 해시 충돌이 자주 발생하지 않도록 구성해야 한다.

### 비둘기집 원리

<img src="https://github.com/Ahrang777/CS-Study/assets/72875528/c702311d-20e7-416a-8cec-77f7b9541177" width="50%" height="50%"/>

> n개 아이템을 m개 컨테이너에 넣을 때, n > m이라면 적어도 하나의 컨테이너에는 반드시 2개 이상의 아이템이 들어있다는 원리

### Birthday Paradox

<img src="https://github.com/Ahrang777/CS-Study/assets/72875528/1d4b9235-07b3-4a20-b48f-0148ac0c7afc" width="60%" height="50%"/>

> 366명의 사람이 모였을 때, 생일이 겹치는 사람이 최소 2명 이상이 된다는 것으로 모든 경우의 수를 넘어서는 통계 표본이 존재할 때, 중복되는 값이 필연적으로 발생한다는 수학적 원리

### 충돌 해결 방법

해시 충돌 해결 방법으로 개별 체이닝(Separate Chaining)과 오픈 어드레싱(Open Addressing)이 있다.

#### 개별 체이닝(Separate Chaining)

> 연결 리스트나 레드-블랙 트리로 연결하는 방식

<img src="https://github.com/Ahrang777/CS-Study/assets/72875528/b7542fe6-7b79-4c9e-b98b-7912b86c3807" width="60%" height="50%"/>

1. 키의 해시 값을 계산한다.
2. 해시 값을 이용해 배열의 인덱스를 구한다.
3. 같은 인덱스가 있다면 연결 리스트로 연결한다.

> Java8에서는 연결 리스트 구조를 좀 더 최적화하여, 데이터의 개수가 많아지면 레드-블랙 트리에 저장하는 형태로 병행해 사용

#### 오픈 어드레싱(Open Addressing)

> 충돌 발생 시 탐사를 통해 빈 공간을 찾아 나서는 방식

<img src="https://github.com/Ahrang777/CS-Study/assets/72875528/7e813dcd-3a0e-4ae9-ae75-628b9cdc01cc" width="60%" height="50%"/>

- 추가 메모리 공간을 사용하지 않고, HashTable 배열의 빈 공간을 사용
- 구현 방법
  1. 선형 탐사
     > 현재 버킷의 index로부터 고정폭만큼 이동하여 차례대로 검색하여 비어있는 버킷에 데이터 저장하는 방식
  2. 제곱 탐사
     > 해시의 저장 순서 폭을 제곱으로 저장하는 방식
  3. 이중해싱
     > 해시된 값을 한 번 더 해싱하여 해시의 규칙성을 없애버리는 방식

> 오픈 어드레싱에서 데이터 삭제 시, 삭제된 공간은 Dummy Space로 활용된다. 따라서 Hash Table을 재정이 해주는 작업이 필요하다.

## 언어별 해시 테이블 구현

| **언어** | **방식**      |
| -------- | ------------- |
| C++      | 개별 체이닝   |
| Java     | 개별 체이닝   |
| Go       | 개별 체이닝   |
| Ruby     | 오픈 어드레싱 |
| Python   | 오픈 어드레싱 |

## HashTable vs HashMap

### HashTable

> 병렬 처리를 하면서 자원의 동기화를 고려해야 하는 상황

- 동기
- Key-Value 값으로 null을 허용하지 않는다.(Key가 hashcode(), equals()를 사용하기 때문)
- 보조 해시 함수와 separating Chaining을 사용해서 비교적 충돌 덜 발생한다.

### HashMap

> 병렬 처리를 하지 않거나, 자원의 동기와를 고려하지 않는 상황

- 비동기
- Key-Value 값으로 null을 허용한다.

## 참고

박상길, 파이썬 알고리즘 인터뷰, 책만, 2020, 11장 해시 테이블  
https://mangkyu.tistory.com/102  
https://en.wikipedia.org/wiki/Hash_table  
https://velog.io/@taeha7b/datastructure-hashtable  
https://velog.io/@mooh2jj/Hash%EC%9D%98-%EA%B0%9C%EB%85%90-%EB%B0%8F-%EC%84%A4%EB%AA%85
