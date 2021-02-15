<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create or update meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meal page</h2>

<form action="${pageContext.request.contextPath}/createReadUpdateMeal" method="post">
    <table>

        <tr>
            <td><label for="id" style="visibility: hidden">Id</label></td>
            <td><input type="text" name="id" id="id" value="${id}" style="visibility: hidden"/></td>
        </tr>

        <tr>
            <td><label for="dateTime">Дата и время</label></td>
            <td><input type="datetime-local" name="dateTime" id="dateTime" value="${dateTime}"/></td>
        </tr>

        <tr>
            <td><label for="description">Описание</label></td>
            <td><input type="text" name="description" id="description" value="${description}"/></td>
        </tr>

        <tr>
            <td><label for="calories">Калории</label></td>
            <td><input type="text" name="calories" id="calories" value="${calories}"/></td>
        </tr>
        <tr></tr>
        <tr>
            <td></td>
            <td><input type="submit" name="submit" value="submit"/></td>
        </tr>
    </table>
</form>
</body>
</html>
