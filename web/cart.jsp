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
    <title>Title</title>
</head>
<body>
    <div style="width:500px; margin:auto; position:relative; top:100px;">

        <form action="Webshop" method="post" >
            <input type="hidden" name="operation" value="cartRemoveItem">
            <table style="border:2px solid black;">
                <tr>
                    <th>Id</th>
                    <th>Item(x)</th>
                    <th style="width:10%;">Remove</th>
                </tr>
                <% for(ShoppingCartItemVO item : ShoppingCartVO.getCartByUser(user.getId()).getItems()){%>
                <tr>
                    <td>
                        <%=item.getId()%>
                    </td>
                    <td>
                        <%=item.getItem().getName()%>
                        <% out.println("(" + item.getAmount() + ")");%>
                    </td>
                    <td>
                        <button type="submit" name="removeItemId" value="<%=item.getId()%>">Remove</button>
                    </td>
                </tr>
                <%}%>

            </table>

        </form>
        <form action="Webshop" method="post">
            <button style="margin-top:10px;" type="submit" name="operation" value="goHome">Back</button>
            <button style="float:right; margin-top:10px;" type="submit" name="operation" value="checkout">Checkout</button>
        </form>
    </div>
</body>
</html>
