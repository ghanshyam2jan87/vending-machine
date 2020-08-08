package deloitee.com.vending.exception;

public class ProductNotAvailableException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;

	public ProductNotAvailableException() {
		super();
	}

	public ProductNotAvailableException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
