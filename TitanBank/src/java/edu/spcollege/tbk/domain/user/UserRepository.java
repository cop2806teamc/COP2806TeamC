/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.spcollege.tbk.domain.user;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author Zhou
 */
public class UserRepository implements IUserRepository {

    private Configuration configuration;
    private ServiceRegistry serviceRegistry;
    private SessionFactory sessionFactory;

    public UserRepository() {
        this.configuration = new Configuration().configure();
        this.serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public User findByUsername(String username) {

        Session session = sessionFactory.openSession();
        
        User user = null;
        try {
            // Using the session to retrieve objects
            //Customer customer = (Customer) session.get(Customer.class, 1);
            //User user = (User) session.get(User.class, 1);

            Query query = session.createQuery("from User user join fetch user.customer where user.username = ?");
            query.setString(0, username);
            user = (User) query.uniqueResult();

        } finally {
            session.close();
        }
        
        return user;
    }
}
