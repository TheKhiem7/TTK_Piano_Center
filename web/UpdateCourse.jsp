<%-- 
    Document   : UpdateCourse'
    Created on : Jul 7, 2024, 12:00:36 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Course</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .form-group {
                margin-bottom: 15px;
            }
            .form-control {
                height: calc(1.5em + 0.75rem + 2px);
            }
            .form-header {
                margin-bottom: 20px;
            }
            .form-img {
                max-width: 100px;
                height: auto;
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <div class="form-header">
                <a href="CourseController"><-- Back To Course</a>
                <h1>Update Course</h1>
                <h2 class="text-danger">${error}</h2>
            </div>
            <form action="updatecourse" method="POST">
                <input type="hidden" name="course_id" value="${course.getCourseId()}"/>
                <div class="form-group row">
                    <label for="name" class="col-sm-2 col-form-label">Course Name:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="name" name="name" value="${course.getCourse_name()}" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="description" class="col-sm-2 col-form-label">Description:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="description" name="description" value="${course.getDescription()}" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="image" class="col-sm-2 col-form-label">Image:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="image" name="image" value="${course.getImage()}" required>
                        <img src="${course.getImage()}" alt="Course Image" class="form-img">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="fee" class="col-sm-2 col-form-label">Tuition Fee:</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="fee" name="fee" value="${course.getTuitionfee()}" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="sDate" class="col-sm-2 col-form-label">Start Date:</label>
                    <div class="col-sm-10">
                        <input type="date" class="form-control" id="sDate" name="sDate" value="${course.getStartDate()}" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="eDate" class="col-sm-2 col-form-label">End Date:</label>
                    <div class="col-sm-10">
                        <input type="date" class="form-control" id="eDate" name="eDate" value="${course.getEndDate()}" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="quantity" class="col-sm-2 col-form-label">Quantity:</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="quantity" name="quantity" value="${course.getQuantity()}" min="1" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="status" class="col-sm-2 col-form-label">Status:</label>
                    <div class="col-sm-10">
                        <select id="status" name="status" class="form-control" required>
                            <option value="1" ${course.status == 1 ? 'selected' : ''}>Active</option>
                            <option value="0" ${course.status == 0 ? 'selected' : ''}>None</option>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="categoryId" class="col-sm-2 col-form-label">Category:</label>
                    <div class="col-sm-10">
                        <select id="categoryId" name="categoryId" class="form-control" required>
                            <c:forEach var="c" items="${lc1}">
                                <option value="${c.getCategoryId()}" ${c.getCategoryId() == course.getCategory().getCategoryId() ? 'selected' : ''}>${c.getCategory_name()}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-10 offset-sm-2">
                        <button type="submit" class="btn btn-primary">Update</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
