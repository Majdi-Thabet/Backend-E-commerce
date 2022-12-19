package com.project.Ecommerce.exceptions;

public class OrderNotFoundException extends IllegalArgumentException  {
	public OrderNotFoundException(String msg) {
        super(msg);
    }

}
