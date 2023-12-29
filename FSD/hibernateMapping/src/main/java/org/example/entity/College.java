package org.example.entity;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class College {

    @Id @GeneratedValue
    @Column(name = "COLLEGE_ID")
    private long id;

    @Column(name = "COLLEGE_NAME")
    private String collegeName;

    @OneToMany(fetch = FetchType.EAGER,
    cascade = CascadeType.ALL,
    mappedBy = "studentCollege")
    private List<Student> students;
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }


    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }



}
