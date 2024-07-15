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
        <style><%@include file="/css/Search.css"%></style>
    </head>


    <body>
        <div class="webpage-background"></div>
        <div class="container">
            <%
                Cookie[] cookies = request.getCookies();
                String username = cookies[cookies.length - 1].getName();
            %>
            <h1 class="heading" style="font-size: 40px">Welcome, <%=username%></h1>

            
            
            <%-- =============== SEARCH BOX =============== --%>
            <form class="form" action="MainController" >
                <%
                    String lastSearch = request.getParameter("txtSearchValue");
                    if (lastSearch != null) {
                %>
                <label class="heading" for="user">search value</label>
                <input class="inp" type="text" name="txtSearchValue" value="<%= request.getParameter("txtSearchValue")%>"><br>
                <% } else { %>
                <label class="heading" for="user">search value</label>
                <input class="inp" type="text" name="txtSearchValue" value=""/>
                <% } %>
                <input class="login-button" type="submit" value="Search" name="btAction"/>
                <a class="login-button" href="MainController?btAction=LogOut">LogOut</a>
            </form>
        </div>


        <%-- =============== SEARCH RESULT =============== --%>
        <div class="container">
            <%
                String searchValue = request.getParameter("txtSearchValue");
                if (searchValue != null) {
                    List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCH");
                    if (result != null) {
            %>
            <table>
                
                <%-- =============== TABLE HEADER =============== --%>
                <thead>
                    <tr class="heading" style="font-size: 25px">
                        <th>No</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Lastname</th>
                        <th>Role</th>
                        <th style="color: #dc3545">Delete</th>
                        <th style="color: blueviolet">Update</th>
                    </tr>
                </thead>
                
                <%-- =============== TABLE ELEMENT =============== --%>
                <tbody>
                    <%
                        int count = 0;
                        for (RegistrationDTO dto : result) {
                            String urlRewriting = "MainController?btAction=Del&pk=" + dto.getUsername() + "&lastSearchValue=" + request.getParameter("txtSearchValue");
                    %>
                    
                <form class="form" action="MainController">

                    <tr>
                        <%-- =============== USER NO =============== --%>
                        <td>
                            <%= ++count%>
                        </td>
                        
                        <%-- =============== USERNAME =============== --%>
                        <td>
                            <div class="tooltip-container">
                                <span class="tooltip"><%= dto.getLastname()%></span>
                                <span class="text"><%= dto.getUsername()%></span>
                            </div>
                        </td>
                        
                        <%-- =============== PASSWORD =============== --%>
                        <td>
                            <input class="inp" type="text" name="txtPassword" value="<%= dto.getPassword()%>" />
                        </td>
                        
                        <%-- =============== LASTNAME =============== --%>
                        <td> 
                            <input class="inp" type="text" name="txtLastname" value="<%= dto.getLastname()%>" />
                        </td>
                        
                        <%-- =============== ROLE =============== --%>
                        <td>
                            <% if (dto.isRole()) {%>
                            <input class="ui-checkbox" type="checkbox" name="chkAdmin" value="ON" checked="checked" />
                            <%} else { %>
                            <input class="ui-checkbox" type="checkbox" name="chkAdmin" value="ON" />
                            <% }%>
                        </td>
                        
                        <%-- =============== DELETE BUTTON =============== --%>
                        <td>
                            <a class="deletebtn" href="<%= urlRewriting%>"> Delete </a>
                        </td>
                        
                        <%-- =============== UPDATE BUTTON =============== --%>
                        <td>
                            <input type="hidden" name="lastSearchValue" value="<%= request.getParameter("txtSearchValue")%>" />
                            <input class="updateBtn" type="submit" value="Update" name="btAction" />
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
        </div>

    </body>
</html>

