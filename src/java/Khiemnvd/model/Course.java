/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Khiemnvd.model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Course {

    private int courseId;
    private String course_name;
    private String description;
    private String image;
    private float tuitionfee;
    private Date startDate;
    private Date endDate;
    private Category category;
    private int quantity;
    private int status;

    public Course() {
    }

    public Course(int courseId, String course_name, String description, String image, float tuitionfee, Date startDate, Date endDate, Category category, int quantity, int status) {
        this.courseId = courseId;
        this.course_name = course_name;
        this.description = description;
        this.image = image;
        this.tuitionfee = tuitionfee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
        this.quantity = quantity;
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getTuitionfee() {
        return tuitionfee;
    }

    public void setTuitionfee(float tuitionfee) {
        this.tuitionfee = tuitionfee;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
