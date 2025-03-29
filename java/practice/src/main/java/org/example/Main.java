package org.example;

import org.example.models.factories_model;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        factories_model fc = new factories_model();


        fc.deleteFactory(11);
        fc.addFactory("Xiaomi", 500, 250, LocalDate.now());
        fc.updateFactoryName(9, "Intel");
        fc.updateFactoryProduced(9, 1200);
        fc.updateFactorySold(9, 1000);
        fc.updateFactoryDateMade(9, LocalDate.of(2022, 5, 3));
        fc.ListFactories();


    }
}
