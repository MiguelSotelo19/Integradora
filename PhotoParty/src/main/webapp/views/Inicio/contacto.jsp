
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Información de Contacto</title>
  <link rel="stylesheet" href="/CSS/contacto.css">
  <link rel="stylesheet" href="/CSS/style.css">
  <jsp:include page="/layouts/header.jsp"/>

</head>

<body>



<main class="main">

  <div class="conteiner">

    <div class="cent">
      <p style="margin-top: auto;">¿Dudas? Escríbenos</p>
      <br>
      <img src="/Img/redes/whatsapp.png" class="a_img"><a href="https://wa.me/7774113800" target="_blank" id="num">7774113800</a>
      <br>
      <img src="/Img/redes/whatsapp.png" class="a_img"> <a href="https://wa.me/7775501299" target="_blank" id="num">7775501299</a>

      <br>
      <div class="correo"> <a href=""> photopartycuernavaca@gmail.com </a> </div>

      <div class="img">
        <img src="/Img/contacto.jpg" alt="" class="contact_img" >

      </div>
    </div>

  </div>

</main>



<jsp:include page="/layouts/footer.jsp"/>
</body>

<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>

<!--script del boton menu responsive No tocar  -->


</html>