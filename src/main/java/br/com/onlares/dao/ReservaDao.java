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
import br.com.onlares.model.Reserva;
import br.com.onlares.model.UnidadeReserva;

public class ReservaDao {

	private final EntityManager em;
	private final Long condominioId;
	
	@Inject
	public ReservaDao(EntityManager em, UsuarioLogado usuarioLogado) {
		this.em = em;
		if (usuarioLogado != null && usuarioLogado.getUsuario() != null
				&& usuarioLogado.getLocalizadorAtual().getCondominio() != null) {
			this.condominioId = usuarioLogado.getLocalizadorAtual().getCondominio().getId();
		} else {
			this.condominioId = Constantes.CONDOMINIO_INEXISTENTE_ID;
		}
	}
	
	@Deprecated
	public ReservaDao() {
		this(null, null); // para uso do CDI
	}
	
	public List<Reserva> lista() {
		List<Reserva> reservas = em.createQuery("SELECT r FROM Reserva r"
				+ " where r.condominio.id = :condominioId", Reserva.class)
				.setParameter("condominioId", condominioId).getResultList();
		return reservas;
	}
	
	public List<UnidadeReserva> listaUnidadeReserva(Long reservaId) {
		List<UnidadeReserva> unidadeReservas = em.createQuery("SELECT ur FROM UnidadeReserva ur"
				+ " where ur.reserva.id = :reservaId", UnidadeReserva.class)
				.setParameter("reservaId", reservaId).getResultList();
		return unidadeReservas;
	}
	
	public void reserva(UnidadeReserva unidadeReserva) {
		em.persist(unidadeReserva);
	}
	
	public void adiciona(Reserva reserva) {
		Condominio condominio = new Condominio();
		condominio.setId(condominioId);
		reserva.setCondominio(condominio);
		em.persist(reserva);
	}
	
	public void altera(Reserva reserva) {
		Condominio condominio = new Condominio();
		condominio.setId(condominioId);
		reserva.setCondominio(condominio);
		em.merge(reserva);
	}
	
	public Reserva buscaReserva(Long reservaId) {
		Reserva reserva;
		String strQuery = "SELECT r FROM Reserva r"
				+ " WHERE r.id = :reservaId"
				+ " AND r.condominio.id = :condominioId";
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
	
	public UnidadeReserva buscaUnidadeReserva(Long unidadeReservaId) {
		UnidadeReserva unidadeReserva;
		String strQuery = "SELECT ur FROM UnidadeReserva ur"
				+ " WHERE ur.id = :unidadeReservaId"
				+ " AND ur.reserva.condominio.id = :condominioId";
		try {
			Query query = em.createQuery(strQuery, UnidadeReserva.class);
			query.setParameter("unidadeReservaId", unidadeReservaId);
			query.setParameter("condominioId", condominioId);
			unidadeReserva = (UnidadeReserva) query.getSingleResult();
		} catch (NoResultException nrExp) {
			unidadeReserva = null;
		}
		return unidadeReserva;
	}
	
	public List<UnidadeReserva> listaDaReserva(Long reservaId) {
		List<UnidadeReserva> unidadeReserva;
		Calendar hoje = Calendar.getInstance();
		try {
			unidadeReserva = em.createQuery("select ur from UnidadeReserva ur"
					+ " where ur.reserva.id = :reservaId"
					+ " and ur.data >= :hoje", UnidadeReserva.class)
					.setParameter("reservaId", reservaId)
					.setParameter("hoje", hoje).getResultList();
		} catch (EntityNotFoundException e) {
			unidadeReserva = new ArrayList<UnidadeReserva>();
		}
		return unidadeReserva;
	}
	
	public List<UnidadeReserva> listaDaUnidade(Long unidadeId) {
		List<UnidadeReserva> unidadeReserva;
		try {
			unidadeReserva = em.createQuery("select ur from UnidadeReserva ur"
					+ " where ur.unidade.id = :unidadeId", UnidadeReserva.class)
					.setParameter("unidadeId", unidadeId).getResultList();
		} catch (EntityNotFoundException e) {
			unidadeReserva = new ArrayList<UnidadeReserva>();
		}
		return unidadeReserva;
	}
	
	public void removeReserva(Reserva reserva) {
		List<UnidadeReserva> unidadesReservas = listaUnidadeReserva(reserva.getId());
		for (UnidadeReserva unidadeReserva : unidadesReservas) {
			em.remove(unidadeReserva);
		}
		em.remove(buscaReserva(reserva.getId()));
	}
	
	public void removeUnidadeReserva(UnidadeReserva unidadeReserva) {
		em.remove(buscaUnidadeReserva(unidadeReserva.getId()));
	}

}
