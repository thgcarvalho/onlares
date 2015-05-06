package br.com.onlares.model;

import java.io.Serializable;

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
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Entity
@Table(name = "espaco")
public class Espaco implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "condominio_id", referencedColumnName = "id", nullable = true)
	private Condominio condominio;
	private String descricao;
	@Column(name="antecedencia_maxima_reservar")
	private int antecedenciaMaximaParaReservar;
	@Column(name="antecedencia_minima_reservar")
	private int antecedenciaMinimaParaReservar;
	@Column(name="antecedancia_minima_cancelar")
	private int antecedenciaMinimaParaCancelar;
	@Column(name="reservas_quantidade")
	private int reservasQuantidade;
	@Column(name="reservas_dias")
	private int reservasDias;
	@Column(name="permitir_posterior")
	private boolean permitirPosterior;
	@Column(name="permitir_sem_reserva")
	private boolean permitirSemReserva;

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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getAntecedenciaMaximaParaReservar() {
		return antecedenciaMaximaParaReservar;
	}
	public void setAntecedenciaMaximaParaReservar(int antecedenciaMaximaParaReservar) {
		this.antecedenciaMaximaParaReservar = antecedenciaMaximaParaReservar;
	}
	public int getAntecedenciaMinimaParaReservar() {
		return antecedenciaMinimaParaReservar;
	}
	public void setAntecedenciaMinimaParaReservar(int antecedenciaMinimaParaReservar) {
		this.antecedenciaMinimaParaReservar = antecedenciaMinimaParaReservar;
	}
	public int getAntecedenciaMinimaParaCancelar() {
		return antecedenciaMinimaParaCancelar;
	}
	public void setAntecedenciaMinimaParaCancelar(int antecedenciaMinimaParaCancelar) {
		this.antecedenciaMinimaParaCancelar = antecedenciaMinimaParaCancelar;
	}
	public int getReservasQuantidade() {
		return reservasQuantidade;
	}
	public void setReservasQuantidade(int reservasQuantidade) {
		this.reservasQuantidade = reservasQuantidade;
	}
	public int getReservasDias() {
		return reservasDias;
	}
	public void setReservasDias(int reservasDias) {
		this.reservasDias = reservasDias;
	}
	public boolean isPermitirPosterior() {
		return permitirPosterior;
	}
	public void setPermitirPosterior(boolean permitirPosterior) {
		this.permitirPosterior = permitirPosterior;
	}
	public boolean isPermitirSemReserva() {
		return permitirSemReserva;
	}
	public void setPermitirSemReserva(boolean permitirSemReserva) {
		this.permitirSemReserva = permitirSemReserva;
	}
	
	@Override
	public String toString() {
		return "rsrv=" + getDescricao() + " cond=" + getCondominio();
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
		Espaco other = (Espaco) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (id.compareTo(id) != 0) {
			return false;
		}
		return true;
	}
	
}