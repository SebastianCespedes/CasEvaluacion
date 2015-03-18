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
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    </head>
    <body>
        <div class="row text-center col-lg-2 col-lg-offset-5"> 
            <br>
            <form action="ControladorUsuario" method="post">
                <label>Ingrese su documento</label>
                <input type="text" name="documento" class="form-control" required><br>
                <input type="submit" name="ingresar" value="Ingresar" class="btn-success form-control">
            </form>
        </div>
    </body>
</html>
