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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Khiemnvd.model.Category;
import Khiemnvd.model.Course;

/**
 *
 * @author Admin
 */
public class UpdateCourse extends HttpServlet {

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
            out.println("<title>Servlet UpdateCourse</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateCourse at " + request.getContextPath() + "</h1>");
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
        String id = request.getParameter("course_id");
        // Kiểm tra xem id có hợp lệ không
        if (id == null || id.isEmpty()) {
            response.sendRedirect("errorPage.jsp"); // Chuyển đến trang lỗi nếu không có id
            return;
        }

        int courseId;
        try {
            courseId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            response.sendRedirect("errorPage.jsp"); // Chuyển đến trang lỗi nếu id không hợp lệ
            return;
        }
        CategoryDAO cdao = new CategoryDAO();
        List<Category> lc1 = cdao.getAllCategories();

        request.setAttribute("lc1", lc1);

        CoursesDAO courseDAO = new CoursesDAO();
        Course course = courseDAO.getCourseById(courseId);

        if (course == null) {
            response.sendRedirect("CourseController");
            return;
        }
        // Đưa thông tin khóa học vào request attributes
        request.setAttribute("course", course);

        // Chuyển tiếp đến trang UpdateCourse.jsp
        request.getRequestDispatcher("UpdateCourse.jsp").forward(request, response);
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
        int courseId;
        try {
            courseId = Integer.parseInt(request.getParameter("course_id"));
        } catch (NumberFormatException e) {
            response.sendRedirect("errorPage.jsp");
            return;
        }

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String image = request.getParameter("image");

        float fee;
        try {
            fee = Float.parseFloat(request.getParameter("fee"));
        } catch (NumberFormatException e) {
            response.sendRedirect("errorPage.jsp");
            return;
        }

        String sDate = request.getParameter("sDate");
        String eDate = request.getParameter("eDate");

        int quantity;
        try {
            quantity = Integer.parseInt(request.getParameter("quantity"));
        } catch (NumberFormatException e) {
            response.sendRedirect("errorPage.jsp");
            return;
        }

        int status;
        try {
            status = Integer.parseInt(request.getParameter("status"));
        } catch (NumberFormatException e) {
            response.sendRedirect("errorPage.jsp");
            return;
        }

        int categoryId;
        try {
            categoryId = Integer.parseInt(request.getParameter("categoryId"));
        } catch (NumberFormatException e) {
            response.sendRedirect("errorPage.jsp");
            return;
        }

        CoursesDAO c = new CoursesDAO();
        c.updateCourse(courseId, name, description, image, fee, sDate, eDate, quantity, status, categoryId);
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
