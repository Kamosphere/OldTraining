package com.tw.jingxi.controller;

import com.tw.jingxi.entity.Inventory;
import com.tw.jingxi.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/inventories")
public class InventoryController {
    @Autowired
    InventoryRepository inventoryRepository;

    @PostMapping
    public void saveInventory(@ModelAttribute Long productId) {
        inventoryRepository.saveByProductId(productId);
    }

    @GetMapping
    public List<Inventory> getInventories() {
        return inventoryRepository.findAll();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable Long id, @RequestBody Inventory inventory) throws Exception {
        inventoryRepository.findById(id).orElseThrow(() -> new Exception("Inventory "+id+" not found"));
        inventoryRepository.updateCountById(id, inventory.getCount());
    }

}