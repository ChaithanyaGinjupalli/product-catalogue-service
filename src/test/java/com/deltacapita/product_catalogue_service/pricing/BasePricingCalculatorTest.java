package com.deltacapita.product_catalogue_service.pricing;

import com.deltacapita.product_catalogue_service.exception.ProductCatalogueFrameworkRuntimeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BasePricingCalculatorTest {

    private BasePricingCalculator calculator;

    @BeforeEach
    void setup() {
        calculator = new BuyThreeForTwoPriceCalculator();
    }

    @ParameterizedTest
    @MethodSource("nullAndEmptySource")
    @DisplayName("GIVEN null Or Zero or Negative Values as productPrice & quantity WHEN calculate called Then returns Zero")
    void testCalculateTotalPrice_NoItems(BigDecimal productPrice, Long quantity) {
        Boolean result = calculator.performNullCheck(productPrice, quantity);
        assertThat(Boolean.TRUE).isEqualTo(result);
    }

    private static Stream<Arguments> nullAndEmptySource() {
        return Stream.of(Arguments.of(BigDecimal.ZERO, null),
                Arguments.of(null, Long.valueOf(0)),
                Arguments.of(null, null),
                Arguments.of(BigDecimal.ZERO, Long.valueOf(0)));
    }

    @ParameterizedTest
    @MethodSource("negativeProductValues")
    @DisplayName("GIVEN null Or Zero or Negative Values as productPrice & quantity WHEN calculate called Then returns Zero")
    void testCalculateTotalPrice_Negative(BigDecimal productPrice, Long quantity) {

        ProductCatalogueFrameworkRuntimeException productCatalogueFrameworkRuntimeException = Assertions.catchThrowableOfType(() ->
                calculator.performNullCheck(productPrice, quantity), ProductCatalogueFrameworkRuntimeException.class);
        assertThat(productCatalogueFrameworkRuntimeException).isNotNull();
    }

    private static Stream<Arguments> negativeProductValues() {
        return Stream.of(Arguments.of(BigDecimal.ZERO, Long.valueOf(-1)),
                Arguments.of(new BigDecimal("-1"), Long.valueOf(-1)),
                Arguments.of(new BigDecimal("-1"), Long.valueOf(0)));
    }

}