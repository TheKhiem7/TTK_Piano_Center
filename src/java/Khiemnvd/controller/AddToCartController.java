package Khiemnvd.controller;

import Khiemnvd.cart.CartObj;
import dal.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Order;
import model.Registration;

/**
 *
 * @author TheKhiem7
 */
public class AddToCartController extends HttpServlet {

    private static final String SHOPPINGPAGE = "BookStore.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
