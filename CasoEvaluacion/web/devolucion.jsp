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
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession(false);
            if (sesion.getAttribute("logueado") != null) {
                UsuarioDto uDto = (UsuarioDto) sesion.getAttribute("logueado");


        %>


        <div class="row col-md-10 col-lg-offset-1"><br>
            <table border="1" class="table table-bordered table-hover table-hover table-striped">
                <h1>Devoluciones de libros</h1>
                <thead>
                    <tr>
                        <th>IdLibro</th>
                        <th>Cliente</th>
                        <th>Fecha de solicitud</th>
                        <th>Fecha de entrega</th>
                        <th>Estado</th>
                        <th>Devolver</th>
                    </tr>
                </thead>
                <%                PrestamoDao prestamo = new PrestamoDao();
                    ArrayList<PrestamoDto> pDto = (ArrayList<PrestamoDto>) prestamo.obtenerPrestamos(uDto.getIdUsuario());

                %>
                <tbody> 
                    <%                    for (PrestamoDto p : pDto) {
                            String estado = "";
                            if (p.isEstadoPrestamo() == true) {
                                estado = "Prestado";
                            } else {
                                estado = "Sin prestamo";
                            }
                    %>
                    <tr>
                        <td><%=p.getIdLibro()%></td>
                        <td><%=p.getIdUsuario()%></td>
                        <td><%=p.getFechaSolicitud()%></td>
                        <td><%=p.getFechaEntrega()%></td>
                        <td><%=estado%></td>
                        <td><form action="ControladorPrestamo" method="post">
                                <select name="estado" class="form-control">
                                    <option value="1">Bueno</option>
                                    <option value="3">Dañado</option>
                                    <option value="4">Perdido</option>

                                </select>
                                <input type="hidden" name="prestamo" value="<%=p.getIdPrestamo()%>">
                                <input type="submit" class="btn btn-info" name="devolver" value="Devolver">
                            </form></td>
                    </tr>


                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        <div class="row text-center col-lg-2 col-lg-offset-5"><br>
            <a href="prestamo.jsp" class="btn-success form-control">Realizar prestamo</a><br>
            <a href="Multas.jsp " class="btn-success form-control">Multas</a><br>
        </div>

        <div class="row text-center col-lg-2 col-lg-offset-5">
            <%
                if (request.getParameter("exito") != null) {
                    out.print(request.getParameter("exito"));
                } else if (request.getParameter("fallido") != null) {
                    out.print(request.getParameter("fallido"));
                }

            %>         

            <%      } else {
                    response.sendRedirect("index.jsp");
                }
            %>
        </div>

    </body>
</html>
