package exception;

public class InvalidEncryptedTextException extends Exception {
	private static final long serialVersionUID = 1L;
	public InvalidEncryptedTextException(Throwable cause) {
		super(cause);
	}
	public InvalidEncryptedTextException() {
		super();
	}
}

