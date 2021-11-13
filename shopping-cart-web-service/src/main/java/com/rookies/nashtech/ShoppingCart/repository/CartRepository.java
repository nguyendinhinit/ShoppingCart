package com.rookies.nashtech.ShoppingCart.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rookies.nashtech.ShoppingCart.entity.Cart;
import com.rookies.nashtech.ShoppingCart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
  Optional<Cart> findByUser(User user);
}
