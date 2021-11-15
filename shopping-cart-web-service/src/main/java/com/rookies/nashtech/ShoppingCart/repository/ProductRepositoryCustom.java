package com.rookies.nashtech.ShoppingCart.repository;

import com.rookies.nashtech.ShoppingCart.entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> filter(String keyword, Double price);
}
