package com.chen.service.exceptions;

/**
 * @author chen
 */
public class AccountNotExistException extends RuntimeException {
    private static final long serialVersionUID = -1056488682262460362L;

    public AccountNotExistException(final String message) {
        super(message);
    }
}
