package com.rookies.nashtech.ShoppingCart.service;

import com.rookies.nashtech.ShoppingCart.dto.ProductDTO;


public interface ProductService {
    ProductDTO findProductByID(Integer id);
}
