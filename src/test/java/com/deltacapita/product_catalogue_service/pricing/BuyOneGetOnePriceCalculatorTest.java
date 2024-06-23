package com.deltacapita.product_catalogue_service.pricing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BuyOneGetOnePriceCalculatorTest {
    private BasePricingCalculator calculator = null;

    @BeforeEach
    void setup() {
        calculator = new BuyOneGetOnePriceCalculator();
    }

    @ParameterizedTest
    @MethodSource("productQuantitySource")
    @DisplayName("GIVEN productPrice & quantity data WHEN calculate called Then returns calculated product price")
    public void testCalculateTotalPrice_OneItem(BigDecimal productPrice, Long quantity, BigDecimal expectedResult) {
        BigDecimal result = calculator.calculate(productPrice, quantity);
        assertThat(expectedResult).isEqualTo(result);
    }

    private static Stream<Arguments> productQuantitySource() {
        return Stream.of(Arguments.of(null, null, BigDecimal.ZERO),
                Arguments.of(new BigDecimal("10.00"), null, BigDecimal.ZERO),
                Arguments.of(null, 1L, BigDecimal.ZERO),
                Arguments.of(new BigDecimal("0"), 1L, BigDecimal.ZERO),
                Arguments.of(new BigDecimal("10.00"), 1L, new BigDecimal("10.00")),
                Arguments.of(new BigDecimal("10.00"), 2L, new BigDecimal("10.00")),
                Arguments.of(new BigDecimal("10.00"), 3L, new BigDecimal("20.00")),
                Arguments.of(new BigDecimal("10.00"), 4L, new BigDecimal("20.00")),
                Arguments.of(new BigDecimal("10.00"), 5L, new BigDecimal("30.00")),
                Arguments.of(new BigDecimal("10.00"), 6L, new BigDecimal("30.00")),
                Arguments.of(new BigDecimal("10.00"), 7L, new BigDecimal("40.00")));
    }


}