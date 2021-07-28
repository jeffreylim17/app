package com.upwork.app.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.upwork.app.mapper.ProductMapper;
import com.upwork.app.model.Category;
import com.upwork.app.model.dao.ProductEntity;
import com.upwork.app.model.dto.Product;
import com.upwork.app.repository.ProductRepository;
import com.upwork.app.service.impl.ProductServiceImpl;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceTest {

  @Mock
  private ProductMapper productMapper;

  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private ProductServiceImpl productService;

  private ProductEntity productEntity;
  private Product product;
  private final String str = "str";
  private final Double dbl = 1d;
  private final long lng = 1l;

  @Before
  public void setup() {
    initMocks(this);
    productEntity = new ProductEntity();
    productEntity.setCategory(Category.ELECTRONICS);
    productEntity.setId(lng);
    productEntity.setPrice(dbl);
    productEntity.setName(str);

    product = new Product();
    product.setCategory(Category.ELECTRONICS);
    product.setId(lng);
    product.setPrice(dbl);
    product.setName(str);

  }

  @Test
  public void getProduct() {
    when(productRepository.getOne(anyLong())).thenReturn(productEntity);
    when(productMapper.entityToDto(any(ProductEntity.class))).thenReturn(product);
    assertEquals(product, productService.getProduct(lng).getBody());
  }

  @Test
  public void getAllProductTest() {
    List<Product> productList = Arrays.asList(product);
    when(productMapper.entityToDto(any(ProductEntity.class))).thenReturn(product);
    when(productRepository.findAll()).thenReturn(Arrays.asList(productEntity));
    assertEquals(productList, productService.getAllProduct().getBody());
  }

  @Test
  public void createProduct() {
    when(productMapper.entityToDto(any(ProductEntity.class))).thenReturn(product);
    when(productMapper.dtoToEntity(any(Product.class))).thenReturn(productEntity);
    when(productRepository.save(any())).thenReturn(productEntity);
    assertEquals(product, productService.createProduct(product).getBody());
  }

  @Test
  public void updateProductTest() {
    when(productMapper.dtoToEntity(product)).thenReturn(productEntity);
    when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
    when(productMapper.entityToDto(any(ProductEntity.class))).thenReturn(product);
    productService.updateProduct(product);
    verify(productMapper, atLeastOnce()).dtoToEntity(any());
    verify(productRepository, atLeastOnce()).save(any());
    verify(productMapper, atLeastOnce()).entityToDto(any());
  }

  @Test
  public void deleteUserTest() {
    productService.deleteProduct(anyLong());
    verify(productRepository, atLeastOnce()).deleteById(anyLong());
  }
}
