package com.learn.point_of_sales_be.exception;

import com.learn.point_of_sales_be.exception.customException.BadRequestException;
import com.learn.point_of_sales_be.exception.customException.ResourceNotFoundException;
import com.learn.point_of_sales_be.exception.customException.UnauthorizedException;
import org.apache.coyote.Response;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
        Map<String, Object> body = createResponseBody(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest request) {
        Map<String, Object> body = createResponseBody(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        Map<String, Object> body = createResponseBody(HttpStatus.UNAUTHORIZED, ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
        Map<String, Object> body = createResponseBody(HttpStatus.BAD_GATEWAY, "Validation Error");

        Map<String, String> fieldErrors = ex.getBindingResult()
                                            .getFieldErrors()
                                            .stream()
                                            .collect(Collectors.toMap(
                                                FieldError::getField, FieldError::getDefaultMessage
                                            ));
        body.put("errors", fieldErrors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex){
        Map<String, Object> body = createResponseBody(HttpStatus.BAD_REQUEST, "Database constraint violation");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex){
        Map<String, Object> body = createResponseBody(HttpStatus.METHOD_NOT_ALLOWED, "Request method not supported");
        return new ResponseEntity<>(body, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex){
        Map<String, Object> body = createResponseBody(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Media type not supported");
        return new ResponseEntity<>(body, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex){
        String message = String.format("Parameter '%s' is missing", ex.getParameterName());
        Map<String, Object> body = createResponseBody(HttpStatus.BAD_REQUEST, message);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request){
        Map<String, Object> body = createResponseBody(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, Object> createResponseBody(HttpStatus status, String message){
        Map<String, Object> body = new HashMap<>();
        body.put("code", status.value());
        body.put("status", status.getReasonPhrase());
        body.put("message", message);
        return body;
    }
}
