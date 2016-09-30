<%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 2016-09-28
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

    <form action="Webshop" method="post">
        <input type="hidden" name="operation" value="login">
        Username:<br><input type="text" name="username"> <br>
        Password:<br><input type="password" name="password"> <br>
        <input type="submit" value="Login">
    </form>

</body>
</html>
