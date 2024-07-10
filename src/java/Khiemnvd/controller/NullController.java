package Khiemnvd.controller;

import Khiemnvd.registration.RegistrationDAO;
import com.sun.corba.se.spi.protocol.RequestDispatcherDefault;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TheKhiem7
 */
public class NullController extends HttpServlet {

    private static final String SEARCHPAGE = "Search.jsp";
    private static final String LOGINPAGE = "index.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGINPAGE;
        try (PrintWriter out = response.getWriter()) {
            try{
                Cookie[] cookies = request.getCookies();
                if(cookies != null){
                    for (Cookie cookie : cookies) {
                        String username = cookie.getName();
                        String password = cookie.getValue();
                        RegistrationDAO dao = new RegistrationDAO();
                        boolean result = dao.checkLogin(username, password);
                        if (result) {
                            url = SEARCHPAGE;
                        }
                    }
                }
            }catch (SQLException ex){
                ex.printStackTrace();
            }finally {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
        }            
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
