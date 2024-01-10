package org.example;

import org.example.entities.Cars;
import org.example.entities.Engine;
import org.example.entities.Owner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(org.example.entities.Cars.class);
        configuration.addAnnotatedClass(org.example.entities.Engine.class);
        configuration.addAnnotatedClass(org.example.entities.Owner.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            Transaction transaction = session.beginTransaction();

            // created four cars
            Cars mercedies = new Cars();
            mercedies.setCarName("Benz");
            mercedies.setCarPrice(3000000);

            Cars audi = new Cars();
            audi.setCarName("Audi Q7");
            audi.setCarPrice(7500000);

            Cars lambo = new Cars();
            lambo.setCarName("Lamborghini Aventador");
            lambo.setCarPrice(55000000);

            Cars rr = new Cars();
            rr.setCarName("Rolls Royce Phantom");
            rr.setCarPrice(114000000);

            // create four engines
            Engine v8 = new Engine();
            v8.setEngineName("v8");

            Engine v7 = new Engine();
            v7.setEngineName("v7");

            Engine rre = new Engine();
            rre.setEngineName("6.75 L N74B68 twin-turbocharged V12 (petrol)");

            Engine lamboe = new Engine();
            lamboe.setEngineName("6.5 L L539 V12");

            // setting cars with engine
            mercedies.setEngineDetails(v8);
            audi.setEngineDetails(v7);
            rr.setEngineDetails(rre);
            lambo.setEngineDetails(lamboe);

            // Creating owners
            Owner sahil = new Owner("sahil");
            List<Cars> sahilsCars = new ArrayList<>();
            sahilsCars.add(rr);
            sahilsCars.add(lambo);

            sahil.setOwnedCars(sahilsCars);

            session.persist(sahil);

            transaction.commit();
        } finally {
            session.close();
            sessionFactory.close();
        }


    }
}
