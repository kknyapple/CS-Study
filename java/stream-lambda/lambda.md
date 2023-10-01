# Lambda

## 람다식이란

JAVA8부터 지원하는 함수적 프로그래밍 기법으로, **익명 함수**를 생성하는 식

### 람다식 사용 조건

- 람다식은 함수적 인터페이스인 경우에만 사용 가능함

> 함수적 인터페이스란?  
> 인터페이스가 단 한 개의 추상 메소드를 정의하고 있는 인터페이스.
> `@FunctionalInterface` 어노테이션을 이용하면 컴파일러가 함수형 인터페이스인지 체크해줌.

### 람다식 표현 방법

`(매개변수) -> {}` 구조

즉, 접근 지정자와 반환형 모두 생략되는 구조임

```java

// 함수형 인터페이스
public interface Calculator {
    public int cal(int num1, int num2)
}

// 기본 사용법 (매개변수 타입) -> {}
public static void main(String[] args) {
    Calculator cal = (int num1, int num2) -> {return num1+num2;}
    System.out.println(cal.cal(1,2));
}

// 매개변수 타입 생략 (매개변수) -> {}
public static void main(String[] args) {
    Calculator cal = (num1, num2) -> {return num1+num2;}
    System.out.println(cal.cal(1,2));
}

// 매개변수가 없는 경우 () -> {}
public static void main (String[] args){
	Calculator cal = () -> {System.out.println("매개변수가 없는 경우");}
	cal.cal();
}

// 중괄호 생략: () -> ;
// 실행할 문장이 1개일 때만 중괄호 생략 가능 (이 경우 return도 생략해야함)
public static void main (String[] args) {
    Calculator cal = (num1, num2) -> num1+num2;
    System.out.println(cal.cal(1,2));
}

// 소괄호 생략: 매개변수 -> ;
// 매개변수가 하나이고, 실행할 문장도 하나라면 (), {} 모두 생략 가능
public static void main (String[] args){
	Calculator cal = num1 -> num1-1;
	System.out.println(cal.cal(1));
}
```

### 장점

- **코드가 간결해짐**
  - 불필요한 반복문의 삭제가 가능하며 복잡한 식을 단순하게 표현할 수 있음
- **지연 연산 수행**
  - 람다는 지연 연산을 수행함으로써 불필요한 연산을 최소화 할 수 있음
- **병렬 처리 가능**
  - 멀티 스레드를 활용하여 병럴 처리 가능

---

### 참고

https://velog.io/@tsi0521/java-Lambda

https://khj93.tistory.com/entry/JAVA-%EB%9E%8C%EB%8B%A4%EC%8B%9DRambda%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B4%EA%B3%A0-%EC%82%AC%EC%9A%A9%EB%B2%95
