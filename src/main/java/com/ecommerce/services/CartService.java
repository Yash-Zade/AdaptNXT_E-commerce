package com.ecommerce.services;

import com.ecommerce.dto.CartDTO;
import com.ecommerce.dto.CartItemRequest;

public interface CartService {
    CartDTO getCurrentUserCart();
    CartDTO addToCart(CartItemRequest request);
    CartDTO updateCartItem(CartItemRequest request);
    CartDTO removeCartItem(Long productId);
    void clearCart();
}
