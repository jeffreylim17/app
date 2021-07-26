package com.upwork.app.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {

  private long id;
  private String productName;
  private String user;
  private Double price;

  public Order() {
  }

  public Order(long id, String productName, String user, Double price) {
    this.id = id;
    this.productName = productName;
    this.user = user;
    this.price = price;
    return;
  }

}
