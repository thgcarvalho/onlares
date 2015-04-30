package br.com.onlares.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Entity
@Table(name = "autorizacao")
public class Autorizacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "unidade_id", referencedColumnName = "id")
	private Unidade unidade;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tipo_de_autorizacao_id", referencedColumnName = "id")
	private TipoDeAutorizacao tipoDeAutorizacao;
	private Calendar data;
	private Calendar hora;
	@Transient
	private boolean podeCancelar;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public TipoDeAutorizacao getTipoDeAutorizacao() {
		return tipoDeAutorizacao;
	}
	public void setTipoDeAutorizacao(TipoDeAutorizacao tipoDeAutorizacao) {
		this.tipoDeAutorizacao = tipoDeAutorizacao;
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
	public boolean isPodeCancelar() {
		return podeCancelar;
	}
	public void setPodeCancelar(boolean podeCancelar) {
		this.podeCancelar = podeCancelar;
	}
	
	@Transient
	public void setHoraString(String hora) {
		Calendar calendar = GregorianCalendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		try {
			calendar.setTime(sdf.parse(hora));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.hora = calendar;
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
		return "rsrv= " + id + " tipoautz= " + tipoDeAutorizacao;
	}
}
