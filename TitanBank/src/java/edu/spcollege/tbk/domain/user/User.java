/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.spcollege.tbk.domain.user;

import edu.spcollege.tbk.domain.Customer;

/**
 *
 * @author Zhou
 */
public class User implements java.io.Serializable {
    private Long id;
    private String username;
    private String password;
    private Customer customer;
    
    // Constructor
    public User(String username, String password, Customer customer) {
        //super(username, password, LoginAccount.LoginType.CUSTOMER);
        this.username = username;
        this.password = password;
        this.customer = customer;
    }

    // Getter
    public Long getId() {
        return this.id;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public Customer getCustomer() {
        return this.customer;
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
    private User() {
        //super();
    }

    private String getPassword() {
        return this.password;
    }
    
    private void setId(Long id) {
        this.id = id;
    }
        
    private void setPassword(String password) {
        this.password = password;
    }

    private void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
