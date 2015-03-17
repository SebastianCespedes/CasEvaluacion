<%-- 
    Document   : devolucion
    Created on : 17-mar-2015, 8:51:11
    Author     : Sena
--%>

<%@page import="modelo.dto.PrestamoDto"%>
<%@page import="modelo.dao.PrestamoDao"%>
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


        %>
        <table border="1">
            <thead>
                <tr>
                    <th>IdPrestamo</th>
                    <th>Cliente</th>
                    <th>Fecha de solicitud</th>
                    <th>Fecha de entrega</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <%                PrestamoDao prestamo = new PrestamoDao();
                ArrayList<PrestamoDto> pDto = (ArrayList<PrestamoDto>) prestamo.obtenerPrestamos(uDto.getIdUsuario());

            %>
            <tbody> 
                <%                    for (PrestamoDto p : pDto) {
                %>
                <tr>
                    <td><%=p.getIdLibro()%></td>
                    <td><%=p.getIdUsuario()%></td>
                    <td><%=p.getFechaLibro()%></td>
                    <td><%=p.getFechaEntrega()%></td>
                    <td><%=p.isEstadoprestamo()%></td>
                    <td><a href="ControladorPrestamo?devolver=<%=p.getIdPrestamo()%>">Devolver</a></td>
                </tr>

                <%
                    }
                %>
            </tbody>
        </table>

        <%            }
        %>
    </body>
</html>
