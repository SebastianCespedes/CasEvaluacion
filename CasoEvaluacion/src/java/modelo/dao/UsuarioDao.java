/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.dto.UsuarioDto;
import utilidades.Conexion;

/**
 *
 * @author Sena
 */
public class UsuarioDao {

    Connection cnn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    String mensaje = "";
    int registro = 0;

    public UsuarioDao() {
        cnn = Conexion.getInstance();
    }

    public int validarMultas() {

        try {
            pstm = cnn.prepareStatement("select count(p.idPrestamo) as multas from prestamos as p join multas as m on p.idPrestamo = m.idPrestamo "
                    + "join usuarios as u on u.idUsuario = p.idUsuario where m.estadoMultas = 1;");
            rs = pstm.executeQuery();
            while (rs.next()) {
                registro = rs.getInt("multas");
            }
        } catch (SQLException ex) {

        }
        return registro;
    }
    
    public UsuarioDto obtenerUsuario(int cedula){
        UsuarioDto usuario = new UsuarioDto();
        try{
            pstm = cnn.prepareStatement("SELECT idUsuario, nombres, apellidos, email, userName, pass FROM usuarios WHERE idUsuario = ?");
            pstm.setInt(1, cedula);
            rs = pstm.executeQuery();
            while(rs.next()){
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setEmail(rs.getString("email"));
                usuario.setUserName(rs.getString("userName"));
                usuario.setPassword(rs.getString("pass"));
            }
        }catch (SQLException ex) {
            
        }
        return usuario;
    }
}
