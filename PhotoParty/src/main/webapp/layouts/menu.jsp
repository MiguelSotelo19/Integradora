
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="../../CSS/RegEmple.css">
  <link rel="stylesheet" type="text/css" href="../../CSS/style.CSS">
  <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
</head>
<body>
<div class="mov">
  <h1 class="tit-col1">Administrador</h1>
  <div class="column1">
    <div class="select-menu">
      <div class="select-btn">
        <span class="sBtn-text">Gestionar empleados</span>
        <i class="bx bx-chevron-down"></i>
      </div>
      <ul class="options">
        <li class="option">
          <a class="option-text" href="/Photo_Party/usuario/usuario-view">Registrar empleado</a>
        </li>
        <li class="option">
          <a class="option-text" href="/Photo_Party/usuario/usuarios">Modificar empleado</a>
        </li>
      </ul>
    </div>
    <div class="select-menu">
      <div class="select-btn">
        <span class="sBtn-text">Paquetes</span>
        <i class="bx bx-chevron-down"></i>
      </div>
      <ul class="options">

        <li class="option">
          <a class="option-text" href="/Photo_Party/cabina/cabina-view">Añadir cabina </a>
        </li>

        <li class="option">
          <a class="option-text" href="/Photo_Party/cabina/cabinas">Editar cabinas</a>
        </li>

      </ul>
    </div>
    <div class="select-menu">
      <div class="select-btn">
        <span class="sBtn-text">Eventos</span>
        <i class="bx bx-chevron-down"></i>
      </div>
      <ul class="options">
        <li class="option">
          <a class="option-text" href="/Photo_Party/agenda">Agenda</a>
        </li>
        <li class="option">
          <a class="option-text" href="/Photo_Party/seguimiento">Seguimiento de contratos</a>
        </li>
        <li class="option">
          <a class="option-text" href="/Photo_Party/contratos/historial">Contratos Eliminados</a>
        </li>
      </ul>
    </div>
  </div>
</div>
</body>
</html>
