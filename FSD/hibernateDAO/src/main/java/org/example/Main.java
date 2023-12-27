package org.example;

import org.example.dao.StudentDao;
import org.example.dao.StudentDaoImp;
import org.example.entities.Student;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(org.example.entities.Student.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        StudentDao studentDao = new StudentDaoImp(sessionFactory);

        Student s1 = new Student();
        s1.setStudentName("sahil");
        s1.setSemester(6);

        Student s2 = new Student();
        s2.setStudentName("pranjal");
        s2.setSemester(6);

        System.out.println("Student 1 saving : " + studentDao.saveStudent(s1));
        System.out.println("Student 2 saving : " + studentDao.saveStudent(s2));

        // same way we can implement other operations
        // updateStudent(Student s), deleteStudent(int id), getAllStudent() and getStudentById(int id)
    }
}
