package br.com.onlares.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Thiago Carvalho
 * 
 */
@Entity
@Table(name = "usuario")
public class Usuario {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String senha;
	private String nome;
	private String tipo;
	@Column(name="alertas_por_email")
	private boolean alertasPorEmail;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "condominio_id", referencedColumnName = "id", insertable = true, updatable = true)
	private Condominio condominio;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "endereco_id", referencedColumnName = "id", insertable = true, updatable = true)
	private Endereco endereco;

	public Usuario() {
		
	}
	
	public Usuario(String email, String senha, String nome) {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
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
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}