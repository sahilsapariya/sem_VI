package org.example.entities;
import jakarta.persistence.*;

@Entity
public class Student {
    @Id @GeneratedValue
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Column(name = "name")
    private String studentName;

    @Column(name = "semester")
    private int semester;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", semester=" + semester +
                '}';
    }
}
