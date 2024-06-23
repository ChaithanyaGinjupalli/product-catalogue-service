package com.deltacapita.product_catalogue_service.exception;

public class EmptyProductCatalogueFound extends ProductCatalogueFrameworkException {
    public EmptyProductCatalogueFound(ErrorCodes errorCodes, String errorMsg) {
        super(errorCodes, errorMsg);
    }
}
