package com.ecommerce.services;

import com.ecommerce.dto.OrderDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
    OrderDTO placeOrderFromCart();
    Page<OrderDTO> getCustomerOrders();
    OrderDTO getOrderById(Long orderId);
}
