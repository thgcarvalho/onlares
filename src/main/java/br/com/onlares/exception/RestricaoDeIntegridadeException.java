package br.com.onlares.exception;

public class RestricaoDeIntegridadeException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;

	public RestricaoDeIntegridadeException(String message) {
		super(message, new Exception("Restrição de integridade. " + message));
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
