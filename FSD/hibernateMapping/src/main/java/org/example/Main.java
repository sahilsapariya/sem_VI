package org.example;

import org.example.entity.Certificate;
import org.example.entity.College;
import org.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(org.example.entity.Student.class);
        configuration.addAnnotatedClass(org.example.entity.College.class);
        configuration.addAnnotatedClass(org.example.entity.Certificate.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();

            // Many To Many
            Student sahil = new Student();
            sahil.setStudentName("Sahil");

            Student prit = new Student();
            prit.setStudentName("Prit");

            Student pranjal = new Student();
            pranjal.setStudentName("Pranjal");

            Student nisarg = new Student();
            nisarg.setStudentName("Nisarg");

            // create 2 colleges
            College ddu = new College();
            ddu.setCollegeName("DDU");

            College au = new College();
            au.setCollegeName("Ahmedabad university");

            // create certificates
            Certificate aws = new Certificate();
            Certificate google = new Certificate();
            Certificate microsoft = new Certificate();

            aws.setCertificateName("Amazon Web Service");
            google.setCertificateName("Google");
            microsoft.setCertificateName("Microsoft");

            Set<Certificate> sahilCertificates = new HashSet<>();
            Set<Certificate> pranjalCertificates = new HashSet<>();
            Set<Certificate> nisargCertificates = new HashSet<>();

            sahilCertificates.add(aws);
            sahilCertificates.add(google);
            sahilCertificates.add(microsoft);
            pranjalCertificates.add(microsoft);
            nisargCertificates.add(aws);

            sahil.setStudentCertificate(sahilCertificates);
            pranjal.setStudentCertificate(pranjalCertificates);
            nisarg.setStudentCertificate(nisargCertificates);

            // set colleges for students
            sahil.setStudentCollege(ddu);
            prit.setStudentCollege(au);
            pranjal.setStudentCollege(ddu);
            nisarg.setStudentCollege(au);

            // create list of students for storing them into college db
            List<Student> dduStudents = new ArrayList<>();
            dduStudents.add(sahil);
            dduStudents.add(pranjal);

            List<Student> auStudents = new ArrayList<>();
            auStudents.add(prit);
            auStudents.add(nisarg);

            ddu.setStudents(dduStudents);
            au.setStudents(auStudents);

            session.persist(ddu);
            session.persist(au);

            // Many to one bidirectional
//            Student sahil = new Student();
//            sahil.setStudentName("Sahil");
//
//            Student prit = new Student();
//            prit.setStudentName("Prit");
//
//            Student pranjal = new Student();
//            pranjal.setStudentName("Pranjal");
//
//            Student nisarg = new Student();
//            nisarg.setStudentName("Nisarg");
//
//            // create 2 colleges
//            College ddu = new College();
//            ddu.setCollegeName("DDU");
//
//            College au = new College();
//            au.setCollegeName("Ahmedabad university");
//
//            // set colleges for students
//            sahil.setStudentCollege(ddu);
//            prit.setStudentCollege(au);
//            pranjal.setStudentCollege(ddu);
//            nisarg.setStudentCollege(au);
//
//            // create list of students for storing them into college db
//            List<Student> dduStudents = new ArrayList<>();
//            dduStudents.add(sahil);
//            dduStudents.add(pranjal);
//
//            List<Student> auStudents = new ArrayList<>();
//            auStudents.add(prit);
//            auStudents.add(nisarg);
//
//            ddu.setStudents(dduStudents);
//            au.setStudents(auStudents);
//
//            session.persist(ddu);
//            session.persist(au);


            // Many to One
//            // create 3 students
//            Student sahil = new Student();
//            sahil.setStudentName("Sahil");
//
//            Student prit = new Student();
//            prit.setStudentName("Prit");
//
//            Student pranjal = new Student();
//            pranjal.setStudentName("Pranjal");
//
//            // create 2 colleges
//            College ddu = new College();
//            ddu.setCollegeName("DDU");
//
//            College au = new College();
//            au.setCollegeName("Ahmedabad university");
//
//            // set colleges for students
//            sahil.setStudentCollege(ddu);
//            prit.setStudentCollege(au);
//            pranjal.setStudentCollege(ddu);
//
//            // save the records
//            session.persist(sahil);
//            session.persist(pranjal);
//            session.persist(prit);

            session.getTransaction().commit();

        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
