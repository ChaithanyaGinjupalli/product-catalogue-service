package com.deltacapita.product_catalogue_service.pricing;

import com.deltacapita.product_catalogue_service.exception.ErrorCodes;
import com.deltacapita.product_catalogue_service.exception.ProductCatalogueFrameworkRuntimeException;

import java.math.BigDecimal;

/**
 * Calculates pricing for FULL_PRICE.
 */
public class FullPriceCalculator extends BasePricingCalculator<BigDecimal, Long> {
    /**
     * Derives the formula of FULL_PRICE. Calculates the payable items and total price.
     *
     * @param productPrice The Product price
     * @param quantity     The quantity of the product.
     * @return Total calculated price.
     */
    @Override
    public BigDecimal calculate(BigDecimal productPrice, Long quantity) {
        if (performNullCheck(productPrice, quantity)) {
            return BigDecimal.ZERO;
        }
        return productPrice.multiply(new BigDecimal(quantity));
    }
}
