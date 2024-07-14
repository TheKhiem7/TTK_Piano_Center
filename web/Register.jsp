<%-- 
    Document   : Register
    Created on : Jun 20, 2024, 3:28:38 PM
    Author     : TheKhiem7
--%>

<%@page import="Khiemnvd.registration.RegistrationInsertError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Register Page</title>
        <style>
            a {
                font-family: monospace;
            }

            .container {
                max-width: 350px;
                background: #F8F9FD;
                background: linear-gradient(0deg, rgb(255, 255, 255) 0%, rgb(244, 247, 251) 100%);
                border-radius: 40px;
                padding: 25px 35px;
                border: 5px solid rgb(255, 255, 255);
                box-shadow: rgba(133, 189, 215, 0.8784313725) 0px 30px 30px -20px;
                margin: 20px;
            }

            .heading {
                text-align: center;
                font-family: fantasy;
                font-weight: 900;
                font-size: 30px;
                color: rgb(16, 137, 211);
            }

            .form {
                margin-top: 20px;
            }

            .form .input {
                width: 87%;
                background: white;
                border: none;
                padding: 15px 20px;
                border-radius: 20px;
                margin-top: 15px;
                box-shadow: #cff0ff 0px 10px 10px -5px;
                border-inline: 2px solid transparent;
            }

            .form .input::-moz-placeholder {
                color: rgb(170, 170, 170);
            }

            .form .input::placeholder {
                color: rgb(170, 170, 170);
            }

            .form .input:focus {
                outline: none;
                border-inline: 2px solid #12B1D1;
            }

            .form .create-account {
                display: block;
                margin-top: 10px;
                margin-left: 10px;
            }

            .form .create-account a {
                font-size: 11px;
                color: #0099ff;
                text-decoration: none;
            }

            .form .login-button {
                font-family: system-ui, -apple-system, system-ui, "Helvetica Neue", Helvetica, Arial, sans-serif;
                text-decoration: none;
                text-align: center;
                display: block;
                width: 100%;
                font-weight: bold;
                background: linear-gradient(45deg, rgb(16, 137, 211) 0%, rgb(18, 177, 209) 100%);
                color: white;
                padding-block: 15px;
                margin: 20px auto;
                border-radius: 20px;
                box-shadow: rgba(133, 189, 215, 0.8784313725) 0px 20px 10px -15px;
                border: none;
                transition: all 0.2s ease-in-out;
            }

            .form .login-button:hover {
                transform: scale(1.03);
                box-shadow: rgba(133, 189, 215, 0.8784313725) 0px 23px 10px -20px;
            }

            .form .login-button:active {
                transform: scale(0.95);
                box-shadow: rgba(133, 189, 215, 0.8784313725) 0px 15px 10px -10px;
            }

            .social-account-container {
                margin-top: 25px;
            }

            .social-account-container .title {
                display: block;
                text-align: center;
                font-size: 10px;
                color: rgb(170, 170, 170);
            }

            .social-account-container .social-accounts {
                width: 100%;
                display: flex;
                justify-content: center;
                gap: 15px;
                margin-top: 5px;
            }

            .social-account-container .social-accounts .social-button {
                background: linear-gradient(45deg, rgb(0, 0, 0) 0%, rgb(112, 112, 112) 100%);
                border: 5px solid white;
                padding: 5px;
                border-radius: 50%;
                width: 40px;
                aspect-ratio: 1;
                display: grid;
                place-content: center;
                box-shadow: rgba(133, 189, 215, 0.8784313725) 0px 12px 10px -8px;
                transition: all 0.2s ease-in-out;
            }

            .social-account-container .social-accounts .social-button .svg {
                fill: white;
                margin: auto;
            }

            .social-account-container .social-accounts .social-button:hover {
                transform: scale(1.2);
            }

            .social-account-container .social-accounts .social-button:active {
                transform: scale(0.9);
            }

            .agreement {
                display: block;
                text-align: center;
                margin-top: 15px;
            }

            .agreement a {
                text-decoration: none;
                color: #0099ff;
                font-size: 9px;
            }


            .webpage-background {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-image: url('img/login.png');
                background-color: #efebe3;
                background-size: cover;
                /*filter: blur(8px);*/
                z-index: -1;
            }

            body{
                height: 100%;
                margin: 0;
                font-family: Consolas, sans-serif;
                /*background-color: #f2f2f2;*/
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
        </style>
    </head>
    <body>
        <div class="webpage-background"></div>
        <div class="container">
            <div class="heading">Create new account</div>

            <%
                String message = (String) request.getAttribute("MESSAGE");
                if (message == null) {
                    message = "";
                }
            %>
            <%=message%>

            <form class="form" action="MainController">
                <input class="input" type="text" name="txtUsername" value="<%= request.getParameter("txtUsername")%>" placeholder="Username (6-20 char)"/> 
                <%
                    RegistrationInsertError errors = (RegistrationInsertError) request.getAttribute("INSERTERRORS");
                    if (errors != null) {
                        if (errors.getUsernameLengthErr() != null) {
                %>
                <font color="red"><%= errors.getUsernameLengthErr()%> </font>
                <%
                        }
                    }
                %>
                <%
                    if (errors != null) {
                        if (errors.getUsernameExisted() != null) {
                %>
                <font color="red"><%= errors.getUsernameExisted()%> </font>
                <%
                        }
                    }
                %><br>         
                <input class="input" type="password" name="txtPassword" value="" placeholder="Password(*)(6-20 char)" /> 
                <%
                    if (errors != null) {
                        if (errors.getPasswordLengthErr() != null) {
                %>
                <font color="red"><%= errors.getPasswordLengthErr()%> </font>
                <%
                        }
                    }
                %><br>
                <input class="input" type="password" name="txtConfirm" value="" placeholder="Confirm Password(*)(6-20 char)"/>
                <%
                    if (errors != null) {
                        if (errors.getConfirmPassNotMatch() != null) {
                %>
                <font color="red"><%= errors.getConfirmPassNotMatch()%> </font>
                <%
                        }
                    }
                %><br>

                <input class="input" type="text" name="txtFullname" value="<%= request.getParameter("txtFullname")%>" placeholder="Full name:(2-20 char)" /> 
                <%
                    if (errors != null) {
                        if (errors.getFullnameLengthErr() != null) {
                %>
                <font color="red"><%= errors.getFullnameLengthErr()%> </font>
                <%
                        }
                    }
                %><br>
                <input class="login-button" type="submit" name="btAction" value="Register" />
                <input class="login-button" type="reset" value="Reset" /> 
            </form>
        </div>
    </body>
</html>
