package com.project.Ecommerce.exceptions;

public class AuthenticationFailException extends IllegalArgumentException {
	public AuthenticationFailException(String msg) {
        super(msg);
    }
}

