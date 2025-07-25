package com.example.inventoryservice.controller;

import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService service;

    @PostMapping
    public ResponseEntity<String> addStock(@RequestBody Inventory inventory) {
        String message = service.addStock(inventory);
        return message.contains("successfully")
                ? ResponseEntity.status(HttpStatus.CREATED).body(message)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @GetMapping
    public List<Inventory> getAllStock() {
        return service.getAllStock();
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<String> getStockByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(service.getStockByProductId(productId));
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<String> updateStock(@PathVariable Long productId, @RequestBody Inventory inventory) {
        return ResponseEntity.ok(service.updateStock(productId, inventory));
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteStockByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(service.deleteStockByProductId(productId));
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllStock() {
        return ResponseEntity.ok(service.deleteAllStock());
    }
}
