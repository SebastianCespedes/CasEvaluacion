<%-- 
    Document   : index
    Created on : 11/03/2015, 10:12:55 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="ControladorUsuario" method="post">
            <label>Ingrese su documento</label>
            <input type="text" name="documento">
            <input type="submit" name="ingresar" value="Ingresar">
        </form>
    </body>
</html>
