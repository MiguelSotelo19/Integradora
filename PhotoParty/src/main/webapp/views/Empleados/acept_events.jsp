<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Empleados</title>
  <link rel="stylesheet" type="text/css" href="/CSS/anadircab.css">
  <link rel="stylesheet" type="text/css" href="/CSS/style.css">
  <link rel="stylesheet" type="text/css"href="/CSS/bootstrap.min.css">
  <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
  <jsp:include page="/layouts/header.jsp"/>
</head>
<body>
<main class="main">
  <jsp:include page="/layouts/menuEmp.jsp" />

  <div class="container-fluid">
    <div class="row">
      <div class="col text-center mt-5">
        <h2>Eventos pendientes por confirmar</h2>
        <div class="card">
          <div class="card-header">
            <div class="row">
              <div class="col">Listado de eventos</div>
            </div>
          </div>
          <div class="card-body">
            <table class="table table-striped">
              <thead>
              <th>ID contrato</th>
              <th>Fecha del Evento</th>
              <th>Hora Inicio</th>
              <th>Hora Final</th>
              <th>Dirección</th>
              <th>Cabina</th>
              <th>Empleados</th>
              <th></th>
              </thead>
              <tbody>
              <c:forEach var="conf" items="${confs}" varStatus="s">
                <tr>
                  <td>
                    <c:out value="${conf.id}"/>
                  </td>
                  <td>
                    <c:out value="${conf.fecha}"/>
                  </td>
                  <td>
                    <c:out value="${conf.inicio}"/>
                  </td>
                  <td>
                    <c:out value="${conf.hrs_final}"/>
                  </td>
                  <td>
                    <c:out value="${conf.direc}"/>
                  </td>
                  <td>
                    <c:out value="${conf.cabina}"/>
                  </td>
                  <td>
                    <c:out value="${conf.emp}"/>
                  </td>
                  <td>
                    <form method="post" action="/empleados_aceptar">
                      <input hidden value="${conf.id}" name="id"/>
                      <button type="submit" class="btn btn-outline-success btn-sm">
                        CONFIRMAR
                      </button>
                    </form>
                  </td>
                </tr>
              </c:forEach>
              <tr>
                <td colspan="6">
                  Sin registros
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>


</main>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script src="/Js/menu.js"></script>
<jsp:include page="/layouts/footer.jsp"/>
</body>
</html>
