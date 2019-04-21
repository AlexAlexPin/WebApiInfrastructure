package com.pinin.alex.service.exceptions;

public class AccountNotFoundException extends ApiException {
    public AccountNotFoundException(String accountId) {
        super(String.format("Account login='%s' not found", accountId));
    }
}
