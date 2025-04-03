package com.practice.demo.controllers;

import com.practice.demo.models.Factories;
import com.practice.demo.models.Products;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/shows")
    public String getProductsShowsView(Model model) {
        Products products = new Products();
        Products firstProduct = products.getFirstProduct();
        model.addAttribute("product", firstProduct); // Передаємо продукт, а не рядок
        return "shows/products";
    }



//    @GetMapping("/{id}")
//    public void getAllProductsByFactoryId(@PathVariable Integer id) {
//        Products product = new Products();
//        product.ListProductsByFactoryId(id);
//    }

    @GetMapping("/getAllProductsByFactoryId/{id}")
    public void ListProductsAVGCostAndSellinByFactoryId(@PathVariable Integer id) {
        Products product = new Products();
        product.ListProductsAVGCostAndSellinByFactoryId(id);
    }

    @GetMapping("/{id}")
    public String getProductsShowsView(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Products products = new Products();
        Products product = products.getProductById(id);

        if (product == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Фабрика з ID " + id + " не знайдена.");
            return "redirect:/index"; // Перенаправлення на головну сторінку
        }

        model.addAttribute("product", product);
        return "shows/products";
    }


//    ListProductsAVGCostAndSellinByFactoryId

    // Оновлення продукту за ID
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateFactories(@PathVariable Integer id, @RequestBody Products updatedProducts) {
        try {
            updatedProducts.updateProductName(id, updatedProducts.getProduct_name());
            updatedProducts.updateProductCode(id, updatedProducts.getProduct_code());
            updatedProducts.updateCostPrice(id, updatedProducts.getCost_price());
            updatedProducts.updateSellingPrice(id, updatedProducts.getSelling_price());
            updatedProducts.updateDateMade(id, updatedProducts.getDate_made());
            updatedProducts.updateFactoriesId(id, updatedProducts.getFactories_id());
            return ResponseEntity.ok("Factory updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update factory");
        }
    }

    // Видалення продукту за ID
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        try {
            Products product = new Products();
            product.deleteProduct(id);
            return ResponseEntity.ok("Product deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete product");
        }
    };
}

