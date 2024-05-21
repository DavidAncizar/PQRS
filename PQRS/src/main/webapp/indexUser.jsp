<%-- 
    Document   : indexUser
    Created on : 4/05/2024, 9:49:43?a.?m.
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

    <head>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Usuario - PQRS</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="styles.css">
        <%
            controlUsuario controlU = new controlUsuario();
            String id = (String) session.getAttribute("ID_usuario");
            int ID_usuario = Integer.parseInt(id);
            List<tablaPQRS> listar = controlU.obtenerPQRS(ID_usuario);
        %>
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
                        <h2>!Bienvenido!</h2>
                    </li>
                    <li class="sidebar-item">
                        <a href="#contenidoInicio" class="sidebar-link" id="mostrarBienvenida" style="color:white;">
                            <i class="fa fa-hand-peace-o"  style="color:white;"></i>
                            Introducción
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a href="#formPQRS" class="sidebar-link" id="mostrarURL" style="color:white;">
                            <i class="fa-solid fa-eye"  style="color:white;"></i>
                            Realizar un PQRS
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link" id="mostrarFormulario" style="color:white;">
                            <i class="fa-solid fa-book-bookmark"  style="color:white;"></i>
                            Mostrar mis PQRS
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
            <div class="contact-info">
                <p><strong>Contáctanos:</strong></p>
                <p>medinuclear@gmail.com</p>
                <p><strong>Celular:</strong></p>
                <p>(+57) 300 456 75 78</p>
                <p><strong>Teléfono:</strong></p>
                <p>(602) 7 37 58 95</p>
                <p><strong>Whatsapp:</strong></p>
                <p>(+57) 321 576 28 29</p>
            </div>
        </aside>

        <div class="welcome-container" style="display:block;">

            <div class="inicio">
                <div id="contenidoInicio">
                    <h1 style="text-align:center;">SISTEMA GESTOR DE PQRS MEDINUCLEAR - USUARIO</h1>
                    <p>En la presente página, tendrás la oportunidad de conocer los tutoriales de diferentes categorías y redireccionarte a ellos para verlos completos.</p>
                    <a href="https://www.youtube.com/watch?v=PEjE8gvNDA8" target="_blank" style="color: brown; text-decoration: underline;">¡Dale clic aquí! Para saber cómo funcionan los PQRS. </a>
                </div>
            </div>
        </div>

        <div class="container" id="formPQRS" >
            <form id="formPQRS" action="SvEnviarPqrs" method="post" enctype="multipart/form-data">
                <h2>!Bienvenido a su interfaz de usuario!</h2>
                <div class="form-group">
                    <input  type ="hidden" name="ID_usuario" value="<%= session.getAttribute("ID_usuario")%>">
                </div>
                <div class="form-group">
                    <label for="descripcion">Descripción:</label>
                    <textarea id="descripcion" name="descripcion" rows="4" required></textarea>
                </div>
                <div class="form-group">
                    <label for="archivo_adj">Archivo adjunto (solo PDF):</label>
                    <input type="file" id="archivo_adj" name="archivo_adj" accept ="application/pdf">
                    <span id="archivoError" style="color: red;"></span>
                </div>
                <div class="form-group">
                    <label for="tipo_pqrs">Tipo de PQRS:</label>
                    <select id="tipo_pqrs" name="tipo_pqrs" required>
                        <option value="1">Petición</option>
                        <option value="2">Queja</option>
                        <option value="3">Reclamo</option>
                        <option value="4">Sugerencia</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Enviar</button>
            </form>
        </div>
        <div class="container" id="tabla" >

            <table class="table">  
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
                            <a type="button" class="btn btn-warning btn-editar" data-bs-toggle="modal" data-bs-target="#editarModal" data-id="<%=list.getID_pqrs()%>">Editar</a>
                            <a type="button" class="btn btn-danger btn-eliminar" data-bs-toggle="modal" data-bs-target="#confirmarEliminar" data-id="<%=list.getID_pqrs()%>">Eliminar</a>

                        </td>
                    </tr>
                    <%
                        }
                    %> 
                </tbody>
            </table>
        </div>

        <div class="modal fade" id="confirmarEliminar" tabindex="-1" aria-labelledby="confirmarEliminarLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="confirmarEliminarLabel">Confirmar eliminación</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        ¿Estás seguro de que deseas eliminar este PQRS?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <form id="eliminarForm" action="SvEliminar" method="post">
                            <input id="pqrsId"type="hidden" name="pqrsId">
                            <button  type="submit" class="btn btn-danger"onclik="eliminarContacto">Eliminar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="editarModal" tabindex="-1" aria-labelledby="editarModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="editarForm" action="SvEditar" method="POST" enctype="multipart/form-data">
                        <input type="hidden" id="pqrsId" name="pqrsId">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editarModalLabel">Editar PQRS</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="editDescripcion">Descripción:</label>
                                <textarea id="descripcion" name="descripcion" rows="4" class="form-control" required></textarea>
                            </div>
                            <div class="form-group">
                                <label for="editArchivo_adj">Archivo adjunto (solo PDF):</label>
                                <input type="file" id="archivo_adj" name="archivo_adj" accept="application/pdf" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="editTipo_pqrs">Tipo de PQRS:</label>
                                <select id="tipo_pqrs" name="tipo_pqrs" class="form-control" required>
                                    <option value="1">Petición</option>
                                    <option value="2">Queja</option>
                                    <option value="3">Reclamo</option>
                                    <option value="4">Sugerencia</option>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                            <button type="submit" class="btn btn-success">Guardar cambios</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
<!-- Modal para editar la PQRS -->
        <div class="modal fade" id="editarModal" tabindex="-1" role="dialog" aria-labelledby="pqrsModalLabel">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="pqrsModalLabel">Editar PQRS</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="SvEditarPqrs" method="POST" >
                            <div class="form-group">
                                <center><label for="Pqrs">Edita tu PQRS.</label></center>      
                            </div>

                            <div id="pqrs-editar">
                            </div> 

                            <button type="submit" class="btn btn-primary btn-block">Enviar</button>
                        </form>

                    </div>
                </div>
            </div>
        </div>
                

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                // Mostrar el contenedor de bienvenida al hacer clic en "Introducción"
                document.getElementById('mostrarBienvenida').addEventListener('click', function () {
                    document.querySelector('.welcome-container').style.display = 'block';
                    document.getElementById('formPQRS').style.display = 'none';
                    document.getElementById('tabla').style.display = 'none';
                });

                // Ocultar el contenedor de bienvenida y mostrar el formulario al hacer clic en "Realizar un PQRS"
                document.getElementById('mostrarURL').addEventListener('click', function () {
                    document.querySelector('.welcome-container').style.display = 'none';
                    document.getElementById('formPQRS').style.display = 'block';
                    document.getElementById('tabla').style.display = 'none';
                });

                document.getElementById('mostrarFormulario').addEventListener('click', function () {
                    // Desplazar la página hacia la sección de la tabla de PQRS
                    document.getElementById('tabla').style.display = 'block';
                    document.querySelector('.welcome-container').style.display = 'none';
                    document.getElementById('formPQRS').style.display = 'none';
                });

                $('#confirmarEliminar').on('show.bs.modal', function (event) {
                    var button = $(event.relatedTarget);
                    var id = button.data('id');

                    $('#pqrsId').val(id);

                });


                // Editar PQRS
                $('#editarModal').on('show.bs.modal', function (event) {
                    var button = $(event.relatedTarget);
                    var id = button.data('id');
                    $('#pqrsId').val(id);
                    console.log(id);
                    // Aquí puedes agregar más lógica para precargar los datos actuales en el formulario de edición si es necesario
                });



                function eliminarContacto() {
                    $('eliminarForm').submit();
                }

            });

        </script>
        <script>
            const myModal = document.getElementById('myModal')
            const myInput = document.getElementById('myInput')

            myModal.addEventListener('shown.bs.modal', () => {
                myInput.focus()
            });
        </script>
    </body>
</html>
