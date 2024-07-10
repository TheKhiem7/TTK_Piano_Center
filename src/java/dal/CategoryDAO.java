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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;

/**
 *
 * @author Admin
 */
public class CategoryDAO extends DBUtils {

    private Connection con;
    DBUtils db = new DBUtils();
    
    public List<Category> getAllCategories() {
        List<Category> listCategories = new ArrayList<>();
        String sql = "SELECT * FROM Category";
        try {
            con = db.makeConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category cate = new Category();
                cate.setCategoryId(rs.getInt(1));
                cate.setCategory_name(rs.getString(2));
                cate.setDescription(rs.getString(3)); 
                listCategories.add(cate);
            }
            return listCategories;
        } catch (SQLException e) {
            Logger.getLogger(CoursesDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return null;
    }
    
    public static void main(String[] args) {
        CategoryDAO d = new CategoryDAO();
        System.out.println(d.getAllCategories().get(1).getCategoryId());
    }
}
