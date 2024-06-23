package com.deltacapita.product_catalogue_service.exception;

/**
 * Throws when UnSupported Discount Type found.
 */
public class InvalidProductFoundException extends ProductCatalogueFrameworkRuntimeException {
    public InvalidProductFoundException(ErrorCodes errorCodes, String errorMsg) {
        super(errorCodes, errorMsg);
    }
}
