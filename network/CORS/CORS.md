# CORS란?

CORS란 Cross Origin Resource Sharing 의 약자로 한국어로 직역하면 교차 출처 리소스 공유라고 해석할 수 있습니다. 여기서 “교차 출처”라고 하는 것은 “다른 출처”를 의미합니다. 쉽게 말하자면 친구의 물건을 쓰려면 친구가 제한하는 규약안에서 사용해야 하듯, 다른 도메인의 자원을 쓰려면 자원의 주인이 허락한 규약을 지켜야 하는 것이고 이러한 규약을 표준화한 것이 CORS 입니다.

![cors1](https://github.com/Ahrang777/Ahrang777.github.io/assets/59478159/0fef1179-2b9c-4b65-b44b-f62dad4c0627)



## 출처(Origin)란?

https://google.com과 같은 URL들은 하나의 문자열 같아 보여도, 사실 여러 구성 요소로 이루어져 있습니다.

 ![cors2](https://github.com/Ahrang777/Ahrang777.github.io/assets/59478159/79c3b0fe-07e6-4323-91aa-beb6771ffaca)



여기서 출처는 Protocol, Host, Port 번호 까지 합친 것을 의미합니다. 즉, 서버의 위치를 찾아가기 위해 필요한 가장 기본적인 것들을 합쳐 놓은 것입니다. 



## SOP(Same-Origin Policy)

웹 생태계에는 다른 출처로의 리소스 요청을 제한하는 것과 관련된 두 가지 정책이 존재합니다. 한 가지는 CORS, 그리고 다른 하나는 **SOP(Same-Origin Policy)** 입니다. 

SOP는 지난 2011년, [RFC 6454](https://tools.ietf.org/html/rfc6454#page-5)에서 처음 등장한 보안 정책으로 말 그대로 **“같은 출처에서만 리소스를 공유할 수 있다”**라는 규칙을 가진 정책입니다. 

그러나 웹이라는 오픈스페이스 환경에서 다른 출처에 있는 리소스를 가져와서 사용하는 일은 굉장히 흔한 일이라 무작정 막을 수도 없는 노릇이니 몇 가지 **예외 조항**을 두고 이 조항에 해당하는 리소스 요청은 출처가 다르더라도 허용하기로 했는데, 그 중 하나가 **“CORS 정책을 지킨 리소스 요청”**입니다. (참고로 CORS라는 이름이 처음 등장한 것은 2009년이라, SOP의 등장보다 빠릅니다)

```
Access to network resources varies depending on whether the resources are in the same origin as the content attempting to access them.

Generally, reading information from another origin is forbidden. However, an origin is permitted to use some kinds of resources retrieved from other origins. For example, an origin is permitted to execute script, render images, and apply style sheets from any origin. Likewise, an origin can display content from another origin, such as an HTML document in an HTML frame. Network resources can also opt into letting other origins read their information, for example, using Cross-Origin Resource Sharing.
```

[RFC 6454 - 3.4.2 Network Access](https://tools.ietf.org/html/rfc6454#section-3.4.2)



우리가 다른 출처로 리소스를 요청한다면 SOP정책을 위반한 것이 되고, SOP 예외 조항인 CORS정책까지 지켜지지 않는다면 아예 다른 출처의 리소스를 사용할 수 없게 되는 것입니다. 



### SOP, CORS의 필요성

이러한 정책들로 인해 개발자 입장에서는 추가적인 설정을 해줘야 하고 귀찮게 한다고 생각할 수 있습니다. 하지만 생각해보면 출처가 다른 두 개의 어플리케이션이 마음대로 소통할 수 있는 환경은 꽤 위험한 환경입니다. 애초에 클라이언트 어플리케이션, 특히나 웹에서 돌아가는 클라이언트 어플리케이션은 사용자의 공격에 너무나도 취약합니다. 

브라우저의 개발자 도구만 열어도 DOM이 어떻게 작성되어 있는지, 어떤 서버와 통신하는지, 리소스의 출처는 어디인지와 같은 각종 정보들을 아무런 제약없이 열람할 수 있습니다. 최근에 자바스크립트 소스 코드를 난독화해서 읽기 어렵다고는 하지만 암호화가 아닌 난독화이고 난독화되어있어도 사람이 바로 이해할 수 없는 정도도 아니며 소스 코드를 직접 볼 수 있다는 것 자체가 보안적으로 취약한 부분입니다. 

이런 상황 속에서 다른 출처의 어플리케이션이 서로 통신하는 것에 대해 아무런 제약도 존재하지 않는다면, 악의를 가진 사용자가 소스 코드를 쓱 구경한 후 [CSRF(Cross-Site Request Forgery)](https://ko.wikipedia.org/wiki/사이트_간_요청_위조)나 [XSS(Cross-Site Scripting)](https://ko.wikipedia.org/wiki/사이트_간_스크립팅)와 같은 방법을 사용하여 여러분의 어플리케이션에서 코드가 실행된 것처럼 꾸며서 사용자의 정보를 탈취하기가 너무나도 쉬워집니다.



## 출처의 구분

두 개의 출처가 서로 같다고 판단하는 로직 자체는 굉장히 간단한데, 두 URL의 구성 요소 중 `Scheme`, `Host`, `Port`, 이 3가지만 동일하면 됩니다.

| URL                                               | 같은 출처 | 이유                        |
| :------------------------------------------------ | :-------- | :-------------------------- |
| **https://evan-moon.github.io**/about             | O         | 스킴, 호스트, 포트가 동일   |
| **https://evan-moon.github.io**/about?q=안뇽      | O         | 스킴, 호스트, 포트가 동일   |
| **https://**user:password@**evan-moon.github.io** | O         | 스킴, 호스트, 포트가 동일   |
| **http://evan-moon.github.io**                    | X         | 스킴이 다름                 |
| **https://api.github.io**                         | X         | 호스트가 다름               |
| **https://evan-moon.naver.com**                   | X         | 호스트가 다름               |
| **https://evan-moon.github.com**                  | X         | 호스트가 다름               |
| **https://evan-moon.github.io:8000**              | ?         | 브라우저의 구현에 따라 다름 |

이러한 출처를 비교하는 로직은 서버에 구현된 스펙이 아닌 브라우저에 구현되어 있는 스펙입니다. 

만약 우리가 CORS 정책을 위반하는 리소스 요청을 하더라도 해당 서버가 같은 출처에서 보낸 요청만 받겠다는 로직을 가지고 있는 경우가 아니라면 서버는 정상적으로 응답을 하고, 이후 브라우저가 이 응답을 분석해서 CORS 정책 위반이라고 판단되면 그 응답을 사용하지 않고 그냥 버리는 형태입니다.

![cors3](https://github.com/Ahrang777/Ahrang777.github.io/assets/59478159/e98a5037-7fee-4cce-bc67-5934af54230a)



즉, CORS는 브라우저의 구현 스펙에 포함되는 정책이기 때문에, 브라우저를 통하지 않고 서버 간 통신을 할 때는 이 정책이 적용되지 않습니다.



[참고](https://tecoble.techcourse.co.kr/post/2020-07-18-cors/)

[참고2](https://evan-moon.github.io/2020/05/21/about-cors/)

