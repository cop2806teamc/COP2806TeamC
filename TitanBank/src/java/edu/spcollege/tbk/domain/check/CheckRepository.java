/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.spcollege.tbk.domain.check;

import edu.spcollege.tbk.domain.Customer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author Zhou
 */
public class CheckRepository implements ICheckRepository{
    private Configuration configuration;
    private ServiceRegistry serviceRegistry;
    private SessionFactory sessionFactory;
    
    public CheckRepository() {
        this.configuration = new Configuration().configure();
        this.serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }
    
    @Override
    public List<Check> findAll() {
        Session session = this.sessionFactory.openSession();
        
        List<Check> results = null;
        
        try {
            Query query = session.createQuery("from Check ck join fetch ck.account");
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
    public Check findById(Long id) {
        Session session = this.sessionFactory.openSession();
        
        Check check = null;
        
        try {
            Query query = session.createQuery("from Check ck join fetch ck.account where ck.id = ?");
            query.setString(0, id.toString());
            check = (Check) query.uniqueResult();
        } finally {
            session.close();
        }
        
        return check;
    }
    
    @Override
    public List<Check> findByCustomer(Customer customer) {
        
        List<Check> allChecks = this.findAll();
        List<Check> results = new ArrayList<>();
        
        for (Check ck : allChecks) {
            if (ck.getAccount().isCustomer(customer)) {
                results.add(ck);
            }
        }
        return Collections.unmodifiableList(results);
    }
    
    @Override
    public List<Check> findByNumber(Customer customer, String accountNumber, String checkNumber) {
        List<Check> allCustomerChecks = this.findByCustomer(customer);
        List<Check> results = new ArrayList<>();
        
        for (Check ck : allCustomerChecks) {
            if (accountNumber != null && accountNumber.length() > 0) {
                if (!ck.getAccount().getAccountNumber().equals(accountNumber)) {
                    continue;
                }
            }
            if (checkNumber != null && checkNumber.length() > 0) {
                if (!ck.getCheckNumber().equals(checkNumber)) {
                    continue;
                }
            }
            results.add(ck);
        }
        return Collections.unmodifiableList(results);
    }
    
    @Override
    public void save (Check check) {
        final Session session = this.sessionFactory.openSession();
        final Transaction tx = session.beginTransaction();
        
        try {
            session.saveOrUpdate(check);
            tx.commit();
        } finally {
            session.close();
        }
    }
}
