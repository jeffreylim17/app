package com.upwork.app.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

import com.upwork.app.model.dto.Cart;
import com.upwork.app.model.dto.Order;
import com.upwork.app.model.dto.Product;
import com.upwork.app.model.dto.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ModelTest {

  private Cart cart;
  private Order order;
  private List<Order> orderList;
  private Product product;
  private User user;
  private final String str = "str";
  private final Double dbl = 1d;
  private final long lng = 1l;
  private final int intParam = 1;

  @Before
  public void setup() {
    initMocks(this);
    order = new Order();
    order.setId(1l);
    order.setPrice(dbl);
    order.setUser(str);
    order.setProductName(str);

    orderList = new ArrayList<>();
    orderList = Arrays.asList(order);

    cart = Cart.builder().total(dbl).discount(dbl).orders(orderList).build();
    cart.setTotal(dbl);
    cart.setDiscount(dbl);
    cart.setOrders(orderList);

    product = new Product();
    product.setPrice(dbl);
    product.setId(lng);
    product.setName(str);
    product.setCategory(Category.ELECTRONICS);

    user = new User();
    user.setId(lng);
    user.setName(str);
    user.setCustomerType(CustomerType.STAFF);
    user.setDuration(intParam);
  }

  @Test
  public void orderTest() {
    assertEquals(lng, order.getId());
    assertEquals(dbl, order.getPrice());
    assertEquals(str, order.getUser());
    assertEquals(str, order.getProductName());
  }

  @Test
  public void cartTest() {
    assertEquals(dbl, cart.getTotal());
    assertEquals(dbl, cart.getDiscount());
    assertEquals(orderList, cart.getOrders());
  }

  @Test
  public void productTest() {
    assertEquals(dbl, product.getPrice());
    assertEquals(lng, product.getId());
    assertEquals(str, product.getName());
    product.setCategory(Category.ELECTRONICS);
  }

  @Test
  public void userTest() {
    assertEquals(lng, user.getId());
    assertEquals(str, user.getName());
    assertEquals(CustomerType.STAFF, user.getCustomerType());
    assertEquals(intParam, user.getDuration());
  }
}
