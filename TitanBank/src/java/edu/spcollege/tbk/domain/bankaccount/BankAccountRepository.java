/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.spcollege.tbk.domain.bankaccount;

import edu.spcollege.tbk.domain.Customer;
import edu.spcollege.tbk.domain.user.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.hibernate.Transaction;

/**
 *
 * @author Zhou
 */
public class BankAccountRepository implements IBankAccountRepository {
    
    private Configuration configuration;
    private ServiceRegistry serviceRegistry;
    private SessionFactory sessionFactory;
	
    public BankAccountRepository() {
        this.configuration = new Configuration().configure();
        this.serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }
    
    @Override
    public List<BankAccount> findByUser(User user) {
        return findByCustomer(user.getCustomer());
    }
    
    @Override
    public List<BankAccount> findByCustomer(Customer customer) {

        Session session = this.sessionFactory.openSession();
        
        List<BankAccount> results = null;
        
        try {
            Query query = session.createQuery("select distinct bk from BankAccount bk join fetch bk.customers cus where cus.id = ?");
            query.setString(0, customer.getId().toString());
            results = query.list();

        } finally {
            session.close();
        }
        
        if (results == null) {
            results = new ArrayList<>();
        }
		
        return Collections.unmodifiableList(results);
    }
    
    @Override
    public BankAccount findByAccountNumber(String accountNumber) {
	
        Session session = this.sessionFactory.openSession();
        
        BankAccount account = null;
        
        try {
            Query query = session.createQuery("select distinct bk from BankAccount bk join fetch bk.customers cus where bk.accountNumber = ?");
            query.setString(0, accountNumber);
            account = (BankAccount) query.uniqueResult();

        } finally {
            session.close();
        }
        
        return account;
    }

    @Override
    public void save (BankAccount account) {
        final Session session = this.sessionFactory.openSession();
        final Transaction tx = session.beginTransaction();
        
        try {
            session.saveOrUpdate(account);
            tx.commit();
        } finally {
            session.close();
        }
    }
}
