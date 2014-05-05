/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.spcollege.tbk.domain.check;

import edu.spcollege.tbk.domain.Customer;
import edu.spcollege.tbk.domain.bankaccount.BankAccount;
import java.util.Date;

/**
 *
 * @author Zhou
 */
public class Check {
    private Long id;
    private String checkNumber;
    private String RoutingNumber;
    private BankAccount account;
    private Customer customer;
    private Date date;
    private String payTo;
    private double amount;
    private CheckStatus status;
    
    // Constructor
    public Check(String checkNumber, String RoutingNumber, BankAccount account, Customer customer, Date date, String payTo, double amount, CheckStatus status) {
        this.checkNumber = checkNumber;
        this.RoutingNumber = RoutingNumber;
        this.account = account;
        this.customer = customer;
        this.date = date;
        this.payTo = payTo;
        this.amount = amount;
        this.status = status;
    }
    
    // Getter
    public Long getId() {
        return this.id;
    }

    public String getCheckNumber() {
        return this.checkNumber;
    }

    public String getRoutingNumber() {
        return this.RoutingNumber;
    }

    public BankAccount getAccount() {
        return this.account;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Date getDate() {
        return this.date;
    }

    public String getPayTo() {
        return this.payTo;
    }

    public double getAmount() {
        return this.amount;
    }

    public CheckStatus getStatus() {
        return this.status;
    }
    
    // Setter
    public void setStatus(CheckStatus status) {
        this.status = status;
    }
    
    // Method
    public boolean stopPayment() {
        if (this.status != CheckStatus.PENDING) {
            return false;
        }
        this.status = CheckStatus.STOPPED;
        return true;
    }
    
    // Needed by Hibernate
    private Check() {
    }
    
    private void setId(Long id) {
        this.id = id;
    }

    private void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    private void setRoutingNumber(String RoutingNumber) {
        this.RoutingNumber = RoutingNumber;
    }

    private void setAccount(BankAccount account) {
        this.account = account;
    }

    private void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    private void setDate(Date date) {
        this.date = date;
    }

    private void setPayTo(String payTo) {
        this.payTo = payTo;
    }

    private void setAmount(double amount) {
        this.amount = amount;
    }
}
