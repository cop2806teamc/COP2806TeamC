/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.spcollege.tbk.domain.transfer;

import edu.spcollege.tbk.domain.Customer;
import java.util.ArrayList;
import java.util.Collections;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;
import org.hibernate.Transaction;

/**
 *
 * @author Zhou
 */
public class TransferRepository implements ITransferRepository{
    
    private Configuration configuration;
    private ServiceRegistry serviceRegistry;
    private SessionFactory sessionFactory;
    
    public TransferRepository() {
        this.configuration = new Configuration().configure();
        this.serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }
    
    @Override
    public List<TransferRequest> findAll() {
        Session session = this.sessionFactory.openSession();
        
        List<TransferRequest> results = null;
        
        try {
            Query query = session.createQuery("from TransferRequest tr join fetch tr.fromAccount join fetch tr.toAccount order by tr.id desc");
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
    public List<TransferRequest> findByCustomer(Customer customer) {
        
        List<TransferRequest> allRequests = this.findAll();
        List<TransferRequest> results = new ArrayList<>();
        
        for (TransferRequest tr : allRequests) {
            if (tr.getFromAccount().isCustomer(customer) ||
                    tr.getToAccount().isCustomer(customer))
            {
                results.add(tr);
            }
        }
        return Collections.unmodifiableList(results);
    }
    
    @Override
    public void save (TransferRequest transferRequest) {
        final Session session = this.sessionFactory.openSession();
        final Transaction tx = session.beginTransaction();
        
        try {
            session.saveOrUpdate(transferRequest);
            tx.commit();
        } finally {
            session.close();
        }
    }
}
