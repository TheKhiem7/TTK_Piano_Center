/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Khiemnvd.dal;

import Khiemnvd.database.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Khiemnvd.model.Category;
import Khiemnvd.model.Course;
import Khiemnvd.model.Order;
import Khiemnvd.model.OrderDetail;

/**
 *
 * @author Admin
 */
public class OrderDAO extends DBUtils {

    private Connection con;
    DBUtils db = new DBUtils();

    public ArrayList<Order> getAllOrderByUserId(int id) {
        ArrayList<Order> listOrder = new ArrayList<>();
        String sql = "Select * from [Order] Where user_id = ?";
        try {
            con = db.makeConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order or = new Order();
                or.setOrderId(rs.getInt(1));
                or.setOrderDate(rs.getDate(3));
                or.setUser_id(rs.getInt(2));
                or.setTotal_money(rs.getFloat(4));
                or.setStatus(rs.getInt(5));
                or.setOrderDetails(getAllOrderDetailByOrderId(rs.getInt(1)));

                listOrder.add(or);
            }
            return listOrder;
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return null;
    }

    public ArrayList<OrderDetail> getAllOrderDetailByOrderId(int id) {
        ArrayList<OrderDetail> listOrderDetail = new ArrayList<>();
        String sql = "Select * from OrderDetail Where order_id = ?";
        try {
            con = db.makeConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail or = new OrderDetail();
                or.setCourse_id(rs.getInt(1));
                or.setOrder_id(rs.getInt(2));
                or.setQuantity(rs.getInt(3));
                or.setPrice(rs.getFloat(4));
                listOrderDetail.add(or);
            }
            return listOrderDetail;
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return null;
    }

    public boolean insertOrderDetail(OrderDetail detail) {
        String sql = "INSERT INTO OrderDetail (order_id, course_id, quantity, price) VALUES (?, ?, ?, ?)";
        try {
            con = db.makeConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, detail.getOrder_id());
            ps.setInt(2, detail.getCourse_id());
            ps.setInt(3, detail.getQuantity());
            ps.setFloat(4, detail.getPrice());
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return false;
    }

    public boolean updateOrderDetailQuantity(int orderId, int courseId, int quantity) {
        String sql = "UPDATE OrderDetail SET quantity = ? WHERE order_id = ? AND course_id = ?";
        try {
            con = db.makeConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, orderId);
            ps.setInt(3, courseId);
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return false;
    }

    public boolean insertOrder(Order order) {
        String sql = "INSERT INTO [Order] (user_id, order_date, total_money, status) VALUES (?, ?, ?, ?)";
        try {
            con = db.makeConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, order.getUser_id());
            ps.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            ps.setFloat(3, order.getTotal_money());
            ps.setInt(4, order.getStatus());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    order.setOrderId(generatedKeys.getInt(1));
                    return true;
                }
            }
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return false;
    }

    public boolean deleteOrderDetail(int orderId, int courseId) {
        String sql = "DELETE FROM OrderDetail WHERE order_id = ? AND course_id = ?";
        try {
            con = db.makeConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.setInt(2, courseId);
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return false;
    }

    public boolean updateOrderStatus(int orderId, int status, float total) {
        String sql = "UPDATE [Order] SET status = ?, total_money = ? WHERE order_id = ?";
        try {
            con = db.makeConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setFloat(2, total);
            ps.setInt(3, orderId);
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return false;
    }
    
    public Order getOrderWithStatusZeroByUserId(int userId) {
        String sql = "SELECT * FROM [Order] WHERE user_id = ? AND status = 0";
        try {
            con = db.makeConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt(1));
                order.setOrderDate(rs.getDate(3));
                order.setUser_id(rs.getInt(2));
                order.setTotal_money(rs.getFloat(4));
                order.setStatus(rs.getInt(5));
                order.setOrderDetails(getAllOrderDetailByOrderId(rs.getInt(1)));
                return order;
            }
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        } 
        return null;
    }
    
    public boolean createOrder(int userId) {
        String sql = "INSERT INTO [Order] (user_id, order_date, total_money, status) VALUES (?, GETDATE(), 0, 0)";
        try {
            con = db.makeConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return true;
                }
            }
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return false;
    }
    
    public boolean addOrderDetail(int orderId, int courseId, int quantity, float price) {
        String sql = "INSERT INTO OrderDetail (order_id, course_id, quantity, price) VALUES (?, ?, ?, ?)";
        try {
            con = db.makeConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.setInt(2, courseId);
            ps.setInt(3, quantity);
            ps.setFloat(4, price);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        } 
        return false;
    }

}
