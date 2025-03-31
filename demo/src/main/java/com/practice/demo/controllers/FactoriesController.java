package com.practice.demo.controllers;

import com.practice.demo.models.Factories;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/factories")
public class FactoriesController {

    // Створення продукту
    @PostMapping
    public Integer createFactories(@RequestBody Factories factories) {
        return factories.addFactory(factories.getName(), factories.getProduced(), factories.getSold(),
                factories.getDate_made());
    }

    // Отримання всіх продуктів
    @GetMapping
    public void getAllFactories() {
        Factories factories = new Factories();
        factories.ListFactories();
    }



    // Оновлення продукту за ID
    @PutMapping("/{id}")
    public void updateFactories(@PathVariable Integer id, @RequestBody Factories updatedFactories) {
        updatedFactories.updateFactoryName(id, updatedFactories.getName());
        updatedFactories.updateFactoryProduced(id, updatedFactories.getProduced());
        updatedFactories.updateFactorySold(id, updatedFactories.getSold());
        updatedFactories.updateFactoryDateMade(id, updatedFactories.getDate_made());
    }

    // Видалення продукту за ID
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        Factories factories = new Factories();
        factories.deleteFactory(id);
    }
}


