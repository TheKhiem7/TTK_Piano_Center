package Khiemnvd.controller.user;

import Khiemnvd.registration.RegistrationDAO;
import Khiemnvd.registration.RegistrationInsertError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TheKhiem7
 */
public class RegisterController extends HttpServlet {
    private final String LOGINPAGE = "index.jsp";
    private final String REGISTERPAGE = "Register.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String url = REGISTERPAGE;
            boolean bErrors = false;
            RegistrationInsertError errors = new RegistrationInsertError();
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String confirm= request.getParameter("txtConfirm");
            String fullname = request.getParameter("txtFullname");
            if(username.trim().length()<6||username.trim().length()>20){
                errors.setUsernameLengthErr("** username must be 6-20 chars! **");
                bErrors = true;
            }
            if(password.trim().length()<6||password.trim().length()>20){
                errors.setPasswordLengthErr("** password must be 6-20 chars! **");
                bErrors = true;
            }
            if(!confirm.trim().equals(password)){
                errors.setConfirmPassNotMatch("** confirm password not matched! **");
                bErrors = true;
            }
            if(fullname.trim().length()<2||fullname.trim().length()>20){
                errors.setFullnameLengthErr("** fullname must be 2-20 chars! **");
                bErrors = true;
            }
            if(bErrors){
                request.setAttribute("INSERTERRORS", errors);
            }else{
            try{
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.insertRecord(username, password, fullname, false);
                if(result){
                    url = LOGINPAGE;
                }
            }catch(SQLException e){
                errors.setUsernameExisted("** username has existed! **");
                bErrors = true;
                request.setAttribute("INSERTERRORS", errors);
                e.printStackTrace();
            }
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
