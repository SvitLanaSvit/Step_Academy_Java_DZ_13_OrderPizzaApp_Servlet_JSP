<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Svitlana
  Date: 25.10.2023
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <jsp:include page = "menu.jsp"></jsp:include>
    <div class="container">
        <h3>YOUR ORDER</h3>
        <hr/>
        <h4>READY PIZZA</h4>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>NAME PIZZA</th>
                <th>COUNT</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pizzaOrderList}" var="pizzaOrder">
                <tr>
                    <td>${pizzaOrder.name}</td>
                    <td>${pizzaOrder.count}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <h4>ADDITIONAL INGREDIENTS FOR PIZZA</h4>
        <ul>
            <c:forEach items="${ingredients}" var="ingredient">
                <li>${ingredient}</li>
            </c:forEach>
        </ul>
    </div>
</body>
</html>
