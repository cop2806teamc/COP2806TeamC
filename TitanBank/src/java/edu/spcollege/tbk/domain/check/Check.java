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
public class Check implements java.io.Serializable {
    private Long id;
    private String checkNumber;
    private BankAccount account;
    private String routingNumber;
    private Date date;
    private String payTo;
    private double amount;
    private CheckStatus status;
    
    // Constructor
    public Check(String checkNumber, BankAccount account, String routingNumber, Date date, String payTo, double amount, CheckStatus status) {
        this.checkNumber = checkNumber;
        this.account = account;
        this.routingNumber = routingNumber;
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
        return this.routingNumber;
    }

    public BankAccount getAccount() {
        return this.account;
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

    private void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }

    private void setAccount(BankAccount account) {
        this.account = account;
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
