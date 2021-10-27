package com.rookies.nashtech.ShoppingCart.service;

import com.rookies.nashtech.ShoppingCart.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    ProductDTO findProductByID(Integer id);
    List<ProductDTO> filterProduct(String keyword, Float price);
}
