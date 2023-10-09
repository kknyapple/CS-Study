# Spring Container

## Spring Container란

자바 객체의 생명 주기를 관리하며, 생성된 자바 객체들에게 추가적인 기능을 제공한다.

스프링에서 자바 객체를 빈(Bean)이라고 한다. 즉, 스프링 컨테이너는 내부에 존재하는 빈의 생성, 관리 제거와 같은 생명주기를 관리하며, 생성된 빈에게 추가적인 기능을 제공한다.

스프링 컨테이너는 XML, 어노테이션 기반의 자바 설정 클래스로 만들 수 있다.

## 스프링 컨테이너의 종류

![image](https://github.com/Ahrang777/CS-Study/assets/100702397/08a565d6-7189-4927-aed2-312bfca159c4)


### 1. Beanfactory

스프링 컨테이너의 최상위 인터페이스로, 빈을 등록, 생성, 조회하는 등의 빈을 관리하는 역할을 하며, getBean() 메서드를 통해 빈을 인스턴스화 할 수 있다.

`@Bean` 어노테이션이 붙은 메서드의 이름을 스프링 빈의 이름으로 사용하여 빈을 등록 한다.

### 2. ApplicationContext

BeanFactory의 기능을 상속 받아 기능을 확장한 것으로, Bean을 관리해주는 역할을 한다.

BeanFactory는 처음으로 getBean() 메소드가 호출된 시점에서 해당 빈을 생성하지만, ApplicationContext는 Context 초기화 시점에 모든 싱글톤 빈을 미리 로드하기 때문에 애플리케이션 가동 후에는 빈을 지연 없이 받을 수 있다.

또한 국제화가 지원되는 텍스트 메시지 관리, 이미지 같은 파일 자원을 로드, 리스너로 등록된 빈에게 이벤트 발생 알림과 같은 부가적인 기능도 제공한다.

따라서 빈을 지연 없이 얻을 수 있다는 점과 부가 기능과 같은 장점으로 실제 개발에서 주로 사용된다.


## 스프링 컨테이너 생성과 등록

### 자바 어노테이션 기반 스프링 컨테이너 등록

```java
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
```

스프링 컨테이너는 @Configuration 어노테이션이 붙은 클래스를 설정 정보로 사용한다.

이 클래스 내부에 @Bean 어노테이션이 적힌 메서드를 모두 호출하여 얻은 객체를 스프링 컨테이너에 등록하게 된다.

이때 스프링 컨테이너에 등록된 객체를 Bean이라 한다.


### 자바 어노테이션 기반 스프링 컨테이너 생성

```java
public class MemberApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class)
    }
}
```

스프링 Bean은 getBean() 메서드를 이용하여 얻을 수 있다.

---

### 참고

https://ittrue.tistory.com/220

https://steady-coding.tistory.com/459
