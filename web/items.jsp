<%@ page import="VO.ItemCategoryVO" %>
<%@ page import="VO.ItemVO" %><%--
  Created by IntelliJ IDEA.
  User: chris
  Date: 2016-09-30
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ItemCategoryVO selectedCategory = null;
    if(request.getAttribute("selectedCategory") != null){
        selectedCategory = ItemCategoryVO.getCategoryById(Integer.parseInt(request.getAttribute("selectedCategory").toString()));
    }

%>
<html>
<head>
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
    <title>Manage items</title>
</head>
<body style="font-family: Verdana;">
<div id="wrapper" style="width:800px; margin:auto;padding: 10px">
    <h3>Add new Item:</h3>
    <div style="border:2px black solid;width:auto;" align="center">
        <form action="Webshop" method="post">
            <input type="hidden" name="operation" value="addItem">


                Item name:<br>
                <input type="text" name="itemName" placeholder="Item name"><br>
                Price:<br>
                <input type="number" step="0.01" name="itemPrice" placeholder="0.00"><br>
                In stock:<br>
                <input type="number" name="itemStock" placeholder="0"><br>
                <select name="itemCategory">
                    <%for (ItemCategoryVO cat: ItemCategoryVO.getAllCategories()){%>
                    <option  value="<%=cat.getId()%>"><%=cat.getName()%></option>
                    <%}%>
                </select>
                <input type="submit" value="Add">

        </form>
    </div>
    <h3>Manage categories</h3>
    <div style="border:2px black solid;width:auto;" align="center">

        Add new:
        <form action="Webshop" method="post">
            <input type="hidden" name="operation" value="createCategory">

            Category name:<br>
            Set parent:
            <select name="parentCategoryId">
                <option value="-1">-None-</option>
                <%for (ItemCategoryVO cat: ItemCategoryVO.getAllCategories()){%>
                <option  value="<%=cat.getId()%>"><%=cat.getName()%></option>
                <%}%>
            </select>
            <input type="text" name="categoryName" placeholder="Category name"><br>
            <input type="submit" value="Add">
        </form>
        Delete:
        <form action="Webshop" method="post">
            <input type="hidden" name="operation" value="deleteCategory">

            <select name="categoryId">
                <%for (ItemCategoryVO cat: ItemCategoryVO.getAllCategories()){%>
                <option  value="<%=cat.getId()%>"><%=cat.getName()%></option>
                <%}%>
            </select>
            <input type="submit" value="Delete">
        </form>
    </div>
    <h3>All Items:</h3>
        <div style="border:2px black solid;width:auto;height: auto">
            <div>
                <div id="categories" style="float:left;width:200px;height:800px;">
                    <form action="Webshop" method="post">
                        <input type="hidden" name="operation" value="selectCategory">
                        Categories<br>
                        <% for (ItemCategoryVO category: ItemCategoryVO.getAllCategories()) {%>
                        <button style="width:100px;" type="submit" name="selectedCategory" value="<%=category.getId()%>"><%=category.getName()%></button><br>
                        <%}%>
                    </form>
                </div>
                <div id="items" style="height:auto;">
                    <form action="Webshop" method="post">

                        <table style="width: auto">
                            <tr>
                                <th style="width:10%;">Id</th>
                                <th>Name</th>
                                <th style="width:10%;">Price</th>
                                <th>In stock</th>
                                <th style="width:10%;">Select</th>
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
                                    <%=item.getPrice()%>
                                </td>
                                <td>
                                    <%=item.getAmount()%>
                                </td>
                                <td>
                                    <input type="radio" name="selectedItemId" value="<%=item.getId()%>">
                                </td>
                            </tr>
                            <%}%>
                        </table>
                        <input type="submit" name="operation" value="deleteItem">
                        <input type="submit" name="operation" value="manageItem">
                    </form>
                </div>
            </div>
        </div>
    <form action="Webshop" method="post">
        <button type="submit" name="operation" value="adminCenter">
            Back
        </button>
    </form>
    </div>
</body>
</html>
