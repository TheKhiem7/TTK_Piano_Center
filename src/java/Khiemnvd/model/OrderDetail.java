/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Khiemnvd.model;

/**
 *
 * @author Admin
 */
public class OrderDetail {
    private int course_id;
    private int order_id;
    private int quantity;
    private float price;

    public OrderDetail() {
    }

    public OrderDetail(int course_id, int order_id, int quantity, float price) {
        this.course_id = course_id;
        this.order_id = order_id;
        this.quantity = quantity;
        this.price = price;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    
}
