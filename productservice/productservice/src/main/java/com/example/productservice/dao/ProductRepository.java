package com.example.productservice.dao;

import com.example.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByExpiryDate(LocalDate expiryDate);
    List<Product> findByManufactureDate(LocalDate manufactureDate);
    List<Product> findByPrice(double price);
    List<Product> findByDescription(String description);
}
