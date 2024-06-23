package com.deltacapita.product_catalogue_service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductCatalogueFrameworkException extends Exception {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCatalogueFrameworkException.class);

    ProductCatalogueFrameworkException(ErrorCodes errorCodes, String message) {
        super(String.format("%s:%s", errorCodes, message));
        LOGGER.error(String.format("%s:%s", errorCodes, message));
    }

    ProductCatalogueFrameworkException(ErrorCodes errorCodes, String message, Throwable throwable) {
        super(String.format("%s:%s", errorCodes, message), throwable);
        LOGGER.error(String.format("%s:%s", errorCodes, message));
    }
}
