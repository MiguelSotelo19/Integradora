<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administrar Empleados</title>
    <link rel="stylesheet" type="text/css" href="/CSS/style.css">
    <link rel="stylesheet" type="text/css" href="/CSS/modempleado.css">
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
                <h2>Usuarios</h2>
                <div class="card">
                    <div class="card-header">
                        <div class="row">
                            <div class="col">Listado de usuarios</div>
                        </div>
                    </div>
                    <div class="card-body">
                        <table class="table table-striped">
                            <thead>
                            <th>#</th>
                            <th>Nombre Completo</th>
                            <th>Telefono</th>
                            <th>Usuario</th>
                            </thead>
                            <tbody>
                            <c:forEach var="usuario" items="${usuarios}" varStatus="s" >
                                <tr>
                                    <td>
                                        <c:out value="${s.count}"/>
                                    </td>
                                    <td>
                                        <c:out value="${usuario.nombre}"/> <c:out value="${usuario.segnombre}"/>
                                        <c:out value="${usuario.ape_p}"/> <c:out value="${usuario.ape_m}"/>
                                    </td>
                                    <td>
                                        <c:out value="${usuario.tel}"/>
                                    </td>
                                    <td>
                                        <c:out value="${usuario.usuario}"/>
                                    </td>
                                    <td>
                                        <form method="get" action="/Photo_Party/usuario/usuario-view-update">
                                            <input hidden value="${usuario.id}" name="id"/>
                                            <button type="submit" class="btn btn-outline-warning btn-sm">
                                                EDITAR
                                            </button>
                                        </form>
                                        <c:if test="${usuario.id!=1}">
                                        <form method="post" action="/usuario/delete">
                                            <input hidden value="${usuario.usuario}" name="id"/>
                                            <button type="submit" class="btn btn-outline-danger btn-sm">
                                                ELIMINAR
                                            </button>
                                        </form>
                                        </c:if>
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