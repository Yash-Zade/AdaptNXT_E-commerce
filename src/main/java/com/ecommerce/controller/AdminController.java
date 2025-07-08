package com.ecommerce.controller;


import com.ecommerce.dto.CreateProductDTO;
import com.ecommerce.dto.ProductDTO;
import com.ecommerce.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController()
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final ProductService productService;

    //Add New Product
    @PostMapping("/products")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody @Valid CreateProductDTO productDTO) {
        ProductDTO createdProduct = productService.addProduct(productDTO);
        return ResponseEntity.ok(createdProduct);
    }

    //Update Product
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody @Valid CreateProductDTO productDTO) {
        ProductDTO updated = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updated);
    }

    //Delete Product
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    //Get Product by ID
    @GetMapping("/product/id")
    public ResponseEntity<ProductDTO> getProductById(@RequestParam Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }


    //List All Products
    @GetMapping("/products")
    public ResponseEntity<Page<ProductDTO>> getAllProducts(@RequestParam(defaultValue = "0") Integer pageOffset,
                                                           @RequestParam(defaultValue = "10", required = false) Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageOffset, pageSize, Sort.by(Sort.Direction.DESC, "appliedDate", "applicationId"));
        return ResponseEntity.ok(productService.getAllProducts(pageRequest));
    }

    @GetMapping("/products/search")
    public ResponseEntity<Page<ProductDTO>> searchProducts(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") Integer pageOffset,
            @RequestParam(defaultValue = "10", required = false) Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageOffset, pageSize, Sort.by(Sort.Direction.DESC, "appliedDate", "applicationId"));
        return ResponseEntity.ok(productService.searchProducts(query, pageRequest));
    }
}
