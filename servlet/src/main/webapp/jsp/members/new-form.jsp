<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/jsp/members/save.jsp" method="post">
    username: <label> <input type="text" name="username"/> </label>
    age: <label> <input type="text" name="age"/> </label>
    <button type="submit">전송</button>

</form>

</body>
</html>


<!---
첫 줄을 제외하고는 완전히 HTML와 똑같다.
JSP는 서버 내부에서 서블릿으로 변환되서 html페이지 문자열을 만들어서 나간다
--->