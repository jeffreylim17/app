package com.upwork.app.model.dto;

import com.upwork.app.model.Category;
import lombok.Data;

@Data
public class Product {

  private long id;
  private String name;
  private Double price;
  private Category category;

}
