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
import modelo.dto.MultaDto;
import utilidades.Conexion;

/**
 *
 * @author kmilogil
 */
public class MultaDao {

    private Connection cnn = null;
    private ResultSet rs = null;
    private PreparedStatement pstm = null;
    private String mensajes = "";
    private int registros = 0;

    public MultaDao() {
        cnn = Conexion.getInstance();
    }

    public MultaDto listarUno(int prestamo) {
        MultaDto mudto = new MultaDto();
        try {
            pstm = cnn.prepareStatement("SELECT `Multas`.`idMulta`, `Multas`.`idPrestamo`, `Multas`.`fechaMulta`, `Multas`.`fechaPago`, `Multas`.`valorPagar`, `Multas`.`estadoMultas`\n"
                    + "FROM `Evaluacion`.`Multas`\n"
                    + "WHERE `Multas`.`idPrestamo` = ?;");
            pstm.setInt(1, prestamo);
            rs = pstm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    mudto.setIdMulta(rs.getInt("idMulta"));
                    mudto.setIdPrestamo(rs.getInt("idPrestamo"));
                    mudto.setFechaMulta(rs.getString("fechaMulta"));
                    mudto.setFechaPago(rs.getString("fechaPago"));
                    mudto.setValorPagar(rs.getInt("valorPagar"));
                    mudto.setEstadoMultas(rs.getBoolean("estadoMultas"));
                }
            }
        } catch (SQLException ex) {
            mensajes = "Error:" + ex.getMessage();
        }
        return mudto;
    }

    public List<MultaDto> listarTodos() {
        ArrayList<MultaDto> multas = new ArrayList();
        try {
            pstm = cnn.prepareStatement("SELECT `Multas`.`idMulta`, `Multas`.`idPrestamo`, `Multas`.`fechaMulta`, `Multas`.`fechaPago`, `Multas`.`valorPagar`, `Multas`.`estadoMultas`\n"
                    + "FROM `Evaluacion`.`Multas`;");
            rs = pstm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    MultaDto mudto = new MultaDto();
                    mudto.setIdMulta(rs.getInt("idMulta"));
                    mudto.setIdPrestamo(rs.getInt("idPrestamo"));
                    mudto.setFechaMulta(rs.getString("fechaMulta"));
                    mudto.setFechaPago(rs.getString("fechaPago"));
                    mudto.setValorPagar(rs.getInt("valorPagar"));
                    mudto.setEstadoMultas(rs.getBoolean("estadoMultas"));
                    multas.add(mudto);
                }
            }
        } catch (SQLException ex) {
            mensajes = "Error:" + ex.getMessage();
        }
        return multas;
    }

    public String pagarMulta(int prestamo) {

        CallableStatement cstm;
        try {
            cstm = cnn.prepareCall("{call sp_pagarMulta(?, ?) }");
            cstm.setInt(1, prestamo);
            cstm.registerOutParameter(2, Types.INTEGER);
            cstm.execute();
            int total = cstm.getInt(2);
            if (total == 0) {
                mensajes = "No se cerro la Multa";
            } else {
                mensajes = "Debe pagar: " + total;
            }

        } catch (SQLException ex) {
            mensajes = "Error: " + ex.getMessage();
        }

        return mensajes;
    }
}
