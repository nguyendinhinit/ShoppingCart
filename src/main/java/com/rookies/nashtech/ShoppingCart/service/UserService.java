package com.rookies.nashtech.ShoppingCart.service;

import com.rookies.nashtech.ShoppingCart.dto.UserDTO;
import com.rookies.nashtech.ShoppingCart.entity.User;

public interface UserService {
  User findByUsername(String username);

  UserDTO createUser(UserDTO userDTO);
}
