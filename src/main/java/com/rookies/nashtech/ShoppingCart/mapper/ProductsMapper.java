package com.rookies.nashtech.ShoppingCart.mapper;

import org.springframework.stereotype.Component;
import com.rookies.nashtech.ShoppingCart.dto.ProductDTO;
import com.rookies.nashtech.ShoppingCart.entity.Product;

@Component
public class ProductsMapper {
  public ProductDTO fromEntity(Product products) {
    ProductDTO productDTO = new ProductDTO();
    productDTO.setId(products.getId());
    productDTO.setName(products.getName());
    productDTO.setPrice(products.getPrice());
    productDTO.setColor(products.getColor());
    productDTO.setCategory(products.getCategory().getName());
    productDTO.setQuantity(products.getQuantity());
    productDTO.setBrand(products.getBrand());
    return productDTO;
  }

}
