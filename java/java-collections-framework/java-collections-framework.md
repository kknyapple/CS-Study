# Java Collections Framework

## 개념

![Untitled (11)](https://github.com/RIN-1011/RIN-1011/assets/60701386/7bedb0fc-04f2-4619-990d-2cc589414927)

- 다수의 데이터를 쉽고 효과적으로 처리할 수 있는 표준화된 방법을 제공하는 클래스의 집합
- 즉, 데이터를 저장하는 자료 구조와 데이터를 처리하는 알고리즘을 구조화하여 클래스로 구현해 놓은 것
- 이러한 컬렉션 프레임워크는 자바의 인터페이스(interface)를 사용하여 구현됨

## 장점

1. 인터페이스와 다형성을 이용한 객체지향적 설계를 통해 표준화되어 있기 때문에, 사용법을 익히기에도 편리하고 재사용성이 높다.
2. 데이터 구조 및 알고리즘의 고성능 구현을 제공하여 프로그램의 성능과 품질을 향상시킨다.
3. 관련 없는 API 간의 상호 운용성을 제공한다. (상위 인터페이스 타입으로 업캐스팅하여 사용)
4. 이미 구현되어있는 API를 사용하면 되기에, 새로운 API를 익히고 설계하는 시간이 줄어든다.
5. 소프트웨어 재사용을 촉진한다. 만일 자바에서 지원하지 않는 새로운 자료구조가 필요하다면, 컬렉션들을 재활용하여 종합하여 새로운 알고리즘을 만들어낼 수 있다.

## **컬렉션 프레임워크 주요 인터페이스**

### ****Iterable 인터페이스****

![Untitled (12)](https://github.com/RIN-1011/RIN-1011/assets/60701386/c45b7307-a527-411b-92e3-50d69137bb6e)

- 컬렉션 인터페이스들의 가장 최상위 인터페이스
- 컬렉션 자료들을 순회할 때 이터레이터 객체를 다루는데, 이 이터레이터 객체를 관리하는 인터페이스

| 메서드 | 설명 |
| --- | --- |
| default void forEach(Consumer<? super T> action) | 함수형 프로그래밍 전용 루프 메서드 |
| Iterator<T> iterator() | 컬렉션에서 이터레이터를 구현 |
| default Spliterator<T> splierator() | 파이프라이닝 관련 메소드 |

### ****Collection 인터페이스****

![Untitled (13)](https://github.com/RIN-1011/RIN-1011/assets/60701386/551c4d22-7581-419d-8b17-7285068a60a2)

- List, Set, Queue에 상속을 하는 실질적인 최상위 컬렉션 타입
- 즉, 업캐스팅으로 다양한 종류의 컬렉션 자료형을 받아 자료를 삽입하거나 삭제, 탐색 기능을 할 수 있다. (다형성)

| 메서드 | 설명 |
| --- | --- |
| boolean add(Object o)boolean addAll(Collection c) | 지정된 객체(o) 또는 Collection(c)의 객체들을 Collection에 추가 |
| boolean contains(Object o)boolean containsAll(Collection c) | 지정된 객체(o) 또는 Collection의 객체들이 Collection에 포함되어 있는지 확인 |
| boolean remove(Object o)boolean removeAll(Collection c) | 지정된 객체 또는 지정된 Collection에 포함된 객체들을 삭제 |
| boolean retainAll(Collection c) | 지정된 Collection에 포함된 객체만을 남기고 다른 객체들은 Collection에서 삭제.<br>사실상 removeAll 의 대칭 버전. (교집합 동작)<br>이 작업으로 Collection에 변화가 있으면 true를 없으면 false를 반환 |
| void clear() | Collection의 모든 객체를 삭제 |
| boolean equals(Object o) | 동일한 Collection인지 비교 |
| int hashCode() | Collection의 hash code를 반환 |
| boolean isEmpty() | Collection이 비어있는지 확인 |
| Iterator iterator() | Collection의 iterator를 얻어서 반환 (상위 Iterable 인터페이스를 상속) |
| int size() | Collection에 저장된 객체의 개수를 반환 |
| Object[] toArray() | Collection에 저장된 객체를 객체배열(Object[])로 반환 |
| Object[] toArray(Object[] a) | 지정된 배열에 Collection의 객체를 저장해서 반환 |

```java
Collection<Number> col1 = new ArrayList<>();
col1.add(1);

Collection<Number> col2 = new HashSet<>();
col1.add(1);

Collection<Number> col3 = new LinkedList<>();
col1.add(1);
```

### List 인터페이스

![Untitled (14)](https://github.com/RIN-1011/RIN-1011/assets/60701386/0f44d89e-160e-45ab-a011-6d65d95d0139)

- 저장 순서가 유지되는 컬렉션을 구현하는 데 사용
- 같은 요소의 중복 저장을 허용
- 배열과 마찬가지로 index로 요소에 접근
- 리스트와 배열의 가장 큰 차이는 리스트는 자료형 크기가 고정이 아닌 데이터 양에 따라 동적으로 늘어났다 줄어들수 있다는 점이다. (가변)
- 요소 사이에 빈공간을 허용하지 않아 삽입/삭제 할때마다 배열 이동이 일어난다.

| 메서드 | 설명 |
| --- | --- |
| void add(int index, Object element)boolean addAll(int index, Collection c) | 지정된 위치(index)에 객체(element) 또는 컬렉션에 포함된 객체들을 추가한다. |
| Object remove(int index) | 지정된 위치(index)에 있는 객체를 삭제하고 삭제된 객체를 반환한다. |
| Object get(int index) | 지정된 위치(index)에 있는 객체를 반환한다. |
| Object set(int index, Object element) | 지정된 위치(index)에 객체(element)를 저장한다. |
| int indexOf(Object o) | 지정된 객체의 위치(index)를 반환한다. (순방향) |
| int lastIndexOf(Object o) | 지정된 객체의 위치(index)를 반환한다. (역방향) |
| List subList(int fromIndex, int toIndex) | 지정된 범위(from ~ to)에 있는 객체를 반환한다. |
| ListIterator listIterator()ListIterator listIterator(int index) | List의 객체에 접근할 수 있는 ListIterator를 반환한다. |
| void sort(Comparator c) | 지정된 비교자(comparator)로 List를 정렬한다. |

---

****ArrayList 클래스****

![Untitled (15)](https://github.com/RIN-1011/RIN-1011/assets/60701386/dc77b103-0896-49b4-a95a-333097d6c712)

- 배열을 이용하여 만든 리스트
- 데이터의 저장순서가 유지되고 중복을 허용
- 데이터량에 따라 공간(capacity)가 자동으로 늘어나거나 줄어들음
- 단방향 포인터 구조로 자료에 대한 순차적인 접근에 강점이 있어 조회가 빠르다.
- 하지만, 삽입 / 삭제가 느리다는 단점이 있다. 단, 순차적으로 추가/삭제 하는 경우에는 가장 빠르다.

```java
List<String> arrayList = new ArrayList<>();

arrayList.add("Hello");
arrayList.add("World");

arrayList.get(0) // "Hello"
```

---

****LinkedList 클래스****

![Untitled (16)](https://github.com/RIN-1011/RIN-1011/assets/60701386/c9ae490a-603d-4fc9-b9a7-b7b931a28545)

- 노드(객체)를 연결하여 리스트처럼 만든 컬렉션 (배열이 아님)
- 데이터의 중간 삽입, 삭제가 빈번할 경우 빠른 성능을 보장한다.
- 하지만 임의의 요소에 대한 접근 성능은 좋지 않다.
- 자바의 LinkedList는 Doubly LinkedList(양방향 포인터 구조)로 이루어져 있다.
- LinkedList는 리스트 용도 이외에도, 스택, 큐, 트리 등의 자료구조의 근간이 된다.

```java
List<String> linkedList = new LinkedList<>();

linkedList.add("Hello");
linkedList.add("World");

linkedList.get(0); // "Hello"
```

---

****Vector 클래스****

![Untitled (17)](https://github.com/RIN-1011/RIN-1011/assets/60701386/8023001b-9a59-4baa-bc73-67d2401b5e48)

- ArrayList의 구형 버전 (내부 구성이 거의 비슷하다)
- ArrayList와의 차이는 모든 메소드가 동기화(synchronized) 되어있어 Thread-Safe 하다는 점이다.
- 구버전 자바와 호환성을 위해 남겨두었으며 잘 쓰이진 않는다.

```java
List<Integer> vector = new Vector<>();

vector.add(10);
vector.add(20);

vector.get(0); // 10
```

---

****Stack 클래스****

![Untitled (18)](https://github.com/RIN-1011/RIN-1011/assets/60701386/5f7dcf2a-826e-4aad-8915-107efc632454)

- 후입선출 LIFO(Last-In-First-Out) 자료구조
- 마지막에 들어온 원소가 처음으로 나간다.
- 들어올 때는 push, 나갈 때는 pop이라는 용어를 사용
- Stack은 Vector를 상속하기 때문에 문제점이 많아 잘 안 쓰인다. (대신 ArrayDeque 사용)

```java
Stack<Integer> stack = new Stack<>();

stack.push(30);
stack.push(50);

stack.pop(); // 50
stack.pop(); // 30
```

### ****Queue 인터페이스****

![Untitled (19)](https://github.com/RIN-1011/RIN-1011/assets/60701386/0ac33ed3-a913-4031-8aaa-e10a45106c30)

- 선입선출 FIFO(First-In-First-Out) 구조
- 처음 들어온 원소가 가장 먼저 나간다.
- 자바에서는 Queue 는 인터페이스이고 필요에 따라 큐 컬렉션을 골라 사용할 수 있다.

| 메서드 | 설명 |
| --- | --- |
| boolean add(Object o) | 지정된 객체를 Queue에 추가.<br>저장공간 부족 시 IllegalStateException 발생 |
| Object remove() | Queue에서 객체를 꺼내 반환.<br>비어있을 경우 NoSuchElementException 발생 |
| Object element() | 삭제없이 요소를 읽어온다.<br>비어있을 경우 NosuchElementException 발생 |
| boolean offer(Object o) | Queue에 객체를 저장 |
| Object poll() | Queue에서 객체를 꺼내서 반환.<br>비어있을 경우 null을 반환 |
| Object peek() | 삭제없이 요소를 읽어온다.<br>비어있을 경우 null을 반환 |

---

****PriorityQueue 클래스****

![Untitled (20)](https://github.com/RIN-1011/RIN-1011/assets/60701386/dcd64749-56be-4a5e-a0a1-f023d714b6e1)

- 우선 순위를 가지는 큐 (우선 순위 큐)
- 일반적인 큐와는 조금 다르게, 원소에 우선 순위(priority)를 부여하여 우선 순위가 높은 순으로 정렬되고 꺼낸다.
- 수행할 작업이 여러 개 있고 시간이 제한되어 있을 때 우선순위가 높은 것부터 수행할 때 쓰인다. (네트워크 제어, 작업 스케줄링)
- 우선순위 큐에 저장할 객체는 필수적으로 Comparable 인터페이스를 구현해야 한다. `compareTo()` 메서드 로직에 따라 자료 객체의 우선순위를 결정하는 식으로 동작되기 때문이다.
- 저장공간으로 배열을 사용하며, 각 요소를 힙(heap) 형태로 저장한다.
- null 저장 불가능

```java
// 우선순위 큐에 저장할 객체는 필수적으로 Comparable를 구현
class Student implements Comparable<Student> {
    String name; // 학생 이름
    int priority; // 우선순위 값

    public Student(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public int compareTo(Student user) {
        // Student의 priority 필드값을 비교하여 우선순위를 결정하여 정렬
        if (this.priority < user.priority) {
            return -1;
        } else if (this.priority == user.priority) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                '}';
    }
}
```

```java
public static void main(String[] args) {

    // 오름차순 우선순위 큐
    Queue<Student> priorityQueue = new PriorityQueue<>();

    priorityQueue.add(new Student("주몽", 5));
    priorityQueue.add(new Student("세종", 9));
    priorityQueue.add(new Student("홍길동", 1));
    priorityQueue.add(new Student("임꺽정", 2));

    // 우선순위 대로 정렬되어 있음
    System.out.println(priorityQueue);
    // [Student{name='홍길동', priority=1}, Student{name='임꺽정', priority=2}, Student{name='주몽', priority=5}, Student{name='세종', priority=9}]

    // 우선순위가 가장 높은 값을 참조
    System.out.println(priorityQueue.peek()); // Student{name='홍길동', priority=1}

    // 차례대로 꺼내기
    System.out.println(priorityQueue.poll()); // Student{name='홍길동', priority=1}
    System.out.println(priorityQueue.poll()); // Student{name='임꺽정', priority=2}
    System.out.println(priorityQueue.poll()); // Student{name='주몽', priority=5}
    System.out.println(priorityQueue.poll()); // Student{name='세종', priority=9}
}
```

### ****Deque 인터페이스****

![Untitled (21)](https://github.com/RIN-1011/RIN-1011/assets/60701386/f7eb03be-4c9e-4e35-b792-401493ce372b)

- Deque(Double-Ended Queue)는 양쪽으로 넣고 빼는 것이 가능한 큐를 말한다.
- 스택과 큐를 하나로 합쳐놓은 것과 같으며 스택으로 사용할 수도 있고, 큐로 사용할 수도 있다.
- Deque의 조상은 Queue이며, 구현체로 ArrayDeque와 LinkedList 등이 있다.

---

****ArrayDeque 클래스****

![Untitled (22)](https://github.com/RIN-1011/RIN-1011/assets/60701386/033ae926-583f-472a-9097-046fd712e964)

- 스택으로 사용할 때 Stack 클래스보다 빠르며, 대기열로 사용할 때는 LinkedList보다 빠르다.
- 사이즈에 제한이 없다.
- null 요소는 저장되지 않는다.

| Deque | Queue | Stack |
| --- | --- | --- |
| offerLast() | offer() | push() |
| pollLast() | - | pop() |
| pollFirst() | poll() | - |
| peekFirst() | peek() | - |
| peekLast() | - | peek() |

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.offerLast(100); // [100]
deque.offerFirst(10); // [10, 100]
deque.offerFirst(20); // [20, 10, 100]
deque.offerLast(30); // [20, 10, 100, 30]

deque.pollFirst(); // 20 <- [10, 100, 30]
deque.pollLast(); // [10, 100] -> 30
deque.pollFirst(); // 10 <- [100]
deque.pollLast(); // [] -> 100
```

---

****LinkedList 클래스****

![Untitled (23)](https://github.com/RIN-1011/RIN-1011/assets/60701386/d9f8a3d1-24e7-4b84-bf69-2340dadc79b6)

- LinkedList는 List 인터페이스와 Queue 인터페이스를 동시에 상속받고 있기 때문에, 스택 / 큐 로서도 응용이 가능하다.
- 실제로 LinkedList 클래스에 큐 동작과 관련된 메서드를 지원한다. (push, pop, poll, peek, offer ..등)

```java
Queue<String> linkedList = new LinkedList<>(); // Queue 타입으로 받음

linkedList.offer("Hello");
linkedList.offer("World");
linkedList.offer("Power");

linkedList.poll(); // "Hello" - 선입선출

System.out.println(linkedList); // [World, Power]
```

### Set 인터페이스

![Untitled (24)](https://github.com/RIN-1011/RIN-1011/assets/60701386/b95b831d-c3ca-4009-95a5-86e15e116720)

- 데이터의 중복을 허용하지 않고 순서를 유지하지 않는 데이터의 집합 리스트
- 순서 자체가 없으므로 인덱스로 객체를 검색해서 가져오는 `get(index)` 메서드도 존재하지 않는다.
- 중복 저장이 불가능하기 때문에 심지어 null값도 하나만 저장할 수 있다.

| 메서드 | 설명 |
| --- | --- |
| boolean add(E e) | 주어진 객체를 저장 후 성공적이면 true를 중복 객체면 false를 리턴한다. |
| boolean contains(Object o) | 주어진 객체가 저장되어있는지 여부를 리턴한다. |
| Iterator<E> iterator() | 저장된 객체를 한번씩 가져오는 반복자를 리턴한다. |
| isEmpty() | 컬렉션이 비어있는지 조사한다. |
| int Size() | 저장되어 있는 전체 객체수를 리턴한다. |
| void clear() | 저장된 모든 객체를 삭제한다. |
| boolean remove(Object o) | 주어진 객체를 삭제한다. |

---

****HashSet 클래스****

![Untitled (25)](https://github.com/RIN-1011/RIN-1011/assets/60701386/72c04384-4fc9-4c60-b331-36fdd0dd8be2)

- 배열과 연결 노드를 결합한 자료구조 형태
- 가장 빠른 임의 검색 접근 속도를 가진다.
- 추가, 삭제, 검색, 접근성이 모두 뛰어나다.
- 대신 순서를 전혀 예측할 수 없다.

```java
Set<Integer> hashSet = new HashSet<>();

hashSet.add(10);
hashSet.add(20);
hashSet.add(30);
hashSet.add(10); // 중복된 요소 추가

hashSet.size(); // 3 - 중복된건 카운트 X

hashSet.toString(); // [20, 10, 30] - 자료 순서가 뒤죽박죽
```

---

****LinkedHashSet 클래스****

- 순서를 가지는 Set 자료
- 추가된 순서 또는 가장 최근에 접근한 순서대로 접근 가능
- 만일 중복을 제거하는 동시에 저장한 순서를 유지하고 싶다면, HashSet 대신 LinkedHashSet을 사용하면 된다.

```java
Set<Integer> linkedHashSet = new LinkedHashSet<>();

linkedHashSet.add(10);
linkedHashSet.add(20);
linkedHashSet.add(30);
linkedHashSet.add(10); // 중복된 수 추가

linkedHashSet.size(); // 3 - 중복된건 카운트 X

linkedHashSet.toString(); // [10, 20, 30] - 대신 자료가 들어온 순서대로 출력
```

---

****TreeSet 클래스****

![Untitled (26)](https://github.com/RIN-1011/RIN-1011/assets/60701386/ad1bd37d-c7c5-4f1e-8f5d-e34bd7eb8d9c)

- 이진 검색 트리(binary search tree) 자료구조의 형태로 데이터를 저장
- 중복을 허용하지 않고, 순서를 가지지 않는다.
- 대신 데이터를 정렬하여 저장하고 있다는 특징이다.
- 정렬, 검색, 범위 검색에 높은 성능을 뽐낸다.

```java
Set<Integer> treeSet = new TreeSet<>();

treeSet.add(7);
treeSet.add(4);
treeSet.add(9);
treeSet.add(1);
treeSet.add(5);

treeSet.toString(); // [1, 4, 5, 7, 9] - 자료가 알아서 정렬됨
```

---

****EnumSet 추상 클래스****

- Enum 클래스와 함께 동작하는 Set 컬렉션이다.
- 중복 되지 않은 상수 그룹을 나타내는데 사용된다.
- 산술 비트 연산을 사용하여 구현되므로 HashSet 보다 훨씬 빠르며, 적은 메모리를 사용한다.
- 단, enum 타입의 요소값만 저장할 수 있고, 모든 요소들은 동인한 enum 객체에 소속되어야 한다.
- EnumSet은 추상 클래스고 이를 상속한 RegularEnumSet 혹은 JumboEnumSet 객체를 사용하게 된다.

```java
enum Color {
    RED, YELLOW, GREEN, BLUE, BLACK, WHITE
}

public class Client {
    public static void main(String[] args) {
        // 정적 팩토리 메서드를 통해 RegularEnumSet 혹은 JumboEnumSet 을 반환
        // 만일 enum 상수 원소 갯수가 64개 이하면 RegularEnumSet, 이상이면 JumboEnumSet 객체를 반환
        EnumSet<Color> enumSet = EnumSet.allOf(Color.class);

        for (Color color : enumSet) {
            System.out.println(color);
        }

        enumSet.size(); // 6

        enumSet.toString(); // [RED, YELLOW, GREEN, BLUE, BLACK, WHITE]
    }
}
```

### Map 인터페이스

![Untitled (27)](https://github.com/RIN-1011/RIN-1011/assets/60701386/31091307-db3e-48f1-9d93-8d8ec7d9eb41)

- 키(Key)와 값(value)의 쌍으로 연관지어 이루어진 데이터의 집합
- 값(value)은 중복되서 저장될수 있지만, 키(key)는 해당 Map에서 고유해야만 한다.
- 만일 기존에 저장된 데이터와 중복된 키와 값을 저장하면 기존의 값은 없어지고 마지막에 저장된 값이 남게 된다.
- 저장 순서가 유지 되지 않는다.

| 추상 메서드 | 설명 |
| --- | --- |
| void clear() | Map의 모든 객체를 삭제 |
| boolean containsKey(Object key) | 지정된 key객체와 일치하는 객체가 있는지 확인 |
| boolean containsValue(Object value) | 지정된 value객체와 일치하는 객체가 있는지 확인 |
| Set entrySet() | Map에 저장된 key-value쌍을 Map.Entry타입의 객체로 저장한 Set을 반환 |
| boolean equals(Object o) | 동일한 Map인지 비교 |
| Object get(Object key) | 지정한 key객체에 대응하는 value객체를 반환 |
| int hashCode() | 해시코드를 반환 |
| boolean isEmpty() | Map이 비어있는지 확인 |
| Set keySet() | Map에 저장된 모든 key객체를 반환 |
| Object put(Object key, Object value) | Map에 key객체와 value객체를 연결(mapping)하여 저장 |
| void putAll(Map t) | 지정된 Map의 모든 key-value쌍을 추가 |
| Object remove(Object key) | 지정한 key객체와 일치하는 key-value객체를 삭제 |
| int size() | Map에 저장된 key-value쌍의 개수를 반환 |
| Collection values() | Map에 저장된 모든 value객체를 반환 |

### ****Map.Entry 인터페이스****

- Map.Entry 인터페이스는 Map 인터페이스 안에 있는 내부 인터페이스이다.
- Map 에 저장되는 key - value 쌍의 Node 내부 클래스가 이를 구현하고 있다.
- Map 자료구조를 보다 객체지향적인 설계를 하도록 유도하기 위한 것이다.

| 메서드 | 설명 |
| --- | --- |
| boolean equals(Object o) | 동일한 Entry 인지 비교 |
| Object getKey( ) | Entry 의 key 객체를 반환 |
| Object getValue( ) | Entry 의 value 객체를 반환 |
| int hashCode( ) | Entry 의 해시코드 반환 |
| Object setValue(Object value) | Entry 의 value 객체를 지정된 객체로 바꾼다. |

```java
Map<String, Integer> map = new HashMap<>();
map.put("a", 1);
map.put("b", 2);
map.put("c", 3);

// Map.Entry 인터페이스를 구현하고 있는 Key-Value 쌍을 가지고 있는 HashMap의 Node 객체들의 Set 집합을 반환
Set<Map.Entry<String, Integer>> entry = map.entrySet();

System.out.println(entry); // [1=a, 2=b, 3=c]

// Set을 순회하면서 Map.Entry를 구현한 Node 객체에서 key와 value를 얻어 출력
for (Map.Entry<String, Integer> e : entry) {
    System.out.printf("{ %s : %d }\n", e.getKey(), e.getValue());
}
// { a : 1 }
// { b : 2 }
// { c : 3 }
```

---

****HashMap 클래스****

![Untitled (28)](https://github.com/RIN-1011/RIN-1011/assets/60701386/7c9b4f69-58be-46fb-971a-4487d3c13234)

- Hashtable을 보완한 컬렉션
- 배열과 연결이 결합된 Hashing형태로, 키(key)와 값(value)을 묶어 하나의 데이터로 저장한다.
- 중복을 허용하지 않고 순서를 보장하지 않는다.
- 키와 값으로 null이 허용된다.
- 추가, 삭제, 검색, 접근성이 모두 뛰어나다.
- HashMap은 비동기로 작동하기 때문에 멀티 쓰레드 환경에서는 어울리지 않는다. (대신 ConcurrentHashMap 사용)

```java
Map<String, String> hashMap = new HashMap<>();

hashMap.put("love", "사랑");
hashMap.put("apple", "사과");
hashMap.put("baby", "아기");

hashMap.get("apple"); // "사과"

// hashmap의 key값들을 set 집합으로 반환하고 순회
for(String key : hashMap.keySet()) {
    System.out.println(key + " => " + hashMap.get(key));
}
/*
love => 사랑
apple => 사과
baby => 아기
*/
```

---

****LinkedHashMap 클래스****

![Untitled (29)](https://github.com/RIN-1011/RIN-1011/assets/60701386/411cc66d-f02d-4c54-86f0-85460879fa25)

- HashMap을 상속하기 때문에 흡사하지만, Entry들이 연결 리스트를 구성하여 데이터의 순서를 보장한다.
- 일반적으로 Map 자료구조는 순서를 가지지 않지만, LinkedHashMap은 들어온 순서대로 순서를 가진다.

```java
Map<Integer, String> hashMap = new HashMap<>();

hashMap.put(10000000, "one");
hashMap.put(20000000, "two");
hashMap.put(30000000, "tree");
hashMap.put(40000000, "four");
hashMap.put(50000000, "five");

for(Integer key : hashMap.keySet()) {
    System.out.println(key + " => " + hashMap.get(key)); // HashMap 정렬 안됨
}

// ----------------------------------------------

Map<Integer, String> linkedHashMap = new LinkedHashMap<>();

linkedHashMap.put(10000000, "one");
linkedHashMap.put(20000000, "two");
linkedHashMap.put(30000000, "tree");
linkedHashMap.put(40000000, "four");
linkedHashMap.put(50000000, "five");

for(Integer key : linkedHashMap.keySet()) {
    System.out.println(key + " => " + linkedHashMap.get(key)); // LinkedHashMap 정렬됨
}
```

---

****TreeMap 클래스****

![Untitled (30)](https://github.com/RIN-1011/RIN-1011/assets/60701386/2a354adc-3b81-48cb-a6f9-79ceaf89177c)

- 이진 검색 트리의 형태로 키와 값의 쌍으로 이루어진 데이터를 저장 (TreeSet 과 같은 원리)
- TreeMap은 SortedMap 인터페이스를 구현하고 있어 Key 값을 기준으로 정렬되는 특징을 가지고 있다.
- 정렬된 순서로 키/값 쌍을 저장하므로 빠른 검색(특히 범위 검색)이 가능하다.
- 단, 키와 값을 저장하는 동시에 정렬을 행하기 때문에 저장시간이 다소 오래 걸린다.
- 정렬되는 순서는 숫자 → 알파벳 대문자 → 알파벳 소문자 → 한글 순이다.

```java
Map<Integer, String> treeMap = new TreeMap<>();

treeMap.put(1, "Toby");
treeMap.put(2, "Ruth");
treeMap.put(3, "jennifer");
treeMap.put(4, "Mark");
treeMap.put(5, "Dan");
treeMap.put(6, "Gail");

for(Integer key : treeMap.keySet()) {
    System.out.println(key + " => " + treeMap.get(key));
}
/*
1 => Toby
2 => Ruth
3 => jennifer
4 => Mark
5 => Dan
6 => Gail
*/
```

---

****HashTable 클래스****

![Untitled (31)](https://github.com/RIN-1011/RIN-1011/assets/60701386/a18bbf2f-2ce3-4d81-910f-43399c481632)

- 자바 초기 버전에 나온 레거시 클래스
- Key를 특정 해시 함수를 통해 해싱한 후 나온 결과를 배열의 인덱스로 사용하여 Value를 찾는 방식으로 동작된다.
- HashMap 보다는 느리지만 동기화가 기본 지원된다.
- 키와 값으로 null이 허용 X

```java
Map<String, Integer> hashtable = new HashMap<>();

hashtable.put("연필", 200);
hashtable.put("볼펜", 3000);
hashtable.put("샤프", 300);
hashtable.put("필통", 15000);

for (String key : hashtable.keySet()) {
    System.out.println(key + " => " + hashtable.get(key));
}
/*
필통 => 15000
볼펜 => 3000
샤프 => 300
연필 => 200
*/
```

## ****컬렉션 프레임워크 선택****

![Untitled (32)](https://github.com/RIN-1011/RIN-1011/assets/60701386/0e21b1f2-4840-41fd-b227-25dbc7ad178d)

- **ArrayList**
    - 리스트 자료구조를 사용한다면 기본 선택
    - 임의의 요소에 대한 접근성이 뛰어남
    - 순차적인 추가/삭제 제일 빠름
    - 요소의 추가/삭제 불리
- **LinkedList**
    - 요소의 추가/삭제 유리
    - 임의의 요소에 대한 접근성이 좋지 않음
- **HashMap / HashSet**
    - 해싱을 이용해 임의의 요소에 대한 추가/삭제/검색/접근성 모두 뛰어남
    - 검색에 최고성능 ( get 메서드의 성능이 O(1) )
- **TreeMap / TreeSet**
    - 요소 정렬이 필요할때
    - 검색(특히 범위검색)에 적합
    - 그래도 검색 성능은 HashMap보다 떨어짐
- **LinkedHashMap / LinkedHashSet**
    - HashMap과 HashSet에 저장 순서 유지 기능을 추가
- **Queue**
    - 스택(LIFO) / 큐(FIFO) 자료구조가 필요하면 ArrayDeque 사용
- **Stack, Hashtable**
    - 가급적 사용 X (deprecated)
    - deprecated : 중요도 떨어져 더 이상 사용되지 않고 앞으로는 사라지게 된다는 의미

## 참고

https://www.tcpschool.com/java/java_collectionFramework_concept

[https://inpa.tistory.com/entry/JCF-🧱-Collections-Framework-종류-총정리](https://inpa.tistory.com/entry/JCF-%F0%9F%A7%B1-Collections-Framework-%EC%A2%85%EB%A5%98-%EC%B4%9D%EC%A0%95%EB%A6%AC)
