package org.example;

import org.example.models.Factories;
import org.example.models.Products;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Factories fc = new Factories();
        Products pc = new Products();


//        fc.deleteFactory(11);
//        fc.addFactory("Xiaomi", 500, 250, LocalDate.now());
//        fc.updateFactoryName(9, "Intel");
//        fc.updateFactoryProduced(9, 1200);
//        fc.updateFactorySold(9, 1000);
//        fc.updateFactoryDateMade(9, LocalDate.of(2022, 5, 3));
        fc.ListFactories();

////        pc.addProduct("ikea", 157489,100.2f , 150.4f, LocalDate.now(), 6);
////        pc.deleteProduct(8);
//        pc.updateProductName(7, "ikea");
//        pc.updateProductCode(7, 369852);
//        pc.updateCostPrice(7, 300);
//        pc.updateSellingPrice(7, 600);
//        pc.updateFactoriesId(7, 4);
//        pc.updateDateMade(7, LocalDate.of(2022, 6, 9));

        pc.ListProducts();


    }
}
