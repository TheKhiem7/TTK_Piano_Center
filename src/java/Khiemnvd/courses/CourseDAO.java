package Khiemnvd.courses;

import Khiemnvd.database.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    // Fetch the first 20 available courses (status is active and quantity > 0) ordered by startDate
    public List<CourseDTO> getCourses(int offset, int limit) throws SQLException {
        List<CourseDTO> courses = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = new DBUtils().makeConnection();
            if (con != null) {
                String sql = "SELECT * FROM courses WHERE status = 'active' AND quantity > 0 ORDER BY startDate OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                pst = con.prepareStatement(sql);
                pst.setInt(1, offset);
                pst.setInt(2, limit);
                rs = pst.executeQuery();
                
                while (rs.next()) {
                    CourseDTO course = new CourseDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getString("description"),
                        rs.getBigDecimal("tuitionFee"),
                        rs.getDate("startDate"),
                        rs.getDate("endDate"),
                        rs.getString("category"),
                        rs.getString("status"),
                        rs.getInt("quantity")
                    );
                    courses.add(course);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (con != null) con.close();
        }
        return courses;
    }

    // Additional methods for search by name or category, if needed
    public List<CourseDTO> searchCourses(String searchValue, String category, int offset, int limit) throws SQLException {
        List<CourseDTO> courses = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = new DBUtils().makeConnection();
            if (con != null) {
                StringBuilder sql = new StringBuilder("SELECT * FROM courses WHERE status = 'active' AND quantity > 0");
                if (searchValue != null && !searchValue.isEmpty()) {
                    sql.append(" AND name LIKE ?");
                }
                if (category != null && !category.isEmpty()) {
                    sql.append(" AND category = ?");
                }
                sql.append(" ORDER BY startDate OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

                pst = con.prepareStatement(sql.toString());
                int paramIndex = 1;
                if (searchValue != null && !searchValue.isEmpty()) {
                    pst.setString(paramIndex++, "%" + searchValue + "%");
                }
                if (category != null && !category.isEmpty()) {
                    pst.setString(paramIndex++, category);
                }
                pst.setInt(paramIndex++, offset);
                pst.setInt(paramIndex, limit);
                
                rs = pst.executeQuery();
                
                while (rs.next()) {
                    CourseDTO course = new CourseDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getString("description"),
                        rs.getBigDecimal("tuitionFee"),
                        rs.getDate("startDate"),
                        rs.getDate("endDate"),
                        rs.getString("category"),
                        rs.getString("status"),
                        rs.getInt("quantity")
                    );
                    courses.add(course);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (con != null) con.close();
        }
        return courses;
    }
}
