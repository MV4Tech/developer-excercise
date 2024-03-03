package com.cloudruid.cloudruid.controller;

import com.cloudruid.cloudruid.model.Product;
import com.cloudruid.cloudruid.service.ScannerService;
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

    // controller for scanning products with discount (take 3 pay 2 and take 1 for second half price)
    @GetMapping("/scan-with-discount")
    public int scanWithDiscount(@RequestBody List<Product> inputProducts){
        return scannerService.scanWithDiscount(inputProducts);
    }

    // controller for scanning products with 3 for 2 discount only
    @GetMapping("scan-with-3for2-discount")
    public String scanWith3For2Discount(@RequestBody List<Product> inputProducts){
        return scannerService.scanWith3For2Discount(inputProducts);
    }

    // controller for scanning products with 1 second half price discount only
    @GetMapping("scan-get-1-second-half-price")
    public String scanGet1SecondHalfPrice(@RequestBody List<Product> inputProducts){
        return scannerService.scanGet1SecondHalfPrice(inputProducts);
    }

    // controller for scanning products without discount
    @GetMapping("/scan-without-discount")
    public String scanWithoutDiscount(@RequestBody List<Product> inputProducts){
        return scannerService.scanWithoutDiscount(inputProducts);
    }




}
