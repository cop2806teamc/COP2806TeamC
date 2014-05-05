/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.spcollege.tbk.domain.transfer;

import edu.spcollege.tbk.domain.bankaccount.BankAccount;
import java.text.DecimalFormat;
import java.util.Date;

/**
 *
 * @author Zhou
 */
public class TransferRequest implements java.io.Serializable {
    
    private Long id;
    private BankAccount fromAccount;
    private BankAccount toAccount;
    private double amount;
    private Date activeDate;
    private ScheduleStatus scheduleStatus;
    private TransferStatus transferStatus;
    
    // Constructor
    public TransferRequest(BankAccount fromAccount, BankAccount toAccount, double amount, Date activeDate) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.activeDate = activeDate;
        
        this.transferStatus = TransferStatus.PENDING;
        Date today = new Date();
        if (this.activeDate.getYear() == today.getYear() &&
            this.activeDate.getMonth() == today.getMonth() && 
            this.activeDate.getDate() == today.getDate())
        {
            this.scheduleStatus = ScheduleStatus.IMMEDIATE;
        }
        else {
            this.scheduleStatus = ScheduleStatus.FUTURE;
        }
    }
	
	public TransferRequest(BankAccount fromAccount, BankAccount toAccount, double amount, Date activeDate, ScheduleStatus scheduleStatus, TransferStatus transferStatus) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.activeDate = activeDate;
        this.scheduleStatus = scheduleStatus;
        this.transferStatus = transferStatus;
    }
    
    // Getter/Setter
    public Long getId() {
        return this.id;
    }
    
    public BankAccount getFromAccount() {
        return this.fromAccount;
    }

    public BankAccount getToAccount() {
        return this.toAccount;
    }

    public double getAmount() {
        return this.amount;
    }

    public Date getActiveDate() {
        return this.activeDate;
    }
	
    public TransferStatus getTransferStatus() {
        return this.transferStatus;
    }
    
    public ScheduleStatus getScheduleStatus() {
        return this.scheduleStatus;
    }


    // Method
    public String getAmountString() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return "$" + df.format(this.amount);
    }
    
    public boolean isReadyToTransfer() {
        if (this.scheduleStatus == ScheduleStatus.IMMEDIATE) {
            return true;
        }

        Date today = new Date();
        if (this.activeDate.getYear() == today.getYear() &&
            this.activeDate.getMonth() == today.getMonth() && 
            this.activeDate.getDate() == today.getDate())
        {
            this.scheduleStatus = ScheduleStatus.IMMEDIATE;
            return true;
        }

        return false;
    }
    
    public void completeTransaction() {
        this.transferStatus = TransferStatus.COMPLETED;
    }
	
	
    // Needed by Hibernate
    private TransferRequest() {
    }

    private void setId(Long id) {
        this.id = id;
    }

    private void setFromAccount(BankAccount fromAccount) {
        this.fromAccount = fromAccount;
    }

    private void setToAccount(BankAccount toAccount) {
        this.toAccount = toAccount;
    }

    private void setAmount(double amount) {
        this.amount = amount;
    }

    private void setActiveDate(Date activeDate) {
        this.activeDate = activeDate;
    }

    private void setScheduleStatus(ScheduleStatus scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    private void setTransferStatus(TransferStatus transferStatus) {
        this.transferStatus = transferStatus;
    }
}
