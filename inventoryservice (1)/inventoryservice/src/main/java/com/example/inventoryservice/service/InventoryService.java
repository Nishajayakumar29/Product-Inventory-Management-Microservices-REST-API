package com.example.inventoryservice.service;

import com.example.inventoryservice.dao.InventoryRepository;
import com.example.inventoryservice.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    // Check if product exists in ProductService
    private boolean isProductExists(Long productId) {
        String productUrl = "http://localhost:8000/products/" + productId;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(productUrl, String.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        } catch (Exception e) {
            return false; // In case ProductService is down
        }
    }

    public String addStock(Inventory inventory) {
        if (!isProductExists(inventory.getProductId())) {
            return "The product has been deleted, stock cannot be added.";
        }
        repository.save(inventory);
        return "Stock added successfully!";
    }

    public List<Inventory> getAllStock() {
        return repository.findAll();
    }

    public String getStockByProductId(Long productId) {
        if (!isProductExists(productId)) {
            return "The product has been deleted, the stock of the deleted product cannot be seen.";
        }
        Optional<Inventory> inventory = repository.findByProductId(productId);
        return inventory.map(Inventory::toString)
                .orElse("No stock found for productId: " + productId);
    }

    public String updateStock(Long productId, Inventory updatedInventory) {
        if (!isProductExists(productId)) {
            return "The product has been deleted, stock cannot be updated.";
        }
        Optional<Inventory> existing = repository.findByProductId(productId);
        if (existing.isPresent()) {
            Inventory inv = existing.get();
            inv.setStock(updatedInventory.getStock());
            repository.save(inv);
            return "Stock updated successfully: " + inv;
        }
        return "No stock found for productId: " + productId;
    }

    public String deleteStockByProductId(Long productId) {
        if (!isProductExists(productId)) {
            return "The product has been deleted, stock cannot be deleted.";
        }
        Optional<Inventory> existing = repository.findByProductId(productId);
        if (existing.isPresent()) {
            repository.delete(existing.get());
            return "Stock deleted successfully for productId: " + productId;
        }
        return "No stock found for productId: " + productId;
    }

    public String deleteAllStock() {
        repository.deleteAll();
        return "All stock deleted successfully!";
    }
}
