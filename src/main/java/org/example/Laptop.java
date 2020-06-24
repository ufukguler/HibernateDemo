package org.example;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Laptop {

    @Id
    @GeneratedValue
    private int lid;
    private String lname;
    /*
    @ManyToOne
    private Student student;
    */
    @ManyToMany
    private List<Student> student = new ArrayList<>();

    public String getLname() {
        return lname;
    }
/*
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
*/

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

}
