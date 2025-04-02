package com.practice.demo.controllers;

import com.practice.demo.models.Factories;
import com.practice.demo.models.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/factories")
public class FactoriesController {

//    // Створення продукту
//    @PostMapping
//    public Integer createFactories(@RequestBody Factories factories) {
//        return factories.addFactory(factories.getName(), factories.getProduced(), factories.getSold(),
//                factories.getDate_made());
//    }



    @PostMapping
    @ResponseBody
    public ResponseEntity<Integer> createFactories(@RequestBody Factories factories) {
        Integer factoryId = factories.addFactory(
                factories.getName(),
                factories.getProduced(),
                factories.getSold(),
                factories.getDate_made()
        );
        return ResponseEntity.ok(factoryId);
    }

    @GetMapping("/forms")
    public String getFactoriesFormView(Model model) {
        model.addAttribute("factories", new Factories());
        return "forms/factories";
    }

    @GetMapping("/index")
    public String getFactoriesIndexView(Model model) {
        Factories factories = new Factories();
        List<Factories> factorieslist = factories.ListFactoriesList();
        model.addAttribute("factories", factorieslist);
        return "index/factories";
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


