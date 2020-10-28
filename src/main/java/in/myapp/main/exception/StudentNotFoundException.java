package in.myapp.main.exception;

/**
 * Exception for handling data not found condition.
 * 
 * @author PranaySK
 */

public class StudentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public StudentNotFoundException(String message) {
		super(message);
	}

}
