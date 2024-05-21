/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pqrs;

/**
 *
 * @author ANCIZAR
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class controlUsuario {

    // Parametros de la cadena de conexion
    private Connection conectar = null;
    private final String usuario = "root";
    private final String contrasenia = "admin";
    private final String baseDatos = "PQRS";
    private final String ip = "localhost";
    private final String puerto = "3306";

    private final String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + baseDatos;

    public Connection estableceConexion() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena, usuario, contrasenia);
            if (conectar != null) {
                System.out.println("Conexion exitosa mi loco");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error: no se ha encontrado el controlador JDBC");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos");
            System.out.println("Cadena de conexion: " + cadena); // Imprimir la cadena de conexion
            e.printStackTrace();
        }
        System.out.println(conectar);
        return conectar;

    }

    public String[] ingresar(String cedula, String contrasenia) throws SQLException {
        String[] redireccion = new String[5]; // Cambia el tamaño del array a 5
        Connection conexion = estableceConexion();
        String sql = "SELECT idRol, ID_usuario FROM usuario WHERE cedula = ? AND contrasenia = ?";
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1, cedula);
        statement.setString(2, contrasenia);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            int idRol = rs.getInt("idRol");
            int ID_usuario = rs.getInt("ID_usuario"); // Obtén el ID de usuario de la consulta
            redireccion[3] = String.valueOf(ID_usuario); // Almacena el ID de usuario en el array
            if (idRol == 1) {
                redireccion[1] = "indexUser";
            } else if (idRol == 2) {
                redireccion[1] = "indexAdmin";
            }
        } else {
            // Si no se encontró ningún usuario, redirigir a login.jsp con mensaje de error
            redireccion[1] = "login.jsp?error=Usuario no registrado";
        }
        rs.close();
        statement.close();
        conexion.close();
        return redireccion;
    }

    public void guardarPQRS(String descripcion, String archivoAdj, String estado, int idTipoPqrs, int ID_usuario) {
        try {
            Connection conexion = estableceConexion();
            String sql = "INSERT INTO tablaPQRS (descripcion, archivo_adj,estado, idTipo,ID_usuario) VALUES (?,?,?,?,?)";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, descripcion);
            statement.setString(2, archivoAdj);
            statement.setString(3, estado);
            statement.setInt(4, idTipoPqrs);
            statement.setInt(5, ID_usuario);
            statement.executeUpdate();
            statement.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Manejar la excepción adecuadamente
        }
    }

    public List<tablaPQRS> obtenerPQRS(int ID_usuario) {
        List<tablaPQRS> pqrsList = new ArrayList<>();

        try (Connection conexion = estableceConexion()) {
            String sql = "SELECT * FROM tablaPQRS WHERE ID_Usuario = ?";
            try (PreparedStatement statement = conexion.prepareStatement(sql)) {
                statement.setInt(1, ID_usuario);

                try (ResultSet resultSet = statement.executeQuery()) {

                    while (resultSet.next()) {
                        tablaPQRS tabla = new tablaPQRS();
                        tabla.setID_pqrs(resultSet.getInt("ID_pqrs"));
                        tabla.setDescripcion(resultSet.getString("descripcion"));
                        tabla.setArchivo_adj(resultSet.getString("archivo_adj"));
                        tabla.setEstado(resultSet.getString("estado"));
                        tabla.setTipoPQRS(resultSet.getString("idTipo"));
                        pqrsList.add(tabla);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al mostrar el registro PQRS del usuario" + ID_usuario + ": " + ex.getMessage());
        }
        return pqrsList;
    }
    
    //metodo listar todos los pqrs
      public List<tablaPQRS> todosPqrs() {
        List<tablaPQRS> pqrsList = new ArrayList<>();

        try (Connection conexion = estableceConexion()) {
            String sql = "SELECT * FROM tablaPQRS";
            try (PreparedStatement statement = conexion.prepareStatement(sql)) {

                try (ResultSet resultSet = statement.executeQuery()) {

                    while (resultSet.next()) {
                        tablaPQRS tabla = new tablaPQRS();
                        tabla.setID_pqrs(resultSet.getInt("ID_pqrs"));
                        tabla.setDescripcion(resultSet.getString("descripcion"));
                        tabla.setArchivo_adj(resultSet.getString("archivo_adj"));
                        tabla.setEstado(resultSet.getString("estado"));
                        tabla.setTipoPQRS(resultSet.getString("idTipo"));
                        pqrsList.add(tabla);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al mostrar el registro PQRS del usuario"  + ": " + ex.getMessage());
        }
        return pqrsList;
    }
    //metodo para eliminar pqrs

    public void eliminarPQRS(int ID_pqrs) {
        controlUsuario controlU = new controlUsuario();
        try (Connection conexion = controlU.estableceConexion()) {
            String sql = "DELETE FROM tablaPQRS WHERE ID_pqrs = ?";
            try (PreparedStatement statement = conexion.prepareStatement(sql)) {
                statement.setInt(1, ID_pqrs);
                int filasAfectadas = statement.executeUpdate();
                if (filasAfectadas > 0) {
                    System.out.println("Tutorial con ID " + ID_pqrs + " eliminado exitosamente.");
                } else {
                    System.out.println("No se encontró un tutorial con ID " + ID_pqrs + " para eliminar.");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al intentar borrar el tutorial con ID " + ID_pqrs + ": " + ex.getMessage());
        }
    }

    public void editarPQRS(String descripcion, String archivo_adj, String tipoPQRS, int ID_pqrs) throws SQLException {
        String query = "UPDATE tablaPQRS SET descripcion = ?, archivo_adj= ?, tipoPQRS = ? WHERE ID_pqrs";
        controlUsuario controlU = new controlUsuario();
        try (Connection conexion = controlU.estableceConexion()) {
            try (PreparedStatement statement = conexion.prepareStatement(query)) {
                statement.setString(1, descripcion);
                statement.setString(2, archivo_adj);
                statement.setString(3, tipoPQRS);
                statement.setInt(4, ID_pqrs);

                //Ejecutar
                int filasModificadas = statement.executeUpdate();
                if (filasModificadas != 1) {
                    throw new SQLException("No se pudo modificar el pqrs con ID:" + ID_pqrs);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

}
