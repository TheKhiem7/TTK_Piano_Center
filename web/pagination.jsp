<%-- 
    Document   : pagination
    Created on : Jul 6, 2024, 10:01:09 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .pagination {
                display: flex;
                justify-content: center;
                margin-top: 20px;
                list-style-type: none;
                padding: 0;
            }

            .pagination li {
                display: inline-block;
                margin: 0 4px;
            }

            .pagination a, .pagination span {
                color: black;
                padding: 8px 16px;
                text-decoration: none;
                border: 1px solid #ddd;
                display: inline-block;
            }

            .pagination a:hover:not(.active) {
                background-color: #ddd;
            }

            .pagination a.active {
                background-color: red;
                color: white;
                border: 1px solid red;
            }
        </style>
    </head>
    <c:if test="${searchKey != null}">
        <c:set var="keySearch" value="&key=${searchKey}" />
    </c:if>
    <body>
        <div class="text-center col-md-6 mt-5" style="margin-left: 40%">
            <nav class="text-center" aria-label="Page navigation example">
                <ul class="pagination text-center" style="margin-left: 6%;">
                    <c:choose>
                        <c:when test="${sessionScope.page.getTotal() <= sessionScope.page.getPageLimit()}">
                            <li class="page-item active"><a class="page-link" href="CourseController?cp=${sessionScope.page.getCurrentPage()}${keySearch}" onclick="handlePageClick(event)">${sessionScope.page.getCurrentPage()}</a></li>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${sessionScope.page.getCurrentPage()>1&&sessionScope.page.getCurrentPage()<sessionScope.page.getTotalPage()}">
                                <li class="page-item"><a class="page-link" href="CourseController?cp=1${keySearch}" onclick="handlePageClick(event)">First</a></li>
                                <li class="page-item"><a class="page-link" href="CourseController?cp=${sessionScope.page.getCurrentPage()-1}${keySearch}" onclick="handlePageClick(event)">${sessionScope.page.getCurrentPage()-1}</a></li>
                                <li class="page-item active"><a class="page-link" href="CourseController?cp=${sessionScope.page.getCurrentPage()}${keySearch}" onclick="handlePageClick(event)">${sessionScope.page.getCurrentPage()}</a></li>
                                <li class="page-item"><a class="page-link" href="CourseController?cp=${sessionScope.page.getCurrentPage()+1}${keySearch}" onclick="handlePageClick(event)">${sessionScope.page.getCurrentPage()+1}</a></li>
                                <li class="page-item"><a class="page-link" href="CourseController?cp=${sessionScope.page.getTotalPage()}${keySearch}">Last</a></li>
                            </c:if>
                            <c:if test="${sessionScope.page.getCurrentPage() == 1}">
                                <li class="page-item active"><a class="page-link" href="CourseController?cp=${sessionScope.page.getCurrentPage()}${keySearch}" onclick="handlePageClick(event)">${sessionScope.page.getCurrentPage()}</a></li>
                                <li class="page-item"><a class="page-link" href="CourseController?cp=${sessionScope.page.getCurrentPage()+1}${keySearch}" onclick="handlePageClick(event)">${sessionScope.page.getCurrentPage()+1}</a></li>
                                <li class="page-item"><a class="page-link" href="CourseController?cp=${sessionScope.page.getTotalPage()}${keySearch}">Last</a></li>
                            </c:if>
                            <c:if test="${sessionScope.page.getCurrentPage() == sessionScope.page.getTotalPage()}">
                                <li class="page-item"><a class="page-link" href="CourseController?cp=1${keySearch}">First</a></li>
                                <li class="page-item"><a class="page-link" href="CourseController?cp=${sessionScope.page.getCurrentPage()-1}${keySearch}" onclick="handlePageClick(event)">${sessionScope.page.getCurrentPage()-1}</a></li>
                                <li class="page-item active"><a class="page-link" href="CourseController?cp=${sessionScope.page.getCurrentPage()}${keySearch}" onclick="handlePageClick(event)">${sessionScope.page.getCurrentPage()}</a></li>
                            </c:if>
                        </c:otherwise>
                    </c:choose>

                </ul>
            </nav>
        </div>
        <script>
            function handlePageClick(event) {
                var currentPage = parseInt(event.target.getAttribute('href').split("=")[1]);
                var currentDisplayedPage = parseInt("${sessionScope.page.getCurrentPage()}");
                if (currentPage === currentDisplayedPage) {
                    event.preventDefault();
                }
            }
        </script>
    </body>
</html>
