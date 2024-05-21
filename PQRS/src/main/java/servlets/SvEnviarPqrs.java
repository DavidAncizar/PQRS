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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import static jdk.jpackage.internal.Arguments.CLIOptions.context;

/**
 *
 * @author ANCIZAR
 */
@WebServlet(name = "SvEnviarPqrs", urlPatterns = {"/SvEnviarPqrs"})

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class SvEnviarPqrs extends HttpServlet {

    controlUsuario controlU = new controlUsuario();

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
        // Obtener los parámetros del formulario
        ServletContext context = getServletContext();
        Connection conexion = controlU.estableceConexion();

        String descripcion = request.getParameter("descripcion");

        Part filePart = request.getPart("archivo_adj");
        String fileName = "";
    
        if (filePart != null && filePart.getSize() > 0) {
            
            fileName = filePart.getSubmittedFileName();
            
            String cargarDir = context.getRealPath("/Archivos");
        
            File cargarFolder = new File(cargarDir);

            if (!cargarFolder.exists()) {
                cargarFolder.mkdir();
            }
            File destFile = new File(cargarFolder,fileName);
            
            try (InputStream fileContent = filePart.getInputStream(); OutputStream output = new FileOutputStream(destFile)) {
                byte[] buffer = new byte[1024];
                int length;
                while((length = fileContent.read(buffer))>0){
                    output.write(buffer, 0, length);
                }
              
            }

        }
        String estado = "Sin revisar";
        String tipoPqrsParam = request.getParameter("tipo_pqrs");

        // Validar si el parámetro "tipo_pqrs" es nulo o está vacío
        if (tipoPqrsParam == null || tipoPqrsParam.isEmpty()) {
            // Manejar el error o mostrar un mensaje al usuario
            return;
        }

        // Convertir el parámetro "tipo_pqrs" a un entero
        int idTipoPqrs = Integer.parseInt(tipoPqrsParam);

        //Convertir el parametro "ID_usuario" a un entero
        int ID_usuario = Integer.parseInt(request.getParameter("ID_usuario"));

        // Llamar al método para guardar en la base de datos
        controlU.guardarPQRS(descripcion, fileName , estado, idTipoPqrs, ID_usuario);

// Hacemos el script, lo imprimimos como html, escribimos y enviamos para mostrarle al usuario si se envio o no.
        String script = "<script>alert('PQRS ENVIADO: Gracias por ayudar a mejorar nuestro servicio, pronto tu gmail sera respondido!.'); window.location.href = 'indexUser.jsp';</script>";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(script);

    }

}
