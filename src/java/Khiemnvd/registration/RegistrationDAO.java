/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Khiemnvd.registration;

import Khiemnvd.database.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import Khiemnvd.model.Registration;

/**
 *
 * @author Admin
 */
public class RegistrationDAO {

    public boolean checkLogin(String username, String password) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        DBUtils db = new DBUtils();
        try {
            con = db.makeConnection();
            if (con != null) {
                String sql = "Select username, password from Registration"
                        + " Where username = ? and password=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
        return false;
    }

    List<RegistrationDTO> ListAccounts;

    public List<RegistrationDTO> getListAccounts() {
        return ListAccounts;
    }

    public boolean searchByLastName(String searchValue) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        DBUtils db = new DBUtils();
        try {
            con = db.makeConnection();
            if (con != null) {
                String sql = "Select username, password, lastname, isAdmin from"
                        + " Registration Where lastname Like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");

                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    RegistrationDTO dto = new RegistrationDTO(username, password, lastname, role);
                    if (this.ListAccounts == null) {
                        this.ListAccounts = new ArrayList<RegistrationDTO>();
                    }
                    this.ListAccounts.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
        return false;
    }

    public boolean deleteRecord(String username) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        DBUtils db = new DBUtils();
        try {
            con = db.makeConnection();
            if (con != null) {
                String sql = "Delete Registration Where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateRecord(String username, String password, String lastname, boolean role) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        DBUtils db = new DBUtils();
        try {
            con = db.makeConnection();
            if (con != null) {
                String sql = "UPDATE Registration Set password = ?, lastname = ?,"
                        + " isAdmin = ? WHERE username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, lastname);
                stm.setBoolean(3, role);
                stm.setString(4, username);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean insertRecord(String username, String password, String lastname, boolean role) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        DBUtils db = new DBUtils();
        try {
            con = db.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Registration values(?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, lastname);
                stm.setBoolean(4, role);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public Registration login(String username, String password) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        DBUtils db = new DBUtils();
        Registration registration = null;

        try {
            con = db.makeConnection();
            if (con != null) {
                String sql = "SELECT * FROM Registration WHERE username = ? AND password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();

                if (rs.next()) {
                    registration = new Registration();
                    registration.setUser_id(rs.getInt("user_id"));
                    registration.setUsername(rs.getString("username"));
                    registration.setPassword(rs.getString("password")); // Note: Returning the password might not be safe
                    registration.setLastname(rs.getString("lastname"));
                    registration.setIsAdmin(rs.getInt("isAdmin"));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return registration;
    }
}
