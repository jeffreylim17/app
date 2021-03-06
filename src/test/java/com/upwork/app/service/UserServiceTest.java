package com.upwork.app.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.upwork.app.mapper.UserMapper;
import com.upwork.app.model.CustomerType;
import com.upwork.app.model.dao.UserEntity;
import com.upwork.app.model.dto.User;
import com.upwork.app.repository.UserRepository;
import com.upwork.app.service.impl.UserServiceImpl;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

  @Mock
  private UserMapper userMapper;

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserServiceImpl userService;

  private UserEntity userEntity;
  private User user;
  private final String str = "str";
  private final int intParam = 1;
  private final long lng = 1l;

  @Before
  public void setup() {
    initMocks(this);
    userEntity = new UserEntity();
    userEntity.setId(lng);
    userEntity.setName(str);
    userEntity.setCustomerType(CustomerType.STAFF);
    userEntity.setMembershipDate(str);

    user = new User();
    user.setName(str);
    user.setId(lng);
    user.setDuration(intParam);
    user.setCustomerType(CustomerType.STAFF);

  }

  @Test
  public void getUserTest() {
    when(userRepository.getOne(anyLong())).thenReturn(userEntity);
    when(userMapper.entityToDto(any(UserEntity.class))).thenReturn(user);
    assertEquals(user, userService.getUser(lng).getBody());
  }

  @Test
  public void getAllUsersTest() {
    List<User> userList = Arrays.asList(user);
    when(userMapper.entityToDto(any(UserEntity.class))).thenReturn(user);
    when(userRepository.findAll()).thenReturn(Arrays.asList(userEntity));
    assertEquals(userList, userService.getAllUsers().getBody());
  }

  @Test
  public void createUser() {
    when(userMapper.entityToDto(any(UserEntity.class))).thenReturn(user);
    when(userMapper.dtoToEntity(any(User.class))).thenReturn(userEntity);
    when(userRepository.save(any())).thenReturn(userEntity);
    assertEquals(user, userService.createUser(user).getBody());
  }

  @Test
  public void updateUserTest() {
    when(userMapper.dtoToEntity(user)).thenReturn(userEntity);
    when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
    when(userMapper.entityToDto(any(UserEntity.class))).thenReturn(user);
    userService.updateUser(user);
    verify(userMapper, atLeastOnce()).dtoToEntity(any());
    verify(userRepository, atLeastOnce()).save(any());
    verify(userMapper, atLeastOnce()).entityToDto(any());
  }

  @Test
  public void deleteUserTest() {
    userService.deleteUser(anyLong());
    verify(userRepository, atLeastOnce()).deleteById(anyLong());
  }
}

