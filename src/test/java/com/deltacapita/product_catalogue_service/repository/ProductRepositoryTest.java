package com.deltacapita.product_catalogue_service.repository;

import com.deltacapita.product_catalogue_service.repository.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testcases covers for fetching products list
 */
class ProductRepositoryTest {
    private final ProductRepository productRepository = new ProductRepository();

    @Test
    @DisplayName("GIVEN products list WHEN fetchProductDetails called THEN returns list of products ")
    void fetchProductDetails() {
        List<Product> productList = productRepository.fetchProductDetails();
        assertThat(productList).isNotEmpty();
        assertThat(productList.size()).isEqualTo(4);
    }
}