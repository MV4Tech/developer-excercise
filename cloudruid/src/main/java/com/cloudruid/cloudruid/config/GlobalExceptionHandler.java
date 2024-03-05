package com.cloudruid.cloudruid.config;

import com.cloudruid.cloudruid.exception.ProductNotFoundException;
import com.cloudruid.cloudruid.exception.ProductNotSupportedByTheSystemException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * handling product not found exception
     * @param  ex
     * @return list of possible errors(set by the message) and http status not found
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, List<String>>> handleProductNotFoundException(ProductNotFoundException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }


    /**
     * handling product not supported by the system exception
     * @param  ex
     * @return list of possible errors(set by the message) and http status not found
     */
    @ExceptionHandler(ProductNotSupportedByTheSystemException.class)
    public ResponseEntity<Map<String, List<String>>> handleProductNotSupportedByTheSystemException(ProductNotSupportedByTheSystemException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }


    /**
     * handling validation errors sent by @Valid annotation
     * @param  ex
     * @return list of possible errors sent by @Valid annotation and http status bad request
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


    /**
     * method to create a map of errors
     * @param errors
     * @return map of errors
     */
    private Map<String,List<String>> getErrorsMap(List<String> errors) {
        Map<String,List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors",errors);
        return errorResponse;

    }

}
