package servlets;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import com.mycompany.pqrs.controlUsuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/SvRegistrar"})
public class SvRegistrar extends HttpServlet {

    controlUsuario controlU = new controlUsuario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Obtener la conexión a la base de datos
    Connection conexion = controlU.estableceConexion();

    // Obtener los parámetros del formulario - pones el id que estableciste en el index  jsp dentro de las comillas 
    String nombre_completo = request.getParameter("nombre_Completo");
    String numeroTelefono = request.getParameter("numeroTelefono");
    String correo = request.getParameter("correo");
    String cedula = request.getParameter("cedula");
    String contrasenia = request.getParameter("contrasenia");

    try {
        // Verificar si la cédula ya está en uso
        if (cedulaEnUso(cedula)) {
            // Si la cédula está en uso, generar un script que muestre el mensaje de error
            String script = "<script>alert('ERROR: La cedula ingresada ya esta registrada. Por favor, ingresa otra cédula.'); window.location.href = 'registro.jsp';</script>";
            response.setContentType("text/html");
            response.getWriter().write(script);
        } else {
            // Crear una sentencia SQL para insertar el nuevo usuario en la tabla 'usuario'
            String sql = "INSERT INTO usuario (nombre_completo, numeroTelefono, correo, cedula, contrasenia, idRol) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, nombre_completo);
            statement.setString(2, numeroTelefono);
            statement.setString(3, correo);
            statement.setString(4, cedula);
            statement.setString(5, contrasenia);
            // Suponiendo que el nuevo usuario se registra como usuario estándar ('idRol = 1')
            statement.setInt(6, 1); // Cambia esto si el rol del usuario puede ser diferente

            // Ejecutar la sentencia SQL
            int filasInsertadas = statement.executeUpdate();

            // Verificar si se insertaron filas correctamente
            if (filasInsertadas > 0) {
                // Si se insertó correctamente, redirigir a login.jsp
                String redirectScript = "<script>alert('EXITO: El usuario se ha registrado exitosamente.'); window.location.href = 'login.jsp';</script>";
                response.setContentType("text/html");
                response.getWriter().write(redirectScript);
            } else {
                // Si no se insertó correctamente, redirigir a un mensaje de error
                response.sendRedirect("login.jsp");
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Si ocurre un error, redirigir a una página de error
        response.sendRedirect("login.jsp");
    } finally {
        try {
            // Cerrar la conexión a la base de datos
            if (conexion != null) {
                conexion.close();

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

    // Método para verificar si la cédula ya está en uso
    private boolean cedulaEnUso(String cedula) throws SQLException {
        Connection conexion = controlU.estableceConexion();
        String sql = "SELECT COUNT(*) AS total FROM usuario WHERE cedula = ?";
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1, cedula);
        ResultSet rs = statement.executeQuery();
        rs.next();
        int total = rs.getInt("total");
        rs.close();
        statement.close();
        conexion.close();
        return total > 0;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

