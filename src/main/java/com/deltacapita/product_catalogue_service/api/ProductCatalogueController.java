package com.deltacapita.product_catalogue_service.api;

import com.deltacapita.product_catalogue_service.api.dto.CatalogueTotalCost;
import com.deltacapita.product_catalogue_service.exception.EmptyProductCatalogueFound;
import com.deltacapita.product_catalogue_service.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/catalogue")
public class ProductCatalogueController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCatalogueController.class);
    private final ProductService productService;

    @Autowired
    public ProductCatalogueController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/calculate")
    @Operation(
            operationId = "calculatePricing",
            description = "Calculates pricing for given items",
            responses = {
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "200", description = "OK, with pricing calculated", content = @Content)
            }
    )
    public ResponseEntity<CatalogueTotalCost> calculatePrice(@RequestBody List<String> productNames) throws EmptyProductCatalogueFound {
        BigDecimal totalCost = productService.calculateTotalCost(productNames);
        LOGGER.info("Total Cost for product {}", totalCost);
        return ResponseEntity.ok(new CatalogueTotalCost(totalCost));
    }
}
