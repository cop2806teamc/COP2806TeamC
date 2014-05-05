/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.spcollege.tbk.domain.transfer;

import edu.spcollege.tbk.domain.Customer;
import java.util.List;

/**
 *
 * @author Zhou
 */
public interface ITransferRepository {
    void save(TransferRequest transferRequest);
    List<TransferRequest> findAll();
    List<TransferRequest> findByCustomer(Customer customer);
//    List<TransferRequest> findByCustomerAndStatus(Customer cusotomer, TransferStatus status);
}
