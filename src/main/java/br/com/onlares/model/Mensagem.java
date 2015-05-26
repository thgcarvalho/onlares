package br.com.onlares.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.onlares.util.DataUtil;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Entity
@Table(name = "mensagem")
public class Mensagem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private Usuario usuario;
	private String assunto;
	private String texto;
	private Calendar data;
	private Calendar hora;
	private String status;
	@Transient
	private boolean visualizado;

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
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public Calendar getHora() {
		return hora;
	}
	public void setHora(Calendar hora) {
		this.hora = hora;
	}
	public boolean isVisualizado() {
		return visualizado;
	}
	public void setVisualizado(boolean visualizado) {
		this.visualizado = visualizado;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Transient
	public String getDataFormatada() {
		return DataUtil.formatarData(this.data);
	}
	
	@Transient
	public String getHoraFormatada() {
		return DataUtil.formatarHora(this.hora);
	}

	@Override
	public String toString() {
		return "msg= " + id + " assnt=" + assunto;
	}

}
