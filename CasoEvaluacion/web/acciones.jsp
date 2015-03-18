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
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession(false);
            if (sesion.getAttribute("logueado") != null) {
                UsuarioDto uDto = (UsuarioDto) sesion.getAttribute("logueado");
                LibroDao lDao = new LibroDao();
                ArrayList<LibroDto> libros = (ArrayList<LibroDto>) lDao.obtenerLibros();

        %>
        <div class="row text-center col-lg-2 col-lg-offset-5"><br>
            <a href="prestamo.jsp" class="btn-success form-control">Realizar prestamo</a><br>
            <a href="devolucion.jsp " class="btn-success form-control">Devolver libro</a><br>
            <a href="Multas.jsp " class="btn-success form-control">Multas</a><br>
        </div>
        <%            }
        %>
    </body>
</html>
