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
@Table(name = "products")
public class products_model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_name", nullable = false)
    private String product_name;

    @Column(name = "product_code", nullable = false)
    private int product_code;

    @Column(name = "cost_price", nullable = false)
    private float cost_price;

    @Column(name = "selling_price", nullable = false)
    private float selling_price;

    @Column(name = "date_made", nullable = false)
    private LocalDate date_made;

    @Column(name = "factories_id", nullable = false)
    private int factories_id;


    // Конструктори, гетери та сетери
    public products_model() {
    }

    public products_model(String product_name,int product_code, float cost_price, float selling_price, LocalDate date_made, int factories_id) {
        this.product_name = product_name;
        this.product_code = product_code;
        this.cost_price = cost_price;
        this.selling_price = selling_price;
        this.date_made = date_made;
        this.factories_id = factories_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setCost_price(float cost_price) {
        this.cost_price = cost_price;
    }

    public float getCost_price() {
        return cost_price;
    }

    public void setSelling_price(float selling_price) {
        this.selling_price = selling_price;
    }

    public float getSelling_price() {
        return selling_price;
    }

    public LocalDate getDate_made() {
        return date_made;
    }

    public void setDate_made(LocalDate date_made) {
        this.date_made = date_made;
    }

    public int getFactories_id() {
        return factories_id;
    }

    public void setFactories_id(int factories_id) {
        this.factories_id = factories_id;
    }


    /* Method to CREATE a factories in the database */
    public Integer addProduct(String product_name,int product_code, float cost_price, float selling_price, LocalDate date_made, int factories_id) {
        SessionFactory factory;

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        Transaction tx = null;
        Integer product_modelID = null;

        try {
            tx = session.beginTransaction();
            products_model products_model = new products_model(product_name, product_code, cost_price, selling_price, date_made, factories_id);
            product_modelID = (Integer) session.save(products_model);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return product_modelID;
    }
}


    /* Method to  READ all the factories */
    public void listProducts () {
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
            List<products_model> productsList = session.createQuery("FROM products_model", products_model.class).list();
            for (Iterator iterator = productsList.iterator(); iterator.hasNext();){
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
//
//    /* Method to DELETE a factories from the records */
//    public void deleteFactory(Integer id){
//        SessionFactory factory;
//
//        try {
//            factory = new Configuration().configure().buildSessionFactory();
//        } catch (Throwable ex) {
//            System.err.println("Failed to create sessionFactory object." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//
//        Session session = factory.openSession();
//        Transaction tx = null;
//
//        try {
//            tx = session.beginTransaction();
//            factories_model factories_model = (factories_model)session.get(factories_model.class, id);
//            session.delete(factories_model);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx!=null) tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }
//
//    /* Method to UPDATE "name" for a factory */
//    public void updateFactoryName(Integer id, String name ){
//        SessionFactory factory;
//
//        try {
//            factory = new Configuration().configure().buildSessionFactory();
//        } catch (Throwable ex) {
//            System.err.println("Failed to create sessionFactory object." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//
//        Session session = factory.openSession();
//        Transaction tx = null;
//
//        try {
//            tx = session.beginTransaction();
//            factories_model factories_model = (factories_model)session.get(factories_model.class, id);
//            factories_model.setName( name );
//            session.update(factories_model);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx!=null) tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }
//
//    /* Method to UPDATE "produced" for a factory */
//    public void updateFactoryProduced(Integer id, Integer produced ){
//        SessionFactory factory;
//
//        try {
//            factory = new Configuration().configure().buildSessionFactory();
//        } catch (Throwable ex) {
//            System.err.println("Failed to create sessionFactory object." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//
//        Session session = factory.openSession();
//        Transaction tx = null;
//
//        try {
//            tx = session.beginTransaction();
//            factories_model factories_model = (factories_model)session.get(factories_model.class, id);
//            factories_model.setProduced( produced );
//            session.update(factories_model);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx!=null) tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }
//
//    /* Method to UPDATE "produced" for a factory */
//    public void updateFactorySold(Integer id, Integer sold ){
//        SessionFactory factory;
//
//        try {
//            factory = new Configuration().configure().buildSessionFactory();
//        } catch (Throwable ex) {
//            System.err.println("Failed to create sessionFactory object." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//
//        Session session = factory.openSession();
//        Transaction tx = null;
//
//        try {
//            tx = session.beginTransaction();
//            factories_model factories_model = (factories_model)session.get(factories_model.class, id);
//            factories_model.setSold( sold );
//            session.update(factories_model);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx!=null) tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }
//
//    /* Method to UPDATE "date_made" for a factory */
//    public void updateFactoryDateMade(Integer id, LocalDate date ){
//        SessionFactory factory;
//
//        try {
//            factory = new Configuration().configure().buildSessionFactory();
//        } catch (Throwable ex) {
//            System.err.println("Failed to create sessionFactory object." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//
//        Session session = factory.openSession();
//        Transaction tx = null;
//
//        try {
//            tx = session.beginTransaction();
//            factories_model factories_model = (factories_model)session.get(factories_model.class, id);
//            factories_model.setDate_made( date );
//            session.update(factories_model);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx!=null) tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }
//}
