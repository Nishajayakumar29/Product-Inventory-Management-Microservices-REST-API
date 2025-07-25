package com.example.productservice.model;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Product {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
		private String name;
	    private double price;
	    private String description;
	    private LocalDate manufactureDate;
	    private LocalDate expiryDate;
	    Product()
	    {
	    	super();
	    }
	    Product (Long id, String name, double price, String description,LocalDate manufactureDate,
	  LocalDate expiryDate)
	    {
	    	super();
	    	this.id = id;
	    	this.name = name;
	    	this.price = price;
	    	this.description = description;
	    	this.manufactureDate = manufactureDate;
	    	this.expiryDate = expiryDate;
	    	  }
	    public Long getId() {
			return id;
		}
		 public void setId(Long id) {
			 this.id = id;
		 }
		 public String getName() {
			 return name;
		 }
		 public void setName(String name) {
			 this.name = name;
		 }
		 public double getPrice() {
			 return price;
		 }
		 public void setPrice(double price) {
			 this.price = price;
		 }
		 public String getDescription() {
			 return description;
		 }
		 public void setDescription(String description) {
			 this.description = description;
		 }
		 public LocalDate getManufactureDate() {
			 return manufactureDate;
		 }
		 public void setManufactureDate(LocalDate manufactureDate) {
			 this.manufactureDate = manufactureDate;
		 }
		 public LocalDate getExpiryDate() {
			 return expiryDate;
		 }
		 public void setExpiryDate(LocalDate expiryDate) {
			 this.expiryDate = expiryDate;
		 }
}

