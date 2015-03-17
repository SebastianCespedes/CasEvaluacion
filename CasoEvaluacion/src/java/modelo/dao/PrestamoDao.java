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
import java.util.ArrayList;
import java.util.List;
import modelo.dto.PrestamoDto;
import utilidades.Conexion;

/**
 *
 * @author Sena
 */
public class PrestamoDao {

    Connection cnn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    String mensaje = "";
    int registro = 0;

    public PrestamoDao() {
        cnn = Conexion.getInstance();
    }

    public List obtenerPrestamos(long idUsuario) {

        ArrayList<PrestamoDto> prestamos = new ArrayList();
        try {
            pstm = cnn.prepareStatement("SELECT idPrestamo, idUsuario,idLibro, fechaSolicitud,fechaEntrega,estadoPrestamo FROM prestamos where idUsuario = ?");
            pstm.setLong(1, idUsuario);
            rs = pstm.executeQuery();
            while (rs.next()) {
                PrestamoDto prestamo = new PrestamoDto();
                prestamo.setIdPrestamo(rs.getInt("idPrestamo"));
                prestamo.setIdUsuario(rs.getInt("idUsuario"));
                prestamo.setIdLibro(rs.getInt("idLibro"));
                prestamo.setFechaLibro(rs.getString("fechaSolicitud"));
                prestamo.setFechaEntrega(rs.getString("fechaEntrega"));
                prestamo.setEstadoprestamo(rs.getBoolean("estadoPrestamo"));
                prestamos.add(prestamo);
            }
        } catch (SQLException ex) {

        }
        return prestamos;
    }
    
    public int devolverLibro(int idPrestamo){
        
        return 1;
    }

    public String registrarPrestamo(PrestamoDto pDto) {

        try {
            pstm = cnn.prepareStatement("insert into prestamos values (null, ?, ?, now(), date_add(now(), interval 3 day), 1);");
            pstm.setInt(1, pDto.getIdUsuario());
            pstm.setInt(2, pDto.getIdLibro());
            registro = pstm.executeUpdate();
            if (registro != 0) {
                mensaje = "Prestamo realizado!!";

            } else {
                mensaje = "Ocurrio algo!!!";
            }
        } catch (SQLException ex) {
            mensaje = ex.getMessage();
        }
        return mensaje;
    }

    public int validarMultas(int idUsuario) {

        try {
            pstm = cnn.prepareStatement("select count(p.idPrestamo) as multas from prestamos as p join multas as m on p.idPrestamo = m.idPrestamo "
                    + "join usuarios as u on u.idUsuario = p.idUsuario where m.estadoMultas = 1  and u.idUsuario = ?;");
            pstm.setInt(1, idUsuario);
            rs = pstm.executeQuery();
            while (rs.next()) {
                registro = rs.getInt("multas");
            }
        } catch (SQLException ex) {

        }
        return registro;
    }
}
