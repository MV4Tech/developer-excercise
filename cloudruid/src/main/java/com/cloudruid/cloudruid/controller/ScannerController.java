package com.cloudruid.cloudruid.controller;

import com.cloudruid.cloudruid.model.Product;
import com.cloudruid.cloudruid.service.ScannerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/scanner")
public class ScannerController {

    private final ScannerService scannerService;

    /**
     * Scan products with all discounts
     * @param inputProducts
     * @return String (bill) of the scanned products
     */
    @Operation(
            description = "Scan products with all discounts",
            summary = "This is a summary for scanning products with all discounts"
    )
    @PostMapping("/scan-with-all-discount")
    public String scanWithAllDiscount(@RequestBody List<Product> inputProducts){
        return scannerService.scanWithAllDiscount(inputProducts);
    }


    /**
     * Scan products with 3 for 2 discount
     * @param inputProducts
     * @return String (bill) of the scanned products
     */
    @Operation(
            description = "Scan products with 3 for 2 discount",
            summary = "This is a summary for scanning products with 3 for 2 discount"
    )
    @PostMapping("scan-with-3for2-discount")
    public String scanWith3For2Discount(@RequestBody List<Product> inputProducts){
        return scannerService.scanWith3For2Discount(inputProducts);
    }

   /**
     * Scan products with 1 second half price discount
     * @param inputProducts
     * @return String (bill) of the scanned products
     */
   @Operation(
           description = "Scan products with 1 second half price discount",
           summary = "This is a summary for scanning products with 1 second half price discount"
   )
    @PostMapping("scan-get-1-second-half-price")
    public String scanGet1SecondHalfPrice(@RequestBody List<Product> inputProducts){
        return scannerService.scanGet1SecondHalfPrice(inputProducts);
    }


    /**
     * Scan products without discount
     * @param inputProducts
     * @return String (bill) of the scanned products
     */
    @Operation(
            description = "Scan products without discount",
            summary = "This is a summary for scanning products without discount"
    )
    @PostMapping("/scan-without-discount")
    public String scanWithoutDiscount(@RequestBody List<Product> inputProducts){
        return scannerService.scanWithoutDiscount(inputProducts);
    }




}
