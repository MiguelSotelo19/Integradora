
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Photo Party</title>
  <link rel="stylesheet" type="text/css" href="/CSS/RegEmple.css">
  <link rel="stylesheet" type="text/css" href="/CSS/style.css">
  <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>

  <jsp:include page="/layouts/header.jsp"/>

</head>

<body>


<main class="main">
  <jsp:include page="/layouts/menu.jsp"/>

  <div class="column2">
    <div class="fbox">

      <h2>Registrar Empleado</h2>

      <br><br><br>
      <form action="/usuario/save" method="post">
        <div class="ubox">
          <input type="text" id="nombre" name="nombre" required>
          <label>Nombre</label>
        </div>

        <div class="ubox">
          <input type="text" id="segnombre" name="segnombre">
          <label>Segundo nombre</label>
        </div>

        <div class="ubox">
          <input type="text" id="ape_p" name="ape_p" required>
          <label>Apellido Paterno</label>
        </div>

        <div class="ubox">
          <input type="text" id="ape_m" name="ape_m" required>
          <label>Apellido Materno</label>
        </div>

        <div class="ubox">
          <input type="text" name="telefono" id="telefono" min="0" maxlength="10" required>
          <label>Telefono</label>
        </div>

        <div class="ubox">
          <input type="tel" name="user" id="user" required>
          <label>Usuario</label>
        </div>


        <div class="ubox">
          <input type="text" name="contra" id="contra" required>
          <label>Contraseña</label>


          <br><br><br>

          <div class="btn">
            <input id="submitBtn" type="submit" value="Registrar">
          </div>
        </div>
      </form>
    </div>
  </div>



</main>



<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script src="/Js/menu.js"></script>
<jsp:include page="/layouts/footer.jsp"/>

</body>



</html>