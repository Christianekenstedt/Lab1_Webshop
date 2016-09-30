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

</head>
<body>
<div id="wrapper" style="width:800px; margin:auto;">
    Welcome
    <%= user.getUsername()%>
    !

    <form action="Webshop" method="post" style="width:100px;">
        <% if(user.getRole().getName().equals("Admin")){ %>
            <button style="width:100px;" type="submit" name="operation" value="adminCenter">Administration</button><br><!-- check session variable if allowed -->
        <% }%>
        <button style="width:100px;" type="submit" name="operation" value="logout">Logout</button><br>
    </form>

    <div id="categories" style="float:left;width:200px;height:800px;">
        <form action="Webshop" method="post">
            <input type="hidden" name="operation" value="selectCategory">
            <% for (ItemCategoryVO category: ItemCategoryVO.getAllCategories()) {%>
                <button style="width:100px;" type="submit" name="selectedCategory" value="<%=category.getId()%>"><%=category.getName()%></button><br>
            <%}%>
        </form>
    </div>

    <div id="items" style="height:800px;">
        <form action="Webshop" method="post">
            <input type="hidden" name="operation" value="buyItem">
            <table style="border:2px black solid;width:500px;">
                <tr>
                    <th style="width:10%;">Id</th>
                    <th>Name</th>
                    <th style="width:10%;">In stock</th>
                    <th style="width:10%;">Amount</th>
                    <th style="width:10%;">Buy</th>
                </tr>

            <% for(ItemVO item : ItemVO.getItemsByCategory(selectedCategory)){ %>
                <tr>
                    <td>
                        <%=item.getId()%>
                    </td>
                    <td>
                        <%=item.getName()%>
                    </td>
                    <td>
                        <%=item.getAmount()%>
                    </td>
                    <td>
                        <input type="text" name="buyAmount">
                    </td>
                    <td>
                        <button type="submit" name="buyItemId" value="<%=item.getId()%>">Buy</button>
                    </td>
                </tr>
            <%}%>
            </table>
        </form>
    </div>

</div>
</body>
</html>
