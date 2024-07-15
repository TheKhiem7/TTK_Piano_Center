package Khiemnvd.controller;

import Khiemnvd.cart.CartObj;
import Khiemnvd.dal.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Khiemnvd.model.Order;
import Khiemnvd.model.Registration;

/**
 * Servlet implementation class AddToCartController
 */
public class AddToCartController extends HttpServlet {

    private static final String SHOPPINGPAGE = "BookStore.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            CartObj cart = (CartObj) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObj();
            }
            String title = request.getParameter("choBook");
            cart.addToCart(title);
            session.setAttribute("CART", cart);
            response.sendRedirect(SHOPPINGPAGE);
        }
    }

    private void handleOrder(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        Registration user = (Registration) session.getAttribute("user");
        int userId = user.getUser_id();
        int courseId = Integer.parseInt(request.getParameter("course_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        float price = Float.parseFloat(request.getParameter("price"));
        OrderDAO db = new OrderDAO();
        PrintWriter out = response.getWriter();
        out.println(userId);
        out.println(courseId);
        out.println(quantity);
        out.println(price);

        Order order = db.getOrderWithStatusZeroByUserId(userId);
        if (order == null) {
            out.println("is null");
            boolean created = db.createOrder(userId);
            if (created) {
                order = db.getOrderWithStatusZeroByUserId(userId);
            }
        }

        if (order != null) {
            db.addOrderDetail(order.getOrderId(), courseId, quantity, price);
        }

        response.sendRedirect("cart");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleOrder(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
