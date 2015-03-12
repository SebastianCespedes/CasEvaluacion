/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
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
        
        if (request.getParameter("id") != null) {
            HttpSession sesion = request.getSession(false);
            UsuarioDto usuario = (UsuarioDto) sesion.getAttribute("logueado");
            PrestamoDao pDao = new PrestamoDao();
            LibroDao lDao = new LibroDao();
            LibroDto lDto = lDao.obtenerLibro(Integer.parseInt(request.getParameter("id")));
            int estadoLibro = lDao.validarEstadoLibro(lDto.getEstado());
            int multas = pDao.validarMultas(usuario.getIdUsuario());

            if (estadoLibro <= 1 && multas == 0) {
                PrestamoDto pDto = new PrestamoDto();
                pDto.setIdUsuario(usuario.getIdUsuario());
                pDto.setIdLibro(lDto.getIdLibro());
                String mensaje = pDao.registrarPrestamo(pDto);
                response.sendRedirect("prestamo.jsp?exito=" + estadoLibro);

            } else {
                response.sendRedirect("prestamo.jsp?fallido=" + estadoLibro);
            }

        }
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
