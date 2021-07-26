package com.upwork.app.mapper;

import com.upwork.app.model.dao.ProductEntity;
import com.upwork.app.model.dto.Product;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring"
)
public interface ProductMapper {

  ProductEntity dtoToEntity(Product productDto);

  Product entityToDto(ProductEntity productEntity);
}
