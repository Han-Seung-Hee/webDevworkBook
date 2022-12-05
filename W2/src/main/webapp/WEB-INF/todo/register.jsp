<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form action="/todo/register" method="post">
    <div>
        <label>
            <input type="text" name = "title" placeholder="type your todo detail"/>
        </label>
    </div>
    <div>
        <label>
            <input type="date" name = "dueDate"/>
        </label>.
    </div>
    <button type="reset">RESET</button>
    <button type="submit">REGISTER</button>
</form>
</body>
</html>
