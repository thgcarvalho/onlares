package br.com.onlares.dao;

import java.util.ArrayList;
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
	
	public Reserva busca(Reserva reserva) {
		return em.find(Reserva.class, reserva.getId());
	}

	public List<Reserva> lista() {
		List<Reserva> reservas = em.createQuery("SELECT v FROM Reserva v"
				+ " where v.condominio.id = :condominioId", Reserva.class)
				.setParameter("condominioId", condominioId).getResultList();
		return reservas;
	}
	
	public void adiciona(Reserva reserva) {
		Condominio condominio = new Condominio();
		condominio.setId(condominioId);
		reserva.setCondominio(condominio);
		em.persist(reserva);
	}
	
	public void altera(Reserva reserva) {
		em.merge(reserva);
	}
	
	public Reserva busca(Long reservaId) {
		Reserva reserva;
		String strQuery = "SELECT v FROM Reserva v"
				+ " WHERE v.id = :reservaId"
				+ " AND v.condominio.id = :condominioId";
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

	@SuppressWarnings("unused")
	private Reserva buscaNaUnidade(Long unidadeId, Long reservaId) {
		Reserva reserva;
		String strQuery = "SELECT v FROM Reserva v"
				+ " WHERE v.unidade.id = :unidadeId"
				+ " AND v.id = :reservaId";
		try {
			Query query = em.createQuery(strQuery, Reserva.class);
			query.setParameter("unidadeId", unidadeId);
			query.setParameter("reservaId", reservaId);
			reserva = (Reserva) query.getSingleResult();
		} catch (NoResultException nrExp) {
			reserva = null;
		}
		return reserva;
	}
	
	public List<Reserva> listaDaUnidade(Long unidadeId) {
		List<Reserva> reservas;
		try {
			reservas = em.createQuery("select v from Reserva v"
					+ " where v.unidade.id = :unidadeId", Reserva.class)
					.setParameter("unidadeId", unidadeId).getResultList();
		} catch (EntityNotFoundException e) {
			reservas = new ArrayList<Reserva>();
		}
		return reservas;
	}
	
	public void remove(Reserva reserva) {
		em.remove(busca(reserva));
	}

}
