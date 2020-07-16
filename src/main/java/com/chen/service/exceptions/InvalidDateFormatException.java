package com.chen.service.exceptions;

/**
 * @author chen
 */
public class InvalidDateFormatException extends RuntimeException {
    private static final long serialVersionUID = 2182452713913460502L;

    public InvalidDateFormatException(String message) {
        super(message);
    }
}
