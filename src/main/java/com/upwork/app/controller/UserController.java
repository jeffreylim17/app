package com.upwork.app.controller;

import com.upwork.app.model.dto.User;
import com.upwork.app.service.UserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping(value = "/user/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> getUser(@PathVariable Long id) {
    return userService.getUser(id);
  }

  @GetMapping(value = "/user",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<User>> getAllProducts() {
    return userService.getAllUsers();
  }

  @PostMapping(value = "/user",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @DeleteMapping(value = "/user/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    return userService.deleteUser(id);
  }

  @PutMapping(value = "/user",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> updateUser(@RequestBody User user) {
    return userService.updateUser(user);
  }
}
