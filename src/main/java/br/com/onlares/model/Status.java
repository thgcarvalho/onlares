package br.com.onlares.model;

/**
 * @author Thiago Carvalho
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
