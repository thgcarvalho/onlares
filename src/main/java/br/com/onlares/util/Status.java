package br.com.onlares.util;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public enum Status {

	PENDENTE("0"), ALTERADO("1"), FALHA("2");
	String status;

	private Status(String status) {
		this.status = status;
	}

	public String getCodigo() {
		return status;
	}
}
