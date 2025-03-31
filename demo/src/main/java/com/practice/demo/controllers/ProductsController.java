package com.practice.demo.controllers;

import com.practice.demo.models.Products;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/products")
public class ProductsController {

    // Створення продукту
    @PostMapping
    public Integer createProduct(@RequestBody Products product) {
        return product.addProduct(product.getProduct_name(), product.getProduct_code(), product.getCost_price(),
                product.getSelling_price(), product.getDate_made(), product.getFactories_id());
    }

    // Отримання всіх продуктів
    @GetMapping
    public String getProductsView(Model model) {
        model.addAttribute("products", new Products());
        return "forms/Products/products";
    }

    //        Products product = new Products();
//        product.ListProducts();
//        product.addAttribute("record", new MyEntity()); // Передаємо порожній об'єкт

    @GetMapping("/{id}")
    public void getAllProductsByFactoryId(@PathVariable Integer id) {
        Products product = new Products();
        product.ListProductsByFactoryId(id);
    }

    @GetMapping("/getAllProductsByFactoryId/{id}")
    public void ListProductsAVGCostAndSellinByFactoryId(@PathVariable Integer id) {
        Products product = new Products();
        product.ListProductsAVGCostAndSellinByFactoryId(id);
    }

//    ListProductsAVGCostAndSellinByFactoryId

    // Оновлення продукту за ID
    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Integer id, @RequestBody Products updatedProduct) {
        updatedProduct.updateProductName(id, updatedProduct.getProduct_name());
        updatedProduct.updateProductCode(id, updatedProduct.getProduct_code());
        updatedProduct.updateCostPrice(id, updatedProduct.getCost_price());
        updatedProduct.updateSellingPrice(id, updatedProduct.getSelling_price());
        updatedProduct.updateDateMade(id, updatedProduct.getDate_made());
        updatedProduct.updateFactoriesId(id, updatedProduct.getFactories_id());
    }

    // Видалення продукту за ID
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        Products product = new Products();
        product.deleteProduct(id);
    }
}

