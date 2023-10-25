<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container">
    <ul class="navbar-nav">
      <li class="nav-item">
        <form action="pizza" method="post">
          <input type="hidden" name="param" value="readyMadePizza" />
          <input type="submit" class="nav-link" value="ORDER READY-MADE PIZZA">
        </form>
      </li>
      <li class="nav-item">
        <form action="pizza" method="post">
          <input type="hidden" name="param" value="makePizza" />
          <input type="submit" class="nav-link" value="CREATE YOUR OWN PIZZA">
        </form>
      </li>
    </ul>
    <ul class="navbar-nav ml-auto" id="connect">
      <li class="nav-item">
        <form action="pizza" method="post">
          <input type="hidden" name="param" value="connect" />
          <input type="submit" class="nav-link" value="CONNECT TO DB">
        </form>
      </li>
      <li class="nav-item">
        <form action="pizza" method="post">
          <input type="hidden" name="param" value="insert" />
          <input type="submit" class="nav-link" value="INSERT DATA INTO DB" <c:if test="${pizzasSize > 0}">disabled</c:if>>
        </form>
      </li>
    </ul>
  </div>
</nav>