package org.example.dao;

import org.example.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDaoImp implements StudentDao {
    private final SessionFactory sessionFactory;

    public StudentDaoImp(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Student getStudentById(int id) {
        try(Session session = sessionFactory.openSession()){
            return session.get(Student.class, id);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean saveStudent(Student student) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Student> getAllStudents() {
        try(Session session = sessionFactory.openSession()){
            Query<Student> query = session.createQuery("FROM Student", Student.class);
            return query.list();
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean updateStudent(Student s) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Student existingStudent = session.get(Student.class, s.getId());
            existingStudent.setStudentName(s.getStudentName());
            existingStudent.setSemester(s.getSemester());
            transaction.commit();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteStudent(int id) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);
            if(student != null) {
                session.remove(student);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e){
            return false;
        }
    }
}
