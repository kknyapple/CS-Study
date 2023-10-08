# Spring Bean이란?

스프링 빈은 스프링 컨테이너(IoC 컨테이너)에서 관리하는 자바 객체입니다. 

Spring이 관리하는 객체가 Bean이기 때문에 아래처럼 단순히 new연산자를 통해 만든 객체는 Bean이 아닙니다. 

```java
BeanTest bean = new BeanTest();
```



```java
ApplicationContext applicationContext;
public void Bean(){
	BeanTest bean = applicationContext.getBean(BeanTest.class);
}
```

여기서 선언된 bean은 Bean객체 입니다. BeanFactory를 상속한 스프링 컨테이너의 종류인 ApplicatoinContext에서 관리를 하기 때문입니다. 



# 스프링 컨테이너 

스프링 컨테이너는 스프링 빈을 생성하고 관리하는 컨테이너입니다. 다음 예시에서 ApplicationContext 가 스프링 컨테이너입니다. 스프링 컨테이너를 만들때는 예시의 AppConfig.class 같은 구성 정보를 지정해줘야 합니다.

```java
ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
```



## BeanFactory, ApplicationContext

스프링 컨테이너를 부를때는 앞서 나왔던 ApplicationContext 그리고 BeanFactory 로 구분해서 말합니다.

![springbean1](https://user-images.githubusercontent.com/59478159/153131470-76216646-10df-4c36-890b-541a32c9ce64.png)

BeanFactory 인터페이스는 스프링 컨테이너의 최상위 인터페이스로 스프링 빈을 관리하고 조회하는 역할을 담당합니다.

ApplicationContext 는 BeanFactory 의 기능을 모두 상속받아서 제공하여 스프링 빈을 관리, 조회하고 여러 부가 기능들을 제공합니다. (BeanFactory 뿐 아니라 MessageSource 인터페이스 등 여러 인터페이스를 추가로 상속받기 때문)

AnnotationConfigApplicatoinContext 는 ApplicationContext 인터페이스의 구현체이고 설정 정보로 어노테이션 기반 자바 코드 설정을 사용합니다.

이처럼 ApplicationContext 는 BeanFactory 뿐 아니라 여러 부가기능을 제공하여 주로 사용됩니다.





# Bean 생성방법

Component Scanning 방법과 직접 XML이나 설정파일에 등록하는 방법이 있습니다. 



## Component Scanning

우리가 흔히 사용하는 @Controller, @RestController의 경우 이들은 @Component라는 어노테이션을 갖고 있습니다. 

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@ResponseBody
public @interface RestController {
	@AliasFor(annotation = Controller.class)
	String value() default "";

}
```

이처럼 @RestController의 경우 @Controller를 확인할 수 있습니다. 



```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Controller {
	@AliasFor(annotation = Component.class)
	String value() default "";

}
```

@Controller의 경우 @Component를 확인할 수 있습니다. 

이처럼 @Component 어노테이션을 포함하는 경우 Component Scanning의 대상이 됩니다. 



이러한 Component Scanning의 시작점은 다음과 같습니다. 우선 SpringBoot 애플리케이션을 만들면 src/main/java 안에 [프로젝트명]Application.java 라는 파일이 생성됩니다. 이를 살펴보면 다음과 같습니다. 

```java
@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
```

SpringApplication.run(TestApplication.class, args); 부분을 통해 Spring Boot의 시작점이라는 것을 알 수 있습니다. 

@SpringBootApplication를 살펴보면 다음과 같습니다. 

```java
@ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication { ... }
```



@ComponentScan이라는 어노테이션에서 알 수 있듯이 Component Scanning 기능이 있음을 알 수 있습니다. 

따라서 @SpringBootApplication 어노테이션이 붙은 곳부터 하위 패키지 안의 @Component 어노테이션이 붙은 클래스는 Bean으로 등록됩니다. @Controller, @RestController, @Service, @Repository, @Configuration 과 같은 어노테이션이 이에 해당합니다. 



## XML 설정

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="memberService" class="hello.corestudy.member.MemberServiceImpl">
        <constructor-arg name="memberRepository" ref="memberRepository"/>
    </bean>

    <bean id="memberRepository" class="hello.corestudy.member.MemoryMemberRepository"/>

    <bean id="orderService" class="hello.corestudy.order.OrderServiceImpl">
        <constructor-arg name="memberRepository" ref="memberRepository"/>
        <constructor-arg name="discountPolicy" ref="discountPolicy"/>
    </bean>

    <bean id="discountPolicy" class="hello.corestudy.discount.RateDiscountPolicy"/>

</beans>
```



## Java 설정

Java에서 설정을 통해 Bean을 등록할 수 있습니다. 다음과 같이 작성할 경우 AppConfig는 @Configuration이기에 Component Scanning의 대상이 되므로 Bean으로 등록이 됩니다. 또한 @Bean 이 붙은 TestController또한 Bean으로 등록됩니다. 

```java
@Configuration
public class AppConfig{
	@Bean
	public TestController testController(){
		return new TestController();
	}
}
```



# BeanDefinition

앞에서 알 수 있듯 스프링 컨테이너는 다양한 설정 형식을 지원합니다. 이는 BeanDefinition 덕분에 가능한 일입니다. XML형식이든 어노테이션 기반 자바 코드 형식이든 상관없이 **BeanDefiniiton이라는 빈 설정메타 정보**를 만들고 스프링 컨테이너는 이 BeanDefinition을 기반으로 스프링 빈을 생성합니다. 

![springbean3](https://user-images.githubusercontent.com/59478159/153131484-82123b8b-02dd-4edc-892c-1233202453c5.png)



![springbean4](https://user-images.githubusercontent.com/59478159/153131492-a1e5ea9c-af61-4151-8430-7ca7f628c181.png)





