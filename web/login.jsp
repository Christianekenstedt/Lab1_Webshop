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
<body style="font-family: Verdana;">
<div id="wrapper" style="width:400px;margin:auto;top:300px;position:relative;text-align:center;">
    <form action="Webshop" method="post">
        <input type="hidden" name="operation" value="login">
        Username<br><input type="text" name="username"> <br><br>
        Password<br><input type="password" name="password"> <br>
        <input type="submit" value="Login" style="align-content:center;">
    </form>
    <form action="Webshop" method="post">
        <input type="submit" name="operation" value="goSignup">
    </form>
</div>
</body>
</html>
