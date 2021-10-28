package com.rookies.nashtech.ShoppingCart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rookies.nashtech.ShoppingCart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
