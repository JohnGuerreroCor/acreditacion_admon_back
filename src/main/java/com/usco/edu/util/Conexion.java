package com.usco.edu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion {

    // MÉTODO PARA VALIDAR UNA CONEXIÓN A LA BASE DE DATOS
    public static boolean validarConexion(String driver, String url, String clave, String usuario) throws SQLException {
        Connection con = null;
        try {
            // CARGAR EL CONTROLADOR JDBC
            Class.forName(driver);
            // ESTABLECER LA CONEXIÓN CON LA BASE DE DATOS
            con = DriverManager.getConnection(url, usuario, clave);
            return true;
        } catch (Exception e) {
            // IMPRIMIR LA TRAZA DE LA EXCEPCIÓN EN CASO DE ERROR
            e.printStackTrace();
            return false;
        } finally {
            // CERRAR LA CONEXIÓN, SI ESTÁ ABIERTA
            if (con != null) {
                con.close();
            }
        }
    }

    // MÉTODO PARA CERRAR UNA CONEXIÓN A LA BASE DE DATOS
    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                // CERRAR LA CONEXIÓN
                conexion.close();
            } catch (SQLException e) {
                // IMPRIMIR LA TRAZA DE LA EXCEPCIÓN EN CASO DE ERROR AL CERRAR LA CONEXIÓN
                e.printStackTrace();
            }
        }
    }

    // MÉTODO PARA CERRAR UN PREPAREDSTATEMENT
    public static void cerrarConexion(PreparedStatement sentencia) {
        if (sentencia != null) {
            try {
                // CERRAR EL PREPAREDSTATEMENT
                sentencia.close();
            } catch (SQLException e) {
                // IMPRIMIR LA TRAZA DE LA EXCEPCIÓN EN CASO DE ERROR AL CERRAR EL PREPAREDSTATEMENT
                e.printStackTrace();
            }
        }
    }
}
