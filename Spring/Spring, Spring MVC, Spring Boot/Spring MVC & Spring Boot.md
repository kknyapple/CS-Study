## Spring MVC
### Spring MVC

- 웹 애플리케이션을 개발에 있어 MVC 패턴을 적용할 수 있도록 Spring에서 제공하는 프레임워크

### Spring MVC 구조

![image](https://github.com/kknyapple/CS-Study/assets/72698829/53452612-ddef-43d1-b7a7-e8412938d3d9)

- DispatcherServlet
    - 가장 앞단에서 클라이언트의 요청을 처리하는 Controller로써 요청부터 응답까지 전반적인 처리 과정을 통제한다.
- Handler(Controller)
    - DispatcherServlet가 전달해준 HTTP 요청을 처리하고 결과를 Model에 저장한다.
- ModelAndView
    - controller에 의해 반환된 Model과 View가 Wrapping된 객체이다.
- ViewResolver
    - Model에 저장된 데이터를 사용해 View를 그리는 역할을 한다.

## Spring Boot
### Spring Boot

- 기본적인 설정과 보일러 플레이트 코드 작성을 최소화하고, 자동 설정과 컨벤션을 통해 개발자들이 빠르게 애플리케이션을 개발할 수 있도록 지원하는 스프링 프레임워크이다.
- Spring의 장점을 가져가면서 기존의 문제가 되는 부분을 보완하였다.

### Spring Boot 특징

- 간결한 설정
    - 번거로운 XML 설정이 필요 없으며, 최소한의 설정으로 Spring을 사용할 수 있다.
    - 기본적인 설정을 자동으로 처리해준다.
- 내장 서버
    - 내장된 서버를 제공하여 별도의 서버 설정 없이 애플리케이션을 실행할 수 있다.
    - 배포를 위해 War 파일을 생성해서 Tomcat에 배포할 필요가 없으며, Jar 파일에 모든 의존성 라이브러리가 포함되어 있어 외부 서버 없이도 애플리케이션을 실행할 수 있다.
- 의존성 관리 간소화
    - 3rd party 라이브러리를 사용할 때 발생하는 라이브러리 버전 충돌로 인한 문제를 해결하기 위해서 이미 테스트된 여러 라이브러리들의 묶음 패키지를 제공한다.
- 운영 편의성
    - 애플리케이션의 상태 모니터링, 로깅, 보안 설정 등 운영에 필요한 기능들을 제공한다.

### Spring MVC, Spring Boot 차이점

- Spring MVC 구조의 경우 XML 파일들에 Dispatcher Servlet, Handler Mapping, View Resolver 설정들을 해줘야한다.
- Spring Boot는 @만 적절하게 명시해주면 Spring이 처리해준다.

## 참고

- https://www.elancer.co.kr/blog/view?seq=158
- https://programforlife.tistory.com/68
- https://jjeongil.tistory.com/2131
- https://aridom.tistory.com/61
