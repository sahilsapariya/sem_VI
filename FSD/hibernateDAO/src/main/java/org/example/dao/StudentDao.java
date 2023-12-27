package org.example.dao;
import org.example.entities.Student;
import java.util.List;


public interface StudentDao {
    Student getStudentById(int id);
    boolean saveStudent(Student s);
    List<Student> getAllStudents();
    boolean updateStudent(Student s);
    boolean deleteStudent(int id);
}
