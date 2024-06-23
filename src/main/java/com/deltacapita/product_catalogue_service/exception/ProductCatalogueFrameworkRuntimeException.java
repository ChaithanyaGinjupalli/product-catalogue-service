package com.deltacapita.product_catalogue_service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductCatalogueFrameworkRuntimeException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCatalogueFrameworkException.class);

    public ProductCatalogueFrameworkRuntimeException(ErrorCodes errorCodes, String message) {
        super(String.format("%s:%s", errorCodes, message));
        LOGGER.error(String.format("%s:%s", errorCodes, message));
    }
}
