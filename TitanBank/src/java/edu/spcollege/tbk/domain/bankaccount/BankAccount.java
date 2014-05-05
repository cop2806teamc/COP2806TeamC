/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.spcollege.tbk.domain.bankaccount;

import edu.spcollege.tbk.domain.Customer;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;



/**
 *
 * @author Zhou
 */
public class BankAccount implements java.io.Serializable {

    private Long id;
    private String accountNumber;
    private String accountName;
    private double balance;
    //@Enumerated(EnumType.STRING)
    private AccountType accountType;
    private Set<Customer> customers;
//    private final Customer customer;
    
    // Constructor
    public BankAccount(String accountNumber, String accountName, double balance, AccountType accountType, Set<Customer> customers) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = balance;
        this.accountType = accountType;
        this.customers = customers;
    }
    
    public BankAccount(String accountNumber, String accountName, double balance, AccountType accountType, Customer customer) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = balance;
        this.accountType = accountType;
        this.customers = new HashSet();
        this.customers.add(customer);
    }
    
    // Getter
    public Long getId() {
        return this.id;
    }
    
    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getAccountName() {
        return this.accountName;
    }
    
    public double getBalance() {
        return this.balance;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }
    
    public Set<Customer> getCustomers() {
        return Collections.unmodifiableSet(this.customers);
    }

    //Setter
    public void setAccountName(String accoutName) {
        this.accountName = accoutName;
    }
    
//    @Override
//    public boolean equals(Object other) {
//        if (other == null) {
//            return false;
//        }
//        if (this.getClass() != other.getClass()) {
//                return false;
//        }
//        if (this.type != ((BankAccount)other).type) {
//            return false;
//        }
//        if (this.customerId != ((BankAccount)other).customerId) {
//            return false;
//        }
//        return this.accountNumber.equals(((BankAccount)other).accountNumber);
//    }

    // Method
    public Customer getCustomer(Customer customer) {
        for(Customer cus : this.customers) {
            if (cus.equals(customer)) {
                return cus;
            }
        }
        return null;
    }
    
    public boolean isCustomer(Customer customer) {
        Customer cus = this.getCustomer(customer);
        return (cus != null);
    }
    
    
    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }
    
    public void removeCustomer(Customer customer) {
        this.customers.remove(customer);
    }
    
    
    public String getBalanceString() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return "$" + df.format(this.balance);
    }

    public boolean hasSufficientFunds(double amount){
        return this.balance >= amount;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }
    
    public void withdraw(double amount) throws InsufficientFundsException {
        if (hasSufficientFunds(amount)) {
            this.balance -= amount; 
        } else {
            throw new InsufficientFundsException();
        }
    }
    


    // Needed by Hibernate
    private BankAccount() {
    }
    
    private void setId(Long id) {
        this.id = id;
    }
    
    private void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    private void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    private void setBalance(double balance) {
        this.balance = balance;
    }

    private void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}
