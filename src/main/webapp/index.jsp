<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tagirovmf
  Date: 19.02.21
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Java Enterprise (Topjava)</title>
</head>
<body>
<h3>Проект <a href="https://github.com/JavaWebinar/topjava" target="_blank">Java Enterprise (Topjava)</a></h3>
<hr>
<form action="users" method="post">
    <h3>Id авторизованного юзера: ${userId}</h3>
    <p>Выберите пользователя
        <select name="userId" >
            <option value=1>Юзер</option>
            <option value=2>Админ</option>
        </select></p>
    <p><input type="submit" value="Отправить"></p>
</form>
<ul>
    <li><a href="meals">Meals</a></li>
    <c:if test="${isAdmin}">
        <li><a href="users">Users</a></li>
    </c:if>
</ul>
</body>
</html>
