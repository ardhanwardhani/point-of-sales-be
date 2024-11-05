package com.learn.point_of_sales_be.exception.customException;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}