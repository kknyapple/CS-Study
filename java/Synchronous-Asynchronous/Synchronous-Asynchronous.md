# 동기화, 비동기화

## 동기화

![Untitled (33)](https://github.com/RIN-1011/RIN-1011/assets/60701386/9319046b-cbb2-4277-b40a-d033278cdadf)

- 요청과 그 결과가 동시에 일어난다는 뜻
- 어떤 객체 또는 함수 내부에서 다른 함수를 호출했을 때 이 함수의 결과를 호출한 쪽에서 처리
- 한 자원에 동시에 접근하는 것 제한
- 순차적으로 진행
- 다음에 실행될 명령은 현재 실행 중인 명령 종료 시까지 대기 (대기시간 버퍼링 발생)
- Java에서 synchronized 키워드 사용
- 코드 예시)
    
```java
public class Synchro {
	public static void main(String[] args) {
		
		method1();
		method2();
		method3();
		
	}
	
	public static void method1() {
		System.out.println("method1");
	}
	public static void method2() {
		System.out.println("method2");
	}
	public static void method3() {
		System.out.println("method3");
	}
	
}

[실행결과]
method1
method2
method3
```
    

### synchronized

- 자바에서 멀티 스레드 접근 제한 키워드
- 동기화가 필요한 메소드나 코드블럭앞에 사용하여 동기화 할 수 있음
- 단, 메소드 단위로 지정할 경우 메소드 전체에 lock이 걸리기 때문에 가능하면 블록 활용 (임계 영역은 작을 수록 좋음)
- synchronized로 지정된 임계 영역은 한 스레드가 이 영역에 접근하여 사용할 때 lock이 걸림으로써 다른 스레드가 접근할 수 없게 됨
- 이후 해당 스레드가 이 임계 영역의 코드를 다 실행 후 벗어나게 되면 unlock 상태가 되어 그때서야 대기하고 있던 다른 스레드가 이 임계 영역에 접근하여 다시 lock을 걸고 사용할 수 있게 됨

**1) 메소드에 synchronized 설정하기**

메소드 이름 앞에 synchronized 키워드를 사용하면 해당 메소드 전체를 임계 영역으로 설정할 수 있다.

```java
synchronized void increase() {
	count++;
	System.out.println(count);
}
```

**2) 코드블럭에 synchronized 설정하기**

동기화를 많이 사용하게 되면 효율이 떨어지게 되므로 꼭 필요한 부분에만 블럭을 지정하여 임계 영역으로 설정할 수 있다. 예제와 같이 synchronized(this)로 지정하게 되면 참조변수(this) 객체의 lock을 사용하게 된다.

```java
void increase() {
synchronized(this) {
		count++;
	}
	System.out.println(count);
}
```

## 비동기화

![Untitled (34)](https://github.com/RIN-1011/RIN-1011/assets/60701386/2c0cdaea-a625-4bd2-a3d1-1d4519028f28)

- 요청과 그 결과가 동시에 일어나지 않는다는 뜻
- 어떤 객체 또는 함수 내부에서 다른 함수를 호출했을 때 이 함수의 결과를 호출한 쪽에서 처리하지 않음
- 현재 실행 중인 명령이 종료되지 않아도 다음 명령 실행 가능
- Callback 함수를 통해 결과 확인
- ex. Ajax, Thread
- 코드 예시)
    
    ```java
    public class Asynchro {
    	public static void main(String[] args) {
    	
    	
    		Thread t = new Thread(()->{
    			method1();
    		});
    		Thread t2 = new Thread(()->{
    			method2();
    		});
    		Thread t3 = new Thread(()->{
    			method3();
    		});
    		
    		
    		t.start();
    		t2.start();
    		t3.start();
    		
    	}
    	
    	public static void method1() {
    		System.out.println("method1");
    	}
    	public static void method2() {
    		System.out.println("method2");
    	}
    	public static void method3() {
    		System.out.println("method3");
    	}
    }
    
    [실행결과]
    method1
    method3
    method2
    ```
    
    - method1, method2, method3의 순서로 실행되지 않으며 실행할 때마다 순서가 계속 바뀌게 됨
    - 즉, 자바의 Multi Thread 처리는 비동기식으로 처리되며, 처리 순서는 보장되지 않음

## 장단점

|  | 동기 | 비동기 |
| --- | --- | --- |
| 장점 | 설계가 매우 간단하고 직관적 | 결과가 주어지는데 시간이 걸리더라도 그 시간 동안 다른 작업을 할 수 있으므로 자원을 효율적으로 사용할 수 있음 |
| 단점 | 결과가 주어질 때까지 아무것도 못하고 대기해야 함 | 설계가 복잡함 |

## 참고

[https://kwangkyun-world.tistory.com/entry/동기화Synchronized-vs-비동기화Asynchronized-블로킹blocking과-논블로킹non-blocking](https://kwangkyun-world.tistory.com/entry/%EB%8F%99%EA%B8%B0%ED%99%94Synchronized-vs-%EB%B9%84%EB%8F%99%EA%B8%B0%ED%99%94Asynchronized-%EB%B8%94%EB%A1%9C%ED%82%B9blocking%EA%B3%BC-%EB%85%BC%EB%B8%94%EB%A1%9C%ED%82%B9non-blocking)

https://kadosholy.tistory.com/123

https://webheck.tistory.com/entry/Java%EB%8F%99%EA%B8%B0%EC%99%80-%EB%B9%84%EB%8F%99%EA%B8%B0-%EB%B0%A9%EC%8B%9DAsynchronous-processing-model

https://sudo-minz.tistory.com/21
