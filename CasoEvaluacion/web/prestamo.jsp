<%-- 
    Document   : prestamo
    Created on : 11/03/2015, 09:40:43 PM
    Author     : User
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
        <table border="1">
            <thead>
                <tr>
                    <th>Titulo</th>
                    <th>Precio</th>
                    <th>Estado</th>
                    <th>Accion</th>
                </tr>
            </thead>

            <tbody>  
                <%                    for (LibroDto libro : libros) {
                %>
                <tr>
                    <td><%=libro.getIdLibro()%></td>
                    <td><%=libro.getTitulo()%></td>
                    <td><%=libro.getPrecioLibro()%></td>
                    <td><%=libro.getEstado()%></td>
                    <td><a href="ControladorPrestamo?id=<%=libro.getIdLibro()%>">Pedir</a></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
            <%
            if(request.getParameter("exito") != null){              
             out.print(request.getParameter("exito"));
            }else if(request.getParameter("fallido") != null){
                out.print(request.getParameter("fallido"));
            }
                
            %>         
   
        <%            } else {
                response.sendRedirect("index.jsp");
            }
        %>
    </body>
</html>
