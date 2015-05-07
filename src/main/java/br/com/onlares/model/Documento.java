package br.com.onlares.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Entity
@Table(name = "documento")
public class Documento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "condominio_id", referencedColumnName = "id")
	private Condominio condominio;
	private String titulo;
	private String nome;
	private byte[] conteudo;
	@Column(name="content_type")
	private String contentType;
	@Column(name="data_modificacao")
	private Calendar dataModificacao;
	
	public Documento(String nome, byte[] conteudo, String contentType, Calendar dataModificacao) {
		this.nome = nome;
		this.conteudo = conteudo;
		this.contentType = contentType;
		this.dataModificacao = dataModificacao;
	}
	
	// para a JPA não reclamar
	public Documento() {}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Condominio getCondominio() {
		return condominio;
	}
	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public byte[] getConteudo() {
		return conteudo;
	}
	public void setConteudo(byte[] conteudo) {
		this.conteudo = conteudo;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Calendar getDataModificacao() {
		return dataModificacao;
	}
	public void setDataModificacao(Calendar dataModificacao) {
		this.dataModificacao = dataModificacao;
	}
	
	@Override
	public String toString() {
		return "docm= " + id + " cond=" + condominio;
	}

}
