# DI(Dependency Injection, 의존성 주입)

## 개념

- 스프링이 다른 프레임워크와 차별화되어 제공하는 의존 관계 주입 기능으로, 객체를 직접 생성하는 게 아니라 외부에서 생성한 후 주입 시켜주는 방식이다.
- 각 클래스 간 의존관계를 Bean 설정 정보를 바탕으로 컨테이너가 자동으로 연결해준다. 따라서 개발자는 단지 Bean 설정 파일에서 의존관계가 필요하다는 정보를 추가하면 된다.
    - IoC ≠ DI : IoC는 프로그램 제어권을 역전시키는 개념이고, DI는 해당 개념을 구현하기 위해 사용하는 디자인 패턴 중 하나

![Untitled](https://github.com/RIN-1011/RIN-1011/assets/60701386/6bf9d264-998d-4ac2-b843-603927b6a02e)

- 첫번째 방법은 A객체가 B와 C객체를 New 생성자를 통해서 직접 생성하는 방법이고, 두번째 방법은 외부에서 생성 된 객체를 setter()를 통해 사용하는 방법이다.
- 이러한 두번째 방식이 의존성 주입의 예시인데, A 객체에서 B, C객체를 사용(의존)할 때 A 객체에서 직접 생성 하는 것이 아니라 외부(IoC컨테이너)에서 생성된 B, C객체를 조립(주입)시켜 setter 혹은 생성자를 통해 사용하는 방식이다.

## 필요성

- 예를 들어 연필이라는 상품과 1개의 연필을 판매하는 Store 클래스가 있다고 하자.

```java
public class Store {

    private Pencil pencil;

    public Store() {
        this.pencil = new Pencil();
    }

}
```

- 위와 같은 예시 클래스는 크게 다음과 같은 문제점을 가지고 있다.
1. 두 클래스가 강하게 결합되어 있음
    
    : 두 클래스가 강하게 결합되어 있어서 만약 다른 상품을 판매하고자 한다면 Store 클래스의 생성자에 변경이 필요하다. → 즉, 유연성이 떨어진다.
    
2. 객체들 간의 관계가 아니라 클래스 간의 관계가 맺어짐
    
    : 올바른 객체지향적 설계라면 객체들 간에 관계가 맺어져야 한다. 객체들 간에 관계가 맺어졌다면 다른 객체의 구체 클래스(Pencil인지 Food 인지 등)를 전혀 알지 못하더라도, 인터페이스의 타입(Product)으로 사용할 수 있다.
    

⇒ 결국 위와 같은 문제점이 발생하는 근본적인 이유는 Store에서 불필요하게 어떤 제품을 판매할 지에 대한 관심이 분리되지 않았기 때문이다. Spring에서는 DI를 적용하여 이러한 문제를 해결하고자 하였다.

## 장점

1. 의존성이 줄어든다.
    1. DI로 구현하게 되었을 때, 주입받는 대상이 변하더라도 그 구현 자체를 수정할 일이 없거나 줄어들게 된다.
2. 재사용성이 높은 코드가 된다.
3. 테스트하기 좋은 코드가 된다.
4. 가독성이 높아진다.

## 의존성 주입 방법

1. ****생성자 주입 (Constructor Injection)**** : 생성자를 통해 의존 관계 주입
    
    ```java
    @Service
    public class UserService {
    
        private UserRepository userRepository;
        private MemberService memberService;
    
        @Autowired
        public UserService(UserRepository userRepository, MemberService memberService) {
            this.userRepository = userRepository;
            this.memberService = memberService;
        }
        
    }
    ```
    
    - 생성자 주입은 생성자의 호출 시점에 1회 호출 되는 것이 보장된다. 그렇기 때문에 주입받은 객체가 변하지 않거나, 반드시 객체의 주입이 필요한 경우에 강제하기 위해 사용할 수 있다.
2. ****수정자 주입(Setter 주입, Setter Injection)**** : 필드 값을 변경하는 Setter를 통해서 의존 관계를 주입하는 방법. Setter 주입은 생성자 주입과 다르게 주입받는 객체가 변경될 가능성이 있는 경우에 사용. (실제로 변경이 필요한 경우는 극히 드물다.)
    
    ```java
    @Service
    public class UserService {
    
        private UserRepository userRepository;
        private MemberService memberService;
    
        @Autowired
        public void setUserRepository(UserRepository userRepository) {
            this.userRepository = userRepository;
        }
    
        @Autowired
        public void setMemberService(MemberService memberService) {
            this.memberService = memberService;
        }
    }
    ```
    
3. ****필드 주입 (Field Injection)**** : 필드에 바로 의존 관계를 주입하는 방법
    
    ```java
    @Service
    public class UserService {
    
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private MemberService memberService;
    
    }
    ```
    
    - 필드 주입은 외부에서 접근이 불가능하다는 단점이 존재하는데, 테스트 코드의 중요성이 부각됨에 따라 필드의 객체를 수정할 수 없는 필드 주입은 거의 사용되지 않게 되었다. 또한 필드 주입은 반드시 DI 프레임워크가 존재해야 하므로 반드시 사용을 지양해야 한다.

## 참고

[https://www.inflearn.com/course/lecture?courseSlug=스프링-입문-스프링부트&unitId=49586](https://www.inflearn.com/course/lecture?courseSlug=%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8&unitId=49586)

[https://velog.io/@ohzzi/Spring-DIIoC-IoC-DI-그게-뭔데](https://velog.io/@ohzzi/Spring-DIIoC-IoC-DI-%EA%B7%B8%EA%B2%8C-%EB%AD%94%EB%8D%B0)

https://velog.io/@gillog/Spring-DIDependency-Injection

https://mangkyu.tistory.com/150

https://mangkyu.tistory.com/125

https://marrrang.tistory.com/58
