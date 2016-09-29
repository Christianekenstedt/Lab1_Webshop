<%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 2016-09-28
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>

</head>
<body>
    Welcome
    <%= session.getAttribute("username")%>
    !

<form action="Webshop" method="post">
    <button type="submit" name="operation" value="articlesShow">Show Articles</button><br>
    <button type="submit" name="operation" value="adminCenter">Administration</button><br><!-- check session variable if allowed -->
    <button type="submit" name="operation" value="logout">Logout</button><br>
</form>

</body>
</html>
