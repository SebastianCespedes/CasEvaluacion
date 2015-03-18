<%-- 
    Document   : prestamo
    Created on : 11/03/2015, 09:40:43 PM
    Author     : User
--%>


<%@page import="modelo.dao.UsuarioDao"%>
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
       
        
        <div class="row col-md-10 col-lg-offset-1"><br><br>
            <table border="1" class="table table-bordered table-striped table-hover">
                <h1>Libros del sistema</h1>
                <thead>
                    <tr>
                        <th>Libro</th>
                        <th>Titulo</th>
                        <th>Precio</th>
                        <th>Estado</th>
                        <th>Pedir</th>
                    </tr>
                </thead>

                <tbody>  
                    <%                    for (LibroDto libro : libros) {
                            String estado = "";
                            if (libro.getEstado() == 1) {
                                estado = "Disponible";
                            } else if (libro.getEstado() == 2) {
                                estado = "No disponible";
                            } else if (libro.getEstado() == 3) {
                                estado = "Dañado";
                            } else if (libro.getEstado() == 4) {
                                estado = "Perdido";
                            }


                    %>
                    <tr>
                        <td><%=libro.getIdLibro()%></td>
                        <td><%=libro.getTitulo()%></td>
                        <td><%=libro.getPrecioLibro()%></td>
                        <td><%=estado%></td>
                        <td><a href="ControladorPrestamo?id=<%=libro.getIdLibro()%>">Pedir</a></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        <div class="row text-center col-lg-2 col-lg-offset-5"><br>            
            <a href="devolucion.jsp " class="btn-success form-control">Devolver libro</a><br>
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

            <%            } else {
                    response.sendRedirect("index.jsp");
                }
            %>
        </div>

    </body>
</html>
