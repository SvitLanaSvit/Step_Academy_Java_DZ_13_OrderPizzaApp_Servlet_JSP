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
                <div style="width: 50%">
                    <c:forEach items="${pizzas}" var="pizza" varStatus="pizzaStatus">
                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" name="pizzaName" id="pizza_${pizza.name}"
                            value="${pizza.name}"/>
                            <label class="form-check-label" for="pizza_${pizza.name}">${pizza.name}</label>
                        </div>
                        <p>STATUS: ${pizzaStatus.index}</p>
                        <div class="mb-3 w-50">
                            <input type="number" class="form-control" id="quantity_${pizza.name}"
                                   name="${pizza.name}_Quantity" min="1" value="1"/>
                        </div>
                    </c:forEach>
                </div>
                <div style="width: 50%;">
                    <h3>additional ingredients</h3>
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
            </div>
            <input type="submit" class="btn btn-outline-primary" value="Submit Order"/>
        </form>
    </div>
</body>
</html>
