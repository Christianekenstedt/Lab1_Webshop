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
<body style="font-family: Verdana;">
    <div style="width:500px; margin:auto; position:relative; top:100px;border:2px black solid;padding:20px;">
        <form>
            Id
            <input type="hidden" name="updateUserId" value="<%=editingUser.getId()%>">
            <%=editingUser.getId()%><br><br>
            Username<br>
            <input type="text" name="newUsername" value="<%=editingUser.getUsername()%>"><br><br>
            Role<br>
            <select name="newRoleId">
                <% for(RoleVO role : RoleVO.viewAllRoles()){%>
                    <option <%if(editingUser.getRole().getId() == role.getId()) out.print("selected");%> value="<%=role.getId()%>"><%=role.getName()%></option>
                <%}%>
            </select><br><br><br>

            <button type="submit" name="operation" value="viewUsers">Cancel</button>
            <button style="float:right;" type="submit" name="operation" value="updateUser">Save</button>
        </form>
    </div>
</body>
</html>
