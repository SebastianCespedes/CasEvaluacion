/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.LibroDto;
import utilidades.Conexion;

/**
 *
 * @author Sena
 */
public class LibroDao {

    Connection cnn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    String mensaje = "";
    int registro = 0;

    public LibroDao() {
        cnn = Conexion.getInstance();
    }

    public String ingresarLibro() {
        LibroDto libro = new LibroDto();
        try {
            pstm = cnn.prepareStatement("INSERT INTO libros VALUES(null,?,?,?,?);");

            pstm.setString(1, libro.getIsbn());
            pstm.setString(2, libro.getTitulo());
            pstm.setInt(3, libro.getPrecioLibro());
            pstm.setInt(4, libro.getEstado());
            registro = pstm.executeUpdate();
            if (registro != 0) {
                mensaje = "Registro exitoso";
            } else {
                mensaje = "Ocurrio algo";
            }

        } catch (SQLException ex) {

        }
        return mensaje;
    }

    public int validarEstadoLibro(int libro) {
        int salida = 0;
        CallableStatement cstm;
        try {
            cstm = cnn.prepareCall("{call sp_validarEstadoLibro(?, ?) }");
            cstm.setInt(1, libro);
            cstm.registerOutParameter(2, Types.INTEGER);
            cstm.execute();
            salida = cstm.getInt(2);            

        } catch (SQLException ex) {
            mensaje = "Error: " + ex.getMessage();
        }
        return salida;
    }

    public List obtenerLibros() {
        ArrayList<LibroDto> libros = new ArrayList();
        try {
            pstm = cnn.prepareStatement("SELECT idLibro, isbn, titulo, precioLibro, estadoLibro FROM libros");
            rs = pstm.executeQuery();
            while (rs.next()) {
                LibroDto libro = new LibroDto();
                libro.setIdLibro(rs.getInt("idLibro"));
                libro.setIsbn(rs.getString("isbn"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setPrecioLibro(rs.getInt("precioLibro"));
                libro.setEstado(rs.getInt("estadoLibro"));
                libros.add(libro);
            }
        } catch (SQLException ex) {

        }
        return libros;
    }

    public LibroDto obtenerLibro(int idLibro) {
        LibroDto libro = new LibroDto();
        try {
            pstm = cnn.prepareStatement("SELECT idLibro, isbn, titulo, precioLibro, estadoLibro FROM libros WHERE idLibro = ?");
            pstm.setInt(1, idLibro);
            rs = pstm.executeQuery();
            while (rs.next()) {
                libro.setIdLibro(rs.getInt("idLibro"));
                libro.setIsbn(rs.getString("isbn"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setPrecioLibro(rs.getInt("precioLibro"));
                libro.setEstado(rs.getInt("estadoLibro"));

            }
        } catch (SQLException ex) {

        }
        return libro;
    }
    
    public int validarEstado(int libro){
        try{
            pstm = cnn.prepareStatement("select estadoLibro from libros where idLibro = ?;");
            pstm.setInt(1, libro);
            rs = pstm.executeQuery();
            while(rs.next()){
                registro = rs.getInt("estadoLibro");
            }
        }catch(SQLException ex){
            
        }
      return registro;   
    }
}
