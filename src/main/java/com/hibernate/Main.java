package com.hibernate;

import com.hibernate.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by vedmant on 12/3/16.
 */
public class Main {

    public static void main(String[] args) {
        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        registryBuilder.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = registryBuilder.build();

        MetadataSources metadataSource = new MetadataSources(serviceRegistry);
        Metadata metadata = metadataSource.buildMetadata();
        SessionFactory sessionFactory = metadata.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        session.save(employee);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

}
