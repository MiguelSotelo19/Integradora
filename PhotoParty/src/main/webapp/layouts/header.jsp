
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="../CSS/style.css">


  <title>Title</title>
</head>
<body>


<header class="header">
  <a href="/Photo_Party/inicio"> <img src="../../Img/Logo.jpg" id="log" class="img-fluid"></a>
  <div class="conteiner logo-nav-conteiner">
    <a href="" class="logo">PHOTO PARTY</a>
    <span class="menu-icon">Ver menu</span>
    <nav class="navigation">
      <ul>
        <li><a href="/Photo_Party/inicio">Inicio</a></li>
        <li><a href="/Photo_Party/contacto">Contacto</a></li>
        <li><a href="/Photo_Party/login">Empleado</a></li>

        </ul>
    </nav>
  </div>
</header>


<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
</body>


<!--script del boton menu responsive No tocar  -->

<script>
  // Tu código jQuery va aquí
  $(document).ready(function () {

    var menuBtn = $('.menu-icon'),
            menu = $('.navigation ul');

    menuBtn.click(function () {

      if (menu.hasClass('show')) {

        menu.removeClass('show');

      } else {
        menu.addClass('show');
      }

    });

  });


</script>



</html>