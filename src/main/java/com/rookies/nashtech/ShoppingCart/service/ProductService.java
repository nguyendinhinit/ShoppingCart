package com.rookies.nashtech.ShoppingCart.service;

import java.util.List;
import com.rookies.nashtech.ShoppingCart.dto.ProductDTO;
import com.rookies.nashtech.ShoppingCart.entity.Product;

public interface ProductService {

  ProductDTO findProductByID(Integer id);

  List<ProductDTO> filterProduct(String keyword, Double price);

  List<ProductDTO> findProductByPriceWithPaging(Double price, Integer paging);

  List<ProductDTO> filterProduct(String keyword, Float price);

  ProductDTO decreaseProductQuantity(Product product, Integer number);

  ProductDTO increaseProductQuantity(Product product, Integer number);

  Product findProductEntityByID(Integer id);

}
