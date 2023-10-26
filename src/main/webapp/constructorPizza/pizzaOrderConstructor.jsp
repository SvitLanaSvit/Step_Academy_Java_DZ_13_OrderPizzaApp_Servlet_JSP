<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Svitlana
  Date: 25.10.2023
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Constructor order pizza</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <jsp:include page = "../menu.jsp"></jsp:include>
    <div class="container">
        <form action="constructor_pizza" method="post">
            <input type="hidden" name="param" value="orderPizza"/>
            <div style="display: flex;">
                <div style="display: flex; flex-wrap: wrap; justify-content: space-around; width: 70%">
                    <c:forEach items="${categories}" var="category">
                        <div class="shadow p-3 mb-5 bg-white rounded">
                            <h3>${category.name}</h3>
                            <c:forEach items="${ingredients}" var="ingredient">
                                <c:if test="${category.name == ingredient.name_category}">
                                    <c:choose>
                                        <c:when test="${ingredient.multi}">
                                            <div>
                                                <input type="checkbox" class="form-check-input" name="ingredients"
                                                       id="ingredient_${ingredient.id}" value="${ingredient.name_ingredient}"/>
                                                <label class="form-check-label" for="pizza_${ingredient.id}">
                                                        ${ingredient.name_ingredient}
                                                </label>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div>
                                                <input class="form-check-input" type="radio" name="ingredient_${category.name}"
                                                       id="ingredient_${ingredient.id}" value="${ingredient.name_ingredient}">
                                                <label class="form-check-label" for="ingredient_${ingredient.id}">
                                                        ${ingredient.name_ingredient}
                                                </label>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </c:forEach>
                        </div>
                    </c:forEach>
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
            <c:if test="${not empty message}">
                <div id="message" class="alert alert-warning">${message}</div>
                <script>
                    setTimeout(function(){
                        document.querySelector(".alert-warning").style.display = "none"
                    }, 3000);
                </script>
            </c:if>
            <div class="d-flex justify-content-end">
                <input type="submit" class="btn btn-outline-success" value="Submit Order" id="submitBtn"/>
            </div>
        </form>
    </div>
</body>
</html>