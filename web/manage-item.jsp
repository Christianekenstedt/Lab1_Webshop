<%@ page import="VO.ItemVO" %>
<%@ page import="VO.ItemCategoryVO" %><%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 2016-10-03
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ItemVO editingItem = ItemVO.get(Integer.parseInt(request.getParameter("selectedItemId")));%>
<html>
<head>
    <title>Title</title>
</head>
<body style="font-family: Verdana;">
<div style="width:500px; margin:auto; position:relative; top:100px;border:2px black solid;padding:20px;">
    <form>
        Id
        <input type="hidden" name="updateItemId" value="<%=editingItem.getId()%>">
        <%=editingItem.getId()%><br><br>
        Name<br>
        <input type="text" name="newItemName" value="<%=editingItem.getName()%>"><br><br>
        Price<br>
        <input type="number" name="newItemPrice" value="<%=editingItem.getPrice()%>">
        Category<br>
        <select name="newCategoryId">
            <% for(ItemCategoryVO category : ItemCategoryVO.getAllCategories()){%>
            <option
                <%if(editingItem.getCategory() != null && editingItem.getCategory().getId() == category.getId())
                    out.print("selected");%> value="<%=category.getId()%>"><%=category.getName()%>
            </option>
            <%}%>
        </select><br><br><br>

        Amount<br>
        <input type="text" name="newAmount" value="<%=editingItem.getAmount()%>"><br><br><br>

        <button type="submit" name="operation" value="manageItems">Cancel</button>
        <button style="float:right;" type="submit" name="operation" value="updateItem">Save</button>
    </form>
</div>
</body>
</html>
