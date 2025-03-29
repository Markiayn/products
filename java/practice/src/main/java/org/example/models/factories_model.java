package org.example.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "factories")
public class factories_model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "produced", nullable = false)
    private int produced;

    @Column(name = "sold", nullable = false)
    private int sold;

    @Column(name = "date_made", nullable = false)
    private LocalDate date_made;

    // Конструктори, гетери та сетери
    public factories_model() {}

    public factories_model(String name, int produced, int sold, LocalDate date_made) {
        this.name = name;
        this.produced = produced;
        this.sold = sold;
        this.date_made = date_made;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProduced() {
        return produced;
    }

    public void setProduced(int produced) {
        this.produced = produced;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public LocalDate getDate_made() {
        return date_made;
    }

    public void setDate_made(LocalDate date_made) {
        this.date_made = date_made;
    }


    /* Method to CREATE a factories in the database */
    public Integer addFactory(String name, Integer produced, Integer sold, LocalDate date_made) {
        SessionFactory factory;

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        Transaction tx = null;
        Integer factories_modelID = null;

        try {
            tx = session.beginTransaction();
            factories_model factories_model = new factories_model(name, produced, sold, date_made);
            factories_modelID = (Integer) session.save(factories_model);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return factories_modelID;
    }



    /* Method to  READ all the factories */
    public void ListFactories () {
         SessionFactory factory;

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List<factories_model> factoriesList = session.createQuery("FROM factories_model", factories_model.class).list();
            for (Iterator iterator = factoriesList.iterator(); iterator.hasNext();){
                factories_model employee = (factories_model) iterator.next();
                System.out.print("| " + employee.getId());
                System.out.print(" | Factory name: " + employee.getName());
                System.out.print(" | Produced: " + employee.getProduced());
                System.out.print(" | Sold: " + employee.getSold());
                System.out.println(" | Date made: " + employee.getDate_made());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE a factories from the records */
    public void deleteFactory(Integer id){
        SessionFactory factory;

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            factories_model factories_model = (factories_model)session.get(factories_model.class, id);
            session.delete(factories_model);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to UPDATE "name" for a factory */
    public void updateFactoryName(Integer id, String name ){
        SessionFactory factory;

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            factories_model factories_model = (factories_model)session.get(factories_model.class, id);
            factories_model.setName( name );
            session.update(factories_model);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to UPDATE "produced" for a factory */
    public void updateFactoryProduced(Integer id, Integer produced ){
        SessionFactory factory;

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            factories_model factories_model = (factories_model)session.get(factories_model.class, id);
            factories_model.setProduced( produced );
            session.update(factories_model);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to UPDATE "produced" for a factory */
    public void updateFactorySold(Integer id, Integer sold ){
        SessionFactory factory;

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            factories_model factories_model = (factories_model)session.get(factories_model.class, id);
            factories_model.setSold( sold );
            session.update(factories_model);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to UPDATE "date_made" for a factory */
    public void updateFactoryDateMade(Integer id, LocalDate date ){
        SessionFactory factory;

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            factories_model factories_model = (factories_model)session.get(factories_model.class, id);
            factories_model.setDate_made( date );
            session.update(factories_model);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}

