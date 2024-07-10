/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import Khiemnvd.database.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

/**
 *
 * @author Admin
 */
public class CoursesDAO extends DBUtils {

    private Connection con;
    DBUtils db = new DBUtils();

    public List<Course> getAllCourses() {
        List<Course> listCourse = new ArrayList<>();
        String sql = "SELECT c.course_id, c.course_name, c.description, c.image, c.tuition_fee, c.startDate, c.endDate, c.quantity, c.status, ct.category_id, ct.category_name\n"
                + "FROM Course c\n"
                + "JOIN Category ct ON c.category_id = ct.category_id\n"
                + "ORDER BY c.startDate asc";
        try {
            con = db.makeConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setCourseId(rs.getInt(1));
                c.setCourse_name(rs.getString(2));
                c.setDescription(rs.getString(3));
                c.setImage(rs.getString(4));
                c.setTuitionfee(rs.getFloat(5));
                c.setStartDate(rs.getDate(6));
                c.setEndDate(rs.getDate(7));
                c.setQuantity(rs.getInt(8));
                c.setStatus(rs.getInt(9));
                Category cate = new Category();
                cate.setCategoryId(rs.getInt(10));
                cate.setCategory_name(rs.getString(11));
                c.setCategory(cate);
                listCourse.add(c);
            }
            return listCourse;
        } catch (Exception e) {
            Logger.getLogger(CoursesDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return null;
    }

    public void addNewCourse(String name, String description, String image, float fee, String sDate, String eDate, int quantity, int status, int cateId) {
        String sql = "INSERT INTO [dbo].[Course]\n"
                + "           ([course_name]\n"
                + "           ,[description]\n"
                + "           ,[image]\n"
                + "           ,[tuition_fee]\n"
                + "           ,[startDate]\n"
                + "           ,[endDate]\n"
                + "           ,[quantity]\n"
                + "           ,[status]\n"
                + "           ,[category_id])\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            con = db.makeConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, image);
            ps.setFloat(4, fee);
            ps.setString(5, sDate);
            ps.setString(6, eDate);
            ps.setInt(7, quantity);
            ps.setInt(8, status);
            ps.setInt(9, cateId);
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(CoursesDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
    }

    public List<Course> getAccountsByPage(int currentPage, int pageLimit, List<Course> allAccounts) {
        Pagination pagination = new Pagination(allAccounts.size(), pageLimit, currentPage);
        List<Course> productsOnPage = new Vector<>();
        for (int i = pagination.getStartItem(); i <= pagination.getLastItem() && i < allAccounts.size(); i++) {
            productsOnPage.add(allAccounts.get(i));
        }
        return productsOnPage;
    }

    public List<Course> searchCourse(String key) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.course_id, c.course_name, c.description, c.image, c.tuition_fee, c.startDate, c.endDate, c.quantity, c.status, ct.category_id, ct.category_name\n"
                + "FROM Course c\n"
                + "JOIN Category ct ON c.category_id = ct.category_id\n"
                + "WHERE ct.category_name LIKE ? OR c.course_name LIKE ?\n"
                + "ORDER BY c.startDate ASC;";

        try {
            con = db.makeConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ps.setString(2, "%" + key + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setCourseId(rs.getInt("course_id"));
                c.setCourse_name(rs.getString("course_name"));
                c.setDescription(rs.getString("description"));
                c.setImage(rs.getString("image"));
                c.setTuitionfee(rs.getFloat("tuition_fee"));
                c.setStartDate(rs.getDate("startDate"));
                c.setEndDate(rs.getDate("endDate"));
                c.setQuantity(rs.getInt("quantity"));
                c.setStatus(rs.getInt("status"));

                Category cate = new Category();
                cate.setCategoryId(rs.getInt("category_id"));
                cate.setCategory_name(rs.getString("category_name"));
                c.setCategory(cate);

                courses.add(c);
            }
            return courses;
        } catch (SQLException e) {
            Logger.getLogger(CoursesDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null; // Retourne null en cas d'erreur
    }

    public void updateCourse(int id, String name, String description, String image, float fee, String sDate, String eDate, int quantity, int status, int cateId) {
        String sql = "UPDATE [dbo].[Course]\n"
                + "   SET [course_name] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[tuition_fee] = ?\n"
                + "      ,[startDate] = ?\n"
                + "      ,[endDate] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[category_id] = ?\n"
                + " WHERE [course_id] = ?";
        try {
            con = db.makeConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, image);
            ps.setFloat(4, fee);
            ps.setString(5, sDate);
            ps.setString(6, eDate);
            ps.setInt(7, quantity);
            ps.setInt(8, status);
            ps.setInt(9, cateId);
            ps.setInt(10, id); // Course ID
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(CoursesDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
    }

    public Course getCourseById(int courseId) {
        String sql = "SELECT * FROM [dbo].[Course] WHERE course_id = ?";
        Course course = null;
        try {
            con = db.makeConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                course = new Course();
                course.setCourseId(rs.getInt("course_id"));
                course.setCourse_name(rs.getString("course_name"));
                course.setDescription(rs.getString("description"));
                course.setImage(rs.getString("image"));
                course.setTuitionfee(rs.getFloat("tuition_fee"));
                course.setStartDate(rs.getDate("startDate"));
                course.setEndDate(rs.getDate("endDate"));
                course.setQuantity(rs.getInt("quantity"));
                course.setStatus(rs.getInt("status"));
                Category category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                course.setCategory(category);
            }
        } catch (SQLException e) {
            Logger.getLogger(CoursesDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return course;
    }

}
