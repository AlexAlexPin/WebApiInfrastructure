package com.pinin.alex.service.exceptions;

public class OutdatedTimestampException extends ApiException {
    public OutdatedTimestampException(String message) {
        super(message);
    }
}
