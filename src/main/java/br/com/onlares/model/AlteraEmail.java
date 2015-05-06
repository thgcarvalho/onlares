package br.com.onlares.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Entity
@Table(name = "altera_email")
public class AlteraEmail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String codigo;
	@Column(name="email_antigo")
	private String emailAntigo;
	@Column(name="email_novo")
	private String emailNovo;
	private String status;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getEmailAntigo() {
		return emailAntigo;
	}
	public void setEmailAntigo(String emailAntigo) {
		this.emailAntigo = emailAntigo;
	}
	public String getEmailNovo() {
		return emailNovo;
	}
	public void setEmailNovo(String emailNovo) {
		this.emailNovo = emailNovo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
