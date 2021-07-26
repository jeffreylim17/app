package com.upwork.app.model.dto;

import com.upwork.app.model.CustomerType;
import lombok.Data;

@Data
public class User {

  private long id;
  private String name;
  private CustomerType customerType;
  /**
   * customer duration in years
   **/
  private int duration;


}
