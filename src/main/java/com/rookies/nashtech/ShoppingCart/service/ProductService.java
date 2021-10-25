package com.rookies.nashtech.ShoppingCart.service;

import com.rookies.nashtech.ShoppingCart.dto.ProductDTO;

import java.util.List;


public interface ProductService {
    ProductDTO findProductByID(Integer id);
    List<ProductDTO> filterProduct(String keyword, Float price);
}
