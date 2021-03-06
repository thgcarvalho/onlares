package br.com.onlares.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Entity
@Table(name = "anuncio")
public class Anuncio implements Comparable<Anuncio>, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String atividade;
	private String titulo;
	private String descricao;
	private String anunciante;
	private String endereco;
	private String fone1;
	private String fone2;
	private String email;
	private String site;
	@Column(name="condominio_id")
	private Long condominioId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAtividade() {
		return atividade;
	}
	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getAnunciante() {
		return anunciante;
	}
	public void setAnunciante(String anunciante) {
		this.anunciante = anunciante;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getFone1() {
		return fone1;
	}
	public void setFone1(String fone1) {
		this.fone1 = fone1;
	}
	public String getFone2() {
		return fone2;
	}
	public void setFone2(String fone2) {
		this.fone2 = fone2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public Long getCondominioId() {
		return condominioId;
	}
	public void setCondominioId(Long condominioId) {
		this.condominioId = condominioId;
	}
	
	public String getFones() {
		String fones = "";
		if (fone1 != null && !fone1.equals("")) {
			fones += fone1;
			if (fone2 != null && !fone2.equals("")) {
				fones += " / ";
				fones += fone2;
			}
		} else if (fone2 != null && !fone2.equals("")) {
			fones += fone2;
		}
		return fones;
	}
	
	@Override
	public String toString() {
		return "atvd=" + atividade + " titl=" + titulo;
	}
	@Override
	public int compareTo(Anuncio outroAnuncio) {
        int atvd = this.atividade.compareTo(outroAnuncio.atividade);
        return atvd == 0 ? this.titulo.compareTo(outroAnuncio.titulo) : atvd;
	}
	
}
