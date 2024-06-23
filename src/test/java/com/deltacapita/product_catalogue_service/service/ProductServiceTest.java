package com.deltacapita.product_catalogue_service.service;

import com.deltacapita.product_catalogue_service.exception.EmptyProductCatalogueFound;
import com.deltacapita.product_catalogue_service.exception.InvalidProductFoundException;
import com.deltacapita.product_catalogue_service.repository.ProductRepository;
import com.deltacapita.product_catalogue_service.repository.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    private ProductService productService;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("GIVEN null & empty catalogue list WHEN calculateTotalCost THEN throws EmptyProductCatalogueFound error")
    void calculateTotalCostProductListNull(List<String> productList) {
        EmptyProductCatalogueFound emptyProductCatalogueFound = Assertions.catchThrowableOfType(() ->
                productService.calculateTotalCost(productList), EmptyProductCatalogueFound.class);
        Assertions.assertThat(emptyProductCatalogueFound).isNotNull();
    }

    @Test
    @DisplayName("GIVEN catalogue list with valid & invalid Products WHEN calculateTotalCost THEN throws InvalidProductFoundException error")
    void calculateTotalCostProductList_UnKnownProducts() {
        List<String> productList = Arrays.asList("Lemons","grapes","Banana","Apple");
        InvalidProductFoundException invalidProductFoundException = Assertions.catchThrowableOfType(() ->
                productService.calculateTotalCost(productList), InvalidProductFoundException.class);
        Assertions.assertThat(invalidProductFoundException).isNotNull();
    }

    @Test
    @DisplayName("GIVEN catalogue list with full price Products WHEN calculateTotalCost THEN returns full price")
    void calculateTotalCostProductList_FullPrice() throws EmptyProductCatalogueFound {
        List<String> productList = Arrays.asList("Apple","Apple","Banana","Banana","Apple");
        BigDecimal result = productService.calculateTotalCost(productList);
        Assertions.assertThat(result).isEqualTo(new BigDecimal("145")); //35p*3 + 20p*2 =145p
    }

    @Test
    @DisplayName("GIVEN catalogue list with full price Products WHEN calculateTotalCost THEN returns full price")
    void calculateTotalCostProductList_BuyOneGetOne() throws EmptyProductCatalogueFound {
        List<String> productList = Arrays.asList("Melons","Melons","Melons","Melons","Melons");
        BigDecimal result = productService.calculateTotalCost(productList);
        Assertions.assertThat(result).isEqualTo(new BigDecimal("150")); //50p*2 + 50p*1 =150p
    }

    @Test
    @DisplayName("GIVEN catalogue list with full price Products WHEN calculateTotalCost THEN returns full price")
    void calculateTotalCostProductList_BuyThreeInTwoPrice() throws EmptyProductCatalogueFound {
        List<String> productList = Arrays.asList("Limes","Limes","Limes","Limes","Limes");
        BigDecimal result = productService.calculateTotalCost(productList);
        Assertions.assertThat(result).isEqualTo(new BigDecimal("60")); //15p*2 + 15p*2 =60p
    }

    @Test
    @DisplayName("GIVEN catalogue list with all mixed Products WHEN calculateTotalCost THEN returns full price")
    void calculateTotalCostProductList_all() throws EmptyProductCatalogueFound {
        List<String> productList = Arrays.asList("Apple","Limes","Banana","Apple","Limes", "Melons", "Banana", "Limes", "Melons");
        BigDecimal result = productService.calculateTotalCost(productList);
        Assertions.assertThat(result).isEqualTo(new BigDecimal("190")); //35p*2 + 20p*2 + 50p*1 + 15p*2 = 190p
    }

}