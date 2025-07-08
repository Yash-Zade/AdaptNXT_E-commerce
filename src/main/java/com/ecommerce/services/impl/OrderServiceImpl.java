package com.ecommerce.services.impl;

import com.ecommerce.dto.OrderDTO;
import com.ecommerce.entity.*;
import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.services.OrderService;
import com.ecommerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ecommerce.entity.enums.OrderStatus.PLACED;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public OrderDTO placeOrderFromCart() {
        User user = userService.getCurrentAuthenticatedUser();

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user."));

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Your cart is empty. Add items before placing an order.");
        }

        Order order = Order.builder()
                .user(user)
                .createdAt(LocalDateTime.now())
                .status(PLACED)
                .build();

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0.0;

        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();
            double price = product.getPrice();

            OrderItem orderItem = OrderItem.builder()
                    .productName(product.getName())
                    .price(price)
                    .quantity(quantity)
                    .build();

            orderItem.setOrder(order);
            orderItems.add(orderItem);

            totalAmount += price * quantity;
        }

        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        cart.getItems().clear();
        cartRepository.save(cart);

        return modelMapper.map(savedOrder, OrderDTO.class);
    }


    @Override
    public Page<OrderDTO> getCustomerOrders() {
        User user = userService.getCurrentAuthenticatedUser();
        Page<Order> orders = orderRepository.findByUser(user, PageRequest.of(0, 10));
        return orders.map(order -> modelMapper.map(order, OrderDTO.class));
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        User user = userService.getCurrentAuthenticatedUser();
        Order order = orderRepository.findByIdAndUser(orderId, user)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found or access denied."));
        return modelMapper.map(order, OrderDTO.class);
    }
}
