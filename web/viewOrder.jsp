<%@ page import="VO.ItemVO" %>
<%@ page import="VO.OrderItemVO" %>
<%@ page import="VO.OrderVO" %><%--
  Created by IntelliJ IDEA.
  User: chris
  Date: 2016-09-30
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <h3>Order status: <%=OrderVO.viewOrder(Integer.parseInt(request.getParameter("orderId"))).getStatus()%></h3>
    <form action="Webshop" method="post">
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
                    <%out.print(item.getCategory());%>
                </td>
            </tr>
            <%}%>
        </table>
    </form>
</div>
</body>
</html>
