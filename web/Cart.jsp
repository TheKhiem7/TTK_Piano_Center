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
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Order History for User ID: ${userId}</h1>

            <c:choose>
                <c:when test="${not empty orders}">
                    <table>
                        <tr>
                            <th>Order ID</th>
                            <th>Order Date</th>
                            <th>Total Money</th>
                            <th>Status</th>
                            <th>Order Details</th>
                            <th>Actions</th>
                        </tr>
                        <c:forEach var="order" items="${orders}">
                            <c:if test="${order.orderDetails.size() > 0}" >
                                <tr>
                                    <td>${order.orderId}</td>
                                    <td>${order.orderDate}</td>
                                    <td>${order.total_money}</td>
                                    <td>${order.status == 1 ? "DONE" : "NOT YET"}</td>
                                    <td>
                                        <table class="order-details">
                                            <tr>
                                                <th>Course ID</th>
                                                <th>Quantity</th>
                                                <th>Price</th>
                                                <th>Actions</th>
                                            </tr>

                                            <c:forEach var="detail" items="${order.orderDetails}">
                                                <tr>
                                                    <td>${detail.course_id}</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${order.status == 0}">
                                                                <form action="cart" method="post">
                                                                    <input type="hidden" name="orderId" value="${order.orderId}" />
                                                                    <input type="hidden" name="courseId" value="${detail.course_id}" />
                                                                    <input type="number" name="quantity" value="${detail.quantity}" min="1" />
                                                                    <input type="hidden" name="type" value="update" />
                                                                    <button type="submit">Update</button>
                                                                </form>
                                                            </c:when>
                                                            <c:otherwise>
                                                                ${detail.quantity}
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td>${detail.price}</td>
                                                    <td class="actions">
                                                        <c:choose>
                                                            <c:when test="${order.status == 0}">
                                                                <form action="cart" method="post" style="display:inline;">
                                                                    <input type="hidden" name="orderId" value="${order.orderId}" />
                                                                    <input type="hidden" name="courseId" value="${detail.course_id}" />
                                                                    <input type="hidden" name="type" value="delete" />
                                                                    <button type="submit">Delete</button>
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
                                    <td class="actions">
                                        <c:if test="${order.status == 0}">
                                            <form action="cart" method="post" style="text-align:center;">
                                                <input type="hidden" name="orderId" value="${order.orderId}" />
                                                <input type="hidden" name="type" value="placeOrder" />
                                                <button type="submit">Order</button>
                                            </form>
                                        </c:if>
                                    </td>
                                </tr> 
                            </c:if>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>No orders found for user ID ${userId}.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
