package Khiemnvd.controller;

import dal.CoursesDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Course;
import model.Pagination;

public class CourseController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CoursesDAO d = new CoursesDAO();
        String key = request.getParameter("key");
        int cp = 1;
        request.setAttribute("searchKey", key);
        if (key != null && !key.isEmpty()) {
            List<Course> searchCourses = d.searchCourse(key.trim());
            String cp_raw = request.getParameter("cp");
            if (cp_raw != null) {
                cp = Integer.parseInt(cp_raw);
            }
            Pagination page = new Pagination(searchCourses.size(), 20, cp);
            session.setAttribute("page", page);
            List<Course> courses = d.getAccountsByPage(page.getCurrentPage(), 20, searchCourses);
            request.setAttribute("courses", courses);
        } else {
            if (request.getParameter("cp") != null) {
                cp = Integer.parseInt(request.getParameter("cp"));
            }
            Pagination page = new Pagination(d.getAllCourses().size(), 20, cp);
            session.setAttribute("page", page);
            List<Course> courses = d.getAccountsByPage(page.getCurrentPage(), 20, d.getAllCourses());
            request.setAttribute("courses", courses);
        }
        
        request.getRequestDispatcher("Courses.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
