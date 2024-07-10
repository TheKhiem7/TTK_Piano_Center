package Khiemnvd.controller;

import Khiemnvd.registration.RegistrationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Registration;

/**
 *
 * @author TheKhiem7
 */
public class LoginController extends HttpServlet {

    private static final String ADMINPAGE = "Admin.jsp";
    private static final String INVALIDPAGE = "Invalid.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String url = INVALIDPAGE;
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            RegistrationDAO dao = new RegistrationDAO();

            try {
                boolean result = dao.checkLogin(username, password);

                if (result) {
                    Registration user = dao.login(username, password);
                    request.getSession().setAttribute("user", user);
                    url = ADMINPAGE;

                    Cookie cookie = new Cookie(username, password);
                    cookie.setMaxAge(60);
                    response.addCookie(cookie);

                    if (user.getIsAdmin() == 0) {
                        url = "CourseController";
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            response.sendRedirect(url);
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
