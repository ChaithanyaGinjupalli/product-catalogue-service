package com.deltacapita.product_catalogue_service.exception;

/**
 * Throws when UnSupported Discount Type found.
 */
public class UnSupportedDiscountTypeFound extends ProductCatalogueFrameworkRuntimeException {
    public UnSupportedDiscountTypeFound(ErrorCodes errorCodes, String errorMsg) {
        super(errorCodes, errorMsg);
    }
}
