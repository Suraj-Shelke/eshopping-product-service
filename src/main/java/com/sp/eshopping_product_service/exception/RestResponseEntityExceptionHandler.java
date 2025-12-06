package com.sp.eshopping_product_service.exception;

import com.sp.eshopping_product_service.payload.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(ProductServiceCustomException.class)
    public ResponseEntity<ErrorResponse> handleProductServiceException(ProductServiceCustomException exception){

        ErrorResponse error=new ErrorResponse(exception.getMessage(),exception.getErrorCode());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
