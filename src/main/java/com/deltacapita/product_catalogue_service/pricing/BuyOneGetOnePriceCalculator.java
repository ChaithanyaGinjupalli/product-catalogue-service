package com.deltacapita.product_catalogue_service.pricing;

import java.math.BigDecimal;

/**
 * Calculates pricing for BUY_ONE_GET_ONE discount type.
 */
public class BuyOneGetOnePriceCalculator extends BasePricingCalculator<BigDecimal, Long> {
    /**
     * Derives the formula of BUY_ONE_GET_ONE. Calculates the payable items and total price.
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

        // Calculate the total cost based on the "BUY_ONE_GET_ONE" offer
        long payableItems = (quantity / 2) + (quantity % 2);
        return productPrice.multiply(new BigDecimal(payableItems));
    }
}
