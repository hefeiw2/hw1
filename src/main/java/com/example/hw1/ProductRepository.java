package com.example.hw1;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private final List<Product> products;

    public ProductRepository() {
        // 模拟数据
        products = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            Product product = new Product();
            product.setId((long)i);
            product.setImageUrl("https://example.com/image" + i + ".png");
            product.setProductName("Product " + i);
            product.setPrice(10.99 + i);
            product.setStock(i * 10);
            products.add(product);
        }
    }

    public Page<Product> findAll(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Product> list;

        if (products.size() < startItem) {
            list = new ArrayList<>();
        } else {
            int toIndex = Math.min(startItem + pageSize, products.size());
            list = products.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, pageable, products.size());
    }
}
