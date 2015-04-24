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

/**
 * @author Thiago Carvalho
 * 
 */
@Entity
@Table(name = "unidade_reserva")
public class UnidadeReserva implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "unidade_id", referencedColumnName = "id")
	private Unidade unidade;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reserva_id", referencedColumnName = "id")
	private Reserva reserva;
	private Calendar data;
	private Calendar hora;
	
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
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
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
	
}
