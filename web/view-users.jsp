<%@ page import="VO.UserVO" %><%--
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
<div style="width:500px; margin:auto; position:relative; top:100px;">
    <form>

        <table>
            <% for(UserVO user : UserVO.getAllUsers())
                out.print(user.getUsername());
            %>
        </table>
    </form>
</div>
</body>
</html>
