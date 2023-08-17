<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar-Cabina</title>
    <link rel="stylesheet" type="text/css" href="/CSS/anadircab.css">
    <link rel="stylesheet" type="text/css"href="/CSS/bootstrap.min.css">
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
            <form action="/cabina/update" method="post" id="editar" enctype="multipart/form-data">

                <input class="" name="nombre_old" id="nombre_old" value="${cabina.nombre}" hidden>
                <div class="ubox">
                    <input class="" name="nombre" id="nombre" value="${cabina.nombre}" required>
                    <label for="nombre">Nombre de cabina</label>
                </div>
                <div class="ubox">
                    <input class="" name="costo" id="costo"  value="${cabina.costo}" required>
                    <label for="costo">Costo</label>
                </div>
                <div class="ubox">
                    <input class="" name="extra" id="extra"  value="${cabina.extra}" required>
                    <label for="extra">Precio hora extra</label>
                </div>
                <div class="ubox">
                    <label for="descripcion">Descripcion de cabina</label><br>
                    <textarea spellcheck="false" name="descripcion" id="descripcion" placeholder="Descripcion...." required>${cabina.descripcion}</textarea>
                </div>
                <div class="ubox">
                    <input class="fil" type="file" id="imagen" accept="image/*" name="imagen" >

                </div>
                    <div class="btn">
                        <input id="submitBtn" type="submit" value="Guardar">
                    </div>
            </form>
        </div>
    </div>



        <div class="columna3">

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
                            <th>Imagen</th>
                            <th></th>
                            </thead>
                            <tbody>
                            <c:forEach var="cabina2" items="${cabinas2}" varStatus="s">
                                <tr>
                                    <td>
                                        <c:out value="${cabina2.id}"/>
                                    </td>

                                    <td>
                                        <c:out value="${cabina2.nombre}"/>
                                    </td>

                                    <td>
                                        <img src="/imagen2?id2=${cabina2.id}" style="height: 100px" style=" width:100px " >
                                    </td>

                                    <td>
                                            <form method="post" action="/cabina/eliminar">
                                                <label >${cabina2.id}</label>
                                                <input hidden value="${cabina2.id}" name="id"/>
                                                <button type="submit" class="btn btn-outline-danger btn-sm">
                                                    Eliminar
                                                </button>
                                            </form>

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



        </div>



</main>



<script src="/Js/menu.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<jsp:include page="/layouts/footer.jsp"/>

</body>



<!--script del boton menu responsive No tocar  -->
<script>



    /**/


    /*TEXT AREA*/

    const textarea = document.querySelector("textarea");
    textarea.addEventListener("keyup", e => {
        textarea.style.height = "63px";
        let scHeight = e.target.scrollHeight;
        textarea.style.height = `${scHeight}px`;
    });


    /* FIN TEXT AREA*/




</script>



</html>