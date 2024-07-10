/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Khiemnvd.registration;

import java.io.Serializable;

/**
 *
 * @author TheKhiem7
 */
public class RegistrationDTO implements Serializable{
    private String username;
    private String password;
    private String lastname;
    private boolean role;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String username, String password, String lastname, boolean role) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }
    
    
}
