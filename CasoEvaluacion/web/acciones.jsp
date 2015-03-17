<%-- 
    Document   : acciones
    Created on : 17-mar-2015, 8:50:11
    Author     : Sena
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modelo.dto.LibroDto"%>
<%@page import="modelo.dao.LibroDao"%>
<%@page import="modelo.dto.UsuarioDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession(false);
            if (sesion.getAttribute("logueado") != null) {
                UsuarioDto uDto = (UsuarioDto) sesion.getAttribute("logueado");
                LibroDao lDao = new LibroDao();
                ArrayList<LibroDto> libros = (ArrayList<LibroDto>) lDao.obtenerLibros();

        %>
        <a href="prestamo.jsp">Realizar prestamo</a>
        <a href="devolucion.jsp">Devolver libro</a>
        <a href="Multas.jsp">Multas</a>

        <%            }
        %>
    </body>
</html>
