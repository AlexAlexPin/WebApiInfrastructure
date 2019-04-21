package com.pinin.alex.controller;

import com.pinin.alex.dto.common.ErrorDto;
import com.pinin.alex.service.exceptions.AccountCreationException;
import com.pinin.alex.service.exceptions.ApiException;
import com.pinin.alex.service.exceptions.OutdatedTimestampException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = OutdatedTimestampException.class)
    public ResponseEntity<ErrorDto> handleApiException(OutdatedTimestampException ex) {
        return getConflictError(ex);
    }

    @ResponseBody
    @ExceptionHandler(value = AccountCreationException.class)
    public ResponseEntity<ErrorDto> handleApiException(AccountCreationException ex) {
        return getConflictError(ex);
    }

    private ResponseEntity<ErrorDto> getConflictError(ApiException ex) {
        ErrorDto body = ErrorDto.builder().message(ex.getApiMessage()).build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }
}
