## Array

### 배열

- 동일한 자료형의 데이터를 연속된 공간에 저장하기 위한 자료구조

### 배열 사용

```java
int[] score = new int[3]; // int 타입의 값 5개가 저장될 빈 공간 생성
score[0] = 10; // 각 빈공간에 값을 초기화
score[1] = 20;
score[2] = 30;
```

```cpp
int score[3]; // int 타입의 값 5개가 저장될 빈 공간 생성
score[0] = 10; // 각 빈공간에 값을 초기화
score[1] = 20;
score[2] = 30;
```

```jsx
var score=[];
score[0] = 10; // 각 빈공간에 값을 초기화
score[1] = 20;
score[2] = 30;
```

- 자바스크립트의 배열은 객체이다.
- 1개의 변수에 여러 개의 값을 순차적으로 저장할 때 사용하고, 배열 내부의 데이터 타입이 다를 수 있다.
- 배열의 크기가 동적으로 변경될 수 있다.
- 배열의 요소를 위한 각각의 메모리 공간은 동일한 크기를 갖지 않아도 되며 연속적으로 이어져 있지 않을 수도 있다.

### 배열의 특징

- 연속된 메모리 공간에 데이터를 저장한다.
- 인덱스를 사용하여 원소에 빠르게 접근 가능하며 O(1)의 시간 복잡도를 가진다.
- 고정된 크기를 가지며 크기 변경이 어렵다.
- 원소의 삽입 삭제에 O(n)의 시간 복잡도를 가진다.
- 메모리 사용이 효율적이다. 각 원소는 인덱스로 접근되며, 추가적인 메모리를 사용하지 않는다.

## (Java) ArrayList

### ArrayList

![image](https://github.com/kknyapple/CS-Study/assets/72698829/4d643a87-431e-4812-8337-8784fab46142)

- Java의 Collection 프레임워크의 일부이며(List 인터페이스) java.util 패키지에 소속되어 있다.
- List 인터페이스에서 상속 받아 사용이 되고, 배열 공간을 초과하면 배열을 copy하는 방식으로 자동으로 부족한 크키 만큼 크기가 늘어난다.

### ArrayList 사용

```java
ArrayList<Integer> list = new ArrayList<>(10);

list.add(1); // ArrayList의 마지막에 객체를 추가
list.add(2);
list.add(3);

list.add(1, 5); // 1번 째 인덱스 자리에 요소 삽입

list.remove(2); // 2번 째 인덱스 자리의 요소 삭제
list.clear(); // list의 모든 데이터를 제거

list.contains(1); // list에 1이 있는 지 검색: true
list.indexOf(1); // list에 1이 있는 지 순차적으로 검색하고 index 반환 (없으면 -1)

list.get(1); // 지정된 위치에 저장된 객체를 반환
```

- 인덱스가 리스트의 capacity를 넘지 않도록 한다. (IndexOutOfBoundsException 예외 발생)

### Array와 ArrayList의 차이점

- Array는 크기가 고정적이고, ArrayList는 크기가 가변적이다.
- Array는 초기화 시 메모리에 할당되어 ArrayList 보다 속도가 빠르다.
- ArrayList는 데이터 추가 및 삭제 시 메모리를 재할당하기 때문에 속도가 Array 보다 느리다.

## 참고 자료

- https://dev-coco.tistory.com/159
- https://boycoding.tistory.com/193
- [https://inpa.tistory.com/entry/JAVA-☕-자바-배열Array-문법-응용-총정리](https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EC%9E%90%EB%B0%94-%EB%B0%B0%EC%97%B4Array-%EB%AC%B8%EB%B2%95-%EC%9D%91%EC%9A%A9-%EC%B4%9D%EC%A0%95%EB%A6%AC)
- [https://thecho7.tistory.com/entry/면접-꿀팁-배열Array과-링크드-리스트Linked-list의-특징](https://thecho7.tistory.com/entry/%EB%A9%B4%EC%A0%91-%EA%BF%80%ED%8C%81-%EB%B0%B0%EC%97%B4Array%EA%B3%BC-%EB%A7%81%ED%81%AC%EB%93%9C-%EB%A6%AC%EC%8A%A4%ED%8A%B8Linked-list%EC%9D%98-%ED%8A%B9%EC%A7%95)
- https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=baekmg1988&logNo=20194514561
