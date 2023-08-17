<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Empleados - Photo Party</title>
    <link rel="stylesheet" href="/CSS/style.css">
    <link rel="stylesheet" href="/CSS/empleado.css">
    <jsp:include page="/layouts/header.jsp"/>

</head>


<body>


<main class="main">


    <div class="login-box">
        <p>Iniciar Sesión</p>
        <form action="/sigin" method="post">
            <div class="user-box">
                <input required name="user" id="user" type="text">
                <label>Usuario</label>
            </div>
            <div class="user-box">
                <input required name="pass" id="pass" type="password">
                <label>Contraseña</label>
            </div>

            <div class="btn">
                <input id="submitBtn" type="submit" value="Ingresar">

            </div>
        </form>
    </div>






</main>




<jsp:include page="/layouts/footer.jsp"/>

</body>

<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>

<!--script del boton menu responsive No tocar  -->
<script>



    // Obtener el botón de inicio de sesión
    var submitBtn = document.getElementById("submitBtn");

    // Agregar clase al botón al hacer clic
    submitBtn.addEventListener("click", function() {
        submitBtn.classList.add("animated");
    });


</script>



</html>