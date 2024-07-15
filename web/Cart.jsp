<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Order History</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }
            .container {
                width: 80%;
                margin: auto;
                overflow: hidden;
            }
            h1 {
                text-align: center;
                color: #333;
            }
            table {
                width: 100%;
                margin-bottom: 20px;
                border-collapse: collapse;
            }
            table, th, td {
                border: 1px solid #ddd;
            }
            th, td {
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
            .order-details {
                margin-top: 10px;
            }
            .actions {
                text-align: center;
            }
            .actions button {
                margin: 5px;
                padding: 5px 10px;
                background-color: #007BFF;
                color: white;
                border: none;
                cursor: pointer;
            }
            .actions button:hover {
                background-color: #0056b3;
            }

            .header{
                height: 55px;
            }
            .header-bg{
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-image: url('img/header.png');
                background-size: cover;
                z-index: -1;
            }

            .header h1{
                text-align: center;
            }


            .updateBtn {
                padding: 1rem 2rem;
                font-weight: 700;
                background: rgb(255, 255, 255);
                color: blueviolet;
                cursor: pointer;
                border-radius: 0.5rem;
                border-bottom: 2px solid blueviolet;
                border-right: 2px solid blueviolet;
                border-top: 2px solid white;
                border-left: 2px solid white;
                transition-duration: 1s;
                transition-property: border-top, border-left, border-bottom, border-right,
                    box-shadow;
            }

            .updateBtn:hover {
                border-top: 2px solid blueviolet;
                border-left: 2px solid blueviolet;
                border-bottom: 2px solid rgb(238, 103, 238);
                border-right: 2px solid rgb(238, 103, 238);
                box-shadow: rgba(240, 46, 170, 0.4) 5px 5px, rgba(240, 46, 170, 0.3) 10px 10px,
                    rgba(240, 46, 170, 0.2) 15px 15px;
            }

            .deletebtn {
                display: inline-block;
                padding: 0.9rem 1.8rem;
                font-size: 16px;
                font-weight: 700;
                color: white;
                border: 3px solid rgb(252, 70, 100);
                cursor: pointer;
                position: relative;
                background-color: transparent;
                text-decoration: none;
                overflow: hidden;
                z-index: 1;
                font-family: inherit;
            }

            .deletebtn::before {
                content: "";
                position: absolute;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                background-color: rgb(252, 70, 100);
                transform: translateX(-100%);
                transition: all .3s;
                z-index: -1;
            }

            .deletebtn:hover::before {
                transform: translateX(0);
            }


        </style>
    </head>
    <body>
        <div class="container">
            <div class="header header-bg">
                <%-- =============== HEADER TITLE ===============  --%>
                <h1 class="my-4">Order list of User: ${userId}</h1>
            </div>
            <c:choose>
                <%-- =============== CHECK IF ORDERS ARE NOT EMPTY ===============  --%>
                <c:when test="${not empty orders}">

                    <table>
                        <%-- =============== TABLE HEADER ===============  --%>
                        <tr>
                            <th>Order ID</th>
                            <th>Order Date</th>
                            <th>Total Money</th>
                            <th>Status</th>
                            <th>Order Details</th>
                            <th>Actions</th>
                        </tr>

                        <%-- =============== LOOP THROUGH ORDERS ===============  --%>
                        <c:forEach var="order" items="${orders}">
                            <%-- =============== CHECK IF ORDER HAS DETAILS ===============  --%>
                            <c:if test="${order.orderDetails.size() > 0}">
                                <tr>
                                    <td>${order.orderId}</td>
                                    <td>${order.orderDate}</td>
                                    <td>${order.total_money}</td>
                                    <td>${order.status == 1 ? "DONE" : "NOT YET"}</td>
                                    <td>
                                        <%-- =============== ORDER DETAL ===============  --%>
                                        <table class="order-details">
                                            <tr>
                                                <th>Course ID</th>
                                                <th>Quantity</th>
                                                <th>Price</th>
                                                <th>Actions</th>
                                            </tr>

                                            <%-- =============== LOOP THROUGH ORDER DETAILS ===============  --%>
                                            <c:forEach var="detail" items="${order.orderDetails}">
                                                <tr>
                                                    <td>${detail.course_id}</td>
                                                    <td>
                                                        <c:choose>
                                                            <%-- =============== CHECK ORDER STATUS FOR QUANTITY INPUT ===============  --%>
                                                            <c:when test="${order.status == 0}">
                                                                <form action="cart" method="post">
                                                                    <input type="hidden" name="orderId" value="${order.orderId}" />
                                                                    <input type="hidden" name="courseId" value="${detail.course_id}" />
                                                                    <input type="number" name="quantity" value="${detail.quantity}" min="1" />
                                                                    <input type="hidden" name="type" value="update" />
                                                                    <button class="updateBtn" type="submit">Update</button>
                                                                </form>
                                                            </c:when>
                                                            <c:otherwise>
                                                                ${detail.quantity}
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td>${detail.price}</td>
                                                    <td class="">
                                                        <c:choose>
                                                            <%-- =============== CHECK ORDER STATUS FOR DELETE ACTION ===============  --%>
                                                            <c:when test="${order.status == 0}">
                                                                <form action="cart" method="post">
                                                                    <input type="hidden" name="orderId" value="${order.orderId}" />
                                                                    <input type="hidden" name="courseId" value="${detail.course_id}" />
                                                                    <input type="hidden" name="type" value="delete" />
                                                                    <button class="deletebtn" type="submit">Delete</button>
                                                                </form>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <!-- No action buttons for status 1 -->
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>

                                            </c:forEach>
                                        </table>
                                    </td>

                                    <td class="">
                                        <%-- =============== CHECK ORDER STATUS FOR PLACE ORDER ACTION ===============  --%>
                                        <c:if test="${order.status == 0}">
                                            <form action="cart" method="post" style="text-align:center;">
                                                <input type="hidden" name="orderId" value="${order.orderId}" />
                                                <input type="hidden" name="type" value="placeOrder" />
                                                <button class="updateBtn" type="submit">Order</button>
                                            </form>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:if>


                        </c:forEach>
                    </table>
                </c:when>
                <%-- =============== DISPLAY MESSAGE IF NO ORDERS FOUND ===============  --%>
                <c:otherwise>
                    <p>No orders found for user ID ${userId}.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
