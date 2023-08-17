<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Seguimiento de contratos</title>
  <link rel="stylesheet" type="text/css" href="/CSS/cabina.css">
  <link rel="stylesheet" type="text/css" href="/CSS/style.css">
  <link rel="stylesheet" type="text/css"href="/CSS/bootstrap.min.css">
  <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
  <jsp:include page="/layouts/header.jsp"/>
</head>
<body>

<main class="main">
  <jsp:include page="/layouts/menu.jsp"/>


  <div class="container-fluid">
    <div class="row">
      <div class="col text-center mt-5">
        <h2>Contratos y Empleados</h2>
        <div class="card">
          <div class="card-header">
            <div class="row">
              <div class="col">Listado de los contratos y los empleados</div>
            </div>
          </div>
          <div class="card-body">
            <table class="table table-striped">
              <thead>
              <th>ID contrato</th>
              <th>Fecha del Evento</th>
              <th>Horas Apartadas</th>
              <th>Direccion</th>
              <th>Nombre del Empleado</th>
              <th>Telefono</th>
              <th>Tipo Cabina</th>
              <th></th>
              </thead>
              <tbody>
              <c:forEach var="rel" items="${rels}" varStatus="s">
                <tr>
                  <td>
                    <c:out value="${rel.id}"/>
                  </td>
                  <td>
                    <c:out value="${rel.fecha}"/>
                  </td>
                  <td>
                    <c:out value="${rel.horas}"/>
                  </td>
                  <td>
                    <c:out value="${rel.direccion}"/>
                  </td>
                  <td>
                    <c:out value="${rel.nombre}"/>
                  </td>
                  <td>
                    <c:out value="${rel.telefono}"/>
                  </td>
                  <td>
                    <c:out value="${rel.cabina}"/>
                  </td>
                  <td>
                    <form method="get" action="/Photo_Party/contrato/info">
                      <input hidden value="${rel.id}" name="id"/>
                      <button type="submit" class="btn btn-outline-secondary btn-sm">
                        Información
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
<script src="/Js/bootstrap.bundle.min.js"></script>


<jsp:include page="/layouts/footer.jsp"/>
</body>
</html>
