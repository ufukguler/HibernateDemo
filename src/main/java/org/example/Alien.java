package org.example;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;


@Entity(name="alien") // optionally I can give spesific name for table
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class Alien {                    //READ_ONLY if you don't update the DB
    @Id
    @GeneratedValue
    private int aid;

    private AlienName aname;
    private String color;

    @Transient // this column will not be created at DB
    private String planet;

    @Override
    public String toString() {
        return "Alien{" +
                "aid=" + aid +
                ", aname=" + aname +
                ", color='" + color + '\'' +
                ", planet='" + planet + '\'' +
                '}';
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public AlienName getAname() {
        return aname;
    }

    public void setAname(AlienName   aname) {
        this.aname = aname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
