<%-- 
    Document   : Courses
    Created on : Jul 5, 2024, 6:31:11 PM
    Author     : TheKhiem7
--%>

<%@page import="Khiemnvd.courses.CourseDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TTK's Courses</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                padding: 20px;
            }
            .course-table img {
                max-width: 100px;
                height: auto;
            }
            .alert-info {
                text-align: center;
                font-weight: bold;
            }
            h2 {
                color: #dc3545;
            }
            .wide-column {
                width: 150px;
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
                background-attachment: fixed;
                /*filter: blur(8px);*/
                z-index: -1;
            }

        </style>
    </head>
    <body>
        <!--<div class="webpage-background"></div>-->
        <div class="container">
            <h1 class="my-4">All Piano Course in TTK Piano Music Center</h1>
            <c:if test="${sessionScope.user.isAdmin == 1}">
                <a href="createcourse" class="btn btn-primary mb-3">Create new Course</a>
            </c:if>

            <c:if test="${sessionScope.user.isAdmin == 0}">
                <a href="cart" class="btn btn-primary mb-3">Cart</a>
            </c:if>    
            <div class="mb-3">
                <form action="CourseController" method="GET" class="form-inline">
                    <input type="hidden" name="cp" value="1">
                    <input type="text" placeholder="search by category or name" name="key" class="form-control mr-2" style="width: 50%;"/>
                    <button type="submit" class="btn btn-success">Search</button>
                </form>
            </div>

            <c:if test="${empty courses}">
                <div class="alert alert-info">No accounts were found with the words '${searchKey}'</div>
                <div class="text-center">
                    <img src="img/notfoundaccount.jpg" alt="Not Found" class="img-fluid" style="height: 400px;">
                </div>
                <h2 class="text-center">Opp! Not found this account...</h2>
            </c:if>

            <c:if test="${not empty courses}">
                <table class="table table-bordered course-table">
                    <thead class="thead-dark">
                        <tr>
                            <th>Course Name</th>
                            <th>Image</th>
                            <th>Description</th>
                            <th>Tuition Fee</th>
                            <th class="wide-column">Start Date</th>
                            <th class="wide-column">End Date</th>
                            <th>Quantity</th>
                            <th>Category Name</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="c" items="${courses}">
                            <tr>
                                <td>${c.getCourse_name()}</td>
                                <td>
                                    <img src="${c.getImage()}" alt="${c.getImage()}" class="img-thumbnail">
                                </td>
                                <td>${c.getDescription()}</td>
                                <td>${c.getTuitionfee()}</td>
                                <td class="wide-column">${c.getStartDate()}</td>
                                <td class="wide-column">${c.getEndDate()}</td>
                                <td>${c.getQuantity()}</td>
                                <td>${c.getCategory().getCategory_name()}</td>
                                <td>${c.getStatus() == 1 ? "Active" : "None"}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${sessionScope.user == null}">
                                            <div class="d-flex flex-column">
                                                <a href="Register.html">Add to Cart</a>
                                            </div>
                                        </c:when>
                                        <c:when test="${sessionScope.user.isAdmin == 1}">
                                            <a href="updatecourse?course_id=${c.getCourseId()}" class="btn btn-warning mb-2">Update</a>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="d-flex flex-column">
                                                <a href="AddToCartController?course_id=${c.getCourseId()}&quantity=${c.getQuantity()}&price=${c.getTuitionfee()}" class="btn btn-primary">Add to Cart</a>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <!--Paging -->
                <%@include file="/pagination.jsp" %>
            </c:if>
        </div>
    </body>
</html>



