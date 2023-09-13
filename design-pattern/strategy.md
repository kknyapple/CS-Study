# Strategy Pattern(전략 패턴)

## 전략 패턴이란

비슷한 동작을 하지만 다르게 구현되어 있는 행위들을, **공통의 인터페이스를 구현하는 각각의 클래스로 구현**하고, 동적으로 바꿀 수 있도록 하는 패턴.

이렇게 구현된 코드는 새로운 로직을 추가하거나 변경할 때, 한 번에 효율적으로 변경 가능하다.

## 전략 패턴을 사용하는 이유

```
🚩 간단한 RPG 게임을 구현한다고 가정해보자.
[직업군] : 전사, 도적, 마법사
[공격 스킬]
전사 - 대검을 휘두른다.
도적 - 단도를 찌른다.
마법사 - 파이어볼을 발사한다.
```

### 전략패턴을 사용하지 않고 구현할 때

> `IF-ELSE` 문을 사용하여 클래스를 구현한다.

```java
class Character {
    private final String job;

    Character(String job) {
        this.job = job;
    }

    void attack() {
        if (job.equals("warrior")) {
            System.out.println("커다란 대검을 휘두른다!");
        } else if (job.equals("thief")) {
            System.out.println("작은 단도로 빠르게 두번 찌른다!");
        } else if (job.equals("magician")) {
            System.out.println("파이어볼을 발사한다!");
        }
    }
}
```

이 때, 궁수 직업군이 추가 되는 경우 attack 메소드를 다음과 같이 수정해줘야 한다.

```java
void attack() {
    if (job.equals("warrior")) {
        System.out.println("커다란 대검을 휘두른다!");
    } else if (job.equals("thief")) {
        System.out.println("작은 단도로 빠르게 두번 찌른다!");
    } else if (job.equals("magician")) {
        System.out.println("파이어볼을 발사한다!");
    } else if (job.equals("archer")) {
        System.out.println("활을 발사한다!");
    }
}
```

하지만 이와 같은 구조는 기능을 확장할 때마다 수정이 필요한 구조이기 때문에, **객체지향 설계 5원칙인 SOLID 중 개방-폐쇄 원칙(OCP: Open-Closed Principle)을 위배**한다.

따라서 이와 같은 문제점을 해결하기 위해 `전략 패턴`을 사용한다.

### 전략 패턴을 사용하는 구조로 개선

> Attack 메소드를 갖는 `AttackStrategy` 인터페이스를 구현한다.

```java
interface AttackStrategy {
    String getAttackMessage();
}
```

이렇게 구현한 인터페이스를 통해 각 직업군별 class를 다음과 같이 구현할 수 있다.

```java
class WarriorAttackStrategy implements AttackStrategy {
    public String getAttackMessage() {
        return "커다란 대검을 휘두른다!";
    }
}
```

```java
class ThiefAttackStrategy implements AttackStrategy {
    public String getAttackMessage() {
        return "작은 단도로 빠르게 두번 찌른다!";
    }
}
```

```java
class MagicianAttackStrategy implements AttackStrategy {
    public String getAttackMessage() {
        return "파이어볼을 발사한다!";
    }
}
```

```java
class ArcherAttackStrategy implements AttackStrategy {
    public String getAttackMessage() {
        return "활을 발사한다!";
    }
}
```

이와 같이 각 직업군 별로 클래스를 구현하면 Character 클래스는 아래와 같이 변경할 수 있다.

```java
class Character {
    private final AttackStrategy attackStrategy;

    Character(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    void attack() {
        System.out.println(attackStrategy.getAttackMessage());
    }
}
```

따라서 이 Character 클래스는 아래와 같이 사용할 수 있다.

```java
Character warrior = new Character(new WarriorAttackStrategy());
warrior.attack();
```

## 결론

이처럼 **Strategy 패턴은** 요구사항이 변경되었을 때 조건문을 추가하는 등과 같은 기존의 코드를 변경하는 방법이 아닌, 새로운 전략에 대해 새로운 클래스를 구현하는 방법을 사용하기 때문에 **OCP 원칙을 준수**할 수 있다.

---

### 참고

https://hudi.blog/strategy-pattern/

[https://mypark.tistory.com/entry/Design-Pattern-전략-패턴Strategy-Pattern에-대해-알아보자](https://mypark.tistory.com/entry/Design-Pattern-%EC%A0%84%EB%9E%B5-%ED%8C%A8%ED%84%B4Strategy-Pattern%EC%97%90-%EB%8C%80%ED%95%B4-%EC%95%8C%EC%95%84%EB%B3%B4%EC%9E%90)
