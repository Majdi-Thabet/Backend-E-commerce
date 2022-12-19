package com.project.Ecommerce.exceptions;

public class CartItemNotExistException extends IllegalArgumentException{
	public CartItemNotExistException(String msg) {
        super(msg);
    }

}
