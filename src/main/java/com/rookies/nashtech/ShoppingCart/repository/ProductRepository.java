package com.rookies.nashtech.ShoppingCart.repository;

import com.rookies.nashtech.ShoppingCart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductById(Integer id);
}
