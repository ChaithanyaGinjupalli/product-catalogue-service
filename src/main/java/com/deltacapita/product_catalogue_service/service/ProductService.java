package com.deltacapita.product_catalogue_service.service;

import com.deltacapita.product_catalogue_service.exception.EmptyProductCatalogueFound;
import com.deltacapita.product_catalogue_service.exception.ErrorCodes;
import com.deltacapita.product_catalogue_service.exception.InvalidProductFoundException;
import com.deltacapita.product_catalogue_service.exception.UnSupportedDiscountTypeFound;
import com.deltacapita.product_catalogue_service.pricing.BuyOneGetOnePriceCalculator;
import com.deltacapita.product_catalogue_service.pricing.BuyThreeForTwoPriceCalculator;
import com.deltacapita.product_catalogue_service.pricing.FullPriceCalculator;
import com.deltacapita.product_catalogue_service.repository.ProductRepository;
import com.deltacapita.product_catalogue_service.repository.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public BigDecimal calculateTotalCost(List<String> productList) throws EmptyProductCatalogueFound {
        if (productList == null || productList.isEmpty()) {
            String errorMsg = "Empty Product Catalog Found";
            LOGGER.error(errorMsg);
            throw new EmptyProductCatalogueFound(ErrorCodes.EMPTY_PRODUCT_CATALOGUE, errorMsg);
        }
        Map<String, Long> catalogueMap = groupProducts(productList);
        Map<String, Product> productMap = fetchProducts();

        return catalogueMap.entrySet().stream()
                .map(e -> calculateProductPrice(e.getKey(), e.getValue(), productMap))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateProductPrice(String productName, Long quantity, Map<String, Product> productMap) {
        Product product = productMap.get(productName);
        if (product == null) {
            throw new InvalidProductFoundException(ErrorCodes.INVALID_PRODUCT, String.format("Unable to find Product details for [%s]", productName));
        }
        return switch (product.discountType()) {
            case FULL_PRICE -> new FullPriceCalculator().calculate(product.price(), quantity);
            case BUY_ONE_GET_ONE -> new BuyOneGetOnePriceCalculator().calculate(product.price(), quantity);
            case BUY_THREE_FOR_TWO_PRICE -> new BuyThreeForTwoPriceCalculator().calculate(product.price(), quantity);
            default ->
                    throw new UnSupportedDiscountTypeFound(ErrorCodes.UNSUPPORTED_DISCOUNT_TYPE, "Discount Type is not supported");
        };
    }

    private Map<String, Product> fetchProducts() {
        return productRepository.fetchProductDetails().stream()
                .collect(Collectors.toMap(product -> product.name().toUpperCase(), product -> product));
    }

    private Map<String, Long> groupProducts(List<String> productList) {
        return productList.stream()
                .map(String::toUpperCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

}
