package org.telusko;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private int rollno;
    private String name;
    private int mark;
    /*
    @OneToOne
    private Laptop laptop;

    @OneToMany(mappedBy = "student")
    private List<Laptop> laptops = new ArrayList<>();
*/
    // default is LAZY; lazy dosy not load relationships until invoke it with  a getter
    // EAGER loads relationships, takes more memory consumption
    @ManyToMany(mappedBy = "student",fetch = FetchType.EAGER)
    private List<Laptop> laptops = new ArrayList<>();
    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public List<Laptop> getLaptops() {
        return laptops;
    }

    public void setLaptops(List<Laptop> laptops) {
        this.laptops = laptops;
    }

    /*
    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollno=" + rollno +
                ", name='" + name + '\'' +
                ", mark=" + mark +
                ", laptop=" + laptop +
                '}';
    }
    */
}

