package org.example;

import org.example.entities.Cars;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration config = new org.hibernate.cfg.Configuration();
        config.addAnnotatedClass(org.example.entities.Cars.class);
        config.configure();
        // local SessionFactory bean created
        SessionFactory sessionFactory = config.buildSessionFactory();

        try (sessionFactory; Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Cars car = new Cars();
            car.setId(2);
            car.setName("Audi Q5");
            car.setPrice(5000000);
            session.persist(car); //Add the car into the context

            session.getTransaction();

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
