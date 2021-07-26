package com.upwork.app.controller;

import com.upwork.app.model.dto.Cart;
import com.upwork.app.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {

  @Autowired
  private CartService cartService;

  @GetMapping(value = "/cart/{userId}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Cart> getCart(@PathVariable("userId") long userId) {
    return cartService.getCart(userId);
  }

  @PostMapping(value = "/cart/{userId}/{productId}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Cart> addToCart(
      @PathVariable("userId") Long userId,
      @PathVariable("productId") Long productId) {
    return cartService.addToCart(userId, productId);
  }
}
