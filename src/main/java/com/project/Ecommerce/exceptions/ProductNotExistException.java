package com.project.Ecommerce.exceptions;

public class ProductNotExistException extends IllegalArgumentException {
	public ProductNotExistException(String msg) {
        super(msg);
    }
}
