package com.practice.demo.controllers;

import com.practice.demo.models.Factories;
import com.practice.demo.models.Products;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @PostMapping
    @ResponseBody
    public ResponseEntity<Integer> createProducts(@RequestBody Products products) {
        Integer productsId = products.addProduct(
                products.getProduct_name(),
                products.getProduct_code(),
                products.getCost_price(),
                products.getSelling_price(),
                products.getDate_made(),
                products.getFactories_id()
        );
        return ResponseEntity.ok(productsId);
    }

    // Отримання всіх продуктів
    @GetMapping("/forms")
    public String getProductsView(Model model) {
        model.addAttribute("products", new Products());
        return "forms/products";
    }

    @GetMapping("/index")
    public String getProductsIndexView(Model model) {
        Products products = new Products();
        List<Products> productsList = products.ListProductsList();
        model.addAttribute("products", productsList);
        return "index/products";
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

