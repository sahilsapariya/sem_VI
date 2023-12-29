package org.example.entity;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Student {
    @Id @GeneratedValue @Column(name = "STUDENT_ID")
    private int studentId;

    @Column(name = "STUDENT_NAME")
    private String studentName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COLLEGE_FK")
    private College studentCollege;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "JOIN_STUDENT_CERTIFICATES",
    joinColumns = {@JoinColumn(name = "STUDENT_ID_FK")},
    inverseJoinColumns = {@JoinColumn(name = "CERTIFICATE_ID_FK")})
    private Set<Certificate> studentCertificate;


    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }


    public College getStudentCollege() {
        return studentCollege;
    }

    public void setStudentCollege(College studentCollege) {
        this.studentCollege = studentCollege;
    }


    public Set<Certificate> getStudentCertificate() {
        return studentCertificate;
    }

    public void setStudentCertificate(Set<Certificate> studentCertificate) {
        this.studentCertificate = studentCertificate;
    }

}
