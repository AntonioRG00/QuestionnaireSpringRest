package es.antoniorg.myspringrest.exception;

public class LoginAttemptsExceded extends RuntimeException{

	private static final long serialVersionUID = 7146426290740188758L;

	public LoginAttemptsExceded(String errorMessage) {
		super(errorMessage);
	}
}
