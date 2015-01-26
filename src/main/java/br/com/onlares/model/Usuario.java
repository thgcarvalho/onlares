package br.com.onlares.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Thiago Carvalho
 * 
 */
@Entity
@Table(name = "usuario")
public class Usuario {

	@Id @GeneratedValue
	private Long id;
	
	private String email;
	private String senha;
	private String nome;
	@Column(name="alertas_por_email")
	private boolean alertasPorEmail;
	@OneToOne
	private Condominio condominio;

	public Usuario() {
		
	}
	
	public Usuario(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean isAlertasPorEmail() {
		return alertasPorEmail;
	}
	
	public void setAlertasPorEmail(boolean alertasPorEmail) {
		this.alertasPorEmail = alertasPorEmail;
	}
	
	public Condominio getCondominio() {
		return condominio;
	}
	
	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}

}