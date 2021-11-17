package com.rookies.nashtech.ShoppingCart.service;

import com.rookies.nashtech.ShoppingCart.dto.ProductDTO;
import com.rookies.nashtech.ShoppingCart.entity.Product;

import java.util.List;

public interface ProductService {
  List<ProductDTO> filterProduct(String keyword, Double price);

  List<ProductDTO> findProductByPriceWithPaging(Double price, Integer paging);

  ProductDTO findProductByID(Integer id);

  List<ProductDTO> filterProduct(String keyword, Float price);

  ProductDTO decreaseProductQuantity(Product product, Integer number);

  ProductDTO increaseProductQuantity(Product product, Integer number);

  Product findProductEntityByID(Integer id);

}
