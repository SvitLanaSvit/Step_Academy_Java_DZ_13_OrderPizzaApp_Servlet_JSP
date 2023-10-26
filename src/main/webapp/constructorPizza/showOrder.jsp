<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Svitlana
  Date: 26.10.2023
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show order</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <jsp:include page = "../menu.jsp"></jsp:include>
    <div class="container">
        <h3>YOUR ORDER</h3>
        <hr/>
        <div style="display: flex">
            <div style="width: 50%" class="shadow p-3 mb-5 bg-white rounded">
                <h4>PIZZA</h4>
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>Ingredients</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${ingredientsRadio}" var="ingredientRadio">
                        <tr>
                            <td>${ingredientRadio}</td>
                        </tr>
                    </c:forEach>
                    <c:forEach items="${ingredients}" var="ingredient">
                        <tr>
                            <td>${ingredient}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div style="width: 50%" class="shadow p-3 mb-5 bg-white rounded">
                <h4>DELIVERY</h4>
                <table class="table table-striped table-hover">
                    <tr>
                        <th>Firstname</th>
                        <td>${delivery.firstname}</td>
                    </tr>
                    <tr>
                        <th>Lastname</th>
                        <td>${delivery.lastname}</td>
                    </tr>
                    <tr>
                        <th>Phone</th>
                        <td>${delivery.phone}</td>
                    </tr>
                    <tr>
                        <th>E-Mail</th>
                        <td>${delivery.email}</td>
                    </tr>
                    <tr>
                        <th>Region</th>
                        <td>${delivery.region}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
