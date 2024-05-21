<%-- 
    Document   : login
    Created on : 2/05/2024, 12:45:16 p. m.
    Author     : ANCIZAR
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Inicio de Sesión</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <style>
            /* Estilos adicionales opcionales */
            body {
                background-image: url('https://www.newmedicaleconomics.es/wp-content/uploads/2022/07/digital-composite-of-doctor-with-white-graph-with-flare-against-blurry-background-with-light-blue-overlay-1200x512.jpg'); /* Reemplaza 'ruta_de_la_imagen.jpg' por la ruta de tu imagen */
                background-size: cover;
                background-repeat: no-repeat;
                background-position: center;
                padding-top: 50px;
                margin: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            .form-login {
                max-width: 350px;
                background-color: rgba(255, 255, 255, 0.8); /* Cambia la opacidad del fondo del formulario */
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0px 0px 10px 0px rgba(0,0,0,1);
                transform: translate(40%, 0%);
            }
            .form-control {
                border-radius: 20px;
            }
            .btn-primary {
                border-radius: 20px;
                width: 100%;
            }
            .btn-home {
                margin-top: 10px;
                border-radius: 10px;
                width: 100%;
            }

        </style>
    </head>
    <body>
        <div class="container">

            <div class="row justify-content-center">
                <div class="col-md-6">
                    <% String error = request.getParameter("error");
                                if (error != null) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= error%>
                    </div>
                    <% }%>                
                    <h2 class="text-center mb-4"> INICIAR SESION</h2>
                    <form class="form-login" action="SvIngresar" method="post">
                        <div class="mb-3">
                            <label for="usuario" class="form-label">cedula: </label>
                            <input type="text" id="cedula" name="cedula"class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Contraseña:</label>
                            <input type="password" id="password" name="contrasenia" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <button type="submit" class="btn btn-primary">Iniciar Sesión</button>
                        </div>
                        <div class="mb-3 text-center">
                            <a href="registro.jsp" class="btn btn-info btn-home">Registrarse</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script>
            // Realizamos un script con el fin de limitar los numeros de la cedula
            document.getElementById('cedula').addEventListener('input', function () {
                if (this.value.length > 10) {
                    this.value = this.value.slice(0, 10);
                }
            });
        </script>
    </body>
</html>
