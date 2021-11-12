package com.rookies.nashtech.ShoppingCart.service;

import com.rookies.nashtech.ShoppingCart.dto.ProductDTO;
import org.springframework.stereotype.Service;
import com.rookies.nashtech.ShoppingCart.entity.Product;


import java.util.List;

public interface ProductService {

    ProductDTO findProductByID(Integer id);
    List<ProductDTO> filterProduct(String keyword, Double price);
    List<ProductDTO> findProductByPriceWithPaging(Double price, Integer paging);

  ProductDTO findProductByID(Integer id);

  List<ProductDTO> filterProduct(String keyword, Float price);

  ProductDTO decreaseProductQuantity(Integer id, Integer number);

  ProductDTO increaseProductQuantity(Integer id, Integer number);

  Product findProductEntityByID(Integer id);

}
