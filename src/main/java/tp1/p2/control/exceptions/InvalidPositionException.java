package tp1.p2.control.exceptions;

public class InvalidPositionException extends CommandParseException {

	public InvalidPositionException() { 
		super(); 
	} 

	public InvalidPositionException(String message) {
		super(message);
	}
	
	public InvalidPositionException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidPositionException(Throwable cause) {
		super(cause);
	}

	InvalidPositionException(String message, Throwable cause, boolean enableSuppression, boolean writeableStackTrace){
		super(message, cause, enableSuppression, writeableStackTrace);
	}
}
