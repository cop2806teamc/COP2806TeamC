/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.spcollege.tbk.domain.check;

import edu.spcollege.tbk.domain.Customer;
import java.util.List;

/**
 *
 * @author Zhou
 */
public interface ICheckRepository {
    void save (Check check);
    List<Check> findAll();
    List<Check> findByCustomer(Customer customer);
    List<Check> findByNumber(Customer customer, String accountNumber, String checkNumber);
    Check findById(Long id);
//    Check findByAccountNumber(String accountNumber);
}
