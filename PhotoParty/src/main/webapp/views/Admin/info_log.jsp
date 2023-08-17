<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Información del contrato</title>
    <link rel="stylesheet" type="text/css" href="/CSS/anadircab.css">
    <link rel="stylesheet" type="text/css" href="/CSS/style.css">
    <link rel="stylesheet" type="text/css"href="/CSS/bootstrap.min.css">
    <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
</head>
<body>
<jsp:include page="/layouts/header.jsp"/>

<main class="main">
    <jsp:include page="/layouts/menu.jsp" />
    <div class="container-fluid">
        <div class="row">
            <h2 class="text-center">Información del Contrato</h2>
            <div class="col mt-5" style="padding-left: 20%">
                <div class="card" style="width: 50rem;">
                    <div class="card-body">
                        <h3>Información del Cliente</h3>
                        <p class="card-text">ID Cliente: ${log.id_cliente}</p>
                        <p class="card-text">Nombre Completo: ${log.nombre} </p>
                        <p class="card-text">Telefono: ${log.telefono}</p>
                        <p class="card-text">Correo: ${log.correo}</p>

                        <br /> <br />
                        <h3>Información del contrato</h3>
                        <p class="card-text">ID Contrato: ${log.id}</p>
                        <p class="card-text">Fecha en la que se realizó el contrato: ${log.fecha_act}</p>
                        <p class="card-text">Fecha del Evento: ${log.fecha_ev}</p>
                        <p class="card-text">Horas Solicitadas: ${log.horas} </p>
                        <p class="card-text">Dirección del Evento ${log.direccion}</p>
                        <p>Total: ${log.monto}</p>

                    </div>
                </div>
            </div>
        </div>
    </div>

</main>


<jsp:include page="/layouts/footer.jsp"/>
<script src="/Js/menu.js"></script>
<script src="/Js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
</body>
</html>