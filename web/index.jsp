<%@ page import="java.util.Date" %>
<%@ page import="VO.ItemVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Test</title>
  </head>
  <body>
  <% ItemVO item = ItemVO.viewItem(1); %>
  Name: <%=item.getName()%>
  </body>
</html>