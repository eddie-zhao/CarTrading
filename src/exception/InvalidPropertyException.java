package exception;

public class InvalidPropertyException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	/* Constructors */
	public InvalidPropertyException() {
	}
	public InvalidPropertyException(String fieldName) {
		super("Invalid property: " + fieldName);
	}
	
	/* field */
	private String fieldName;
	public String getFieldName() {
		return fieldName;
	}
}

