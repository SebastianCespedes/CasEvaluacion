<%-- 
    Document   : multas
    Created on : 17/03/2015, 08:10:40 AM
    Author     : kmilogil
--%>

<%@page import="modelo.dao.MultaDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.dto.MultaDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <h3> Favor Ingresar el numero del Prestamo </h3>
    </head>
    <body>

       
        <%
            HttpSession sesion = request.getSession(false);
            if (sesion.getAttribute("logueado") != null) {

        %>
      
        <%            MultaDao mdao;
            mdao = new MultaDao();
        %>


        <form action="ControladorMulta" method="post">

            <input type="text" name="multa" id="multa" value="">
            <input type="submit" name="ingresar" id="ingresar" value="Ingresar">

        </form>

        <%         ArrayList<MultaDto> multas;

            multas = (ArrayList<MultaDto>) mdao.listarTodos();
        %>


        <table border="1">

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

        <%
            if (request.getParameter("msg") != null) {

        %>
        <div><%=request.getParameter("msg")%></div>
        <a href="index.jsp">Volver</a>
        <%    }
            }
        %>
    </body>
</html>
