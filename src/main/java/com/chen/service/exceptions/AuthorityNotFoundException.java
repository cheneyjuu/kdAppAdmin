package com.chen.service.exceptions;

public class AuthorityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AuthorityNotFoundException() {
        super("没有找到角色");
    }

}
