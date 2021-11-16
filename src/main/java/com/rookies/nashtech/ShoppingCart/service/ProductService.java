package com.rookies.nashtech.ShoppingCart.service;

import java.util.List;
import com.rookies.nashtech.ShoppingCart.dto.ProductDTO;
import com.rookies.nashtech.ShoppingCart.entity.Product;


  List<ProductDTO> filterProduct(String keyword, Double price);

  List<ProductDTO> findProductByPriceWithPaging(Double price, Integer paging);

  ProductDTO findProductByID(Integer id);

  List<ProductDTO> filterProduct(String keyword, Double price);

  List<ProductDTO> findProductByPriceWithPaging(Double price, Integer paging);

  List<ProductDTO> filterProduct(String keyword, Float price);

  ProductDTO decreaseProductQuantity(Integer id, Integer number);

  ProductDTO increaseProductQuantity(Integer id, Integer number);

  Product findProductEntityByID(Integer id);

}
