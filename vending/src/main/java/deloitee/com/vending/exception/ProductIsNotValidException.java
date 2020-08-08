package deloitee.com.vending.exception;

public class ProductIsNotValidException extends Exception {

	private static final long serialVersionUID = 4L;
	private String message;

	public ProductIsNotValidException() {
		super();
	}

	public ProductIsNotValidException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
