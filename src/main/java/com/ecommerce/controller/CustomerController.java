package com.ecommerce.controller;

import com.ecommerce.dto.CartDTO;
import com.ecommerce.dto.CartItemRequest;
import com.ecommerce.dto.OrderDTO;
import com.ecommerce.dto.ProductDTO;
import com.ecommerce.services.CartService;
import com.ecommerce.services.OrderService;
import com.ecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "/customer")
@RequiredArgsConstructor
@PreAuthorize("hasRole('CUSTOMER')")
public class CustomerController {

    private final ProductService productService;
    private final CartService cartService;
    private final OrderService orderService;

    // ------------------- PRODUCTS -----------------------

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // ------------------- CART -----------------------

    @GetMapping("/cart")
    public ResponseEntity<CartDTO> getCart() {
        return ResponseEntity.ok(cartService.getCurrentUserCart());
    }

    @PostMapping("/cart/add")
    public ResponseEntity<CartDTO> addToCart(@RequestBody CartItemRequest request) {
        return ResponseEntity.ok(cartService.addToCart(request));
    }

    @PutMapping("/cart/update")
    public ResponseEntity<CartDTO> updateCartItem(@RequestBody CartItemRequest request) {
        return ResponseEntity.ok(cartService.updateCartItem(request));
    }

    @DeleteMapping("/cart/remove/{productId}")
    public ResponseEntity<CartDTO> removeCartItem(@PathVariable Long productId) {
        return ResponseEntity.ok(cartService.removeCartItem(productId));
    }

    @DeleteMapping("/cart/clear")
    public ResponseEntity<Void> clearCart() {
        cartService.clearCart();
        return ResponseEntity.noContent().build();
    }

    // ------------------- ORDERS -----------------------

    @PostMapping("/orders")
    public ResponseEntity<OrderDTO> placeOrder() {
        return ResponseEntity.ok(orderService.placeOrderFromCart());
    }

    @GetMapping("/orders")
    public ResponseEntity<Page<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getCustomerOrders());
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

}
