package com.practice.demo.models;

import jakarta.persistence.*;

import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "products")
public class Products {
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
    public Products() {
    }

    public Products(String product_name, int product_code, float cost_price, float selling_price, LocalDate date_made, int factories_id) {
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

    public int getProduct_code() {
        return product_code;
    }

    public void setProduct_code(int product_code) {
        this.product_code = product_code;
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


    /* Method to CREATE a product in the database */
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
            Products Products = new Products(product_name, product_code, cost_price, selling_price, date_made, factories_id);
            product_modelID = (Integer) session.save(Products);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return product_modelID;
    }

        /* Method to  READ all the products */
    public void ListProducts () {
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
            List<Products> productsList = session.createQuery("FROM Products", Products.class).list();
            for (Iterator iterator = productsList.iterator(); iterator.hasNext();){
                Products products = (Products) iterator.next();
                System.out.print("| " + products.getId());
                System.out.print(" | Product name: " + products.getProduct_name());
                System.out.print(" | Product code: " + products.getProduct_code());
                System.out.print(" | Cost: " + products.getCost_price());
                System.out.print(" | Selling price: " + products.getSelling_price());
                System.out.print(" | Factories ID: " + products.getFactories_id());
                System.out.println(" | Date made: " + products.getDate_made());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Products> ListProductsList () {
        SessionFactory factory;

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        Transaction tx = null;
        List<Products> productsList = null;

        try {
            tx = session.beginTransaction();
            productsList = session.createQuery("FROM Products", Products.class).list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return productsList;
    }

    public Products getFirstProduct() {
        SessionFactory factory;

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        Transaction tx = null;
        Products firstProduct = null;

        try {
            tx = session.beginTransaction();
            firstProduct = session.createQuery("FROM Products", Products.class)
                    .setMaxResults(1) // Отримуємо тільки перший запис
                    .uniqueResult();  // Повертає один об'єкт замість списку
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return firstProduct;
    }

    public Products getProductById(Integer id){
        SessionFactory factory;
        Products ProductById = null;
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
            ProductById  = (Products)session.get(Products.class, id);
            System.out.println(ProductById.toString());
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return ProductById;
    };

    public List<Products> getProductByFactoryId(Integer factories_id){
        SessionFactory factory;
        List<Products> ProductByFactoryId = null;
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
            return session.createQuery("FROM Products WHERE factories_id = :factoryId", Products.class)
                    .setParameter("factoryId", factories_id)
                    .list();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return ProductByFactoryId;
    };




    /* Method to DELETE a factories from the records */
    public void deleteProduct(Integer id){
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
            Products Products = (Products)session.get(Products.class, id);
            session.delete(Products);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }



    /* Method to UPDATE product name */
    public void updateProductName(Integer id, String productName) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Products product = session.get(Products.class, id);
            product.setProduct_name(productName);
            session.update(product);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to UPDATE product code */
    public void updateProductCode(Integer id, int productCode) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Products product = session.get(Products.class, id);
            product.setProduct_code(productCode);
            session.update(product);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to UPDATE cost price */
    public void updateCostPrice(Integer id, float costPrice) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Products product = session.get(Products.class, id);
            product.setCost_price(costPrice);
            session.update(product);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to UPDATE selling price */
    public void updateSellingPrice(Integer id, float sellingPrice) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Products product = session.get(Products.class, id);
            product.setSelling_price(sellingPrice);
            session.update(product);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to UPDATE date made */
    public void updateDateMade(Integer id, LocalDate dateMade) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Products product = session.get(Products.class, id);
            product.setDate_made(dateMade);
            session.update(product);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to UPDATE factories ID */
    public void updateFactoriesId(Integer id, int factoriesId) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Products product = session.get(Products.class, id);
            product.setFactories_id(factoriesId);
            session.update(product);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void ListProductsByFactoryId (Integer id) {
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
            List<Products> productsList = session.createQuery("FROM Products", Products.class).list();
            for (Iterator iterator = productsList.iterator(); iterator.hasNext();){
                Products products = (Products) iterator.next();
                if (products.getFactories_id() == id){
                    System.out.print("| " + products.getId());
                    System.out.print(" | Product name: " + products.getProduct_name());
                    System.out.print(" | Product code: " + products.getProduct_code());
                    System.out.print(" | Cost: " + products.getCost_price());
                    System.out.print(" | Selling price: " + products.getSelling_price());
                    System.out.print(" | Factories ID: " + products.getFactories_id());
                    System.out.println(" | Date made: " + products.getDate_made());
                }

            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void ListProductsAVGCostAndSellinByFactoryId (Integer id) {
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
            int count = 0;
            double totalCost = 0;
            double totalSellingPrice = 0;
            List<Products> productsList = session.createQuery("FROM Products", Products.class).list();
            for (Iterator iterator = productsList.iterator(); iterator.hasNext();){
                Products products = (Products) iterator.next();
                if (products.getFactories_id() == id){
                    count ++;
                    totalCost += products.getCost_price();
                    totalSellingPrice += products.getSelling_price();
                    System.out.print("| " + products.getId());
                    System.out.print(" | Product name: " + products.getProduct_name());
                    System.out.println(" | Factories ID: " + products.getFactories_id());
                }
            }
            double AVG_cost = totalCost / count;
            double AVG_selling_price = totalSellingPrice / count;
            System.out.print("Total cost: " + totalCost + " |");
            System.out.println(" Total selling price: " + totalSellingPrice);
            System.out.print("AVG cost: " + AVG_cost + " |");
            System.out.println(" AVG selling price: " + AVG_selling_price);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
