package com.upwork.app.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.upwork.app.mapper.OrderMapper;
import com.upwork.app.mapper.UserMapper;
import com.upwork.app.model.Category;
import com.upwork.app.model.CustomerType;
import com.upwork.app.model.dao.OrderEntity;
import com.upwork.app.model.dao.ProductEntity;
import com.upwork.app.model.dao.UserEntity;
import com.upwork.app.model.dto.Cart;
import com.upwork.app.model.dto.Order;
import com.upwork.app.model.dto.User;
import com.upwork.app.repository.OrderRepository;
import com.upwork.app.repository.ProductRepository;
import com.upwork.app.repository.UserRepository;
import com.upwork.app.service.impl.CartServiceImpl;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class CartServiceTest {

  @Mock
  private OrderRepository orderRepository;

  @Mock
  private UserRepository userRepository;

  @Mock
  private ProductRepository productRepository;

  @Mock
  private OrderMapper orderMapper;

  @InjectMocks
  private CartServiceImpl cartService;

  @Mock
  private UserMapper userMapper;
  private final long lng = 1l;
  private final String str = "str";
  private final Double dbl = 1d;
  private OrderEntity orderEntity;
  private ProductEntity productEntity;
  private UserEntity userEntity;
  private Order order;
  private User user;
  private Cart cart;
  private List<Order> orders;
  private static final Double computedDiscount = 0.2d;
  private static final Double computedTotal = 0.8d;

  @Before
  public void setup() {
    initMocks(this);
    productEntity = new ProductEntity();
    productEntity.setName(str);
    productEntity.setPrice(dbl);
    productEntity.setCategory(Category.ELECTRONICS);
    productEntity.setId(lng);

    userEntity = new UserEntity();
    userEntity.setId(lng);
    userEntity.setName(str);
    userEntity.setCustomerType(CustomerType.STAFF);
    userEntity.setMembershipDate(str);

    user = new User();
    user.setName(str);
    user.setId(lng);
    user.setDuration(1);
    user.setCustomerType(CustomerType.STAFF);

    order = new Order();
    order.setId(lng);
    order.setPrice(dbl);
    order.setUser(str);
    order.setProductName(str);

    orderEntity = new OrderEntity();
    orderEntity.setProduct(productEntity);
    orderEntity.setId(lng);
    orderEntity.setUser(userEntity);

    orders = Arrays.asList(order);
    cart = new Cart();
    cart.setDiscount(dbl);
    cart.setOrders(orders);
    cart.setTotal(dbl);

  }

  @Test
  public void getCartTest() {
    List<OrderEntity> orderEntityList = Arrays.asList(orderEntity);
    when(orderRepository.findAllByUserId(anyLong())).thenReturn(orderEntityList);
    when(orderMapper.entityToDto(any(OrderEntity.class))).thenReturn(order);
    when(userMapper.entityToDto(any(UserEntity.class))).thenReturn(user);
    ResponseEntity<Cart> response = cartService.getCart(lng);
    assertEquals(computedDiscount, response.getBody().getDiscount());
    assertEquals(cart.getOrders(), response.getBody().getOrders());
    assertEquals(computedTotal, response.getBody().getTotal());
  }
}
