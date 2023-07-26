1. [Stack](#Stack)
2. [Queue](#Queue)
3. [Circular Queue](#Circular Queue)
4. [Deque](#Deque)

# **Stack**

## **개념**

맨 위의 특정 위치에서 가장 나중에 들어온 것이 가장 먼저 빠져 나가는 구조.

즉, 후입선출 형태의 선형 자료구조이다.

## **장단점**

### 장점

1) 구조가 단순해서 구현이 쉬움

2) 데이터 저장/읽기 속도가 빠름

### 단점

1) 데이터 최대 개수를 미리 정해야 함

2) 저장 공간의 낭비가 발생할 수 있음

3) 중간에 위치한 데이터에 대한 접근 불가능

## **연산 (JAVA)**

- push(e) : 원소 e를 삽입
- pop( ) : 가장 나중에 삽입된 원소를 삭제
- clear( ) : 스택의 전체 값 제거
- peek( ) : 가장 상단의 값 반환
- search(e) : 원소 e가 스택에서 몇 번째에 있는지 반환
- size( ) : 스택에 저장된 원소 수 반환
- empty( ) : 스택이 비어있는지 여부 반환 (비어있으면 true)
- contains(e) : 스택에 원소 e 있는지 여부 반환 (있으면 true)

## **시간 복잡도**

- 삽입, 삭제 : O(1)
- 조회 : O(n)

## **사용 사례**

1. 재귀 알고리즘
    
    : 재귀적으로 함수를 호출해야 하는 경우에 임시 데이터를 스택에 넣어줌
    
    : 재귀함수를 빠져나와 퇴각 검색을 할 때는 스택에 넣어 두었던 임시 데이터를 빼 줘야 함
    
    : 스택은 이런 일련의 행위를 직관적으로 가능하게 해줌
    
    : 또한 스택은 재귀 알고리즘을 반복적 형태를 통해서 구현할 수 있게 해줌
    
2. 웹 브라우저 방문기록 (뒤로 가기) : 가장 나중에 열린 페이지부터 다시 보여줌
3. 역순 문자열 만들기 : 가장 나중에 입력된 문자부터 출력
4. 실행 취소 (undo) : 가장 나중에 실행된 것부터 실행 취소
5. 수식의 괄호 검사 (연산자 우선순위 표현을 위한 괄호 검사)
6. 후위 표기법 계산

## **구현 예시 코드 (JAVA)**

```java
import java.util.Stack;

import javax.lang.model.element.Element;

public class Main {

		public static void main(String[] args) {
		
		Stack<Integer> stack = new Stack<>();//push, pop, peek, empty, seach 지원
		
		for(int i=1; i<=5 ; i++) {
		
		stack.push(i);
		
		System.out.println(stack.peek());
		
		} //1, 2, 3, 4, 5 출력
		
		stack.pop();
		
		System.out.println("Pop()");
		
		System.out.println(stack.peek());    //4출력
		
		System.out.println(stack.search(3));    //2출력
		
		System.out.println(stack.empty());    //false출력
		
		}

}
```


# **Queue**

## **개념**

양 쪽 끝에서만 데이터를 넣거나 뺄 수 있는 선형 구조. 선입선출 형태의 선형 자료구조이다.

데이터가 삽입되는 곳을 rear, 데이터가 제거되는 곳을 front라 한다.

## **장단점**

### 장점

1) 데이터 저장/읽기 속도가 빠름

2) 데이터가 입력된 시간 순서대로 처리해야 할 필요가 있는 상황에 유리함

### 단점

1) 데이터 최대 개수를 미리 정해야 함

2) 저장 공간의 낭비가 발생할 수 있음

3) 중간에 위치한 데이터에 대한 접근 불가능

## **연산 (JAVA)**

- add(e), offer(e) : 원소 e를 삽입
- poll( ), remove( ) : 맨 앞의 값 삭제
- clear( ) : 큐의 전체 값 제거
- peek( ) : 큐의 첫번째 값 반환
- size( ) : 큐에 저장된 원소 수 반환
- isEmpty( ) : 큐가 비어있는지 여부 반환 (비어있으면 true)
- contains(e) : 큐에 원소 e 있는지 여부 반환 (있으면 true)

## **시간 복잡도**

- 삽입, 삭제 : O(1)

## **사용 사례**

1. 너비 우선 탐색 (BFS)
    
    : 처리해야 할 노드의 리스트를 저장하는 용도로 큐를 사용함
    
    : 노드를 하나 처리할 때마다 해당 노드와 인접한 노드들을 큐에 다시 저장함
    
    : 노드를 접근한 순서대로 처리 가능
    
2. 캐시 구현
3. 우선순위가 같은 작업 예약 (인쇄 대기열)
4. 선입선출이 필요한 대기열 (티켓 카운터)
5. 콜센터 고객 대기 시간
6. 프린터의 출력 처리
7. 윈도우 시스템의 메시지 처리기
8. 프로세스 관리

## **구현 예시 코드**

```java
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<Integer>();

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);

        while(!queue.isEmpty()) {
            System.out.println(queue.poll());
        }

    }

}
```


# Circular Queue

## 개념

선형 큐의 문제점을 보완하기 위한 자료구조

- 설명
    - 메모리 낭비 보완 : 데이터를 처음부터 채운다고 가정하면 front의 값은 계속 0이고 rear의 값은 증가한다. rear가 배열의 마지막까지 도달했는데 만약 배열의 맨 앞에 데이터가 빠져서 빈 공간이 생기면 rear는 다시 처음으로 돌아와 0을 가리키며 그 위치에 데이터를 저장할 수 있다.
    - 연산 낭비 보완 : 데이터의 맨 앞과 맨 뒤 위치를 기억하기 때문에 기존의 큐처럼 데이터를 매번 한 칸씩 당겨오지 않아도 공간이 허락하는 한 데이터를 빠르게 처리할 수 있다.

### 선형 큐의 문제점

1) 배열 큐 : 이미 사용한 영역의 front 앞부분에 대해 다시 활용 불가 → 메모리 낭비

2) 이동 큐 : 큐가 다 찼을 경우 데이터 앞쪽으로 이동시키는 연산 → 연산 낭비


# Deque

## 개념

Deque(데크)는 double-ended-queue의 줄임말로, 양방향에서 데이터를 처리할 수 있는 queue형 자료구조

## 장단점

### 장점

1) 스택과 큐의 장점을 모두 가지고 있음

2) index로 임의 원소 접근 가능

### 단점

1) 중간에서의 삽입과 삭제가 어려움

## 연산 (JAVA)

- addFirst() : 덱의 앞쪽에 엘리먼트 삽입, 용량 초과하면 예외 발생
- offerFirst() : 덱의 앞쪽에 엘리먼트 삽입, 정상적으로 엘리먼트 삽입되면 true 리턴
- addLast() : 덱의 마지막 쪽에 엘리먼트 삽입, 용량 초과하면 예외 발생
- add() : addLast()와 동일
- offerLast() : 덱의 마지막 쪽에 엘리먼트 삽입, 정상적으로 엘리먼트 삽입되면 true 리턴
- removeFirst() : 덱의 앞쪽에서 엘리먼트 하나를 뽑아서 제거한 다음 해당 엘리먼트를 리턴
- pollFirst() : 덱의 앞쪽에서 엘리먼트 하나를 뽑아서 제거한 다음 해당 엘리먼트를 리턴, 덱이 비어있으면 null 이 리턴
- removeLast() : 덱의 앞쪽에서 엘리먼트 하나를 뽑아서 제거한 다음 해당 엘리먼트를 리턴, 덱이 비어있으면 예외 발생
- pollLast() : 덱의 마지막 쪽에서 엘리먼트 하나를 뽑아서 제거한 다음 해당 엘리먼트를 리턴, 덱이 비어있으면 null 이 리턴
- remove() : removeFirst()와 동일
- poll() : pollFirst()와 동일
- getFirst() : 덱의 앞쪽 엘리먼트 하나를 제거하지 않은채 리턴, 덱이 비어있으면 예외 발생
- peekFirst() : 덱의 앞쪽 엘리먼트 하나를 제거하지 않은채 리턴, 덱이 비어있으면 nul 리턴
- getLast() : 덱의 마지막쪽 엘리먼트 하나를 제거하지 않은채 리턴, 덱이 비어있으면 예외 발생
- peekLast() : 덱의 마지막 엘리먼트 하나를 제거하지 않은 채 리턴, 덱이 비어있으면 null 리턴
- peek() : peekFirst()와 동일
- push() : addFirst()와 동일. 덱을 스택으로 사용할 때 쓰임
- pop() : removeFirst()와 동일. 덱을 스택으로 사용할 때 쓰임
- contain(Object o) : 덱에 Object o와 동일한 엘리먼트가 포함되어 있는지 확인
- size() : 덱의 크기
- iterator() : 덱의 앞쪽부터 순차적으로 실행되는 이터레이터(iterator)를 얻어옴
- descendingIterator() : 덱의 뒤쪽부터 순차적으로 실행되는 이터레이터(iterator)를 얻어옴

## **시간 복잡도**

- 삽입, 삭제 : O(1)

## 구현 예시 코드

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class DequeDemo {
	public static void main(String[] args)  {
		Deque<String> deque = new ArrayDeque<String>(); // Deque 선언
		
		// 값 추가
		deque.addFirst("Hello");
		deque.offerFirst("Hello");
		deque.addLast("World");
		deque.offerLast("World");
		deque.add("Hello");
		
		System.out.print(deque); // 결과 출력
	}
}
```
