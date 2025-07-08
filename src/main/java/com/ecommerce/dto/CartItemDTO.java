package com.ecommerce.dto;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import lombok.Data;

@Data
public class CartItemDTO {
    private Long id;
    private User user;
    private Product product;
    private Integer quantity;
}
