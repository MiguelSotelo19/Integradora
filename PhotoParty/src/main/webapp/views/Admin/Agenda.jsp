<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agenda</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="/CSS/main.min.css">
    <link rel="stylesheet" href="/CSS/agenda.css">
    <jsp:include page="/layouts/header.jsp"/>
</head>
<body>

<main class="main">
    <jsp:include page="/layouts/menu.jsp"/>
    <div class="container"  style="background-color: #ffffff ">
        <div id='calendar'></div>
    </div>
    <div class="modal fade modal" id="formModal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdrop" aria-hidden="true"  >
        <div class="modal-dialog">
            <div class="modal-content" >
                <div class="modal-header bg-info" >
                    <h5 class="modal-title" id="titulo">Modal title</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="" id='formulario' method="post">
                    <div class="modal-body">
                        <div class="form-floating mb-3">
                            <input type="text" id="id" name="id" class="form-control" disabled>
                            <label for="id" class="form-label">Id</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="nombre" name="nombre" pattern="[A-Za-z]+" maxlength="15">
                            <label for="nombre" class="form-label">Nombre</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="segnombre" name="segnombre" pattern="[A-Za-z]+">
                            <label for="segnombre" class="form-label">Segundo nombre</label>
                        </div>

                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="ape_p" name="ape_p" pattern="[A-Za-z]+">
                            <label for="ape_p" class="form-label">Apellido Paterno </label>
                        </div>
                        <div class="form-floating mb-3">

                            <input type="text" class="form-control" id="ape_m" name="ape_m" pattern="[A-Za-z]+">
                            <label for="ape_m" class="form-label">Apellido materno </label>
                        </div>

                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="direccion" name="direccion" pattern="[A-Za-z]+">
                            <label for="direccion" class="form-label">Dirección</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="number" class="form-control" id="telefono" name="telefono" min="0"  maxlength="10">
                            <label for="telefono" class="form-label">Teléfono</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="email" class="form-control" id="correo" name="correo" >
                            <label for="correo" class="form-label">Correo</label>
                        </div>


                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="start" name="start">
                            <label for="start" class="form-label">Fecha de Evento</label>
                        </div>

                        <div class="form-floating mb-3">
                            <select name="hora_inicio" id="hora_inicio" class="form-control" onchange="addHoraAgenda()">
                                <datalist id="limites">
                                    <span id="auxiliar"></span>
                                </datalist>
                            </select>
                            <label for="hora_inicio" class="form-label">Hora de inicio</label>
                        </div>

                        <div class="form-floating mb-3">
                            <select class="form-control" name="hora_final" id="hora_final">
                                <datalist id="limite2">
                                    <span id="auxiliar2"></span>
                                </datalist>
                            </select>
                            <label for="hora_final" class="form-label">Hora final</label>
                        </div>

                        <div class="form-floating mb-3">
                            <input type="number" class="form-control" id="hora_extra" name="hora_extra">
                            <label for="hora_extra" class="form-label">Horas Extras</label>
                        </div>


                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="cabina" name="cabina">
                            <label for="cabina" class="form-label">Cabina:</label>
                        </div>


                        <div class="form-floating mb-3">
                            <input type="number" class="form-control" id="total" name="total" maxlength="10" max="1000000" >
                            <label for="total" class="form-label">Total a Cobrar</label>
                        </div>

                        <div class="form-floating mb-3">
                            <input type="color" class="form-control" id="color" name="color">
                            <label for="color" class="form-label">Color</label>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-warning" type="button" data-bs-dismiss="modal">Cancelar</button>
                        <button class="btn btn-danger" type="button" id="btnEliminar">Eliminar</button>
                        <button class="btn btn-info" id='btnAccion' type="submit">Registrar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</main>
<script>
    function limitarTelefono() {
        const telefonoInput = document.getElementById("telefono");
        const maxChars = 10;
        if (telefonoInput.value.length > maxChars) {
            telefonoInput.value = telefonoInput.value.slice(0, maxChars);
        }
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="/Js/main.min.js"></script>
<script src="/Js/es.js"></script>
<script src="/Js/sweetalert2.all.min.js"></script>
<script src="/Js/app.js?v=1"></script>
<script src="/Js/menu.js"></script>
<jsp:include page="/layouts/footer.jsp"/>
</body>
</html>
