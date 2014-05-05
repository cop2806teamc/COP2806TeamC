/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.spcollege.tbk.domain.check;

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
public class CheckOrderRepository implements ICheckOrderRepository{
    private Configuration configuration;
    private ServiceRegistry serviceRegistry;
    private SessionFactory sessionFactory;
    
    public CheckOrderRepository() {
        this.configuration = new Configuration().configure();
        this.serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }
    
    @Override
    public void save (CheckOrder checkOrder) {
        final Session session = this.sessionFactory.openSession();
        final Transaction tx = session.beginTransaction();
        
        try {
            session.saveOrUpdate(checkOrder);
            tx.commit();
        } finally {
            session.close();
        }
    }
}
