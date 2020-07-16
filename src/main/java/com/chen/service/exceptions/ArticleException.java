package com.chen.service.exceptions;

/**
 * @author chen
 */
public class ArticleException extends RuntimeException {
    private static final long serialVersionUID = 3657708468460277844L;

    public ArticleException(String message) {
        super(message);
    }
}
