<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <title>Contratos eliminados</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/CSS/style.css">
    <link rel="stylesheet" type="text/css" href="/CSS/cabina.css">
    <link rel="stylesheet" type="text/css"href="/CSS/bootstrap.min.css">
</head>
<body>
<jsp:include page="/layouts/header.jsp"/>
<main class="main">
    <jsp:include page="/layouts/menu.jsp"/>

    <div class="container-fluid">
        <div class="row">
            <div class="col text-center mt-5">
                <h2>Contratos Eliminados</h2>
                <div class="card">
                    <div class="card-header">
                        <div class="row">
                            <div class="col">Listado de eventos</div>
                        </div>
                    </div>
                    <div class="card-body">
                        <table class="table table-striped">
                            <thead>
                            <th>ID Contrato</th>
                            <th>Fecha del Evento</th>
                            <th>Horas Apartadas</th>
                            <th>Direccion</th>
                            <th>Monto</th>
                            <th>Cliente</th>
                            <th>Telefono</th>
                            <th></th>
                            </thead>
                            <tbody>
                            <c:forEach var="log" items="${log}" varStatus="s">
                                <tr>
                                    <td>
                                        <c:out value="${log.id}"/>
                                    </td>
                                    <td>
                                        <c:out value="${log.fecha_ev}"/>
                                    </td>
                                    <td>
                                        <c:out value="${log.horas}"/>
                                    </td>
                                    <td>
                                        <c:out value="${log.direccion}"/>
                                    </td>
                                    <td>
                                        <c:out value="${log.monto}"/>
                                    </td>
                                    <td>
                                        <c:out value="${log.nombre}"/>
                                    </td>
                                    <td>
                                        <c:out value="${log.telefono}"/>
                                    </td>
                                    <td>
                                        <form method="get" action="/Photo_Party/contratos/eliminados">
                                            <input hidden value="${log.id}" name="id"/>
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
<jsp:include page="/layouts/footer.jsp"/>
<script src="/Js/menu.js"></script>
<script src="/Js/bootstrap.bundle.min.js"></script>
</body>
</html>
