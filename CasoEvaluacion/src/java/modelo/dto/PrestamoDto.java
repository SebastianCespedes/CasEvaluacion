/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.dto;

/**
 *
 * @author LENOVO
 */
public class PrestamoDto {
    private int idPrestamo=0;
    private int idUsuario=0;
    private int idLibro=0;
    private String fechaLibro="";
    private String fechaEntrega="";
    private boolean estadoPrestamo=true;

    /**
     * @return the idPrestamo
     */
    public int getIdPrestamo() {
        return idPrestamo;
    }

    /**
     * @param idPrestamo the idPrestamo to set
     */
    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the idLibro
     */
    public int getIdLibro() {
        return idLibro;
    }

    /**
     * @param idLibro the idLibro to set
     */
    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    /**
     * @return the fechaLibro
     */
    public String getFechaLibro() {
        return fechaLibro;
    }

    /**
     * @param fechaLibro the fechaLibro to set
     */
    public void setFechaLibro(String fechaLibro) {
        this.fechaLibro = fechaLibro;
    }

    /**
     * @return the fechaEntrega
     */
    public String getFechaEntrega() {
        return fechaEntrega;
    }

    /**
     * @param fechaEntrega the fechaEntrega to set
     */
    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    /**
     * @return the estadoPrestamo
     */
    public boolean isEstadoprestamo() {
        return estadoPrestamo;
    }

    /**
     * @param estadoprestamo the estadoPrestamo to set
     */
    public void setEstadoprestamo(boolean estadoprestamo) {
        this.estadoPrestamo = estadoprestamo;
    }

    @Override
    public String toString() {
        return "prestamodto{" + "idPrestamo=" + idPrestamo + ", idUsuario=" + idUsuario + ", idLibro=" + idLibro + ", fechaLibro=" + fechaLibro + ", fechaEntrega=" + fechaEntrega + ", estadoprestamo=" + estadoPrestamo + '}';
    }
    
}
