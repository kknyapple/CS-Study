# GET, POST Method

HTTP 프로토콜을 이용하여 서버에 무엇인가 요청할 때 사용하는 방식.

## GET Method

### 개념

- 정보를 조회하기 위한 메소드(Select)

### 사용

- 자료 검색, 게시글 조회, 특정 상품 정보 조회 등

### 사용법

- url 끝에 '?'가 붙고, 그 뒤에 요청한 데이터를 붙여 request를 보낸다.
- 요청 정보가 여러 개일 경우 '&'로 구분한다.  
  `ex) www.example.com?id=hello&pass=1234`

### 특징

- 요청하는 데이터가 HTTP Request Message의 `Header` 부분에 URL이 담겨서 전송된다.
- 요청 성공 시 200을, 에러 발생 시 주로 404(Not Found)나 400 응답을 반환한다.
- url 이라는 공간에 담겨가기 때문에 전송할 수 있는 데이터의 크기가 제한적이다. -> 이미지나 동영상 같은 바이너리 파일 전송 불가
- 데이터가 HTTP 패킷의 헤더에 포함되므로 BODY는 빈 상태로 보내진다.
- 보안이 필요한 데이터도 url에 그대로 노출되기 때문에 보안상 취약하다.
- GET 메소드는 캐싱을 사용할 수 있어 GET 요청과 그에 대한 응답이 브라우저에 의해 캐쉬되기 때문에 POST 메소드 보다 빠르다.

## POST Method

### 개념

- 서버의 값이나 상태를 변경하기 위한 메소드(insert, update, delete)

### 특징

- 요청 정보를 HTTP 패킷의 `BODY` 안에 숨겨서 서버로 전송한다.
- Request Header 필드 중 body의 데이터를 설명하는 `Content-Type`에 해당 데이터 타입이 표현되며, 전송하고자 하는 데이터 타입을 적어주어야 한다.

```
POST http://localhost:8080/web02/LoginServlet HTTP/1.1 // POST 메소드
Host: localhost:8080
Connection: keep-alive
Content-Length: 24 // 데이터 길이
Cache-Control: max-age=0
Origin: http://localhost:8080
Upgrade-Insecure-Requests: 1
Content-Type: application/x-www-form-urlencoded // 데이터 타입
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36
Sec-Fetch-User: ?1
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
Sec-Fetch-Site: same-origin
Sec-Fetch-Mode: navigate
Referer: http://localhost:8080/web02/PostTest.html
Accept-Encoding: gzip, deflate, br
Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7

id=abc123&password=1234* // Message Body 부분(get 요청은 이 부분을 사용하지 않음)
```

- 요청 정보를 body 안에 숨겨서 전송하기 때문에 대용량의 데이터를 전송하기 적합하다.
- GET 방식보다 보안상 안전하다.

## `Get` vs `Method` 정리

|                  | GET           | POST          |
| ---------------- | ------------- | ------------- |
| 캐시             | O             | X             |
| 브라우저 기록    | O             | X             |
| 데이터 길이 제한 | O             | X             |
| HTTP 응답 코드   | 200(Ok)       | 201(Created)  |
| 언제 사용?       | 리소스 요청시 | 리소스 생성시 |
| 리소스 전달 방식 | 쿼리스트링    | http body     |

<br/>

---

### [참고]

https://velog.io/@songyouhyun/Get%EA%B3%BC-Post%EC%9D%98-%EC%B0%A8%EC%9D%B4%EB%A5%BC-%EC%95%84%EC%8B%9C%EB%82%98%EC%9A%94
https://atoz-develop.tistory.com/entry/HTTP-GET-%EB%A9%94%EC%86%8C%EB%93%9C%EC%99%80-POST-%EB%A9%94%EC%86%8C%EB%93%9C%EC%9D%98-%EB%B9%84%EA%B5%90
