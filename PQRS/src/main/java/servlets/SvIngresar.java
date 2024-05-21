/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.mycompany.pqrs.controlUsuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ANCIZAR
 */
@WebServlet(name = "SvIngresar", urlPatterns = {"/SvIngresar"})
public class SvIngresar extends HttpServlet {

    controlUsuario controlU = new controlUsuario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la conexión a la base de datos

        // Obtener los parámetros del formulario
        String cedula = request.getParameter("cedula");
        String contrasenia = request.getParameter("contrasenia");

        try {

            String[] resultadoAutenticacion = controlU.ingresar(cedula, contrasenia);
            if (resultadoAutenticacion != null) {
                // Verificar si los datos son válidos
                String nombre_Completo = resultadoAutenticacion[0];
                String rol = resultadoAutenticacion[1];
                String correo = resultadoAutenticacion[2];
                String ID_usuario = resultadoAutenticacion[3];
                request.getSession().setAttribute("rol", rol);
                System.out.println(rol);

                if (rol.equals("indexAdmin")) {
                    
                    response.sendRedirect("indexAdmin.jsp");
                } else {
                    // Si el usuario tiene el rol 1, redireccion a el usuario común.
                    request.getSession().setAttribute("ID_usuario", ID_usuario);
                    response.sendRedirect("indexUser.jsp" );
                    
                }
            } else {
                response.sendRedirect("login.jsp?alert=error");
            }

        } catch (SQLException ex) {
            Logger.getLogger(SvIngresar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
