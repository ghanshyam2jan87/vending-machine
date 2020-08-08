package deloitee.com.vending.exception;

public class InsufficientMoneyException extends Exception {

	private static final long serialVersionUID = 2L;
	private String message;

	public InsufficientMoneyException() {
		super();
	}

	public InsufficientMoneyException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
