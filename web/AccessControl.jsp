<%-- 
    Document   : AccessControl
    Created on : Jul 7, 2024, 9:45:09 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Access Denied</title>

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,700" rel="stylesheet">

        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="css/accesscontrol.css" />

    </head>

    <body>
        <div id="notfound">
            <div class="notfound">
                <div class="notfound-404">
                    <h1><span></span></h1>
                </div>
                <h2>Access Denied</h2>
                <p>Your account is not authorized to access this website</p>
                <a href="Home.jsp">Back to Home page</a>
            </div>
        </div>
    </body>

</html>
