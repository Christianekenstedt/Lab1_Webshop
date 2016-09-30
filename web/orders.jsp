<%@ page import="VO.OrderVO" %>
<%@ page import="VO.UserVO" %><%--
  Created by IntelliJ IDEA.
  User: chris
  Date: 2016-09-30
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% UserVO user = UserVO.getUserByID(Integer.parseInt(session.getAttribute("userId").toString()));%>
<html>
<head>
    <title>My Orders</title>
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
    <% if(user.getRole().getName().equals("Personnel")){ %>
    <h1>View All Orders</h1>
    <%}else{%>
      <h1>My orders</h1>
        <%if (Boolean.valueOf(request.getParameter("orderCreated"))){%>
            <p style="color: green">Order created.</p>
        <%}%>
    <%}%>

<form action="Webshop" method="post">
    <input type="hidden" name="operation" value="manageOrder">

    <% if(user.getRole().getName().equals("Personnel")){ %>
    <table >
        <tr>
            <th>Order number</th>
            <th>Order status</th>
        </tr>

        <% for(OrderVO order : OrderVO.getAllOrders()){ %>
        <tr>
            <td>
                <%out.print(order.getId()); %>
            </td>
            <td>
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
            </td>
            <td>
                <button type="submit" name="orderId" value="<%=order.getId()%>">View</button>

            </td>
        </tr>

        <%}%>
    </table>
    <%}else{%>
    <table >
        <tr>
            <th>Order number</th>
            <th>Order status</th>
        </tr>

        <% for(OrderVO order : OrderVO.viewOrders(Integer.parseInt(session.getAttribute("userId").toString()))){ %>
        <tr>
            <td>
                <%out.print(order.getId()); %>
            </td>
            <td>
                <%out.print(order.getStatus()); %>
            </td>
            <td>
                <button type="submit" name="orderId" value="<%=order.getId()%>">View</button>

            </td>

        </tr>

        <%}%>
    </table>
    <%}%>



</form>
</div>
</body>
</html>
