package com.rookies.nashtech.ShoppingCart.service;

import com.rookies.nashtech.ShoppingCart.entity.User;

public interface UserService {
  User findByUsername(String username);
}
