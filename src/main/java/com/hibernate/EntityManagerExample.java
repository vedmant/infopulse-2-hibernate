package com.hibernate;


import com.hibernate.entity.Employee;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by vedmant on 12/3/16.
 */
public class EntityManagerExample {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("initentitrymanager");
        SessionFactory sessionFactory = (SessionFactory) entityManagerFactory;

        EntityManager em = sessionFactory.createEntityManager();
        em.getTransaction().begin();

        insertEmployee(em);
        selectEmployees(em);

        em.getTransaction().commit();
        em.close();
        sessionFactory.close();
    }

    private static void insertEmployee(EntityManager em) {
        Employee employee = new Employee();
        employee.setFirstName("Richard");
        employee.setLastName("Hudgens");
        em.persist(employee);
    }

    private static void selectEmployees(EntityManager em) {
        List<Employee> employees = em
                .createQuery("from Employee where firstName = :name", Employee.class)
                .setParameter("name", "Doe")
                .getResultList();

        employees.forEach(employee -> {
            System.out.println(employee);
        });
    }
}
