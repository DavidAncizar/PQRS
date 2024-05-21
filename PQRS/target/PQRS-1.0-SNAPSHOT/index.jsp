<%-- 
    Document   : index
    Created on : 1/05/2024, 10:01:56 p. m.
    Author     : ANCIZAR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PQRS - MEDINUCLEAR🏥</title>
    <style>
        /* Estilos para el fondo de pantalla */
        html { 
            height: 100%;
        }

        body {
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100%;
            background: url(https://www.newmedicaleconomics.es/wp-content/uploads/2022/07/digital-composite-of-doctor-with-white-graph-with-flare-against-blurry-background-with-light-blue-overlay-1200x512.jpg) no-repeat center center fixed; 
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
            box-shadow: 0px 0px 10px 0px rgba(0,0,0,1);
        }

        /* Estilos para el contenedor principal */
        .container {
            text-align: center;
            padding: 30px;
            background-color: rgba(255, 255, 255, 0.8);
            border-radius: 10px;
            max-width: 600px;
            box-shadow: 0px 0px 10px 0px rgba(0,0,0,1);
        }

        /* Estilos para el logo */
        .logo {
            max-width: 200px;
            margin-bottom: 30px;
            box-shadow: 0px 0px 10px 0px rgba(0,0,0,1);
        }

        /* Estilos para el título */
        .title {
            font-size: 3rem;
            margin-bottom: 20px;
            color: #000; /* Cambiado a negro */
            text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.5);
        }

        /* Estilos para el párrafo */
        .lead {
            font-size: 1.5rem;
            margin-bottom: 20px;
            color: #000; /* Cambiado a negro */
        }

        /* Estilos para el botón */
        .btn-initial {
            background-color: #28a745;
            color: #000; /* Cambiado a negro */
            border: none;
            padding: 15px 30px;
            font-size: 1.2rem;
            border-radius: 30px;
            cursor: pointer;
            transition: all 0.3s ease-in-out;
        }

        .btn-initial:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="container">
        <img src="https://static.vecteezy.com/system/resources/previews/003/805/406/original/online-tutorial-concept-learning-courses-tutorials-illustration-flat-vector.jpg" alt="Logo" class="logo">
        <h1 class="title">PQRS-MEDINUCLEAR🏥</h1>
        <p class="lead">¡Bienvenido a nuestro sistema de petición, queja, reclamo y sugerencia!</p>
        <p style="color: black;">Por favor, crea tu cuenta e inicia sesión para realizar tu PQRS.</p>
        <button onclick="window.location.href='login.jsp'" class="btn-initial btn-lg">Iniciar sesión</button>
    </div>
</body>
</html>

