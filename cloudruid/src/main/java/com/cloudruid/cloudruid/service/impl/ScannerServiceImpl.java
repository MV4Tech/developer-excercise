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

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ScannerServiceImpl implements ScannerService {
    private static final Logger logger = LoggerFactory.getLogger(ScannerServiceImpl.class);

    /**
     * The discount service
     */
    private final DiscountService discountService;

    /**
     * The product service
     */
    private final ProductService productService;



    /**
     * Scan products with all discounts
     * @param inputProducts (list of input products)
     * @return String (bill) of the scanned products formatted with aws and clouds design
     */
    @Override
    public String scanWithAllDiscount(List<Product> inputProducts) {
            List<Product> supportedProductsByTheSystem = productService.fetchAllProducts();
            isProductSupportedByTheSystem(inputProducts, supportedProductsByTheSystem);
            double cost = sumOfProductsWithoutDiscount(inputProducts);

            if (inputProducts.size() == 1){
                return formatCost(sumOfProductsWithoutDiscount(inputProducts));
            }
            if(inputProducts.size() == 2){
                BigDecimal costBigDecimal = new BigDecimal(Double.toString(cost));
                BigDecimal discountBigDecimal = new BigDecimal(Double.toString(discountService.calculate1SecondHalfPriceDiscount(inputProducts)));
                BigDecimal result = costBigDecimal.subtract(discountBigDecimal);
                return formatCost(result.doubleValue());
            }
            if(inputProducts.size() == 3){
                BigDecimal costBigDecimal = new BigDecimal(Double.toString(cost));
                BigDecimal discountBigDecimal = new BigDecimal(Double.toString(discountService.calculate3For2Discount(inputProducts)));
                BigDecimal result = costBigDecimal.subtract(discountBigDecimal);
                return formatCost(result.doubleValue());
            }

            BigDecimal costBigDecimal = new BigDecimal(Double.toString(cost));
            BigDecimal discountBigDecimal = new BigDecimal(Double.toString(discountService.calculateWithAllDiscount(inputProducts)));
            BigDecimal result = costBigDecimal.subtract(discountBigDecimal);
            logger.info("Result: " + result);
            return formatCost(result.doubleValue());
    }

    /**
     * Scan products without discount
     * @param inputProducts (list of input products)
     * @return String (bill) of the scanned products formatted with aws and clouds design
     */
    @Override
    public String scanWithoutDiscount(List<Product> inputProducts) {
        // Check if the products are supported by the system
        List<Product> supportedProductsByTheSystem = productService.fetchAllProducts();
        isProductSupportedByTheSystem(inputProducts, supportedProductsByTheSystem);
        logger.info("bill: " + formatCost(sumOfProductsWithoutDiscount(inputProducts)));
        return formatCost(sumOfProductsWithoutDiscount(inputProducts));
    }

    /**
     * Scan products with 3 for 2 discount
     * @param inputProducts  (list of input products)
     * @return String (bill) of the scanned products formatted with aws and clouds design
     */
    @Override
    public String scanWith3For2Discount(List<Product> inputProducts) {
        // Check if the products are supported by the system
        List<Product> supportedProductsByTheSystem = productService.fetchAllProducts();
        isProductSupportedByTheSystem(inputProducts, supportedProductsByTheSystem);
        if(inputProducts.size() < 3){
            return formatCost(sumOfProductsWithoutDiscount(inputProducts)) + " (for this discount you need at least 3 products)";
        }
        double cost = sumOfProductsWithoutDiscount(inputProducts);
        BigDecimal costBigDecimal = new BigDecimal(Double.toString(cost));
        logger.info("Cost: " + costBigDecimal);
        BigDecimal discountBigDecimal = new BigDecimal(Double.toString(discountService.calculate3For2Discount(inputProducts)));
        logger.info("Discount: " + discountBigDecimal);
        BigDecimal result = costBigDecimal.subtract(discountBigDecimal);
        logger.info("Result: " + result);
        return formatCost(result.doubleValue());
    }


   /**
     * Scan products with 1 second half price discount
     * @param inputProducts  (list of input products)
     * @return String (bill) of the scanned products formatted with aws and clouds design
     */
    @Override
    public String scanGet1SecondHalfPrice(List<Product> inputProducts) {
        // Check if the products are supported by the system
        List<Product> supportedProductsByTheSystem = productService.fetchAllProducts();
        isProductSupportedByTheSystem(inputProducts, supportedProductsByTheSystem);
        if(inputProducts.size() < 2){
            return formatCost(sumOfProductsWithoutDiscount(inputProducts)) + " (for this discount you need at least 2 products)";
        }
        double cost = sumOfProductsWithoutDiscount(inputProducts);
        BigDecimal costBigDecimal = new BigDecimal(Double.toString(cost));
        BigDecimal discountBigDecimal = new BigDecimal(Double.toString(discountService.calculate1SecondHalfPriceDiscount(inputProducts)));
        BigDecimal result = costBigDecimal.subtract(discountBigDecimal);
        return formatCost(result.doubleValue());
    }

    /**
     * Format the cost with aws and clouds design
     * @param cost
     * @return String formatted cost
     */
    private String formatCost(double cost) {
        int decimalPlaces = countDecimalPlaces(cost);
        if (decimalPlaces == 2) {
            logger.info("Iam here at decimalPlaces ==2  ");
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.ENGLISH);
            symbols.setDecimalSeparator('.');
            DecimalFormat decimalFormat = new DecimalFormat("#.##", symbols);
            String formattedCost = decimalFormat.format(cost);
            return formattedCost.replace(".", " aws and ") + " clouds";

        } else if (decimalPlaces == 1) {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.ENGLISH);
            symbols.setDecimalSeparator('.');
            DecimalFormat decimalFormat = new DecimalFormat("#.##", symbols);
            String formattedCost = decimalFormat.format(cost);
            logger.info("Iam here at decimalPlaces ==1 ");
            // Split the formattedCost into integer and fractional parts
            String[] parts = formattedCost.split("\\.");
            if(parts.length != 2){
                logger.info("Iam here at length!=2 ");
                int integerPart = Integer.parseInt(parts[0]);
                return integerPart + " aws and " + 00 + " clouds";
            }
            int integerPart = Integer.parseInt(parts[0]);
            int fractionalPart = Integer.parseInt(parts[1]);

            return integerPart + " aws and " + (fractionalPart * 10) + " clouds";
        } else {
            logger.info("I am at else ");
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.ENGLISH);
            symbols.setDecimalSeparator('.');
            DecimalFormat decimalFormat = new DecimalFormat("#.##", symbols);
            String formattedCost = decimalFormat.format(cost);
            return formattedCost.replace(".", " aws and ") + " clouds";
        }
    }

    /**
     * Count the decimal places of the number
     * method made to format the cost with aws and clouds design
     * @param number
     * @return int decimal places
     */
    private int countDecimalPlaces(double number) {
        String[] parts = String.valueOf(number).split("\\.");
        if (parts.length == 2) {
            return parts[1].length();
        } else {
            return 0;
        }
    }


    /**
     * Sum of products without discount
     * @param inputProducts (list of input products)
     * @return double sum of products without discount
     */
    public double sumOfProductsWithoutDiscount(List<Product> inputProducts) {
        double sum = 0;
        for (Product inputProduct : inputProducts) {
            sum += inputProduct.getCloud();
        }

        return sum/100;
    }


    /**
     * Check if the products are supported by the system
     * @param inputProducts (list of input products)
     * @param supportedProductsByTheSystem (list of supported products by the system)
     * if the input product is not supported by the system
     * Throw ProductNotSupportedByTheSystemException
     */
    public void isProductSupportedByTheSystem(List<Product> inputProducts, List<Product> supportedProductsByTheSystem) {

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
