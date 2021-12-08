
## 타임리프 기본
- 사용 선언
```html
<html xmlns:th="http://www.thymeleaf.org">
```
- 속성 변경 - th:href
th:href="@{/css/bootstrap.min.css}"
href="value1" 을 th:href="value2" 의 값으로 변경한다.
타임리프 뷰 템플릿을 거치게 되면 원래 값을 th:xxx 값으로 변경한다. 만약 값이 없다면 새로 생성한다.
HTML을 그대로 볼 때는 href 속성이 사용되고, 뷰 템플릿을 거치면 th:href 의 값이 href 로
대체되면서 동적으로 변경할 수 있다.
대부분의 HTML 속성을 th:xxx 로 변경할 수 있다.

- 속성 변경 - th:onclick
onclick="location.href='addForm.html'"
th:onclick="|location.href='@{/basic/items/add}'|"
여기에는 다음에 설명하는 리터럴 대체 문법이 사용되었다. 자세히 알아보자.

## 타임리프로 VIEW 구성하는 핵심원리
- 핵심은 th:xxx 가 붙은 부분은 서버사이드에서 렌더링 되고, 기존 것을 대체한다. 
- th:xxx 이 없으면 기존 html의 xxx 속성이 그대로 사용된다.
- HTML을 파일로 직접 열었을 때, th:xxx 가 있어도 웹 브라우저는 th: 속성을 알지 못하므로 무시한다.
- 따라서 HTML을 파일 보기를 유지하면서 템플릿 기능도 할 수 있다.

## URL 링크 표현식 - @{...},
- `th:href="@{/css/bootstrap.min.css}"` 이렇게 생간것들 형태가 `@{...}`이라면
- 타임리프는 URL 링크를 사용하는 경우이며, URL 링크 표현식을 사용하면 서블릿 컨텍스트를 자동으로 포함
- 다른 페이지로 이동이 가능하다 (ex)상품 등록 폼으로 이동
