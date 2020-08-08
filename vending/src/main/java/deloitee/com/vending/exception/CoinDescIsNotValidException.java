package deloitee.com.vending.exception;

public class CoinDescIsNotValidException extends Exception {

	private static final long serialVersionUID = 3L;
	private String message;

	public CoinDescIsNotValidException() {
		super();
	}

	public CoinDescIsNotValidException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
