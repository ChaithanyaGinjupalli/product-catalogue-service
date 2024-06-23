package com.deltacapita.product_catalogue_service.repository.entity;

import lombok.Builder;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Record that models Product and it's pricing details
 *
 * @param id           The Product ID
 * @param name         The Product Name
 * @param price        Price of the product in pences
 * @param discountType if any product discounts default value would be FULL_PRICE
 */
@Builder
public record Product(@NotEmpty BigInteger id,
                      @NotEmpty String name,
                      @NotEmpty BigDecimal price,
                      DiscountType discountType) {
}
