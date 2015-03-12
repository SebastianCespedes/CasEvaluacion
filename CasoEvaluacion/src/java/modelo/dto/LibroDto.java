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
public class LibroDto {

    private int idLibro = 0;
    private String isbn = "";
    private String titulo = "";
    private int precioLibro = 0;
    private int estado = 0;

    @Override
    public String toString() {
        return "librosdto{" + "idLibro=" + getIdLibro() + ", isbn=" + getIsbn() + ", titulo=" + getTitulo() + ", precioLibro=" + getPrecioLibro() + ", estado=" + getEstado() + '}';
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
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the precioLibro
     */
    public int getPrecioLibro() {
        return precioLibro;
    }

    /**
     * @param precioLibro the precioLibro to set
     */
    public void setPrecioLibro(int precioLibro) {
        this.precioLibro = precioLibro;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

}
