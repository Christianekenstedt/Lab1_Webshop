<%@ page import="VO.ItemVO" %>
<%@ page import="VO.UserVO" %>
<%@ page import="VO.ItemCategoryVO" %><%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 2016-09-28
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% UserVO user = UserVO.getUserByID(Integer.parseInt(session.getAttribute("userId").toString()));%>
<%
    ItemCategoryVO selectedCategory = null;
    if(request.getAttribute("selectedCategory") != null){
        selectedCategory = ItemCategoryVO.getCategoryById(Integer.parseInt(request.getAttribute("selectedCategory").toString()));
    }

%>
<html>
<head>
    <title>Home</title>
    <script src="https://use.fontawesome.com/13e83c0e2c.js"></script>
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
        input{
            width:30px;
        }
    </style>
</head>
<body style="font-family:Verdana;">
<div id="wrapper" style="width:800px; margin:auto;position:relative;top:100px;">
    <div id="categories" style="float:left;width:200px;height:800px;">
        Welcome
        <%= user.getUsername()%>
        !<br>

    <form action="Webshop" method="post" style="width:100px;">
        <br>
        <% if(user.getRole().getName().equals("Admin")){ %>
            <button style="width:100px;" type="submit" name="operation" value="adminCenter">Administration</button><br><!-- check session variable if allowed -->
        <% }%>
        <br>

        <button style="width:100px;" type="submit" name="operation" value="myOrders">Orders</button><br>

        <button style="width:100px;" type="submit" name="operation" value="viewCart">Shopping Cart<i class="fa fa-shopping-cart"></i></button><br>

        <br>

        <button style="width:100px;" type="submit" name="operation" value="logout">Logout</button><br>
    </form>

        <div>
            <form action="Webshop" method="post">
                <input type="hidden" name="operation" value="selectCategory">
                Categories<br>
                <button style="width:100px;">All items</button><br>
                <% for (ItemCategoryVO category: ItemCategoryVO.getAllCategories()) {%>
                    <button style="width:100px;" type="submit" name="selectedCategory" value="<%=category.getId()%>"><%=category.getName()%></button><br>
                <%}%>
            </form>
        </div>
    </div>

    <div id="items" style="height:800px;">

            <table style="border:2px black solid;width:550px;">
                <tr>
                    <th style="width:auto;">Id</th>
                    <th style="=width:auto;">Name</th>
                    <th>Price</th>
                    <th style="width:auto;">In stock</th>
                    <th style="width:auto;">Amount</th>
                    <th style="width:auto;">Buy</th>
                </tr>

            <% for(ItemVO item : ItemVO.getItemsByCategory(selectedCategory)){ %>
                <form action="Webshop" method="post">
                    <input type="hidden" name="operation" value="buyItem">
                    <tr>
                        <td>
                            <%=item.getId()%>
                        </td>
                        <td>
                            <%=item.getName()%>
                        </td>
                        <td style="text-align:right;">
                            <%=item.getPrice()%>
                        </td>
                        <td>
                            <%=item.getAmount()%>
                        </td>
                        <td>
                            <input type="number" name="buyAmount" value="1">
                        </td>
                        <td>
                            <button class="fa fa-cart-plus" type="submit" name="buyItemId" value="<%=item.getId()%>"></button>
                        </td>
                    </tr>
                </form>
            <%}%>
            </table>

    </div>

</div>
</body>
</html>
