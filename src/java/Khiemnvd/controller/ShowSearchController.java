package Khiemnvd.controller;

import Khiemnvd.registration.RegistrationDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TheKhiem7
 */
public class ShowSearchController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShowSearchController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> search result </h1>");

            String searchValue = request.getParameter("txtSearchValue");
            out.println("your search value is:" + searchValue);
            List<RegistrationDTO> result
                    = (List<RegistrationDTO>) request.getAttribute("SEARCH");
            if (result != null) {
                out.println("<table border='1'>");
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th>No</th>");
                out.println("<th>Usename</th>");
                out.println("<th>Password</th>");
                out.println("<th>Fullname</th>");
                out.println("<th>Role</th>");
                out.println("<th>Delete</th>");
                out.println("<th>Update</th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");
                int count=0;
                for (RegistrationDTO dto : result) {
                    String urlRewriting = "MainController?btAction=Del&pk=" + dto.getUsername() + "&lastSearchValue=" +request.getParameter("txtSearchValue");
                    out.println("<form action=\'MainController\'");
                    out.println("<tr>");
                    out.println("<td>"
                            + ++count
                            + "</td>");
                    out.println("<td>"
                            + "<input type='hidden' name='txtUsername' value='"
                            + dto.getUsername()
                            + "' />"
                            + dto.getUsername()      
                            + "</td>");
                    out.println("<td>"
                            + "<input type='text' name='txtPassword' value='"
                            + dto.getPassword()
                            + "' />"
                            + "</td>");
                    out.println("<td>"
                            + "<input type='text' name='txtLastname' value='"
                            + dto.getLastname()
                            + "' />"
                            + "</td>");
                    if(dto.isRole()){
                    out.println("<td>"
                            + "<input type='checkbox' name='chkAdmin' value='ADMIN' checked='checked' />"
                            + "</td>");
                    }else{
                    out.println("<td>"
                            + "<input type='checkbox' name='chkAdmin' value='ADMIN' />"
                            + "</td>");
                    }
                    out.println("<td>"
                            + "<a href='"
                            + urlRewriting
                            + "'>Delete</a>"
                            + "</td>");
                    out.println("<td>"
                            + "<input type='submit' value='Update' name='btAction' />"
                            + "<input type='hidden' name='lastSearchValue' value='"
                            + request.getParameter("txtSearchValue")
                            + "' />"
                            + "</td>");
                    out.println("</tr>");
                    out.println("</form>");
                }
                
                out.println("</tbody>");
                out.println("</table>");
            }else{
                out.println("<h2> There are no record is matched </h2>");
            }

            out.println("</body>");
            out.println("</html>");
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
