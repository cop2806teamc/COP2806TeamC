/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.spcollege.tbk.domain.bankaccount;

import edu.spcollege.tbk.domain.Customer;
import edu.spcollege.tbk.domain.user.User;
import java.util.List;

/**
 *
 * @author Zhou
 */
public interface IBankAccountRepository {
    List<BankAccount> findByUser(User user);
    List<BankAccount> findByCustomer(Customer customer);
    BankAccount findByAccountNumber(String accountNumber);
    void save(BankAccount bankAccount);
}
