package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello Hibernate!" );
        Alien ufuk = new Alien();
        ufuk.setAid(1);
        ufuk.setAname("alien ufuk");
        ufuk.setColor("green");

        Configuration conf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Alien.class);
        // sf is an interface so you cannot create an object from that
        SessionFactory sf = conf.buildSessionFactory();
        // session is also an interface
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction(); // start transaction
        session.save(ufuk); //insert
        tx.commit();
    }
}
