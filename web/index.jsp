<%@ page import="java.util.Date" %>
<%@ page import="VO.ItemVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Test</title>
  </head>
  <body>


  <% for(ItemVO item : ItemVO.getAll()){
    out.print(item.getName() + " " + item.getId() + "<br>");
  }%>


  <%= ItemVO.get(15).getName()%>

  <form action="index.jsp" method="post">
      <input id="name" type="text"/>
      <button type="submit">Save</button>
  </form>

  </body>
</html>