package com.example.productservice.controller;

import com.example.productservice.model.Product;
import com.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    // CREATE product
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        service.saveProduct(product);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Product is added successfully!");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // UPDATE product by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existing = service.getProductById(id);
        if (existing != null) {
            existing.setName(product.getName());
            existing.setPrice(product.getPrice());
            existing.setDescription(product.getDescription());
            existing.setManufactureDate(product.getManufactureDate());
            existing.setExpiryDate(product.getExpiryDate());
            Product updatedProduct = service.saveProduct(existing);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Product is updated successfully!");
            response.put("updatedProduct", updatedProduct);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(Map.of("message", "Sorry! The product not found."));
    }

    // FETCH all products
    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    // FETCH product by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Product product = service.getProductById(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(Map.of("message", "Sorry! The product not found."));
        }
        return ResponseEntity.ok(product);
    }

    // DELETE product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Product product = service.getProductById(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Sorry! The product not found."));
        }
        service.deleteProduct(id);
        return ResponseEntity.ok(Map.of("message", "Product deleted successfully!"));
    }

    // DELETE all products
    @DeleteMapping("/deleteAll")
    public String deleteAllProducts() {
        service.deleteAllProducts();
        return "All products deleted successfully!";
    }

    // GET all prices
    @GetMapping("/prices")
    public List<Double> getAllPrices() {
        return service.getAllPrices();
    }

    // GET price of a particular product
    @GetMapping("/{id}/price")
    public ResponseEntity<?> getPriceById(@PathVariable Long id) {
        Double price = service.getPriceById(id);
        if (price == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(Map.of("message", "Sorry! The product not found."));
        }
        return ResponseEntity.ok(price);
    }
}
