package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello Hibernate!" );

        AlienName alienName = new AlienName();
        alienName.setFname("ufuk");
        alienName.setLname("guler");
        alienName.setMname("");

        Alien alien = new Alien();
        alien.setAname(alienName);
        alien.setColor("green");

        Configuration confAlien = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Alien.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(confAlien.getProperties()).buildServiceRegistry();
        // sf is an interface so you cannot create an object from that
        SessionFactory sf = confAlien.buildSessionFactory();
        // session is also an interface
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction(); // start transaction
        session.save(alien); //insert
        tx.commit();

        /*

         */
        Laptop laptop = new Laptop();
        laptop.setLname("asus");


        Student ss = new Student();
        ss.setName("ufuk");
        ss.setMark(90);
        ss.getLaptops().add(laptop);

        //set student for laptop  @manytoone
        //laptop.setStudent(ss);

        //many to many student
        laptop.getStudent().add(ss);

        Configuration confs = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class);
        ServiceRegistry regs = new ServiceRegistryBuilder().applySettings(confs.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = confs.buildSessionFactory();
        Session ses = sessionFactory.openSession();

        Transaction transaction = ses.beginTransaction(); // start transaction
        ses.save(laptop);
        ses.save(ss);
        transaction.commit();

/*
not usable anymore cuz of the AlienName class
        Alien al; // doesn't need to create a new object
        al = (Alien) session.get(Alien.class,1); //fetch alien from DB where id=1
        System.out.println(al);
*/

    }
}
