<%@ page import="VO.UserVO" %>
<%@ page import="VO.ShoppingCartItemVO" %>
<%@ page import="BL.ShoppingCart" %>
<%@ page import="VO.ShoppingCartVO" %><%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 2016-09-30
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% UserVO user = UserVO.getUserByID(Integer.parseInt(session.getAttribute("userId").toString()));%>
<html>
<head>
    <title>Shopping Cart</title>
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
    <div style="width:500px; margin:auto; position:relative; top:100px;">

        <h3>Shopping cart of '<%=user.getUsername()%>'</h3>
        <form action="Webshop" method="post" >
            <input type="hidden" name="operation" value="cartRemoveItem">
            <table style="border:2px solid black;">
                <tr>
                    <th>Id</th>
                    <th>Item</th>
                    <th>Amount</th>
                    <th>Price</th>
                    <th style="width:10%;">Remove</th>
                </tr>
                <% for(ShoppingCartItemVO item : ShoppingCartVO.getCartByUser(user.getId()).getItems()){%>
                <tr>
                    <td>
                        <%=item.getId()%>
                    </td>
                    <td>
                        <%=item.getItem().getName()%>
                    </td>
                    <td>
                        <%=item.getAmount()%>
                    </td>
                    <td>
                        <%=item.getAmount() * item.getItem().getPrice()%>
                    </td>
                    <td>
                        <button type="submit" name="removeItemId" value="<%=item.getId()%>">Remove</button>
                    </td>
                </tr>
                <%}%>
                <tr>
                    <td colspan="3">
                        Total:
                    </td>
                    <td colspan="2">
                        <%=ShoppingCartVO.getCartByUser(user.getId()).getTotalPrice()%>
                    </td>
                </tr>
            </table>

        </form>
        <form action="Webshop" method="post">
            <button style="margin-top:10px;" type="submit" name="operation" value="goHome">Back</button>
            <button style="float:right; margin-top:10px;" type="submit" name="operation" value="checkout">Checkout</button>
        </form>
    </div>
</body>
</html>
