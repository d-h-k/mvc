
## 타임리프 기본
- 사용 선언
```html
<html xmlns:th="http://www.thymeleaf.org">
```


## 타임리프로 VIEW 구성하는 핵심원리!! 핵심
- 핵심은 th:xxx 가 붙은 부분은 서버사이드에서 렌더링 되고, 기존 것을 대체
- th:xxx 이 없으면 기존 html의 xxx 속성이 그대로 사용
- 따라서 HTML을 파일 보기를 유지하면서 템플릿 기능도 할 수 있다 (그래서 내츄럴 템플릿)
- 과거에 JSP 같은걸 이용하면 하나도 자연스럽지 못하고 브라우저로 볼수 없어서

### 속성 변경 - th:href
- href="value1" 을 th:href="value2" 의 값으로 변경한다.
- HTML을 그대로 볼 때는 href 속성이 사용되고, 뷰 템플릿을 거치면 th:href 의 값이 href 로 대체
- 대부분의 HTML 속성을 th:xxx 로 변경가능

### 속성 변경 - th:onclick
- 원래는 : `onclick="location.href='addForm.html'"`
- 대체된다`th:onclick="|location.href='@{/basic/items/add}'|"`
- 링크 표현식 `@{...}` 이걸로 동적으로 URI를 알아내야할때(번호같은건 매번 어떤 URI로 접근해야하는지 그때 아는거니까

### URL 링크 표현식 - @{...},
- `th:href="@{/css/bootstrap.min.css}"` 이렇게 생간것들 형태가 `@{...}`이라면
- 타임리프는 URL 링크를 사용하는 경우이며, URL 링크 표현식을 사용하면 서블릿 컨텍스트를 자동으로 포함
- 다른 페이지로 이동이 가능하다 (ex)상품 등록 폼으로 이동


### 리터럴 대체 - |...|
