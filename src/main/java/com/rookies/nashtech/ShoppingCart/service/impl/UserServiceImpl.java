package com.rookies.nashtech.ShoppingCart.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rookies.nashtech.ShoppingCart.entity.User;
import com.rookies.nashtech.ShoppingCart.service.UserService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

  @Override
  public User findByUsername(String username) {
    // TODO Auto-generated method stub
    return null;
  }

}
