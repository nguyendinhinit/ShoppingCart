package com.rookies.nashtech.ShoppingCart.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.rookies.nashtech.ShoppingCart.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<com.rookies.nashtech.ShoppingCart.entity.User> optionalUser = userRepository.findByUsername(username);
    UserBuilder userBuilder = null;
    if (optionalUser.isPresent()) {
      userBuilder = User.withUsername(username);
      userBuilder.password(optionalUser.get().getPassword());
      // userBuilder.password(new BCryptPasswordEncoder().encode(optionalUser.get().getPassword()));
    } else {
      throw new UsernameNotFoundException("Username not found");
    }
    return userBuilder.build();
  }

}
