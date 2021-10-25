package com.rookies.nashtech.ShoppingCart.mapper;

import com.rookies.nashtech.ShoppingCart.dto.ProductDTO;
import com.rookies.nashtech.ShoppingCart.entity.Product;

public class ProductMapper {
    public ProductDTO fromEntity(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setBrand(product.getBrand());
        return productDTO;
    }
}
