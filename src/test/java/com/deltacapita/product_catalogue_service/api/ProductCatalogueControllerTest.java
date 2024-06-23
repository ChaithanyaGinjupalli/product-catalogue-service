package com.deltacapita.product_catalogue_service.api;

import com.deltacapita.product_catalogue_service.repository.ProductRepository;
import com.deltacapita.product_catalogue_service.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Contains TestCases for Product Catalogue
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProductCatalogueControllerTest {
    private static final String REQUEST_BODY = """
            ["Apple","Limes","Banana","Apple","Limes", "Melons", "Banana", "Limes", "Melons"]
            """;
    @Autowired
    private MockMvc mockMvc;
    @MockBean(answer = Answers.CALLS_REAL_METHODS)
    private ProductRepository productRepository;
    @SpyBean
    private ProductService productService;

    @Test
    @DisplayName("GIVEN a valid product list received THEN total cost would be returned")
    void givenValidProductsTOCalculatePricingResponseBodyWithTotalCost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/catalogue/calculate")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(REQUEST_BODY))
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                        MockMvcResultMatchers.jsonPath("$.totalCost").value(190));
    }

}