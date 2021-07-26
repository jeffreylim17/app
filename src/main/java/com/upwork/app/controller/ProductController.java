package com.upwork.app.controller;

import com.upwork.app.model.dto.Product;
import com.upwork.app.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ProductController {

  @Autowired
  private ProductService productService;


  @GetMapping(value = "/product/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Product> getProduct(@PathVariable Long id) {
    return productService.getProduct(id);
  }

  @GetMapping(value = "/product",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Product>> getAllProducts() {
    return productService.getAllProduct();
  }

  @PostMapping(value = "/product",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    return productService.createProduct(product);
  }

  @DeleteMapping(value = "/product/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    return productService.deleteProduct(id);
  }

  @PutMapping(value = "/product",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> updateProduct(@RequestBody Product product) {
    return productService.updateProduct(product);
  }
}