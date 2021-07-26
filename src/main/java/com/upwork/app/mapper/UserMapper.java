package com.upwork.app.mapper;

import com.upwork.app.model.dao.UserEntity;
import com.upwork.app.model.dto.User;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring"
)
public interface UserMapper {


  UserEntity dtoToEntity(User userDto);

  User entityToDto(UserEntity userEntity);

}
