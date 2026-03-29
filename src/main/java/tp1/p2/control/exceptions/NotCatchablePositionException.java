package tp1.p2.control.exceptions;

public class NotCatchablePositionException extends CommandExecuteException{

	public NotCatchablePositionException() { 
		super(); 
	} 

	public NotCatchablePositionException(String message) {
		super(message);
	}
	
	public NotCatchablePositionException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotCatchablePositionException(Throwable cause) {
		super(cause);
	}

	NotCatchablePositionException(String message, Throwable cause, boolean enableSuppression, boolean writeableStackTrace){
		super(message, cause, enableSuppression, writeableStackTrace);
	}
}
