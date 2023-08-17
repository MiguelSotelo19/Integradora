<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Paquetes</title>
  <link rel="stylesheet" href="/CSS/style.css">
  <link rel="stylesheet" href="/CSS/paquetes.css">

  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

  <jsp:include page="/layouts/header.jsp"/>

</head>

<body>

<main class="main">
  <div class="inf">
    <h2 class="titulo">${cabina.nombre}</h2>
    <p>${cabina.descripcion}</p>
    <p class="costo">${cabina.costo}</p>
  </div>


  <div class="imagen-cab">
    <div id="carouselExampleIndicators" class="carousel slide carousel-fixed-size">
      <div class="carousel-inner">

        <div class="carousel-item active">
          <img src="/imagen?id=${cabina.id}" class="d-block w-100" alt="...">
        </div>
        <c:forEach items="${cabinas2}" var="cabinas" >
          <div class="carousel-item active">
            <img src="/imagen2?id2=${cabinas.id}" class="d-block w-100" alt="...">
          </div>
        </c:forEach>

      </div>

      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
              data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
              data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>

      <form action="/Photo_Party/crear_contrato" method="get">
        <input type="hidden" value="${cabina.id}" name="id" id="id">
        <button type="submit" class="btn btn-primary">Agendar contrato</button>
      </form>
    </div>
  </div>

</main>
<jsp:include page="/layouts/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script>
  document.addEventListener("DOMContentLoaded", function () {
    var animatedDiv = document.querySelector(".inf");
    animatedDiv.style.animationDelay = "0s";
    animatedDiv.classList.add("animated-text");
  });
</script>
</body>
</html>