package com.rookies.nashtech.ShoppingCart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rookies.nashtech.ShoppingCart.entity.Cart;
import com.rookies.nashtech.ShoppingCart.entity.User;

public interface CartRepository extends JpaRepository<Cart, User> {

}
