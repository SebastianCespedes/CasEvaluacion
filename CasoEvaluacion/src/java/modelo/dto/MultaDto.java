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

    private int idMultas = 0;
    private int idPrestamo = 0;
    private String fechaMulta = "";
    private String fechapago = "";
    private int valorPagar = 0;
    private boolean estadoMultas = true;

    /**
     * @return the idMultas
     */
    public int getIdMultas() {
        return idMultas;
    }

    /**
     * @param idMultas the idMultas to set
     */
    public void setIdMultas(int idMultas) {
        this.idMultas = idMultas;
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
     * @return the fechapago
     */
    public String getFechapago() {
        return fechapago;
    }

    /**
     * @param fechapago the fechapago to set
     */
    public void setFechapago(String fechapago) {
        this.fechapago = fechapago;
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
        return "multasdto{" + "idMultas=" + getIdMultas() + ", idPrestamo=" + getIdPrestamo() + ", fechaMulta=" + getFechaMulta() + ", fechapago=" + getFechapago() + ", valorPagar=" + getValorPagar() + ", estadoMultas=" + isEstadoMultas() + '}';
    }

}
