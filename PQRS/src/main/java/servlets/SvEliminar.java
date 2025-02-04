/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.mycompany.pqrs.controlUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ANCIZAR
 */
@WebServlet(name = "SvEliminar", urlPatterns = {"/SvEliminar"})
public class SvEliminar extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pqrsIdStr = request.getParameter("pqrsId");
        int pqrsId = Integer.parseInt(pqrsIdStr);
        
        controlUsuario controlU = new controlUsuario();
        controlU.eliminarPQRS(pqrsId);
        
        // Redirigir a la página donde se muestra la lista de PQRS
        response.sendRedirect("indexUser.jsp");
       
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
