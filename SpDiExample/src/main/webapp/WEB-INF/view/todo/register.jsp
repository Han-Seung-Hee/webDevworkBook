<%--
  Created by IntelliJ IDEA.
  User: Han-Seung-Hee
  Date: 2022-12-12
  Time: 오후 8:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
  <form action="/todo/register" method="post">
    <div>
      Title : <input type="text" name="title">
    </div>
    <div>
      DueDate : <input type="date" name="dueDate" value="2022-12-12">
    </div>
    <div>
      Writer : <input type="text" name="writer">
    </div>
    <div>
      Finished : <input type="checkbox" name="finished" value="false">
    </div>
    <div>
      <button type="submit">Register!</button>
    </div>
  </form>
</body>
</html>
