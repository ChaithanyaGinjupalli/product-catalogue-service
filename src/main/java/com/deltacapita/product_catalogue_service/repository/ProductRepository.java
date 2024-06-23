package com.deltacapita.product_catalogue_service.repository;

import com.deltacapita.product_catalogue_service.repository.entity.DiscountType;
import com.deltacapita.product_catalogue_service.repository.entity.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Fetch List of Products, it's pricing and Discount Model. Ideally it should be from database. Returning static data for the exercise.
 */
@Repository
public class ProductRepository {
    /**
     * Fetching list of the products
     *
     * @return The list of product including product name, price and DiscountType.
     */
    public List<Product> fetchProductDetails() {
        List<Product> productList = new ArrayList<>();
        productList.add(product(BigInteger.ONE, "Apple", new BigDecimal("35"), DiscountType.FULL_PRICE));
        productList.add(product(BigInteger.TWO, "Banana", new BigDecimal("20"), DiscountType.FULL_PRICE));
        productList.add(product(BigInteger.valueOf(3), "Melons", new BigDecimal("50"), DiscountType.BUY_ONE_GET_ONE));
        productList.add(product(BigInteger.valueOf(4), "Limes", new BigDecimal("15"), DiscountType.BUY_THREE_FOR_TWO_PRICE));

        return productList;
    }

    private Product product(BigInteger id, String name, BigDecimal price, DiscountType discountType) {
        return Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .discountType(discountType)
                .build();
    }
}
