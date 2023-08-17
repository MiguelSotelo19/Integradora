<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <title>Cabinas</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Photo Party</title>
    <link rel="stylesheet" type="text/css" href="/CSS/RegEmple.css">
    <link rel="stylesheet" type="text/css" href="/CSS/cabina.css">
    <link rel="stylesheet" type="text/css" href="/CSS/style.css"/>
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
                <h2>Cabinas</h2>
                <div class="card">
                    <div class="card-header">
                        <div class="row">
                            <div class="col">Listado de cabinas</div>
                        </div>
                    </div>
                    <div class="card-body">
                        <table class="table table-striped">
                            <thead>
                            <th>#</th>
                            <th>Nombre</th>
                            <th>Descripcion</th>
                            <th>Costo</th>
                            <th>Precio Horas Extra</th>
                            <th>Activo</th>
                            <th></th>
                            </thead>
                            <tbody>
                            <c:forEach var="cabina" items="${cabinas}" varStatus="s">
                                <tr>
                                    <td>
                                        <c:out value="${s.count}"/>
                                    </td>
                                    <td>
                                        <c:out value="${cabina.nombre}"/>
                                    </td>
                                    <td>
                                        <c:out value="${cabina.descripcion}"/>
                                    </td>
                                    <td>
                                        <c:out value="${cabina.costo}"/>
                                    </td>
                                    <td>
                                        <c:out value="${cabina.extra}"/>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${cabina.activo==true}">
                                                Activo
                                            </c:when>
                                            <c:otherwise>
                                                Desactivado
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <form method="get" action="/Photo_Party/cabina/cabina-view-update">
                                            <input hidden value="${cabina.id}" name="id" id="id"/>
                                            <button type="submit" class="btn btn-outline-warning btn-sm">
                                                EDITAR
                                            </button>
                                        </form>
                                        <c:choose>
                                            <c:when test="${cabina.activo==true}">
                                                <form method="post" action="/cabina/delete">
                                                    <input hidden value="${cabina.nombre}" name="id"/>
                                                    <button type="submit" class="btn btn-outline-danger btn-sm">
                                                        DESACTIVAR
                                                    </button>
                                                </form>
                                            </c:when>
                                            <c:otherwise>
                                                <form method="post" action="/cabina/activar">
                                                    <input hidden value="${cabina.nombre}" name="nombre"/>
                                                    <button type="submit" class="btn btn-outline-success btn-sm">
                                                        ACTIVAR
                                                    </button>
                                                </form>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="9" style="text-align: center">
                                    Sin más registros
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
