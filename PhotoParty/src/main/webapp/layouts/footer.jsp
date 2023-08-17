<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css"href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="../CSS/style.css">

    <title>Title</title>
</head>
<body>

<footer class="pie">
    <div class="pie-contenedor">
        <div class="pie-columna">

            <div class="pie-enlace">
                <h4>Compañia</h4>
                <ul>
                    <li><a style="text-decoration: none" href="/Photo_Party/informacion#nosotros" target="_blank">Nosotros</a></li>
                    <li><a style="text-decoration: none" href="/Photo_Party/informacion#mision" target="_blank">Misión</a></li>
                    <li><a style="text-decoration: none" href="/Photo_Party/informacion#vision" target="_blank">Visión</a></li>
                </ul>
            </div>


            <div class="pie-enlace">
                <h4>Ayuda</h4>
                <ul>
                    <li><a style="text-decoration: none" href="/Photo_Party/informacion#cancelaciones" target="_blank">Cancelaciones</a></li>
                    <li><a  style="text-decoration: none" href="/Photo_Party/informacion#equipamiento" target="_blank">Equipamiento</a></li>
                    <li><a style="text-decoration: none" href="/Photo_Party/informacion#retardos" target="_blank">Retardos</a></li>
                </ul>
            </div>

            <div class="pie-enlace">
                <h4>Siguenos</h4>
                <div class="social-link">
                    <a href="https://www.facebook.com/lasmejorescabinasdemorelos/" target="_blank"><i
                            class="fab fa-facebook-f"></i></a>
                    <a href="https://www.instagram.com/cabinasphotopartycuernavaca/" target="_blank"><i
                            class="fab fa-instagram"></i></a>
                    <a href="https://wa.me/7775501299" target="_blank"> <i class="fab fa-whatsapp"></i></a>
                    <a href="mailto:photopartycuernavaca@gmail.com?subject=Asunto del correo&body=Cuerpo del correo"
                       target="_blank"><i class="fas fa-envelope"></i></a>
                </div>

            </div>
            <div class="pie-enlace">
                <div class="aviso">
                    <a id="openModal" class="av_priv">Avisos de privacidad</a>
                    <div id="myModal" class="modal2">
                        <div class="contenido-modal2">
                            <span class="cerrar">&times;</span>
                            <h2>Avisos de privacidad</h2>
                            <p>La presente Política de Privacidad establece los términos en que Photo Party utiliza y protege la información que es proporcionada por
                                sus usuarios al momento de utilizar su sitio web. Esta compañía está comprometida con la seguridad de los datos de sus usuarios. Cuando
                                se le solicita llenar los campos de información personal con la cual usted pueda ser identificado, lo hacemos asegurando que sólo se empleará
                                de acuerdo con los términos de este documento. Sin embargo esta Política de Privacidad puede cambiar con el tiempo o ser actualizada por lo que
                                le recomendamos y enfatizamos revisar continuamente esta página para asegurarse que está de acuerdo con dichos cambios.

                                <br /> <br /> <br />
                                <b>Información que es recopilada</b>
                                Nuestro sitio web podrá almacenar información personal por ejemplo: Nombre, información de contacto, cotización, su dirección de correo electrónico e
                                información demográfica. Así mismo cuando sea necesario podrá ser requerida información específica para procesar algún pedido o realizar una entrega o facturación.

                                <br /> <br /> <br />
                                <b>Uso de la información recopilada</b>
                                Nuestro sitio web desea proporcionarle el mejor servicio posible. Es posible que sean enviados correos electrónicos periódicamente a través de nuestro
                                sitio con ofertas especiales, nuevos productos y otra información publicitaria que consideremos relevante para usted o que pueda brindarle algún beneficio,
                                estos correos electrónicos serán enviados a la dirección que usted proporcione y podrán ser cancelados en cualquier momento.</p>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</footer>


<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script>
    var modal = document.getElementById("myModal");
    var btn = document.getElementById("openModal");
    var close = document.getElementsByClassName("cerrar")[0];

    btn.onclick = function () {
        modal.style.display = "block";
    }

    close.onclick = function () {
        modal.style.display = "none";
    }

    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }



</script>
</body>
</html>