/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.mycompany.pqrs.controlUsuario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author ANCIZAR
 */
@WebServlet(name = "SvEditar", urlPatterns = {"/SvEditar"})

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class SvEditar extends HttpServlet {

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
        controlUsuario controlU = new controlUsuario();
        Connection conexion = controlU.estableceConexion();

        try {
            // Obtener los parámetros del formulario
            int idPqrs = Integer.parseInt(request.getParameter("pqrsId"));

            System.out.println("ID_pqrs received: " + idPqrs); // Log para depuración

            String descripcion = request.getParameter("descripcion");
            System.out.println("descripcion: " + descripcion); // Log para depuración
            String tipoPQRS = request.getParameter("tipo_pqrs");
            System.out.println("tipoPQRS: " + tipoPQRS); // Log para depuración

            Part filePart = request.getPart("archivo_adj");
            String fileName = "";

            if (filePart != null && filePart.getSize() > 0) {
                fileName = filePart.getSubmittedFileName();
                String cargarDir = getServletContext().getRealPath("/Archivos");

                File cargarFolder = new File(cargarDir);
                if (!cargarFolder.exists()) {
                    cargarFolder.mkdir();
                }
                File destFile = new File(cargarFolder, fileName);
                try (InputStream fileContent = filePart.getInputStream(); OutputStream output = new FileOutputStream(destFile)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fileContent.read(buffer)) > 0) {
                        output.write(buffer, 0, length);
                    }
                }
            }

            // Llamar al método para editar en la base de datos
            controlU.editarPQRS(descripcion, fileName, tipoPQRS, idPqrs);
            // Hacemos el script, lo imprimimos como html, escribimos y enviamos para mostrarle al usuario si se edito o no.
            String script = "<script>alert('PQRS editado exitosamente!'); window.location.href = 'indexUser.jsp';</script>";
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(script);
            try {
                if (conexion != null && !conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Error parsing ID_pqrs: " + e.getMessage()); // Log para depuración
            String script = "<script>alert('Error al editar PQRS: ID no válido'); window.location.href = 'indexUser.jsp';</script>";
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(script);
        } catch (SQLException ex) {
            Logger.getLogger(SvEditar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
