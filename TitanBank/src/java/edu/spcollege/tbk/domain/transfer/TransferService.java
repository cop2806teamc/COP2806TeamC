/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.spcollege.tbk.domain.transfer;
import edu.spcollege.tbk.domain.Customer;
import edu.spcollege.tbk.domain.bankaccount.InsufficientFundsException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zhou
 */
public class TransferService {
    
    private TransferRepository transferRepo;
//    private List<TransferRequest> transactions;
    
    // Constructor
    public TransferService() {
        this.transferRepo = new TransferRepository();
//        this.transactions = new TransferRepository().findAll();
    }
    
    public void transfer(TransferRequest transferRequest) {
        if (!transferRequest.isReadyToTransfer()) {
            this.transferRepo.save(transferRequest);
//            this.transactions.add(transferRequest);
            return;
        }

        try {
            transferRequest.getFromAccount().withdraw(transferRequest.getAmount());
            transferRequest.getToAccount().deposit(transferRequest.getAmount());
            transferRequest.completeTransaction();
            this.transferRepo.save(transferRequest);
//            this.transactions.add(transferRequest);
        } catch (InsufficientFundsException ex) {
            Logger.getLogger(TransferService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<TransferRequest> getTransactions(){
        return this.transferRepo.findAll();
    }

    public List<TransferRequest> getTransactionsByCustomer(Customer customer){
        return this.transferRepo.findByCustomer(customer);
    }
    
    
    
    /*
    public List<TransferRequest> getTransactions(TransferStatus status){
        ArrayList<TransferRequest> results = new ArrayList<>();
        for (TransferRequest request: transactions){
            if (request.getTransferStatus() == status)
                results.add(request);
        }
        return Collections.unmodifiableList(results);
    }
    */
    
    
}
