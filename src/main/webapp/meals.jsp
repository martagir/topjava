<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<a href="createReadUpdateMeal">Create meal</a>
<br>
<table border="1" style="border: black">
    <th>Id</th>
    <th>Дата и время</th>
    <th>Описание</th>
    <th>Калории</th>
    <th>Действия</th>
    <c:forEach var="meal" items="${meals}">
       <tr style="${meal.excess ? 'color: red' : 'color: green'}">
           <td>${meal.id}</td>
           <td><javatime:format value="${meal.dateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
           <td>${meal.description}</td>
           <td>${meal.calories}</td>
           <td>
               <a href="createReadUpdateMeal?id=${meal.id}">Редактировать</a>
               <a href="deleteMeal?id=${meal.id}">Удалить</a>
           </td>
       </tr>
    </c:forEach>
</table>
</body>
</html>