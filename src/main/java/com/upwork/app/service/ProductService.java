package com.upwork.app.service;

import com.upwork.app.model.dto.Product;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface ProductService {

  ResponseEntity<Product> getProduct(long id);

  ResponseEntity<Product> createProduct(Product product);

  ResponseEntity<Void> updateProduct(Product product);

  ResponseEntity<Void> deleteProduct(long id);

  ResponseEntity<List<Product>> getAllProduct();
}
