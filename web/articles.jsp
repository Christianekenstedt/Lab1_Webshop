<%@ page import="VO.ItemVO" %><%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 2016-09-29
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
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

</body>
</html>
