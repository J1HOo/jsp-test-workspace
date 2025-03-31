<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- html xml:th="https://www.thymeleaf.org"--%>
<html>
<head>
    <title>로그인</title>
</head>
<body>
<h2>로그인</h2>
<form name="loginFrm" action="/login.do" method="POST">
    <input type="text" name="userId" required placeholder="아이디"><br>
    <input type="password" name="userPw" required placeholder="비밀번호"><br>
    <input type="submit" value="로그인">
    <input type="reset" value="취소">
</form>
</body>
</html>
