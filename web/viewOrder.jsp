<%@ page import="VO.ItemVO" %>
<%@ page import="VO.OrderItemVO" %>
<%@ page import="VO.OrderVO" %>
<%@ page import="VO.UserVO" %><%--
  Created by IntelliJ IDEA.
  User: chris
  Date: 2016-09-30
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% OrderVO order = OrderVO.viewOrder(Integer.parseInt(request.getParameter("orderId")));%>
<% UserVO user = UserVO.getUserByID(Integer.parseInt(session.getAttribute("userId").toString()));%>
<html>
<head>
    <title>Order</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: auto;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<div style="margin: auto"align="center">
    <h1>Order requested: <%=request.getParameter("orderId")%></h1>
    <h3>Order status:
        <%if (order.getStatus() == 1){%>
        Received
        <%}else if (order.getStatus() == 2){%>
        Picking goods
        <%}else if (order.getStatus() == 3){%>
        Packed
        <%}else if (order.getStatus() == 4){%>
        Sent
        <%}else if (order.getStatus() == 5){%>
        Done
        <%}%>
    </h3>
    <form action="Webshop" method="post">
        <input type="hidden" name="operation" value="packOrder">
        <table>
            <tr>
                <th>Item id</th>
                <th>Item name</th>
                <th>Category</th>
            </tr>
            <%for (OrderItemVO item: OrderItemVO.getItemsFromOrder(Integer.parseInt(request.getParameter("orderId")))){%>
            <tr>
                <td>
                    <%out.print(item.getId()); %>
                </td>
                <td>
                    <%out.print(item.getName()); %>
                </td>
                <td>
                    <%out.print(item.getCategory().getName());%>
                </td>
            </tr>
            <%}%>
        </table>
        <% if(user.getRole().getName().equals("Personnel")){ %>
            <button type="submit" name="pOrder" value="<%=order.getId()%>">Pack</button>
        <%}%>
    </form>
</div>
</body>
</html>
