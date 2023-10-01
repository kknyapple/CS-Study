# Annotaion

## 자바 어노테이션

### 개념

- Annotation : [사전적의미] 주석
- 자바 어노테이션 : 소스코드에 추가해서 사용할 수 있는 메타 데이터의 일종
- 메타 데이터 : 애플리케이션이 처리해야할 데이터가 아니라 컴파일 과정과 실행 과정에서 코드를 어떻게 처리해야하는지를 알려주기 위한 추가 정보

### 특징

- '@'를 앞에 붙여서 사용한다.
- JDK 1.5버전 이상부터 사용가능하다.
- 자바 어노테이션은 클래스 파일에 임베드되어 컴파일러에 의해 생성된 이후 JVM에 포함되어 동작한다.

> 자바 어노테이션을 이용하면 다음을 할 수 있다.

- 컴파일러에게 코드 작성 문법 에러를 체크하도록 정보를 제공한다.
- 소프트웨어 개발 환경이 빌드나 배포시 코드를 자동으로 생성할 수 있도록 정보를 제공한다.
- 런타임에 특정 기능을 실행하도록 정보를 제공한다.

## 자바 빌트인 어노테이션

1. @Override
   - 메소드를 오버라이드하겠다는 의미로 메소드의 선언 앞에 붙여준다.
   - 만약 상속받은 부모 클래스 또는 구현해야할 인터페이스에서 해당 메소드가 없다면 컴파일 오류가 발생한다.
2. @Deprecated
   - 메소드를 Deprecated 시킨다.
   - 이 메소드를 사용하는 애플리케이션을 컴파일 할 경우 컴파일 경고가 발생한다.
3. @SuppressWarnings
   - 컴파일러 경고를 출력하지 않도록 설정한다.
4. @SafeVarargs
   - 제너릭 같은 가변인자 매개변수를 사용할 때 사용하여 경고를 무시하게 한다.
5. @FunctionalInterface
   - 자바에서 람다 함수를 위한 인터페이스를 지정한다.
   - 함수형 인터페이션에 사용하기 적합하지 않은 경우에는 컴파일 오류가 발생한다.

## 메타 어노테이션

> 커스텀 어노테이션을 만들 때 사용

1. @Retention

   - 어노테이션의 리텐션 기간을 명명한다.
   - RetentionPolicy.Class
     - 바이트 코드 파일까지 어노테이션 정보를 유지한다.
     - 리플랙션을 이용해서 어노테이션 정보를 얻을 수는 없다.
   - RetentionPolicy.Runtime
     - 바이트 코드 파일까지 어노테이션 정보를 유지하면서 리플랙션을 이용해 런타임에 어노테이션 정보를 가져올 수 있다.
   - RetentionPolicy.Source
     - Compile 이후에는 삭제된다.

2. @Documented
   - 자바 문서에서도 어노테이션 정보가 표현된다.
3. @Target
   - 생성할 어노테이션이 적용될 수 있는 위치를 나열한다.
   - ElementType.TYPE : 클래스, 인터페이션, 열거 타입
   - ElementType.ANNOTATION_TYPE : 어노테이션
   - ElementType.FILED : 필드
   - ElementType.CONSTRUCTOR : 생성자
   - ElementType.METHOD : 메소드
   - ElementType.LOCAL_VARIABLE : 로컬변수
   - ElementType.PACKAGE : 패키지
4. @Ingerited
   - 자식 클래스가 어노테이션을 상속 받을 수 있다.
5. @Repeatable
   - 반복적으로 어노테이션을 선언할 수 있다.

## 커스텀 어노테이션

- Annotation 선언

```
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
        String value() default "MyAnnotation : default value"
}
```

```
class MyObject {
    @MyAnnotation
    public void testMethod1() {
        System.out.println("This is testMethod1");
  }

    @MyAnnotation(value = "My new Annotation")
    public void testMethod1() {
        System.out.println("This is testMethod1");
  }
}
```

- 리플랙션을 통해 어노테이션의 값을 가져올 수 있다.

```
public class MyMain {
    public static void main(String[] args) {
        Method[] methodList = MyObject.class.getMethods();

        for (Method method : methodList) {
            if(method.isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation annotation=method.getDeclaredAnnotation(MyAnnotation.class);
                String value=annotation.value();
                System.out.println(method.getName() + ":" + value);
            }
        }
    }
}
```

## 참고

https://hbase.tistory.com/169
