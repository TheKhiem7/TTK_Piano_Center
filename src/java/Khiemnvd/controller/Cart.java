/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Khiemnvd.controller;

import Khiemnvd.dal.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Khiemnvd.model.Order;
import Khiemnvd.model.OrderDetail;
import Khiemnvd.model.Registration;

/**
 *
 * @author Admin
 */
public class Cart extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
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
        Registration user = (Registration)session.getAttribute("user");
        int userId = user.getUser_id();
        
        PrintWriter out = response.getWriter();
        out.print(userId);

        OrderDAO dao = new OrderDAO();
        ArrayList<Order> orders = dao.getAllOrderByUserId(userId);

        request.setAttribute("orders", orders);
        request.setAttribute("userID", userId);
        request.getRequestDispatcher("Cart.jsp").forward(request, response);
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
        OrderDAO dao = new OrderDAO();
        String type = request.getParameter("type");
        if (type.equals("update")) {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            dao.updateOrderDetailQuantity(orderId, courseId, quantity);
            response.sendRedirect("cart");

        } else if (type.equals("delete")) {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            int courseId = Integer.parseInt(request.getParameter("courseId"));

            dao.deleteOrderDetail(orderId, courseId);
            response.sendRedirect("cart");

        }else if(type.equals("placeOrder")){
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            ArrayList<OrderDetail> orders = dao.getAllOrderDetailByOrderId(orderId);
            float sum = 0;
            for (OrderDetail o : orders) {
                sum += o.getPrice();
            }
            dao.updateOrderStatus(orderId, 1, sum);
            response.sendRedirect("cart");
        }
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
