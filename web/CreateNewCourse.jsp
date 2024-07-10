<%-- 
    Document   : CreateNewCourse
    Created on : Jul 6, 2024, 10:46:20 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Course</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .form-group {
                margin-bottom: 15px;
            }
            .form-control {
                height: calc(1.5em + 0.75rem + 2px);
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <a href="CourseController"><-- Back To Course</a>
            <h1>Create New Course</h1>
            <h2 class="text-danger">${error}</h2>
            <form action="createcourse" method="POST">
                <div class="form-group row">
                    <label for="name" class="col-sm-2 col-form-label">Course Name:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="name" name="name" required="">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="description" class="col-sm-2 col-form-label">Description:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="description" name="description" required="">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="image" class="col-sm-2 col-form-label">Image:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="image" name="image" required="">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="fee" class="col-sm-2 col-form-label">Tuition Fee:</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="fee" name="fee" required="">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="sDate" class="col-sm-2 col-form-label">Start Date:</label>
                    <div class="col-sm-10">
                        <input type="date" class="form-control" id="sDate" name="sDate" required="">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="eDate" class="col-sm-2 col-form-label">End Date:</label>
                    <div class="col-sm-10">
                        <input type="date" class="form-control" id="eDate" name="eDate" required="">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="quantity" class="col-sm-2 col-form-label">Quantity:</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="quantity" name="quantity" min="1" required="" value="1">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="status" class="col-sm-2 col-form-label">Status:</label>
                    <div class="col-sm-10">
                        <select id="status" name="status" class="form-control" required="">
                            <option value="1">Active</option>
                            <option value="0">None</option>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="categoryId" class="col-sm-2 col-form-label">Category:</label>
                    <div class="col-sm-10">
                        <select id="categoryId" name="categoryId" class="form-control" required="">
                            <c:forEach var="c" items="${lc}">
                                <option value="${c.getCategoryId()}">${c.getCategory_name()}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-10 offset-sm-2">
                        <button type="submit" class="btn btn-primary">Create</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>


