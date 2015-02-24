package br.com.onlares.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Thiago Carvalho
 * 
 */
@Entity
@Table(name = "unidade")
public class Unidade implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String localizacao;
	@Column(name="condominio_id")
	private Long condominioId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = (localizacao != null ? localizacao.toUpperCase() : null);
	}
	public Long getCondominioId() {
		return condominioId;
	}
	public void setCondominioId(Long condominioId) {
		this.condominioId = condominioId;
	}
	
}
