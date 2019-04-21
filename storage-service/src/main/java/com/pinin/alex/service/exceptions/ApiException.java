package com.pinin.alex.service.exceptions;

import lombok.Getter;

public abstract class ApiException extends RuntimeException {

    @Getter
    private String apiMessage;

    ApiException(String apiMessage) {
        this.apiMessage = apiMessage;
    }

    ApiException(String apiMessage, Exception e) {
        super(e);
        this.apiMessage = apiMessage;
    }
}

