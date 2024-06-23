package com.deltacapita.product_catalogue_service.pricing;

import com.deltacapita.product_catalogue_service.exception.ErrorCodes;
import com.deltacapita.product_catalogue_service.exception.ProductCatalogueFrameworkRuntimeException;

import java.math.BigDecimal;

/**
 * Abstract class with abstract calculate method which will be implemented for different discount types
 *
 * @param <P> The price of the product
 * @param <Q> The quantity of the products
 */
public abstract class BasePricingCalculator<P, Q> {
    abstract BigDecimal calculate(P productPrice, Q quantity);

    /**
     * Verify the productPrice and quantity checks.
     *
     * @param productPrice The product price
     * @param quantity     The product quantity
     * @return
     */
    protected Boolean performNullCheck(BigDecimal productPrice, Long quantity) {
        Boolean result = Boolean.FALSE;
        if (productPrice == null || productPrice.equals(BigDecimal.ZERO) || quantity == null || quantity == 0) {
            result = Boolean.TRUE;
        }

        if (productPrice != null && productPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new ProductCatalogueFrameworkRuntimeException(ErrorCodes.INVALID_PRODUCT_PRICE, String.format("Invalid Product Price found : [%s]", productPrice));
        }
        if (quantity != null && quantity < 0) {
            throw new ProductCatalogueFrameworkRuntimeException(ErrorCodes.INVALID_PRODUCT_QUANTITY, String.format("Invalid Product Quantity found : [%s]", quantity));
        }
        return result;
    }
}
