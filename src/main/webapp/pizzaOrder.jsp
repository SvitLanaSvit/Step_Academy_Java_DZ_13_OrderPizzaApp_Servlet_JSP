<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Svitlana
  Date: 24.10.2023
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pizza order</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <jsp:include page = "menu.jsp"></jsp:include>
    <div class="container">
        <form action="pizza" method="post">
            <input type="hidden" name="param" value="orderPizza"/>
            <div style="display: flex">
                <div style="width: 35%">
                    <h3>PIZZA</h3>
                    <c:forEach items="${pizzas}" var="pizza" varStatus="pizzaStatus">
                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" name="pizzaName" id="pizza_${pizza.name}"
                            value="${pizza.name}"/>
                            <label class="form-check-label" for="pizza_${pizza.name}">${pizza.name}</label>
                        </div>
                        <div class="mb-3 w-50">
                            <input type="number" class="form-control" id="quantity_${pizza.name}"
                                   name="${pizza.name}_Quantity" min="1" value="1"/>
                        </div>
                    </c:forEach>
                </div>
                <div style="width: 35%;">
                    <h3>ADDITIONAL INGREDIENTS</h3>
                    <div  style="height: 500px; overflow-y: auto">
                        <c:forEach items="${ingredients}" var="ingredient">
                            <div class="mb-3 form-check">
                                <input type="checkbox" class="form-check-input" name="ingredient"
                                       id="ingredient_${ingredient.name}" value="${ingredient.name}"/>
                                <label class="form-check-label" for="ingredient_${ingredient.name}">
                                        ${ingredient.name}
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="shadow p-3 mb-5 bg-white rounded" style="width: 30%">
                    <h3>DELIVERY</h3>
                    <div class="mb-3 w-100">
                        <div class="mb-3 w-100">
                            <label for="firstname" class="form-label">Firstname:</label>
                            <input type="text" class="form-control" id="firstname" name="firstname" required>
                        </div>
                        <div class="mb-3 w-100">
                            <label for="lastname" class="form-label">Lastname:</label>
                            <input type="text" class="form-control" id="lastname" name="lastname" required>
                        </div>
                        <div class="mb-3 w-100">
                            <label for="phone" class="form-label">Phone`s number:</label>
                            <input type="tel" class="form-control" id="phone" name="phone" required>
                        </div>
                        <div class="mb-3 w-100">
                            <label for="email" class="form-label">E-Mail:</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                        <div class="mb-3 w-100">
                            <label for="region" class="form-label">Select region:</label>
                            <select class="form-control" id="region" name="region" required>
                                <c:forEach items="${regions}" var="region">
                                    <option value="${region.name_region}">${region.name_region}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="d-flex justify-content-end">
                <input type="submit" class="btn btn-outline-primary" value="Submit Order"/>
            </div>
        </form>
    </div>
</body>
</html>
