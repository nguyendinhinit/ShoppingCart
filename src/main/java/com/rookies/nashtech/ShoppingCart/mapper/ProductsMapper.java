package com.rookies.nashtech.ShoppingCart.mapper;

import com.rookies.nashtech.ShoppingCart.dto.ProductDTO;
import com.rookies.nashtech.ShoppingCart.entity.Product;

public class ProductsMapper {
    public ProductDTO fromEntity(Product products){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setBranch(products.getBranch());

        return productDTO;
    }
}
