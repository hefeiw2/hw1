package com.example.hw1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getProducts(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, Math.min(pageSize, 30));
        return productRepository.findAll(pageable);
    }
}
