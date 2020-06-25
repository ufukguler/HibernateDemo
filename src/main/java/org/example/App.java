package org.example;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello Hibernate!" );

        Configuration cfg = new Configuration().configure().addAnnotatedClass(Phone.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();

        session.beginTransaction();

        SQLQuery sqlQuery = session.createSQLQuery("select * from phone where price>80");
        sqlQuery.addEntity(Phone.class);
        List query = sqlQuery.list();

        for(Object o : query) System.out.println(o);

        // Native Query
        SQLQuery sqlQuery1 = session.createSQLQuery("select brand,price from phone where price>80");
        sqlQuery1.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List query1 = sqlQuery1.list();

        for(Object o : query1){
            Map m = (Map) o;
            System.out.println(m.get("brand")+", price:"+m.get("price"));
        }

        session.getTransaction().commit();

        /*
        Configuration cfg = new Configuration().configure().addAnnotatedClass(Phone.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();

        session.beginTransaction();

        Random rand = new Random();
        for (int i=1 ;i<50; i++){
            Phone s = new Phone();
            s.setPrice(rand.nextInt(100));
            s.setBrand("brand-"+i);
            session.save(s);
        }
        session.getTransaction().commit();

        // get all rows as a list
        Query q = session.createQuery("from Phone"); //// write classes name for the query not table name
        List<Phone> phoneList= q.list();
        for(Phone p: phoneList) System.out.println(p);

        // get a spesific row
        q = session.createQuery("from Phone where id=1"); // id's name comes from class variables not the column at DB
        Object phone = (Phone) q.uniqueResult();
        System.out.println(phone);
        */

        /*  // caching
        Alien alien;

        Configuration con = new Configuration().configure().addAnnotatedClass(Alien.class);
        ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();

        alien = (Alien) session.get(Alien.class,1);
        System.out.println(alien);

        session.getTransaction().commit();
        session.close();

        Session session1 = sf.openSession();
        session1.beginTransaction();

        Alien alien1 = (Alien) session1.get(Alien.class,2);
        System.out.println(alien1);

        session1.getTransaction().commit();
        session1.close();

        // caching level 2 w/ query
        Query query = session.createQuery("from alien where aid=1");
        query.setCacheable(true);
        Alien alien2 = (Alien) query.uniqueResult();
        System.out.println(alien2);

        Query query1 = session1.createQuery("from alien where aid=1");
        query1.setCacheable(true);
        Alien alien3 = (Alien) query1.uniqueResult();
        System.out.println(alien3);
        */

        /*
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
        Student s1 = (Student) ses.get(Student.class,1);
        System.out.println(s1);
        */

        /*
        not usable anymore cuz of the AlienName class
        Alien al; // doesn't need to create a new object
        al = (Alien) session.get(Alien.class,1); //fetch alien from DB where id=1
        System.out.println(al);
        */

    }
}
