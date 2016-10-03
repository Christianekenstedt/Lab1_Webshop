<%@ page import="VO.RoleVO" %>
<%@ page import="VO.UserVO" %><%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 2016-09-30
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% UserVO editingUser = UserVO.getUserByID(Integer.parseInt(request.getParameter("selectedUser")));%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div style="width:500px; margin:auto; position:relative; top:100px;">
        <form>
            Username<br>
            <input type="text" name="username" value="<%=editingUser.getUsername()%>"><br>
            Role<br>
            <select name="selectedRole">
                <% for(RoleVO role : RoleVO.viewAllRoles()){%>
                    <option <%if(editingUser.getRole().getId() == role.getId()) out.print("selected");%> value="<%=role.getId()%>"><%=role.getName()%></option>
                <%}%>
            </select><br>
            <button type="submit" name="operation" value="updateUser">Save</button>
            <button type="submit" name="operation" value="viewUsers">Cancel</button>
        </form>
    </div>
</body>
</html>
