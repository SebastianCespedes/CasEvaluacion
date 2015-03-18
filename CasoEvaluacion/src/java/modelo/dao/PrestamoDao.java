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
                prestamo.setFechaSolicitud(rs.getString("fechaSolicitud"));
                prestamo.setFechaEntrega(rs.getString("fechaEntrega"));
                prestamo.setEstadoPrestamo(rs.getBoolean("estadoPrestamo"));
                prestamos.add(prestamo);
            }
        } catch (SQLException ex) {

        }
        return prestamos;
    }

    public PrestamoDto obtenerPrestamo(long idPrestamo) {
        PrestamoDto prestamo = new PrestamoDto();
        try {
            pstm = cnn.prepareStatement("SELECT idPrestamo, idUsuario,idLibro, fechaSolicitud,fechaEntrega,estadoPrestamo FROM prestamos where idPrestamo = ?");
            pstm.setLong(1, idPrestamo);
            rs = pstm.executeQuery();
            while (rs.next()) {

                prestamo.setIdPrestamo(rs.getInt("idPrestamo"));
                prestamo.setIdUsuario(rs.getInt("idUsuario"));
                prestamo.setIdLibro(rs.getInt("idLibro"));
                prestamo.setFechaSolicitud(rs.getString("fechaSolicitud"));
                prestamo.setFechaEntrega(rs.getString("fechaEntrega"));
                prestamo.setEstadoPrestamo(rs.getBoolean("estadoPrestamo"));
            }
        } catch (SQLException ex) {

        }
        return prestamo;
    }

    public int devolverLibro(int idPrestamo) {

        int salida = 0;
        CallableStatement cstm;
        try {
            cstm = cnn.prepareCall("{call sp_cerrarPrestamo(?,?) }");
            cstm.setInt(1, idPrestamo);
            cstm.registerOutParameter(2, Types.INTEGER);
            cstm.execute();
            salida = cstm.getInt(2);

        } catch (SQLException ex) {
            mensaje = "Error: " + ex.getMessage();
        }
        return salida;
    }

    public int registrarPrestamo(PrestamoDto pdto) {

        int salida = 0;
        CallableStatement cstm;
        try {
            cstm = cnn.prepareCall("{call sp_crearPrestamo(?,?,?,?) }");
            cstm.setInt(1, pdto.getIdUsuario());
            cstm.setInt(2, pdto.getIdLibro());
            cstm.setString(3, pdto.getFechaSolicitud());
            cstm.registerOutParameter(4, Types.INTEGER);
            cstm.execute();
            salida = cstm.getInt(4);

        } catch (SQLException ex) {
            mensaje = "Error: " + ex.getMessage();
        }
        return salida;
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
