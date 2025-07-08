package com.ecommerce.services;

import com.ecommerce.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getUserById(Long userId);
}
