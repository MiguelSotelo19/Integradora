<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/CSS/style.css">
    <link rel="stylesheet" type="text/css" href="/CSS/crearcontratos.css"/>
    <title>Generar contrato</title>
    <jsp:include page="/layouts/header.jsp"/>
</head>
<body>

<main class="main">

    <div class="columna1">
    </div>

    <div class="column2">
        <div class="fbox">
            <h2>Crear contrato</h2>
            <form action="/contrato/save" method="post">

                <div class="ubox">
                    <input type="text" name="nombre" id="nombre" required>
                    <label>Nombre</label>
                </div>
                <label class="esp">Segundo nombre</label>
                <div class="ubox espe" style="margin-top: -4%;">
                    <input type="text" name="segnombre" id="segnombre">
                </div>
                <div class="ubox">
                    <input type="text" name="ape_p" id="ape_p" required>
                    <label>Apellido Paterno</label>
                </div>

                <div class="ubox">
                    <input type="text" name="ape_m" id="ape_m" required>
                    <label>Apellido Materno</label>
                </div>


                <div class="ubox">
                    <input type="number" name="telefono" id="telefono" min="0" maxlength="10" required>
                    <label>Telefono</label>
                </div>

                <div class="ubox">
                    <input type="text" name="correo" id="correo" required>
                    <label>Correo</label>
                </div>

                <label class="esp">Fecha del Evento</label>
                <div class="ubox">
                    <input type="date" name="fecha_ev" id="fecha_ev" oninput="addElement()" required />
                </div>

                <div class="form-group">
                    <div class="select-wrapper">
                <select class="select2" name="hora_inicio" id="hora_inicio" onchange="addHora()">
                    <datalist id="limites">
                        <option disabled selected>Hora de inicio</option>
                        <option></option>
                        <span id="auxiliar"></span>
                    </datalist>
                </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="select-wrapper">
                <select name="hora_final" id="hora_final" class="select2">
                    <datalist id="limite2">
                        <option disabled selected>Hora final</option>
                        <span id="auxiliar2"></span>
                    </datalist>
                </select>
                    </div>
                </div>

                <br>
                <div class="ubox">
                    <input type="text" name="direccion" id="direccion" required>
                    <label>Direccion</label>
                </div>

                <div class="ubox">
                    <input type="text" value="${cabina.nombre}" name="cabina" id="cabina" required>
                    <label>Cabina Seleccionada</label>
                </div>

                <input type="hidden" value="${cabina.costo}" name="costo" id="costo" />

                <br>
                <div class="btn">
                    <input id="submitBtn" type="submit" value="Registrar">
                </div>
            </form>
        </div>
    </div>
    </div>
</main>

<script src="/Js/menu.js"></script>
<script src="/Js/horas.js"></script>
<script>

</script>
<jsp:include page="/layouts/footer.jsp"/>
</body>
</html>
