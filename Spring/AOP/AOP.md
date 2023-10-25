## AOP

### AOP(Aspect-Oriented Programming)

- 핵심 로직과 부가 기능을 분리하여 애플리케이션 전체에 걸쳐 사용되는 부가 기능을 모듈화하여 재사용할 수 있도록 지원하는 것이다.

### AOP 장점

- 애플리케이션 전체에 흩어진 공통 기능이 하나의 장소에서 관리되어 유지보수가 좋다.
- 핵심 로직과 부가 기능의 명확한 분리로, 핵심 로직은 자신의 목적 외에 사항들에는 신경 쓰지 않는다.

## AOP 적용 방식

### 컴파일 시점

- .java 파일을 컴파일러를 통해 .class를 만드는 시점에 부가 기능 로직을 추가하는 방식이다.
- 모든 지점에 적용 가능하다.
- AspectJ가 제공하는 특별한 컴파일러를 사용해야 하기 때문에 특별한 컴파일러가 필요한 점과 복잡하다는 단점이 있다.

### 클래스 로딩 시점

- .class 파일을 JVM 내부의 클래스 로더에 보관하기 전에 조작하여 부가 기능 로직을 추가하는 방식이다.
- 모든 지점에 적용 가능하다.
- 특별한 옵션과 클래스 로더 조작기를 지정해야하므로 운영하기 어렵다.

### 런타임 시점

- 스프링이 사용하는 방식이다.
- 컴파일이 끝나고 클래스 로더에 이미 다 올라가 자바가 실행된 다음에 동작하는 런타임 방식이다.
- 프록시는 메서드 오버라이딩 개념으로 동작하기 때문에 메서드에만 적용 가능하다. 스프링 빈에만 AOP를 적용 가능한다.

## Spring AOP

### Spring AOP

- Spring AOP는 런타임 시점에 적용하는 방식을 사용한다.
- 컴파일 시점과 클래스 로딩 시점에 적용하려면 별도의 컴파일러와 클래스로더 조작기를 써야 하는데, 이것을 정하고 사용 및 유지하는 과정이 어렵고 복잡하기 때문이다.

### AOP 용어 및 개념

![image](https://github.com/kknyapple/CS-Study/assets/72698829/aa6c4be6-fc1d-4f68-b12c-6afb218a33d2)

- Aspect
    - Advice + pointcut을 모듈화 한 것이다.
    - @Aspect와 같은 의미이다.
- Join Point
    - 추상적인 개념으로 adivce가 적용될 수 있는 모든 위치를 말한다.
    - 스프링 AOP는 프록시 방식을 사용하므로 조인 포인트는 항상 메서드 실행 지점이다.
- Advice
    - 실질적인 부가 기능 로직을 정의하는 곳이다.
    - 특정 조인 포인트에서 Aspect에 의해 취해지는 조치이다.
- Pointcut
    - 조인 포인트 중에서 advice가 적용될 위치를 선별하는 기능이다.
    - 스프링 AOP는 프록시 기반이기 때문에 조인 포인트가 메서드 실행 시점 뿐이 없고 포인트 컷도 메서드 실행 시점만 가능
- Target
    - advice의 대상이 되는 객체이다.
    - Pointcut으로 설정한다.
- Advisor
    - 스프링 AOP에서만 사용되는 용어로 adivce+  pointcut의 한 쌍이다.

### 참고

- [https://velog.io/@backtony/Spring-AOP-총정리](https://velog.io/@backtony/Spring-AOP-%EC%B4%9D%EC%A0%95%EB%A6%AC)
- [https://velog.io/@kai6666/Spring-Spring-AOP-개념](https://velog.io/@kai6666/Spring-Spring-AOP-%EA%B0%9C%EB%85%90)
- https://backtony.github.io/spring/2021-09-12-spring-aop-0/
