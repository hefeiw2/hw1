package com.example.hw1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void testGetProducts() throws Exception {
        // Mock the return value of the productService.getProducts() method
        Page<Product> mockedPage = new PageImpl<>(Collections.singletonList(new Product()));
        when(productService.getProducts(anyInt(), anyInt())).thenReturn(mockedPage);

        // Perform the GET request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .param("page", "1")
                        .param("pageSize", "15"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(1));

        // Verify that the productService.getProducts() method was called with the correct parameters
        verify(productService, times(1)).getProducts(1, 15);
    }
}