package com.ecommerce.services.impl;

import com.ecommerce.dto.CreateProductDTO;
import com.ecommerce.dto.ProductDTO;
import com.ecommerce.entity.Product;
import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductDTO addProduct(CreateProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        return modelMapper.map(productRepository.save(product), ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(Long productId, CreateProductDTO productDTO) {
        Product existing = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));

        modelMapper.map(productDTO, existing); // updates fields from DTO into entity
        return modelMapper.map(productRepository.save(existing), ProductDTO.class);
    }

    @Override
    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Product not found with ID: " + productId);
        }
        productRepository.deleteById(productId);
    }

    @Override
    public Page<ProductDTO> getAllProducts(PageRequest pageRequest) {
        Page<Product> products = productRepository.findAll(pageRequest);
        return products.map(product -> modelMapper.map(product, ProductDTO.class));
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public Page<ProductDTO> searchProducts(String query, PageRequest pageRequest) {
        Page<Product> products = productRepository.findAll(pageRequest);
        List<ProductDTO> filteredProducts = products.getContent().stream()
                .filter(product -> product.getName().toLowerCase().contains(query.toLowerCase()) ||
                                  product.getDescription().toLowerCase().contains(query.toLowerCase()))
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
        return new PageImpl<>(filteredProducts, pageRequest, products.getTotalElements());
    }
}
