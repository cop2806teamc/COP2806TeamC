/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.spcollege.tbk.domain;

/**
 *
 * @author Zhou
 */
public class LoginAccount {
    
    public enum LoginType {
        CUSTOMER,
        ADMINISTRATOR
    }
    
    private String username;
    private String password;
    private LoginType type;
    
    // Constructor
    public LoginAccount(String username, String password, LoginType type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }
    
    // Getter
    public String getUsername() {
        return this.username;
    }
    
    public LoginType getType() {
        return this.type;
    }
    
    //Setter
    public void setUsername(String username) {
        this.username = username;
    }

    // Methods
    public boolean isValidPassword(String password){
        return this.password.equals(password);
    }
    
    public boolean resetPassword(String oldPassword, String newPassword) {
        if (!this.password.equals(oldPassword)) {
            return false;
        }
        this.password = newPassword;
        return true;
    }
    
    // Needed by Hibernate
    protected LoginAccount() {
    }
    
    protected String getPassword() {
        return this.password;
    }
    
    protected void setPassword(String password) {
        this.password = password;
    }
}
