package com.thewhite.utilitystorage.api;

import com.thewhite.utilitystorage.exception.BadInputDataForRating;
import com.thewhite.utilitystorage.exception.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public @ResponseBody ErrorDto processConstraintViolationException(ConstraintViolationException exception) {
        return ErrorDto.of(exception.getMessage());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public @ResponseBody ErrorDto processNotFoundException(NotFoundException exception) {
        return ErrorDto.of(exception.getMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(BadInputDataForRating.class)
    public @ResponseBody ErrorDto processBadInputDataForRating(BadInputDataForRating exception) {
        return ErrorDto.of(exception.getMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public @ResponseBody ErrorDto processHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return ErrorDto.of(exception.getMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody ErrorDto processMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        System.out.println(exception.getMessage());
        return ErrorDto.of(Objects.requireNonNull(exception.getFieldError()).getDefaultMessage());
    }

}
