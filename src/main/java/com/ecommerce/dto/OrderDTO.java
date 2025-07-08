package com.ecommerce.dto;

import com.ecommerce.entity.OrderItem;
import com.ecommerce.entity.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private User user;
    private LocalDateTime createdAt;
    private Double totalAmount;
    private List<OrderItem> items;
}
