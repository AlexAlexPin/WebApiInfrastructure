package com.pinin.alex.service.exceptions;

import lombok.Getter;

@Getter
public class AccountCreationException extends ApiException {

    public AccountCreationException(String login, String message, Exception e) {
        super(String.format("Can not create account login='%s', error: %s", login, message), e);
    }
}
