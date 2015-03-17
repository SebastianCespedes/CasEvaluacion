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
public class MultaDto {

    private int idMulta = 0;
    private int idPrestamo = 0;
    private String fechaMulta = "";
    private String fechaPago = "";
    private int valorPagar = 0;
    private boolean estadoMultas = true;

    /**
     * @return the idMulta
     */
    public int getIdMulta() {
        return idMulta;
    }

    /**
     * @param idMulta the idMulta to set
     */
    public void setIdMulta(int idMulta) {
        this.idMulta = idMulta;
    }

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
     * @return the fechaMulta
     */
    public String getFechaMulta() {
        return fechaMulta;
    }

    /**
     * @param fechaMulta the fechaMulta to set
     */
    public void setFechaMulta(String fechaMulta) {
        this.fechaMulta = fechaMulta;
    }

    /**
     * @return the fechaPago
     */
    public String getFechaPago() {
        return fechaPago;
    }

    /**
     * @param fechaPago the fechaPago to set
     */
    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * @return the valorPagar
     */
    public int getValorPagar() {
        return valorPagar;
    }

    /**
     * @param valorPagar the valorPagar to set
     */
    public void setValorPagar(int valorPagar) {
        this.valorPagar = valorPagar;
    }

    /**
     * @return the estadoMultas
     */
    public boolean isEstadoMultas() {
        return estadoMultas;
    }

    /**
     * @param estadoMultas the estadoMultas to set
     */
    public void setEstadoMultas(boolean estadoMultas) {
        this.estadoMultas = estadoMultas;
    }
    @Override
    public String toString() {
        return "multasdto{" + "idMulta=" + getIdMulta() + ", idPrestamo=" + getIdPrestamo() + ", fechaMulta=" + getFechaMulta() + ", fechapago=" + getFechaPago() + ", valorPagar=" + getValorPagar() + ", estadoMultas=" + isEstadoMultas() + '}';
    }

}
