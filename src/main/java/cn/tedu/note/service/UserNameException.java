package cn.tedu.note.service;

public class UserNameException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNameException() {
	}

	public UserNameException(String message) {
		super(message);
	}

	public UserNameException(Throwable cause) {
		super(cause);
	}

	public UserNameException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
