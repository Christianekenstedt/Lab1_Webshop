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
<body style="font-family:Verdana;">
<div style="width:500px; margin:auto; position:relative; top:100px;">
    <form action="Webshop" method="post">

        <table style="border:2px black solid;">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Role</th>
                <th>Select</th>
            </tr>
            <% for(UserVO user : UserVO.getAllUsers()){%>
                <tr>
                    <td>
                        <%=user.getId()%>
                    </td>
                    <td>
                        <%=user.getUsername()%>
                    </td>
                    <td>
                        <%=user.getRole().getName()%>
                    </td>
                    <td>
                        <input style="float:left;" type="radio" name="selected" value="<%=user.getId()%>">
                    </td>
                </tr>
            <%}%>
        </table>
        <button type="submit" name="operation" value="removeUser">Remove</button>
        <button style="float:right;" type="submit" name="operation" value="editUser">Edit</button>
        <br>
        <br>
        <button type="submit" name="operation" value="adminCenter">Back</button>
    </form>
</div>
</body>
</html>
