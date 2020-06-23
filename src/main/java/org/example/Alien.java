package org.example;

import javax.persistence.*;


@Entity(name="alien") // optionally I can give spesific name for table
public class Alien {
    @Id
    @GeneratedValue
    @Column(name="id")
    private int aid;

    @Column(name="alian_name")
    private String aname;

    @Column(name="alian_color")
    private String color;

    @Transient // this column will not be created at DB
    private String planet;
    public Alien(){}

    public Alien(String aname, String color) {
        this.aname = aname;
        this.color = color;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
