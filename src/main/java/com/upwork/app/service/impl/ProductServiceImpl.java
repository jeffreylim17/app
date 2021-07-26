package com.upwork.app.service.impl;

import com.upwork.app.mapper.ProductMapper;
import com.upwork.app.model.dto.Product;
import com.upwork.app.repository.ProductRepository;
import com.upwork.app.service.ProductService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductMapper productMapper;

  @Autowired
  private ProductRepository productRepository;

  @Override
  public ResponseEntity<Product> getProduct(long id) {
    return ResponseEntity.ok(productMapper.entityToDto(productRepository.getOne(id)));
  }

  @Override
  public ResponseEntity<List<Product>> getAllProduct() {
    return ResponseEntity.ok(
        productRepository.findAll()
            .stream()
            .map(productMapper::entityToDto)
            .collect(Collectors.toList()));
  }

  @Override
  public ResponseEntity<Product> createProduct(Product product) {
    return ResponseEntity
        .ok(productMapper.entityToDto(productRepository.save(productMapper.dtoToEntity(product))));
  }

  @Override
  public ResponseEntity<Void> updateProduct(Product product) {
    productMapper.entityToDto(productRepository.save(productMapper.dtoToEntity(product)));
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<Void> deleteProduct(long id) {
    productRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }


}
