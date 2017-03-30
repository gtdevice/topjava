<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal{color: green}
        .exceeded{color: red}
    </style>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<div>
    <h2>Meal Form</h2>
    <form action="meals" method="post">
        <table border="0">
            <tr>
                <td>Meal time</td>
                <td><input type="datetime" name="time" value="some time"></td>
            </tr>
            <tr>
                <td>Description</td>
                <td><input type="text" name="descr" value="lunch or something else"></td>
            </tr>
            <tr>
                <td>Calories</td>
                <td><input type="text" name="calories" value="calories of meal"></td>
            </tr>
        </table>
    </form>
</div>
<div>
<h2>Meal list</h2>
<table border="1">
    <tr>
        <td>Time</td>
        <td>Description</td>
        <td>Calories</td>
    </tr>
    <c:forEach var="meal" items="${meals}">
        <jsp:useBean id="meal" class="ru.javawebinar.topjava.model.MealWithExceed" scope="page"/>
        <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
            <td><c:set var="cleanedDateTime" value="${fn:replace(meal.dateTime, 'T', ' ')}"/>
            <c:out value="${cleanedDateTime}"/>
            </td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>