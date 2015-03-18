/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.sql.Connection;
import modelo.dao.LibroDao;
import modelo.dto.LibroDto;

/**
 *
 * @author User
 */
public class Verificar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Connection cnn = Conexion.getInstance();
        if (cnn != null) {
            System.out.println("Conectado");
        }
        LibroDao lDao = new LibroDao();
        LibroDto lDto = lDao.obtenerLibro(7);
        System.out.println(lDto);
    }

}
