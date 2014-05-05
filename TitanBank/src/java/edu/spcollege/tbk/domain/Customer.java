/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.spcollege.tbk.domain;

import java.util.Date;
import java.util.Objects;



/**
 *
 * @author Zhou
 */
public class Customer implements java.io.Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address;
    private String email;
    private String phone;
    
    // Constructor
    public Customer(String firstName, String lastName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
    
    public Customer(String firstName, String lastName, Date dateOfBirth, String address, String email, String phone) {
       this.firstName = firstName;
       this.lastName = lastName;
       this.dateOfBirth = dateOfBirth;
       this.address = address;
       this.email = email;
       this.phone = phone;
    }
    
    // Getter
    public Long getId() {
        return this.id;
    }
    
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
    
    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getPhone() {
        return this.phone;
    }
    
    // Setter
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }
        return this.id.equals(((Customer)other).id);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    
    // Method
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    // Needed by Hibernate
    private Customer() {
    }
    
    private void setId(Long id) {
        this.id = id;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
