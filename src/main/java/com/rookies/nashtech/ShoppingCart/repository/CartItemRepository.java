package com.rookies.nashtech.ShoppingCart.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rookies.nashtech.ShoppingCart.entity.Cart;
import com.rookies.nashtech.ShoppingCart.entity.CartItem;
import com.rookies.nashtech.ShoppingCart.entity.Product;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
  List<CartItem> findByCart(Cart cart);

  CartItem findByProduct(Product product);
}
