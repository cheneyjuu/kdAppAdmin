package com.chen.service.exceptions;

/**
 * @author chen
 */
public class CommonException extends RuntimeException {
    private static final long serialVersionUID = 3657708468460277844L;

    public CommonException(String message) {
        super(message);
    }
}
