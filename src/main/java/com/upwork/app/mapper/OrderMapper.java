package com.upwork.app.mapper;

import com.upwork.app.model.dao.OrderEntity;
import com.upwork.app.model.dto.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
    componentModel = "spring"
)
public interface OrderMapper {

  @Mappings({
      @Mapping(target="id",source = "entity.id"),
      @Mapping(target = "productName",source = "entity.product.name"),
      @Mapping(target = "user",source = "entity.user.name"),
      @Mapping(target = "price",source = "entity.product.price")
  })
  Order entityToDto(OrderEntity entity);

}
