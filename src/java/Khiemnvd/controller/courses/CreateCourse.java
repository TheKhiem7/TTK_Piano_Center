/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Khiemnvd.controller.courses;

import Khiemnvd.dal.CategoryDAO;
import Khiemnvd.dal.CoursesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Khiemnvd.model.Category;

/**
 *
 * @author Admin
 */
public class CreateCourse extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateCourseServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateCourseServlet at " + request.getContextPath() + "</h1>");
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
        CategoryDAO cdao = new CategoryDAO();
        List<Category> lc = cdao.getAllCategories();
        request.setAttribute("lc", lc);
        request.getRequestDispatcher("CreateNewCourse.jsp").forward(request, response);
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
        CoursesDAO c = new CoursesDAO();
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        String feeStr = request.getParameter("fee");
        String sDate = request.getParameter("sDate");
        String eDate = request.getParameter("eDate");
        String quantityStr = request.getParameter("quantity");
        String statusStr = request.getParameter("status");
        String categoryIdStr = request.getParameter("categoryId");

        // Validate and parse data
        float fee = 0;
        int quantity = 0;
        int status = 0;
        int categoryId = 0;

        try {
            fee = Float.parseFloat(feeStr);
            quantity = Integer.parseInt(quantityStr);
            status = Integer.parseInt(statusStr);
            categoryId = Integer.parseInt(categoryIdStr);

        } catch (NumberFormatException e) {
            // Handle parsing error
            request.setAttribute("error", "Invalid input format");
            doGet(request, response);
            return;
        }

        if (sDate.compareTo(eDate) >= 0) {
            // Set error message and forward back to form
            request.setAttribute("error", "Start date must be before end date");
            doGet(request, response);
            return;
        }

        c.addNewCourse(name, description, image, fee, sDate, eDate, quantity, status, categoryId);
        response.sendRedirect("CourseController");
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
