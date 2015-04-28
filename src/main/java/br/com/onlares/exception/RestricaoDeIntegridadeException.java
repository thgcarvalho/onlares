package br.com.onlares.exception;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
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
