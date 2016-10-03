<%--
  Created by IntelliJ IDEA.
  User: chris
  Date: 2016-09-30
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Administration</title>
</head>
<body>

<div style="width:500px; margin:auto; position:relative; top:100px;border:2px black solid;padding:20px;">
    <h3>Administration page</h3>
    <form action="Webshop" method="post">
        <button type="submit" name="operation" value="manageItems">Manage items and categories</button><br>
        <button type="submit" name="operation" value="viewUsers">Manage users</button><br><br>
        <button type="submit" name="operation" value="goHome">Back</button><br>
    </form>
</div>
</body>
</html>
