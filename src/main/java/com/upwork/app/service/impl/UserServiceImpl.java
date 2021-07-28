package com.upwork.app.service.impl;

import com.upwork.app.mapper.UserMapper;
import com.upwork.app.model.dto.User;
import com.upwork.app.repository.UserRepository;
import com.upwork.app.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private UserRepository userRepository;

  @Override
  public ResponseEntity<User> getUser(long id) {
    return ResponseEntity.ok(userMapper.entityToDto(userRepository.getOne(id)));
  }

  @Override
  public ResponseEntity<List<User>> getAllUsers() {
    return ResponseEntity.ok(
        userRepository.findAll()
            .stream()
            .map(userMapper::entityToDto)
            .collect(Collectors.toList()));
  }

  @Override
  public ResponseEntity<User> createUser(User user) {
    return ResponseEntity
        .ok(userMapper.entityToDto(userRepository.save(userMapper.dtoToEntity(user))));
  }

  @Override
  public ResponseEntity<Void> updateUser(User user) {
    userMapper.entityToDto(
        userRepository.save(
            userMapper.dtoToEntity(user)));
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<Void> deleteUser(long id) {
    userRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
