package com.example.productservice.service;

import com.example.productservice.dao.ProductRepository;
import com.example.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    // CREATE or UPDATE product
    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    // FETCH all products
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // FETCH a product by ID
    public Product getProductById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // DELETE a product by ID
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    // DELETE all products
    public void deleteAllProducts() {
        repository.deleteAll();
    }

  
    // VIEW all prices
    public List<Double> getAllPrices() {
        return repository.findAll()
                         .stream()
                         .map(Product::getPrice)
                         .collect(Collectors.toList());
    }

    // VIEW price of a particular product
    public Double getPriceById(Long id) {
        Product product = repository.findById(id).orElse(null);
        return (product != null) ? product.getPrice() : null;
    }

    
}
