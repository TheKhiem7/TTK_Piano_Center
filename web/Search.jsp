<%-- 
    Document   : Search
    Created on : May 30, 2024, 3:52:30 PM
    Author     : TheKhiem7
--%>

<%@page import="Khiemnvd.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <style>
            body {
                font-family: Consolas, sans-serif;
                background-color: #f2f2f2;
                /*display: flex;*/
                justify-content: center;
                align-items: center;
                /*height: 100vh;*/
                margin: 0;
            }

            .container {
                background-color: #fff;
                padding: 2rem;
                border-radius: 5px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                width: 300px;
            }

            a {
                background-color: #00692c;
                color: #fff;
                padding: 0.5rem;
                margin-top: 1rem;
                margin-bottom: 1rem;
                border: 1px solid #ccc;
                border-radius: 5px;
                text-decoration:none;
            }

            h1 {
                color: #00a8ff;
                text-align: center;
            }

            form {
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            label {
                margin-bottom: 0.5rem;
            }

            input[type="text"], input[type="password"] {
                padding: 0.5rem;
                /*margin-bottom: 1rem;*/
                border: 1px solid #ccc;
                border-radius: 5px;
/*                width: 50%;*/
            }

            input[type="submit"] {
                background-color: #007bff;
                color: #fff;
                padding: 0.5rem 1.5rem;
                /*margin-bottom: 1rem;*/
                border: none;
                border-radius: 30px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            input[type="submit"]:hover {
                background-color: #0069d9;
            }

            .centertable{
                margin-top: 1rem;
                margin-left: auto;
                margin-right: auto;
            }

            .Welcome{
                display: flex;
                justify-content: flex-end;
            }

            .Search{
                width: 50%;
            }
        </style>
    </head>


    <body>
        <%
            Cookie[] cookies = request.getCookies();
            String username = cookies[cookies.length - 1].getName();
        %>
        <font class="Welcome" color="red">Welcome, <%=username%></font>
        <a href="MainController?btAction=LogOut">LogOut</a>
        <h1>Search page!</h1>
        <form action="MainController" >
            <%
                String lastSearch = request.getParameter("txtSearchValue");
                if (lastSearch != null) {
            %>
            <label for="user">search value</label>
            <input class="Search" type="text" name="txtSearchValue" value="<%= request.getParameter("txtSearchValue")%>"><br>
            <% } else { %>
            <input class="Search" type="text" name="txtSearchValue" value=""/>
            <% } %>
            <input type="submit" value="Search" name="btAction"/>
        </form>
        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCH");
                if (result != null) {
        %>
        <table class="centertable" border="1" >
            <thead>
                <tr>
                    <th>No</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Lastname</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        String urlRewriting = "MainController?btAction=Del&pk=" + dto.getUsername() + "&lastSearchValue=" + request.getParameter("txtSearchValue");
                %>
            <form action="MainController">

                <tr>
                    <td>
                        <%= ++count%>
                    </td>
                    <td>
                        <%= dto.getUsername()%>
                        <input type="hidden" name="txtUsername" value="<%= dto.getUsername()%>" />
                    </td>
                    <td>
                        <input type="text" name="txtPassword" value="<%= dto.getPassword()%>" />
                    </td>
                    <td> 
                        <input type="text" name="txtLastname" value="<%= dto.getLastname()%>" />
                    </td>
                    <td>
                        <% if (dto.isRole()) {%>
                        <input type="checkbox" name="chkAdmin" value="ON" checked="checked" />
                        <%} else { %>
                        <input type="checkbox" name="chkAdmin" value="ON" />
                        <% }%>
                    </td>
                    <td>
                        <a href="<%= urlRewriting%>"> Delete </a>
                    </td>
                    <td>
                        <input type="hidden" name="lastSearchValue" value="<%= request.getParameter("txtSearchValue")%>" />
                        <input type="submit" value="Update" name="btAction" />
                    </td>
                </tr>
            </form>
            <%
                }
            %>
        </tbody>
    </table>

    <%
    } else {
    %>
    <h2> there are not any record is matched! </h2 >
    <%
            }
        }
    %>
</body>
</html>

