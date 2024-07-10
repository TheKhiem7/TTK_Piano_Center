package Khiemnvd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TheKhiem7
 */
public class MainController extends HttpServlet {

    private static final String LOGINCONTROLLER = "LoginController";
    private static final String SEARCHCONTROLLER = "SearchController";
    private static final String DELETECONTROLLER = "DeleteController";
    private static final String UPDATECONTROLLER = "UpdateController";
    private static final String ADDTOCARTCONTROLLER = "AddToCartController";
    private static final String VIEWCARTPAGE = "ViewCart.jsp";
    private static final String REMOVEITEMCONTROLLER = "RemoveController";
    private static final String NULLCONTROLLER = "NullController";
    private static final String REGISTERCONTROLLER = "RegisterController";
    private static final String LOGOUTCONTROLLER = "LogoutController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String url = "";
            String action = request.getParameter("btAction");
            if (action == null) {
                url = NULLCONTROLLER;
            } else if (action.equals("Login")) {
                url = LOGINCONTROLLER;
            } else if (action.equals("Search")) {
                url = SEARCHCONTROLLER;
            } else if (action.equals("Del")) {
                url = DELETECONTROLLER;
            } else if (action.equals("Update")) {
                url = UPDATECONTROLLER;
            } else if (action.equals("Add To Cart")) {
                url = ADDTOCARTCONTROLLER;
            } else if (action.equals("View Your Cart")) {
                url = VIEWCARTPAGE;
            } else if (action.equals("Remove")) {
                url = REMOVEITEMCONTROLLER;
            } else if (action.equals("Register")) {
                url = REGISTERCONTROLLER;
            } else if (action.equals("LogOut")) {
                url = LOGOUTCONTROLLER;
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
        processRequest(request, response);
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
