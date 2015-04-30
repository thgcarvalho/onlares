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
import br.com.onlares.model.Condominio;
import br.com.onlares.model.Constantes;
import br.com.onlares.model.Espaco;
import br.com.onlares.model.Reserva;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class EspacoDao {

	private final EntityManager em;
	private final Long condominioId;
	
	@Inject
	public EspacoDao(EntityManager em, UsuarioLogado usuarioLogado) {
		this.em = em;
		if (usuarioLogado != null && usuarioLogado.getUsuario() != null
				&& usuarioLogado.getLocalizadorAtual().getCondominio() != null) {
			this.condominioId = usuarioLogado.getLocalizadorAtual().getCondominio().getId();
		} else {
			this.condominioId = Constantes.CONDOMINIO_INEXISTENTE_ID;
		}
	}
	
	@Deprecated
	public EspacoDao() {
		this(null, null); // para uso do CDI
	}
	
	public List<Espaco> lista() {
		List<Espaco> reservas = em.createQuery("SELECT e FROM Espaco e"
				+ " where e.condominio.id = :condominioId", Espaco.class)
				.setParameter("condominioId", condominioId).getResultList();
		return reservas;
	}
	
	private List<Reserva> listaReserva(Long espacoId) {
		List<Reserva> unidadeReservas = em.createQuery("SELECT r FROM Reserva r"
				+ " where r.espaco.id = :espacoId", Reserva.class)
				.setParameter("espacoId", espacoId).getResultList();
		return unidadeReservas;
	}
	
	public void adiciona(Espaco espaco) {
		Condominio condominio = new Condominio();
		condominio.setId(condominioId);
		espaco.setCondominio(condominio);
		em.persist(espaco);
	}
	
	public void altera(Espaco espaco) {
		Condominio condominio = new Condominio();
		condominio.setId(condominioId);
		espaco.setCondominio(condominio);
		em.merge(espaco);
	}
	
	public Espaco buscaEspaco(Long espacoId) {
		Espaco espaco;
		String strQuery = "SELECT e FROM Espaco e"
				+ " WHERE e.id = :espacoId"
				+ " AND e.condominio.id = :condominioId";
		try {
			Query query = em.createQuery(strQuery, Espaco.class);
			query.setParameter("espacoId", espacoId);
			query.setParameter("condominioId", condominioId);
			espaco = (Espaco) query.getSingleResult();
		} catch (NoResultException nrExp) {
			espaco = null;
		}
		return espaco;
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
	
	public void removeEspaco(Espaco espaco) {
		List<Reserva> reservas = listaReserva(espaco.getId());
		for (Reserva reserva : reservas) {
			em.remove(reserva);
		}
		em.remove(buscaEspaco(espaco.getId()));
	}

}
