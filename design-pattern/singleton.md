## Singleton Pattern
### 싱글톤 패턴

- 객체를 딱 하나만 생성하여 생성된 객체를 프로그램 어디에서나 접근하여 사용할 수 있도록 하는 패턴이다.
- 객체 생성을 한 번으로 제한하여 객체들이 복제되는 경우를 방지할 수 있다.
- 클래스를 사용하는 여러 곳에서 인스턴스를 계속 생성하여 불필요하게 메모리 낭비를 유발할 수 있다고 판단되는 경우에 사용할 수 있다.

### 싱글톤 패턴의 장점

- 메모리 낭비를 방지할 수 있다.
- 싱글톤으로 만들어진 클래스와 다른 클래스의 인스턴스들의 데이터 공유가 쉽다.
- 인스턴스가 한개만 존재하는 것을 보증하기에 개발 시 실수를 줄일 수 있다.

### 싱글톤 패턴의 단점

- 싱글톤의 역할이 커질수록 결합도가 높아져 객체 지향 설계 원칙에 어긋날 수 있다.
- 멀티쓰레드 환경에서 컨트롤이 어렵다.
- 싱글톤 인스턴스는 자원을 공유하고 있기 때문에 테스트가 결정적으로 격리된 환경에서 수행되려면 매번 인스턴스의 상태를 초기화 시켜주어야 하여 테스트하기 어렵다.

### 싱글톤 패턴 사용법

```java
public class Singleton {

    private static Singleton instance = new Singleton();
	
    private Singleton() {}
	
    public static Singleton getInstance() {
        return instance;
    }
}
```

```java
public class SingletonTest {

		public static void main(String[] args) {
				Singleton sc = Singleton.getInstance();
		}
}
```

- 외부에서 생성자 접근 금지 → 생성자의 접근 제한자를 private로 성정
- 내부에서는 private에 접근 가능하므로 직접 객체 생성 → 멤버 변수이므로 private 설정
- 외부에서 private member에 접근 가능한 getter 생성 → setter는 불필요
- 객체 없이 외부에서 접근할 수 있도록 getter와 변수에 static 추가
- 외부에서는 언제나 getter을 통해서 객체를 참조하므로 하나의 객체 재사용

### 참고

- https://coding-factory.tistory.com/709
- https://tecoble.techcourse.co.kr/post/2020-11-07-singleton/
