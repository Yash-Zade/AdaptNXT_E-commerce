package com.ecommerce.services;

import com.ecommerce.dto.CreateProductDTO;
import com.ecommerce.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductService {

    ProductDTO addProduct(CreateProductDTO productDTO);

    ProductDTO updateProduct(Long productId, CreateProductDTO productDTO);

    void deleteProduct(Long productId);

    Page<ProductDTO> getAllProducts(PageRequest pageRequest);

    ProductDTO getProductById(Long id);

    Page<ProductDTO> searchProducts(String query, PageRequest pageRequest);
}
