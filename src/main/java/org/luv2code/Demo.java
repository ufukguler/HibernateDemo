package org.luv2code;
/*
  User: Ufuk
  Date: 15.09.2020 22:43
*/

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class Demo {
    public static void main(String[] args) {
    }

    static boolean deleteStudent(int id){
        Session session = createSession();
        session.beginTransaction();
        session.delete(findStudent(id));
        session.getTransaction().commit();
        if(findStudent(id) == null){
            return true;
        }
        return false;
    }

    static Student updateStudent(int id, String firstname, String lastname, String email) {
        Session session = createSession();
        Student student = findStudent(id);
        System.out.println("Before: "+findStudent(1));

        session.beginTransaction();

        student.setEmail(email);
        student.setFirstName(firstname);
        student.setLastName(lastname);

        session.update(student);
        session.getTransaction().commit();
        System.out.println("After: "+findStudent(1));
        return new Student();
    }

    static List<Student> findByLastName(String lastname) {
        Session session = createSession();
        session.beginTransaction();
        List<Student> students = session.createQuery("from Student s where s.lastName LIKE '%" + lastname + "%'").list();
        session.getTransaction().commit();
        return students;
    }

    static Student findStudent(int id) {
        Session session = createSession();
        Student student = new Student();
        try {
            session.beginTransaction();
            student = (Student) session.get(Student.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        }

        return student;
    }

    static List<Student> findAll() {
        Session session = createSession();
        session.beginTransaction();
        List<Student> theStudents = session.createQuery("from Student").list();
        session.getTransaction().commit();
        return theStudents;
    }

    static Session createSession() {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        return factory.getCurrentSession();
    }

    static void createAStudent(String firstname, String lastname, String email) {
        Session session = createSession();
        try {
            // use session to sace java object
            Student student = new Student(firstname, lastname, email);
            // start a transaction
            session.beginTransaction();
            // save java object to db
            session.save(student);
            session.getTransaction().commit();
            ;
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
