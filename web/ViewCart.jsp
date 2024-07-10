<%-- 
    Document   : ViewCart
    Created on : Jun 6, 2024, 4:17:39 PM
    Author     : TheKhiem7
--%>

<%@page import="java.util.Map"%>
<%@page import="Khiemnvd.cart.CartObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
        <style>
            h1{
                color: #00a8ff;
                text-align: center;
            }

            a {
                background-color: #00692c;
                color: #fff;
                padding: 0.2rem;
                margin-top: 1rem;
                margin-bottom: 1rem;
                border: 1px solid #ccc;
                border-radius: 25px;
                text-decoration:none;
            }


            body {
                font-family: Consolas, sans-serif;
            }

            .centertable{
                margin-left: auto;
                margin-right: auto;
            }
        </style>
    </head>
    <body>
        <h1>Your Cart</h1>
        <%
            if (session != null) {
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart != null) {
                    if (cart.getItem() != null) {
        %>
        <table class="centertable" border="1" width = 50%>
            <thead>
                <tr>
                    <th>No</th>
                    <th>Title</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>           
            <form action="MainController">
                <%
                    int count = 0;
                    for (Map.Entry item : cart.getItem().entrySet()) {
                %>
                <tr>
                    <td>
                        <%= ++count%>
                    </td>
                    <td>
                        <%= item.getKey()%>
                    </td>
                    <td>
                        <%= item.getValue()%>
                    </td>
                    <td>
                        <input type="checkbox" name="chkItem" value="<%= item.getKey()%>" />
                    </td>
                </tr>
                <%
                    }
                %>

                <tr>
                    <td colspan="3">
                        <a href="BookStore.html">Add more book</a>
                    </td>
                    <td>
                        <input type="submit" value="Remove" name="btAction" />
                    </td>
                </tr>  
            </form>

        </tbody>
    </table>
    <%
    } else {//end item
    %>
    <h2> Nothing in your cart </h2>
    <%
        }
    } else {//end cart
    %>
    <h2> Nothing in your cart </h2>
    <%
        }
    } else {//end session 
    %>
    <h2> Nothing in your cart </h2>
    <%
        }
    %>
</body>
</html>
