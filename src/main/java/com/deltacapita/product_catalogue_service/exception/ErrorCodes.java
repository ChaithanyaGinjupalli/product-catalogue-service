package com.deltacapita.product_catalogue_service.exception;

public enum ErrorCodes {
    EMPTY_PRODUCT_CATALOGUE("SERVICE_ERR_01","Empty Product List found"),
    UNSUPPORTED_DISCOUNT_TYPE("SERVICE_ERR_02", "UnSupported discount type found"),
    INVALID_PRODUCT_PRICE("SERVICE_ERR_03","Product price can't be negative"),
    INVALID_PRODUCT_QUANTITY("SERVICE_ERR_04","Product Quantity can't be negative"),
    INVALID_PRODUCT("SERVICE_ERR_05","Invalid Product found")
    ;

    ErrorCodes(String serviceErr01, String s) {
    }
}
