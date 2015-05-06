package br.com.onlares.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.onlares.controller.UsuarioLogado;
import br.com.onlares.model.LocalizadorDoUsuarioLogado;
import br.com.onlares.model.Reserva;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class ReservaDao {

	private final EntityManager em;
	private final Long condominioId;
	
	@Inject
	public ReservaDao(EntityManager em, UsuarioLogado usuarioLogado) {
		this.em = em;
		this.condominioId = LocalizadorDoUsuarioLogado.getCondominioIdAtual(usuarioLogado);
	}
	
	@Deprecated
	public ReservaDao() {
		this(null, null); // para uso do CDI
	}
	
	public List<Reserva> listaReserva(Long espacoId) {
		List<Reserva> unidadeReservas = em.createQuery("SELECT r FROM Reserva r"
				+ " where r.espaco.id = :espacoId", Reserva.class)
				.setParameter("espacoId", espacoId).getResultList();
		return unidadeReservas;
	}
	
	public void reserva(Reserva reserva) {
		em.persist(reserva);
	}
	
	public Reserva buscaReserva(Long reservaId) {
		Reserva reserva;
		String strQuery = "SELECT r FROM Reserva r"
				+ " WHERE r.id = :reservaId"
				+ " AND r.espaco.condominio.id = :condominioId";
		try {
			Query query = em.createQuery(strQuery, Reserva.class);
			query.setParameter("reservaId", reservaId);
			query.setParameter("condominioId", condominioId);
			reserva = (Reserva) query.getSingleResult();
		} catch (NoResultException nrExp) {
			reserva = null;
		}
		return reserva;
	}
	
	public List<Reserva> listaDaReserva(Long espacoId) {
		List<Reserva> unidadeReserva;
		Calendar hoje = Calendar.getInstance();
		try {
			unidadeReserva = em.createQuery("select r from Reserva r"
					+ " where r.espaco.id = :espacoId"
					+ " and r.data >= :hoje", Reserva.class)
					.setParameter("espacoId", espacoId)
					.setParameter("hoje", hoje).getResultList();
		} catch (EntityNotFoundException e) {
			unidadeReserva = new ArrayList<Reserva>();
		}
		return unidadeReserva;
	}
	
	public List<Reserva> listaDaUnidade(Long unidadeId) {
		List<Reserva> reservas;
		try {
			reservas = em.createQuery("select r from Reserva r"
					+ " where r.unidade.id = :unidadeId", Reserva.class)
					.setParameter("unidadeId", unidadeId).getResultList();
		} catch (EntityNotFoundException e) {
			reservas = new ArrayList<Reserva>();
		}
		return reservas;
	}
	
	public List<Reserva> listaDoCondominio() {
		List<Reserva> reservas;
		try {
			reservas = em.createQuery("select r from Reserva r"
					+ " where r.espaco.condominio.id = :condominioId", Reserva.class)
					.setParameter("condominioId", condominioId).getResultList();
		} catch (EntityNotFoundException e) {
			reservas = new ArrayList<Reserva>();
		}
		return reservas;
	}
	
	public void removeReserva(Reserva unidadeReserva) {
		em.remove(unidadeReserva);
	}

}
