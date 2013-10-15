package de.fhdo.pka.webshop.controller;

public class EmptyCartException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5393395663174818827L;
	private String message;
	
	public EmptyCartException(String message) {
		this.message = message;
	}
	
	public EmptyCartException() {
		this.message = "Your Cart contains no Items!";
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
