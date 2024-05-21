<%-- 
    Document   : indexAdmin
    Created on : 4/05/2024, 9:49:55?a.?m.
    Author     : ANCIZAR
--%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.pqrs.tablaPQRS"%>
<%@page import="com.mycompany.pqrs.tablaPQRS"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.mycompany.pqrs.controlUsuario"%>
<!DOCTYPE html>
<html lang="es">
    <%
        controlUsuario controlU = new controlUsuario();
        List<tablaPQRS> listar = controlU.todosPqrs();
    %>
    <head>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin - PQRS</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="styles.css">

        <style>
            body {
                background-size: cover;
                background-position: center;
                margin: 0;
                height: 100vh;
                background-image: url('imagenFondo/medinuclear.jpg');
            }

            /* Estilos del sidebar */
            #sidebar {
                position: fixed;
                top: 0;
                left: 0;
                height: 100%;
                width: 250px;
                background-color: #f3e0bc; /* Color café claro */
                z-index: 1000;
                box-shadow: 2px 0 5px rgba(0, 0, 0, 0.5); /* Sombreado negro */
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            .sidebar-nav {
                margin-top: 20px;
                padding: 0;
                list-style: none;
                text-align: center;
                flex-grow: 1;
                display: flex;
                flex-direction: column;
                justify-content: center;
            }

            .sidebar-item {
                margin-bottom: 10px;
            }

            .sidebar-link {
                display: block;
                padding: 10px;
                color: white !important; /* Color blanco para enlaces */
                text-decoration: none;
                text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.8); /* Sombreado negro */
            }

            .sidebar-link:hover {
                background-color: #d5c1a1; /* Color de fondo al pasar el ratón */
            }



            .container {
                background-color: rgba(255, 255, 255, 0.8);
                padding: 30px;
                border-radius: 10px;
                width: 80%;
                max-width: 600px;
                position: absolute;
                left: 50%;
                top: 50%;
                transform: translate(-50%, -50%);
                display: none;
                text-align: left;
                position: center;
            }

            .welcome-container {
                background-color: rgba(255, 255, 255, 0.8);
                padding: 30px;
                border-radius: 10px;
                width: 80%;
                max-width: 600px;
                position: absolute;
                left: 50%;
                top: 50%;
                transform: translate(-50%, -50%);
                text-align: center;
                display: block;
            }

            .inicio {
                margin-bottom: 20px; /* Espacio inferior */
            }

            .contact-info {
                padding: 20px;
                border: 1px solid #f3e0bc; /* Borde estilo cuadrícula */
                border-radius: 10px;
                margin-bottom: 20px; /* Espacio inferior */
                color: brown;
                text-align: center;
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                border-color: brown;
            }

            .contact-info p {
                margin-bottom: 10px;
            }

        </style>
    </head>


    <body>
        <aside id="sidebar">
            <div class="h-100">
                <div class="sidebar-logo"></div>
                <!-- Sidebar Navigation -->
                <ul class="sidebar-nav">
                    <li class="sidebar-header" style="color:black">
                        <h2>ADMINISTRADOR</h2>
                    </li>

                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link" id="mostrarFormulario" style="color:white;">
                            <i class="fa-solid fa-book-bookmark"  style="color:white;"></i>
                            Mostrar TODAS LAS PQRS
                        </a>
                    </li>

                    <li class="sidebar-item">
                        <a href="index.jsp" class="sidebar-link" id="InicioPagina" style="color:white;">
                            <i class="fa fa-hand-peace-o"  style="color:white;"></i>
                            Regresar Inicio
                        </a>
                    </li>
                </ul>
            </div>
        </aside> 

        <div class="container" id="tabla"  >

            <table class="table" >  
                <thead>
                    <tr>
                        <th>Tipo de solicitud</th>
                        <th>Descripcion</th>
                        <th>Archivo adjunto </th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (tablaPQRS list : listar) {

                    %>
                    <tr>
                        <td><%= list.getTipoPQRS()%></td>
                        <td><%= list.getDescripcion()%></td>
                        <% if (list.getArchivo_adj().isBlank()) { %>
                        <td><button class="btn btn-outline-secondary""> Sin documento </button></td>
                        <% } else {%>
                        <td><button class="btn btn-outline-info" onclick="window.open('Archivos/<%= list.getArchivo_adj()%>', '_blank')">Abrir PDF</button></td>
                        <% }%>
                        <td><%= list.getEstado()%></td>
                        <td>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" data-bs-toggle="modal" data-bs-target="#responderModal" data-nombre="<%=list.getID_pqrs()%>">Responder</button>
                        </td>
                    </tr>
                    <%
                        }
                    %> 
                </tbody>
            </table>
        </div>
                
                <!-- Modal para responder la PQRS -->
        <div class="modal fade" id="responderModal" tabindex="-1" role="dialog" aria-labelledby="pqrsModalLabel">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="pqrsModalLabel">Responder PQRS</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="SvResponder" method="POST" >

                            <div class="form-group">
                                <center><label for="Pqrs">Responder</label></center>      
                            </div>
                            <div class="form-group">
                                <input type="text" id="Respuesta" name="Respuesta" class="form-control" placeholder="Escribe tu respuesta" >
                            </div>
                            <div id="pqrs-responder">
                            </div> 

                            <button type="submit" class="btn btn-primary btn-block">Enviar</button>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </body>         
    <script>   document.getElementById('mostrarFormulario').addEventListener('click', function () {
            // Desplazar la página hacia la sección de la tabla de PQRS
            document.getElementById('tabla').style.display = 'block';
            document.querySelector('.welcome-container').style.display = 'none';
            document.getElementById('formPQRS').style.display = 'none';
        });
    </script>
    <script>
        //Responder la PQRS
        $(document).ready(function () {
            // Al hacer clic en un elemento con el atributo data-bs-toggle="responder"
            $('[data-bs-toggle="responder"]').on('click', function () {
                // Obtener el id de la PQRS desde el atributo data-nombre
                var id = $(this).data('nombre');
                // Realizar una solicitud AJAX para obtener los valores de la PQRS por su id
                $.ajax({
                    url: 'SvResponderPqrs?id=' + id, // URL del servlet o recurso que maneja la solicitud
                    method: 'GET',
                    success: function (data) {
                        $('#pqrs-responder').html(data);
                        $('#responderModal').modal('show');
                    },
                    error: function () {
                        console.log('Error al cargar los detalles del PQRS.');
                    }
                });
            });
        });
    </script>