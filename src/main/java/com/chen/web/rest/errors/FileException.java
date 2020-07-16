package com.chen.web.rest.errors;

/**
 * @author chen
 */
public class FileException extends RuntimeException {
    private static final long serialVersionUID = -1568263069623857132L;

    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
