package br.com.onlares.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Entity
@Table(name = "localizador")
public class Localizador implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private Usuario usuario;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "condominio_id", referencedColumnName = "id", nullable = true)
	private Condominio condominio;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "unidade_id", referencedColumnName = "id", nullable = true)
	private Unidade unidade;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Condominio getCondominio() {
		return condominio;
	}
	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}
	public Unidade getUnidade() {
		if (unidade == null) {
			unidade = new UnidadeNaoRelacionada();
		}
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	
	@Override
	public String toString() {
		return "cond=" + getCondominio() + " user=" + getUsuario() + " unid=" + getUnidade();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((condominio == null) ? 0 : condominio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Localizador other = (Localizador) obj;
		if (usuario == null) {
			if (other.usuario != null) {
				return false;
			}
		} else if (usuario.getId().compareTo(other.usuario.getId()) != 0) {
			return false;
		}
		if (condominio == null) {
			if (other.condominio != null) {
				return false;
			}
		} else if (condominio.getId().compareTo(other.condominio.getId()) != 0) {
			return false;
		}
		if (unidade == null) {
			if (other.unidade != null) {
				return false;
			}
		} else if (unidade.getId().compareTo(other.unidade.getId()) != 0) {
			return false;
		}
		return true;
	}
	
}