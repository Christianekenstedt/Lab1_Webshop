<%@ page import="VO.ItemVO" %>
<%@ page import="VO.UserVO" %><%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 2016-09-28
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% UserVO user = UserVO.getUserByID(Integer.parseInt(session.getAttribute("userId").toString()));%>

<html>
<head>
    <title>Home</title>

</head>
<body>
<div id="wrapper" style="width:800px; margin:auto;">
    Welcome
    <%= user.getUsername()%>
    !



    <form action="Webshop" method="post">
        <button type="submit" name="operation" value="articlesShow">Show Articles</button><br>
        <% if(user.getRole().getName().equals("Admin")){ %>
            <button type="submit" name="operation" value="adminCenter">Administration</button><br><!-- check session variable if allowed -->
        <% }%>
        <button type="submit" name="operation" value="logout">Logout</button><br>
    </form>

    <table style="margin:auto;">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Quantity</th>
        </tr>

        <% for(ItemVO item : ItemVO.getAll()){ %>
        <tr>
            <td style="width:20px;">
                <%out.print(item.getId()); %>
            </td>
            <td style="width:300px;">
                <%out.print(item.getName()); %>
            </td>
            <td style="width:20px;">
                <%out.print(item.getAmount()); %>
            </td>

        </tr>

        <%}%>
    </table>
</div>
</body>
</html>
