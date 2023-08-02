## Linked List

- 노드와 포인터로 구성되어 있으며 각 노드는 포인터로 연결되어 있는 자료구조이다.

### Linked List 특징

- 원소에 접근하기 위해서는 리스트를 순차적으로 탐색해야 하며 O(n)의 시간 복잡도를 가진다.
- 크기 변경이 쉽고 새 노드를 동적으로 할당하거나 제거함으로써 리스트의 크기를 변경할 수 있다.
- 원소를 삽입하거나 삭제할 때 포인터만 업데이트 하면 되므로 O(1)의 시간 복잡도를 가진다. (단, 삽입이나 삭제할 위치를 찾는 과정은 O(n)의 시간 복잡도를 갖는다. 맨 앞과 맨 뒤에 삽입 삭제 시 O(1))
- 메모리 사용이 비효율적일 수 있다. 각 노드를 데이터와 포인터를 저장해야 하므로, 추가적인 메모리를 사용한다.

### 단순 연결 리스트(Singly Linked List)

![image](https://github.com/kknyapple/CS-Study/assets/72698829/43bfa492-2190-425c-9edd-321f8ab6c67e)

```java
class Node {
	Node next; // 다음 요소의 주소
	Object obj; // 데이터
}
```

- 다음 노드에 대한 참조만 가진 연결 리스트의 형태이다.

### 이중 연결 리스트(Doubley Linked List)

![image](https://github.com/kknyapple/CS-Study/assets/72698829/177616e7-8cb7-44d1-b2db-b6e6cdf1af14)

```java
class Node{
	Node next;
	Node previous; // 이전 요소의 주소
	Object obj;
}
```

- 다음 노드의 참조 뿐만 아니라 이전 노드의 참조도 같이 가리키게 하는 형태이다.
- 이동 방향이 단방향이기 때문에 다음 요소에 대한 접근은 쉽지만 이전 요소에 대한 접근이 어려운 단순 연결 리스트의 단점을 보완했다.

### 순환 연결 리스트(Circluar Linked List)

![image](https://github.com/kknyapple/CS-Study/assets/72698829/a4f82f62-1829-4154-88ee-5b229b8d8e61)

- 단순 연결 리스트에서 마지막 원소가 null 대신 처음 원소를 가리키게 하는 형태이다.

*자바의 LinkedList 클래스는 Doubley Linked List 리스트로 구현되어 있다.

## (Java) Array, ArrayList, LinkedList

### Array vs Linked List

- 배열은 인덱스를 사용한 빠른 접근이 필요하거나 크기가 고정된 경우에 적합하다.
- 링크드 리스트는 데이터의 삽입과 삭제가 빈번하게 발생하거나 크기가 가변적인 경우에 적합하다.

### ArrayList vs LinkedList

- 각 요소에 접근하거나 순차적으로 추가 삭제 하는 경우는 ArrayList가 LinkedList보다 빠르다.
- 중간 데이터를 추가 삭제 하는 경우에는 LinkedList가 ArrayList 보다 빠르다.

## 정리

- 배열과 연결리스트는 선형 자료구조이다.
- 배열은 동일한 타입의 변수가 연속된 메모리 공간을 사용하며, 인덱스를 통한 검색이 빠르지만 삽입과 삭제가 느리다.
    
    검색 → O(1), 삽입 삭제 → O(n)
    
- 연결 리스트는 연속되지 않은 메모리 공간에 있는 데이터를 포인터를 이용해 연결하며, 삽입 삭제가 빠르지만 검색이 느리다.
    
    검색 → O(n), 삽입 삭제 → O(1) (위치 찾는 과정은 O(n))
    
- 배열에서 메모리는 선언 시 컴파일 타임에 할당되고(Stack), 연결리스트는 새로운 요소가 추가될 때 런타임에 메모리를 할당한다(Heap).

*선형 자료구조: 자료를 구성하는 데이터를 순차적으로 나열시킨 형태

## 참고 자료

- https://woovictory.github.io/2018/12/27/DataStructure-Diff-of-Array-LinkedList/index.html
- [https://namu.wiki/w/연결 리스트](https://namu.wiki/w/%EC%97%B0%EA%B2%B0%20%EB%A6%AC%EC%8A%A4%ED%8A%B8)
- https://velog.io/@seongwon97/Java-LinkedList-class
- Java의 정석 chapter11
