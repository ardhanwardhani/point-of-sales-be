package com.learn.point_of_sales_be.exception.customException;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
