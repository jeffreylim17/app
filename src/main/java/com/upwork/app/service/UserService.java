package com.upwork.app.service;

import com.upwork.app.model.dto.User;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface UserService {

  ResponseEntity<User> getUser(long id);

  ResponseEntity<User> createUser(User user);

  ResponseEntity<Void> updateUser(User user);

  ResponseEntity<Void> deleteUser(long id);

  ResponseEntity<List<User>> getAllUsers();
}
