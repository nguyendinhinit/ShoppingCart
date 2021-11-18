package com.rookies.nashtech.ShoppingCart.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rookies.nashtech.ShoppingCart.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findByUsername(String username);
}
