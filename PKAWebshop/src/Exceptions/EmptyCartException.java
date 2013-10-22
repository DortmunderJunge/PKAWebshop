package Exceptions;

public class EmptyCartException extends Exception {

	private static final long serialVersionUID = -5393395663174818827L;
	
	public EmptyCartException(String message) {
		super(message);
	}
	
	public EmptyCartException() {
		super("Your Cart contains no Items!");
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
