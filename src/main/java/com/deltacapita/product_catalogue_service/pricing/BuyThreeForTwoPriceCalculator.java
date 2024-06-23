package com.deltacapita.product_catalogue_service.pricing;

import com.deltacapita.product_catalogue_service.exception.ErrorCodes;
import com.deltacapita.product_catalogue_service.exception.ProductCatalogueFrameworkRuntimeException;

import java.math.BigDecimal;

/**
 * Calculates pricing for BUY_THREE_FOR_TWO_PRICE discount type.
 */
public class BuyThreeForTwoPriceCalculator extends BasePricingCalculator<BigDecimal, Long> {
    /**
     * Derives the formula of BUY_THREE_FOR_TWO_PRICE. Finds the setsOfThree and Remaining items. Then calculates the full pricing.
     *
     * @param productPrice The Price of the product
     * @param quantity     The quantity of the product
     * @return Total cost of the product.
     */
    @Override
    public BigDecimal calculate(BigDecimal productPrice, Long quantity) {
        if (performNullCheck(productPrice, quantity)) {
            return BigDecimal.ZERO;
        }
        // Calculate the number of sets of 3 items
        long setsOfThree = quantity / 3;

        // Calculate the remaining items not included in the sets of 3
        long remainingItems = quantity % 3;

        BigDecimal setOfThreePrice = productPrice.multiply(new BigDecimal(setsOfThree * 2));
        BigDecimal remainingItemsPrice = productPrice.multiply(new BigDecimal(remainingItems));

        return setOfThreePrice.add(remainingItemsPrice);
    }
}
