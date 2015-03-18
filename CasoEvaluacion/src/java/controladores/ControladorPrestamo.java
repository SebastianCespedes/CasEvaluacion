/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.dao.LibroDao;
import modelo.dao.PrestamoDao;
import modelo.dto.LibroDto;
import modelo.dto.PrestamoDto;
import modelo.dto.UsuarioDto;

/**
 *
 * @author User
 */
public class ControladorPrestamo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Calendar fecha = null;
        String mesactual = "";
        String dia = "";
        String año = "";
        String fechaActual = "";
        int mes = 0;
        if (request.getParameter("id") != null) {
            HttpSession sesion = request.getSession(false);
            UsuarioDto usuario = (UsuarioDto) sesion.getAttribute("logueado");
            PrestamoDao pDao = new PrestamoDao();
            LibroDao lDao = new LibroDao();
            LibroDto lDto = lDao.obtenerLibro(Integer.parseInt(request.getParameter("id")));
            int id = lDto.getIdLibro();
//            int estadoLibro = lDao.validarEstado(lDto.getEstado());
            int estado = lDto.getEstado();
            int multas = pDao.validarMultas(usuario.getIdUsuario());
            int prestamos = lDao.validarCantidadLibros(usuario.getIdUsuario());

            fecha = new GregorianCalendar();
            dia = Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
            mes = fecha.get(Calendar.MONTH) + 1;
            mesactual = Integer.toString(mes);
            año = Integer.toString(fecha.get(Calendar.YEAR));

            fechaActual = año + "/" + mesactual + "/" + dia;

            if (estado <= 1) {
                if (multas == 0) {
                    if (prestamos == 1) {
                        PrestamoDto pDto = new PrestamoDto();
                        pDto.setIdUsuario(usuario.getIdUsuario());
                        pDto.setIdLibro(lDto.getIdLibro());
                        pDto.setFechaSolicitud(fechaActual);
                        int salida = pDao.registrarPrestamo(pDto);
                        String mensaje = "";
                        if (salida == 1) {
                            mensaje = " Hecho!!";
                        } else {
                            mensaje = " Fail!!";
                        }
                        response.sendRedirect("prestamo.jsp?exito=Prestamo Realizado!!" + " id " + id + " estado " + estado + mensaje);
                    } else {
                        response.sendRedirect("prestamo.jsp?fallido=Ya tiene dos prestamos activos" + prestamos);
                    }
                } else {
                    response.sendRedirect("prestamo.jsp?fallido=Usted tiene multas");
                }

            } else {
                response.sendRedirect("prestamo.jsp?fallido=El libro no esta disponible" + " id " + id + " estado " + estado);
            }

        } else if (request.getParameter("devolver") != null) {

            PrestamoDao pDao = new PrestamoDao();
            PrestamoDto pDto;
            pDto = pDao.obtenerPrestamo(Integer.parseInt(request.getParameter("prestamo")));
            LibroDao lDao = new LibroDao();
            LibroDto lDto = lDao.obtenerLibro(pDto.getIdLibro());
            int estado = Integer.parseInt(request.getParameter("estado"));
            String mensaje = "";
            if (estado != 1) {
                mensaje = lDao.modificarEstado(estado, lDto.getIdLibro());
            }
            int salida = pDao.devolverLibro(Integer.parseInt(request.getParameter("prestamo")));
            if (salida == 0) {
                response.sendRedirect("devolucion.jsp?exito=Prestamo cancelado" + mensaje + estado);
            } else {
                response.sendRedirect("devolucion.jsp?fallido=No se logro cancelar, tiene multa " + mensaje + " " + estado);
            }

        }

        //            if (estadoLibro == 1 && multas == 0 && prestamos == 1) {
//                PrestamoDto pDto = new PrestamoDto();
//                pDto.setIdUsuario(usuario.getIdUsuario());
//                pDto.setIdLibro(lDto.getIdLibro());
//                String mensaje = pDao.registrarPrestamo(pDto);
//                response.sendRedirect("prestamo.jsp?exito=" + mensaje);
//
//            } else {
//                response.sendRedirect("prestamo.jsp?fallido=No se logro realizar el prestamo");
//            }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
