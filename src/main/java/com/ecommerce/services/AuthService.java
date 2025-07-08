package com.ecommerce.services;

import com.ecommerce.dto.SignupDTO;
import com.ecommerce.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    String[] login(String email, String password);

    UserDTO signup(SignupDTO signupDto);

    String refreshToken(String refreshToken);

    Void logout(HttpServletRequest request, HttpServletResponse response);
}
