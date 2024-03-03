package com.cloudruid.cloudruid.service.impl;

import com.cloudruid.cloudruid.exception.ProductNotSupportedByTheSystemException;
import com.cloudruid.cloudruid.model.Product;
import com.cloudruid.cloudruid.service.DiscountService;
import com.cloudruid.cloudruid.service.ProductService;
import com.cloudruid.cloudruid.service.ScannerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ScannerServiceImpl implements ScannerService {
    private static final Logger logger = LoggerFactory.getLogger(ScannerServiceImpl.class);
    private final DiscountService discountService;
    private final ProductService productService;



    // Scan the products with discount
    @Override
    public int scanWithDiscount(List<Product> inputProducts) {
            List<Product> supportedProductsByTheSystem = productService.fetchAllProducts();
            isProductSupportedByTheSystem(inputProducts, supportedProductsByTheSystem);
            return 0;
    }

    // Scan the products without discount
    @Override
    public String scanWithoutDiscount(List<Product> inputProducts) {
        // Check if the products are supported by the system
        List<Product> supportedProductsByTheSystem = productService.fetchAllProducts();
        logger.info("Supported products by the system: " + supportedProductsByTheSystem);
        isProductSupportedByTheSystem(inputProducts, supportedProductsByTheSystem);
        return formatCost(sumOfProductsWithoutDiscount(inputProducts));
    }

    @Override
    public String scanWith3For2Discount(List<Product> inputProducts) {
        // Check if the products are supported by the system
        List<Product> supportedProductsByTheSystem = productService.fetchAllProducts();
        isProductSupportedByTheSystem(inputProducts, supportedProductsByTheSystem);
        if(inputProducts.size() < 3){
            return formatCost(sumOfProductsWithoutDiscount(inputProducts)) + " (for this discount you need at least 3 products)";
        }
        double cost = sumOfProductsWithoutDiscount(inputProducts);
        logger.info("Cost before 3 for 2 discount: " + cost);
        cost -= discountService.calculate3For2Discount(inputProducts);
        logger.info("Cost after 3 for 2 discount: " + cost);
        return formatCost(cost);
    }

    // TODO: Implement the scanGet1SecondHalfPrice method
    @Override
    public String scanGet1SecondHalfPrice(List<Product> inputProducts) {
        return null;
    }

    // Format the cost method
    private String formatCost(double cost) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.ENGLISH);
        symbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#.##", symbols);
        String formattedCost = decimalFormat.format(cost);
        return formattedCost.replace(".", " aws and ") + " clouds";
    }


    // Calculate the sum of the products method
    private double sumOfProductsWithoutDiscount(List<Product> inputProducts) {
        double sum = 0;
        for (Product inputProduct : inputProducts) {
            sum += inputProduct.getCloud();
        }
        return sum/100;
    }


    // Check if the products are supported by the system method
    private void isProductSupportedByTheSystem(List<Product> inputProducts, List<Product> supportedProductsByTheSystem) {

        for (Product inputProduct : inputProducts) {
            boolean isSupported = false;

            for (Product supportedProduct : supportedProductsByTheSystem) {

                if (inputProduct.getName().equalsIgnoreCase(supportedProduct.getName())) {
                    isSupported = true;
                    break;
                }
            }
            if (!isSupported) {
                throw new ProductNotSupportedByTheSystemException("Product '" + inputProduct.getName() + "' is not supported by the system.");
            }
        }

    }
}
