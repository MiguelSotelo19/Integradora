<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Photo Party</title>
  <link rel="stylesheet" type="text/css" href="/CSS/anadircab.css">
  <link rel="stylesheet" type="text/css" href="/CSS/style.css">


  <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>

  <jsp:include page="/layouts/header.jsp"/>

</head>
<body>

<main class="main">
  <jsp:include page="/layouts/menu.jsp"/>

  <div class="columna2">
    <div class="wrapper">

      <h2>Cabina nueva </h2><br>
      <form action="/cabina/save" method="post" enctype="multipart/form-data">

        <div class="ubox">
          <input name="nombre" id="nombre" class="" required>
          <label>Nombre de cabina</label>
        </div>

        <div class="ubox">
          <input name="costo" id="costo" class="" required>
          <label>Costo</label>
        </div>

        <div class="ubox">
          <input name="extra" id="extra" class="" required name="">
          <label>Precio hora extra</label>
        </div>
        <div class="ubox">
          <label>Descripcion de cabina</label><br>
          <textarea id="descripcion" name="descripcion" spellcheck="false" placeholder="Descripcion...." required></textarea>
        </div>

        <div id="archivos-subidos2" class="uploaded-files"></div>

        <label class="selec" for="imagen" style="color: #0a0a0a">Seleccione la imagen para carrusel</label>
        <input class="fil" type="file" id="imagen" accept="image/*" name="imagen" hidden>

        <div class="ubox">
          <div class="btn">
            <input id="submitBtn" type="submit" value="Guardar" style="margin-top: 6%">

          </div>
        </div>

      </form>

    </div>

  </div>



  <div class="columna3">
    <form id="upload-form" class="upload-form" method="post" action="/cabina/imagenes" enctype="multipart/form-data">
      <h2>Añadir más imágenes</h2>
      <div class="form-group">
        <div class="select-wrapper">


          <select name="cabina" id="cabina" class="select2" required>
            <option disabled selected>Seleccionar cabina</option>
            <c:forEach items="${cabinas}" var="cabina">
              <c:choose>

                <c:when test="${cabina.activo==true}">
                  <option class="op" value="${cabina.nombre}">${cabina.nombre}</option>
                </c:when>
              </c:choose>

            </c:forEach>
          </select>
        </div>
      </div>

      <div id="archivos-subidos" class="uploaded-files"></div>

      <label for="archivo" class="selec">Seleccionar imágen</label>
      <input type="file" id="archivo" name="archivo"  accept="image/*" required hidden>
      <div class="wrapper">
        <div class="ubox">
          <div class="btn">
            <input type="submit" id="subir-archivos" class="upload-button" value="Guardar" />
          </div>
        </div>
      </div>
    </form>

    <script>

      const inputFile = document.getElementById('archivo');
      const inputFile2 = document.getElementById('imagen');
      const imagenPrevisualizacion = document.getElementById('archivos-subidos');
      const imagenPrev = document.getElementById('archivos-subidos2');

      inputFile.addEventListener('change', function(event) {
        const file = event.target.files[0];

        if (file && file.type.startsWith('image/')) {
          const imgPreview = document.createElement('img');
          imgPreview.style.maxWidth = '150px';
          imgPreview.style.maxHeight = '150px';
          imgPreview.src = URL.createObjectURL(file);
          imagenPrevisualizacion.innerHTML = '';
          imagenPrevisualizacion.appendChild(imgPreview);
        } else {
          imagenPrevisualizacion.innerHTML = '<p>No se seleccionó una imagen válida</p>';
        }
      });

      inputFile2.addEventListener('change', function(event) {
        const file = event.target.files[0];

        if (file && file.type.startsWith('image/')) {
          const imgPreview = document.createElement('img');
          imgPreview.style.maxWidth = '100px';
          imgPreview.style.maxHeight = '100px';
          imgPreview.src = URL.createObjectURL(file);
          imagenPrev.innerHTML = '';
          imagenPrev.appendChild(imgPreview);
        } else {
          imagenPrev.innerHTML = '<p>No se seleccionó una imagen válida</p>';
        }
      });
    </script>

  </div>
</main>
<script src="/Js/menu.js"></script>



<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<jsp:include page="/layouts/footer.jsp"/>
</body>
<!--script del boton menu responsive No tocar  -->
<script>

  /**/
  /TEXT AREA/
  const textarea = document.querySelector("textarea");
  textarea.addEventListener("keyup", e => {
    textarea.style.height = "63px";
    let scHeight = e.target.scrollHeight;
    textarea.style.height = `${scHeight}px`;
  });
  /TEXT AREA/
</script>
</html>