package com.chen.service.exceptions;

/**
 * @author chen
 */
public class ActivityException extends RuntimeException {
    private static final long serialVersionUID = 3657708468460277844L;

    public ActivityException(String message) {
        super(message);
    }
}
