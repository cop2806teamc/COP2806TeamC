/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.spcollege.tbk.domain.check;

import edu.spcollege.tbk.domain.Customer;
import edu.spcollege.tbk.domain.bankaccount.BankAccount;

/**
 *
 * @author Zhou
 */
public class CheckOrder implements java.io.Serializable {
    private Long id;
    private Customer customer;
    private BankAccount account;
    
    public CheckOrder(Customer customer, BankAccount account) {
        this.customer = customer;
        this.account = account;
    }
    
    // Getter
    public Long getId() {
        return this.id;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public BankAccount getAccount() {
        return this.account;
    }
    
    // Needed by Hibernate
    private CheckOrder() {
    }
    
    private void setId(Long id) {
        this.id = id;
    }

    private void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private void setAccount(BankAccount account) {
        this.account = account;
    }
}
