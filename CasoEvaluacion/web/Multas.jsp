<%-- 
    Document   : multas
    Created on : 17/03/2015, 08:10:40 AM
    Author     : kmilogil
--%>

<%@page import="modelo.dto.UsuarioDto"%>
<%@page import="modelo.dao.MultaDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.dto.MultaDto"%>
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
                UsuarioDto uDto = (UsuarioDto) session.getAttribute("logueado");
        %>

        <%            MultaDao mdao;
            mdao = new MultaDao();
        %>

        <div class="row col-md-6 col-lg-offset-4 col-lg-4">
            <form action="ControladorMulta" method="post">
                <h3> Favor Ingresar el numero del Prestamo </h3>
                <input type="text" name="multa" id="multa" value="" class="form-control" required><br>
                <input type="submit" name="ingresar" id="ingresar" value="Cancelar" class="form-control btn-success">

            </form>
        </div>

        <%         ArrayList<MultaDto> multas;

            multas = (ArrayList<MultaDto>) mdao.listarTodos(uDto.getIdUsuario());
        %>

        <div class="row col-md-10 col-lg-offset-1"><br>
            <table border="1" class="table table-bordered table-hover table-hover table-striped">
                <h1>Estas son sus multas</h1>
                <tr>
                    <th>idMulta</th>
                    <th>idPrestamo</th>
                    <th>fechaMulta</th>
                    <th>fechaPago</th>
                    <th>valorPagar</th>
                    <th>estadoMultas</th>


                </tr>
                <%                for (MultaDto mdto : multas) {

                %>

                <tr>
                    <td><%=mdto.getIdMulta()%></td>
                    <td><%=mdto.getIdPrestamo()%></td>
                    <td><%=mdto.getFechaMulta()%></td>
                    <td><%=mdto.getFechaPago()%></td>
                    <td><%=mdto.getValorPagar()%></td>
                    <td><%=mdto.isEstadoMultas()%></td>

                </tr>
                <%
                    }
                %>
            </table>
        </div>
        <div class="row text-center col-lg-2 col-lg-offset-5"><br>
            <a href="prestamo.jsp" class="btn-success form-control">Realizar prestamo</a><br>
            <a href="devolucion.jsp " class="btn-success form-control">Devolver libro</a><br>

        </div>

        <%
            if (request.getParameter("msg") != null) {

        %>
        <div class="row text-center col-lg-2 col-lg-offset-5"><%=request.getParameter("msg")%></div>
        
        <%    }
            } else {
                response.sendRedirect("index.jsp");
            }
        %>
    </body>
</html>
