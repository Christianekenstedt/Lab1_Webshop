<%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 2016-09-30
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div id="wrapper" style="width:400px;margin:auto;top:300px;position:relative;text-align:center;">
        <form action="Webshop" method="post">
            <input type="hidden" name="operation" value="signup">
            Username<br><input type="text" name="username"> <br><br>
            Password<br><input type="password" name="password1"> <br>
            re-enter password<br><input type="password" name="password2"> <br>
            <input type="submit" value="Create Account" style="align-content:center;">
        </form>
    </div>
</body>
</html>
