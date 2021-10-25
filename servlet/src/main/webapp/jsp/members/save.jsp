<%@ page import="hello.servlet.domain.MemberRepository" %>
<%@ page import="hello.servlet.domain.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    //java코드의 영역, request, response 사용 가능

    MemberRepository memberRepository = MemberRepository.getInstance();
    System.out.println("save.jsp file : JspClass.jsp_service_method");
    String username = request.getParameter("username");
    Integer age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    System.out.println("member = " + member);
    memberRepository.save(member);


%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>

</body>
</html>
<!---
JSP는 자바 코드를 그대로 다 사용할 수 있다.
< % ~~ %>
이 부분에는 자바 코드를 입력할 수 있다.
< %= ~~ %>
이 부분에는 자바 코드를 출력할 수 있다.
회원 저장 JSP를 보면, 회원 저장 서블릿 코드와 같다.
- 다른 점이 있다면, HTML을 중심으로 하고, 자바 코드를 부분부분 입력
--->