package com.upwork.app.service.impl;

import com.upwork.app.mapper.OrderMapper;
import com.upwork.app.mapper.UserMapper;
import com.upwork.app.model.CustomerType;
import com.upwork.app.model.dao.OrderEntity;
import com.upwork.app.model.dto.Cart;
import com.upwork.app.model.dto.Order;
import com.upwork.app.model.dto.User;
import com.upwork.app.repository.OrderRepository;
import com.upwork.app.repository.ProductRepository;
import com.upwork.app.repository.UserRepository;
import com.upwork.app.service.CartService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private OrderMapper orderMapper;

  @Autowired
  private UserMapper userMapper;

  @Override
  public ResponseEntity<Cart> getCart(long userId) {
    List<OrderEntity> orderEntityList = orderRepository.findAllByUserId(userId);
    List<Order> orders = orderEntityList.stream().map(orderMapper::entityToDto)
        .collect(Collectors.toList());
    return ResponseEntity.ok(discountLogic(orders, userMapper.entityToDto(
        orderEntityList.get(0).getUser())));
  }


  private Cart discountLogic(List<Order> orders, User user) {
    Double total = orders.stream().collect(Collectors.summingDouble(o -> o.getPrice()));
    Double hundredDollarDiscount = dollarCounter(total);
    Double discount = 0d;
    if (user.getCustomerType().equals(CustomerType.STAFF)) {
      discount = total * 0.20d;
    } else if (user.getCustomerType().equals(CustomerType.AFFILIATE)) {
      discount = total * 0.15d;
    } else {
      if (user.getDuration() > 3) {
        discount = total * 0.10d;
      }
    }

    total = total - discount; // percentage based discount
    discount = discount + hundredDollarDiscount;
    total = total - hundredDollarDiscount; // discount for every hundred dollar
    return Cart.builder()
        .orders(orders)
        .total(total)
        .discount(discount)
        .build();
  }

  private Double dollarCounter(Double total) {
    int count = 0;
    while (total > 99) {
      count++;
      total = total - 100;
    }
    return count * 5d;
  }


  @Override
  public ResponseEntity<Cart> addToCart(long userId, long productId) {
    OrderEntity orderEntity = new OrderEntity();
    orderEntity.setUser(userRepository.getOne(userId));
    orderEntity.setProduct(productRepository.getOne(productId));
    orderEntity = orderRepository.save(orderEntity);

    Order order = Order.builder()
        .id(orderEntity.getId())
        .productName(orderEntity.getProduct().getName())
        .price(orderEntity.getProduct().getPrice())
        .user(orderEntity.getUser().getName())
        .build();
    return ResponseEntity
        .ok(Cart.builder().orders(Arrays.asList(order)).build());
  }
}

