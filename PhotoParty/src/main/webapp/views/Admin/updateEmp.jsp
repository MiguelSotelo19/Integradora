
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <title>Actualizar Usuario</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Photo Party</title>
    <link rel="stylesheet" type="text/css" href="/CSS/RegEmple.css">
    <link rel="stylesheet" type="text/css" href="/CSS/style.css">
    <link rel="stylesheet" type="text/css"href="/CSS/bootstrap.min.css">
    <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
    <jsp:include page="/layouts/header.jsp"/>

</head>
<body>
<main class="main">
    <jsp:include page="/layouts/menu.jsp"/>

    <div class="container-fluid">
        <div class="row">
            <div class="col">
                <div class="card mt-5">
                    <div class="card-header">Actualización de Usuario</div>
                    <div class="card-body">
                        <form id="user-form" class="needs-validation" novalidate
                              action="/usuario/update" method="post">
                            <input hidden value="${usuario.id}" name="id">
                            <div class="form-group mb-3">
                                <div class="row">
                                    <div class="col">
                                        <label for="nombre" class="fw-bold">Nombre:</label>
                                        <input type="text" name="nombre" id="nombre" class="form-control"
                                               value="${usuario.nombre}" required/>
                                        <div class="invalid-feedback">Campo obligatorio</div>
                                    </div>
                                    <div class="col">
                                        <label for="segnombre" class="fw-bold">Segundo nombre:</label>
                                        <input type="text" name="segnombre" id="segnombre" class="form-control"
                                                value="${usuario.segnombre}"/>
                                        <div class="invalid-feedback">Campo obligatorio</div>
                                    </div>
                                    <div class="col">
                                        <label for="ape_p" class="fw-bold">Apellido Paterno:</label>
                                        <input type="text" name="ape_p" id="ape_p" class="form-control"
                                               required value="${usuario.ape_p}"/>
                                        <div class="invalid-feedback">Campo obligatorio</div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group mb-3">
                                <div class="row">
                                    <div class="col">
                                        <label for="ape_m" class="fw-bold">Apellido Materno:</label>
                                        <input type="text" name="ape_m" id="ape_m" class="form-control"
                                               required value="${usuario.ape_m}"/>
                                        <div class="invalid-feedback">Campo obligatorio</div>
                                    </div>
                                    <div class="col">
                                        <label for="telefono" class="fw-bold">Telefono:</label>
                                        <input type="text" name="telefono" id="telefono" class="form-control"
                                               required value="${usuario.tel}"/>
                                        <div class="invalid-feedback">Campo obligatorio</div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group mb-3">
                                <div class="row">
                                    <div class="col">
                                        <label for="user" class="fw-bold">Usuario:</label>
                                        <input type="text" name="user" id="user" class="form-control"
                                               required value="${usuario.usuario}"/>
                                        <div class="invalid-feedback">Campo obligatorio</div>
                                    </div>
                                    <div class="col">
                                        <label for="contra" class="fw-bold">Contraseña:</label>
                                        <input type="text" name="contra" id="contra" class="form-control"
                                               required value="${usuario.contrasenia}"/>
                                        <div class="invalid-feedback">Campo obligatorio</div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group mb-3">
                                <div class="row">
                                    <div class="col text-end">
                                        <a href="/Photo_Party/usuario/usuarios" class="btn btn-outline-danger btn-sm">
                                            CANCELAR
                                        </a>
                                        <button type="submit" class="btn btn-outline-primary btn-sm">
                                            ACEPTAR
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>


<script>
    (function () {
        const form = document.getElementById("user-form");
        form.addEventListener("submit", function (event) {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add("was-validate");
        }, false)
    })();
</script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script src="/Js/menu.js"></script>
<jsp:include page="/layouts/footer.jsp"/>

</body>
</html>
