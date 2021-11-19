package com.rookies.nashtech.ShoppingCart.util;

import com.rookies.nashtech.ShoppingCart.config.JwtTokenUtil;
import com.rookies.nashtech.ShoppingCart.entity.User;
import com.rookies.nashtech.ShoppingCart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtUtil {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  public User getUser(HttpServletRequest request) {
    return userRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(request.getHeader("Authorization").substring(7))).get();
  }
}
