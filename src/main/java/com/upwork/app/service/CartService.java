package com.upwork.app.service;


import com.upwork.app.model.dto.Cart;
import org.springframework.http.ResponseEntity;

public interface CartService {

  ResponseEntity<Cart> getCart(long userId);

  ResponseEntity<Cart> addToCart(long userId, long productId);
}
