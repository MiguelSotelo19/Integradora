<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <title>Información del contrato</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/CSS/style.css">
    <link rel="stylesheet" type="text/css"href="/CSS/bootstrap.min.css">
</head>
<body>
<jsp:include page="/layouts/header.jsp"/>

<main class="main">
    <jsp:include page="/layouts/menu.jsp"/>

    <div class="container-fluid">
        <div class="row">
            <div class="col text-center mt-5">
                <h2>Datos del contrato</h2>

                <div class="accordion" id="accordionExample">
                    <div class="accordion-item">
                        <h2 class="accordion-header">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                Información del Contrato
                            </button>
                        </h2>
                        <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
                            <div class="accordion-body">
                                <strong>ID: ${contrato.id}</strong>

                                <h3>Información del Cliente</h3>
                                <p>Nombre Completo: ${cliente.nombre} ${cliente.segnombre} ${cliente.ape_p} ${cliente.ape_m}</p>
                                <p>Telefono: ${cliente.tel}</p>
                                <p>Correo: ${cliente.correo}</p>

                                <h3>Información del contrato</h3>
                                <p>Fecha en la que se realizó el contrato: ${contrato.actual}</p>
                                <p>Cabina Solicitada: ${contrato.fk_id_cabina}</p>
                                <p>Fecha del Evento: ${solicitud.fecha}</p>
                                <p>Horas Solicitadas: ${solicitud.hr_inicio} &#45; ${solicitud.hr_final}</p>
                                <p>Horas Extra: ${solicitud.extra}</p>
                                <p>Dirección del Evento ${solicitud.direccion}</p>

                                <p>Total: ${contrato.total}</p>

                            </div>
                        </div>
                    </div>

                    <c:forEach var="user" items="${usuarios}" varStatus="s">
                        <c:choose>
                            <c:when test="${s.count==1}">
                                <div class="accordion-item">
                                    <h2 class="accordion-header">
                                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                            <c:out value="${s.count}"/>. Información del Empleado Asignado: ${user.nombre}
                                        </button>
                                    </h2>
                                    <div id="collapseTwo" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
                                        <div class="accordion-body">
                                            <p>Nombre Completo: ${user.nombre} ${user.segnombre} ${user.ape_p} ${user.ape_m}</p>
                                            <p>Telefono: ${user.tel}</p>
                                            <p>Usuario: ${user.usuario}</p>
                                        </div>
                                    </div>
                                </div>
                            </c:when>

                            <c:when test="${s.count==2}">
                                <div class="accordion-item">
                                    <h2 class="accordion-header">
                                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                            <c:out value="${s.count}"/>. Información del Empleado Asignado: ${user.nombre}
                                        </button>
                                    </h2>
                                    <div id="collapseThree" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
                                        <div class="accordion-body">
                                            <p>Nombre Completo: ${user.nombre} ${user.segnombre} ${user.ape_p} ${user.ape_m}</p>
                                            <p>Telefono: ${user.tel}</p>
                                            <p>Usuario: ${user.usuario}</p>
                                        </div>
                                    </div>
                                </div>
                                </div>
                            </c:when>

                            <c:otherwise>
                                Sin empleados asignados
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

            </div>
        </div>
    </div>


</main>

<jsp:include page="/layouts/footer.jsp"/>
<script src="/Js/menu.js"></script>
<script src="/Js/bootstrap.bundle.min.js"></script>
</body>
</html>
