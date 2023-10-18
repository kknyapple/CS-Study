# 필터(Filter)

필터는 **J2EE 표준 스펙 기능으로 디스패처 서블릿(Dispatcher Servlet)에 요청이 전달되기 전/후에 url 패턴에 맞는 모든 요청에 대해 부가작업을 처리할 수 있는 기능을 제공**합니다. 디스패처 서블릿은 스프링의 가장 앞단에 존재하는 프론트 컨트롤러이므로, 필터는 스프링 범위 밖에서 처리가 되는 것입니다. 즉, 스프링 컨테이너가 아닌 톰캣과 같은 웹 컨테이너(서블릿 컨테이너)에 의해 관리가 되는 것이고(스프링 빈으로 등록은 됩니다), 디스패처 서블릿 전/후에 처리하는 것입니다. 

![filter](https://github.com/Ahrang777/Ahrang777.github.io/assets/59478159/d9acbeba-0fc9-4f07-ae09-dfa8383a74e7)



## 메소드

필터를 추가하기 위해서는 javax.servlet의 Filter 인터페이스를 구현(implements)해야 하며 이는 다음의 3가지 메소드를 가지고 있습니다. 

- init 메소드
- doFilter 메소드
- destroy 메소드

```java
public interface Filter {

    public default void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException;

    public default void destroy() {}
}
```



### init 메소드

init 메소드는 필터 객체를 초기화하고 서비스에 추가하기 위한 메소드입니다. 웹 컨테이너가 1회 init 메소드를 호출하여(서블릿 컨테이너가 생성 될 때 호출) 필터 객체를 초기화하면 이후의 요청들은 doFilter를 통해 처리됩니다.

### doFilter 메소드

doFilter 메소드는 url-pattern에 맞는 모든 HTTP 요청이 디스패처 서블릿으로 전달되기 전에 웹 컨테이너에 의해 실행되는 메소드입니다. doFilter의 파라미터로는 FilterChain이 있는데, FilterChain의 doFilter 통해 다음 대상으로 요청을 전달하게 됩니다. chain.doFilter() 전/후에 우리가 필요한 처리 과정을 넣어줌으로써 원하는 처리를 진행할 수 있습니다.



### destroy 메소드

destroy 메소드는 필터 객체를 서비스에서 제거하고 사용하는 자원을 반환하기 위한 메소드입니다. 이는 웹 컨테이너에 의해 1번 호출되며 이후에는 이제 doFilter에 의해 처리되지 않습니다. 서블릿 컨테이너가 종료될 때 호출됩니다. 



# 인터셉터(Interceptor)

인터셉터는 J2EE 표준 스펙인 필터와 달리 Spring이 제공하는 기술로써, 디스패처 서블릿(Dispatcher Servlet)이 컨트롤러를 호출하기 전과 후에 요청과 응답을 참조하거나 가공할 수 있는 기능을 제공합니다. 즉, 웹 컨테이너(서블릿 컨테이너)에서 동작하는 필터와 달리 인터셉터는 스프링 컨텍스트에서 동작하는 것입니다. 

디스패처 서블릿은 핸들러 매핑을 통해 적절한 컨트롤러를 찾도록 요청하는데, 그 결과로 실행 체인(HandlerExecutionChain)을 돌려줍니다. 그래서 이 실행 체인은 1개 이상의 인터셉터가 등록되어 있다면 순차적으로 인터셉터들을 거쳐 컨트롤러가 실행되도록 하고, 인터셉터가 없다면 바로 컨트롤러를 실행합니다.

인터셉터는 스프링 컨테이너 내에서 동작하므로 필터를 거쳐 프론트 컨트롤러인 디스패처 서블릿이 요청을 받은 이후에 동작하게 되는데, 이러한 호출 순서를 그림으로 표현하면 다음과 같습니다. (실제로는 Interceptor가 Controller로 요청을 위임하지는 않습니다. 아래의 그림은 처리 순서를 도식화한 것으로만 이해하면 됩니다. )

![interceptor](https://github.com/Ahrang777/Ahrang777.github.io/assets/59478159/8734202a-560c-4be0-aa67-9dd8020d4da9)



## 메소드

인터셉터를 추가하기 위해서는 org.springframework.web.servlet의 HandlerInterceptor 인터페이스를 구현(implements)해야 하며, 이는 다음의 3가지 메소드를 가지고 있습니다.

- preHandle 메소드
- postHandle 메소드
- afterCompletion 메소드



```java
public interface HandlerInterceptor {

    default boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        
        return true;
    }

    default void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        @Nullable ModelAndView modelAndView) throws Exception {
    }

    default void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
        @Nullable Exception ex) throws Exception {
    }
}
```



### preHandle 메소드

preHandle 메소드는 컨트롤러가 호출되기 전에 실행됩니다. 그렇기 때문에 컨트롤러 이전에 처리해야 하는 전처리 작업이나 요청 정보를 가공하거나 추가하는 경우에 사용할 수 있습니다.

preHandle의 3번째 파라미터인 handler 파라미터는 핸들러 매핑이 찾아준 컨트롤러 빈에 매핑되는 HandlerMethod라는 새로운 타입의 객체로써, @RequestMapping이 붙은 메소드의 정보를 추상화한 객체입니다.

또한 preHandle의 반환 타입은 boolean인데 반환값이 true이면 다음 단계로 진행이 되지만, false라면 작업을 중단하여 이후의 작업(다음 인터셉터 또는 컨트롤러)은 진행되지 않습니다.

### postHandle 메소드

postHandle 메소드는 컨트롤러를 호출된 후에 실행됩니다. 그렇기 때문에 컨트롤러 이후에 처리해야 하는 후처리 작업이 있을 때 사용할 수 있습니다. 이 메소드에는 컨트롤러가 반환하는 ModelAndView 타입의 정보가 제공되는데, 최근에는 Json 형태로 데이터를 제공하는 RestAPI 기반의 컨트롤러(@RestController)를 만들면서 자주 사용되지는 않습니다.

또한 컨트롤러 하위 계층에서 작업을 진행하다가 중간에 예외가 발생하면 postHandle은 호출되지 않습니다.

### afterCompletion 메소드

afterCompletion 메소드는 이름 그대로 모든 뷰에서 최종 결과를 생성하는 일을 포함해 모든 작업이 완료된 후에 실행됩니다. 요청 처리 중에 사용한 리소스를 반환할 때 사용하기에 적합합니다. postHandler과 달리 컨트롤러 하위 계층에서 작업을 진행하다가 중간에 예외가 발생하더라도 afterCompletion은 반드시 호출됩니다.





reference

[참고1](https://mangkyu.tistory.com/173)
