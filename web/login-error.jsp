<%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 2016-09-29
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Could not log in</title>
</head>
<body>
    Error logging in. Please try again<br>
    <form action="Webshop" method="post">
        <input type="hidden" name="operation" value="login">
        Username:<input type="text" name="username"> <br>
        Password:<input type="password" name="password"> <br>
        <input type="submit" value="Login">
    </form>
</body>
</html>
