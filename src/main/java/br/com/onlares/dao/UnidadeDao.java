package br.com.onlares.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.controller.UsuarioLogado;
import br.com.onlares.exception.RestricaoDeIntegridadeException;
import br.com.onlares.model.Unidade;

public class UnidadeDao {

	private final EntityManager em;
	private final Long condominioId;
	
	@Inject
	public UnidadeDao(EntityManager em, UsuarioLogado usuarioLogado) {
		this.em = em;
		this.condominioId = usuarioLogado.getUsuario().getCondominio().getId();
	}
	
	@Deprecated
	public UnidadeDao() {
		this(null, null); // para uso do CDI
	}
	
	public void adiciona(Unidade unidade) {
		unidade.setCondominioId(condominioId);
		em.getTransaction().begin();
		em.persist(unidade);
		em.getTransaction().commit();
	}
		
	public void altera(Unidade unidade) {
		if (mesmoCondominio(unidade)) {
			em.getTransaction().begin();
			em.merge(unidade);
			em.getTransaction().commit();
		}
	}

	public void remove(Unidade unidade) {
		if (mesmoCondominio(unidade)) {
			em.getTransaction().begin();
			em.remove(busca(unidade));
			em.getTransaction().commit();
		}
	}
	
	public void verificaIntegridade(long unidadeId) throws RestricaoDeIntegridadeException {
		boolean temUsuario = !em.createQuery("select u from Usuario u"
				+ " where u.unidade.id = :unidade_id"
				+ " and u.condominioId = :condominioId", Unidade.class)
				.setParameter("unidade_id", unidadeId)
				.setParameter("condominioId", condominioId)
				.getResultList().isEmpty();
		if (temUsuario) {
			//throw new RestricaoDeIntegridadeException("Existe(m) usuário(s) relacionados com está unidade");
		}
	}

	public Unidade busca(Unidade unidade) {
		return buscaPorId(unidade.getId());
	}
	
	public Unidade buscaPorId(long id) {
		Unidade unidade = em.find(Unidade.class, id);
		if (mesmoCondominio(unidade)) {
			return unidade;
		}
		return null;
	}

	public boolean existe(Unidade unidade) {
		return !em.createQuery("select u from Unidade u"
				+ " where u.localizacao = :localizacao"
				+ " and u.condominioId = :condominioId", Unidade.class)
			.setParameter("localizacao", unidade.getLocalizacao())
			.setParameter("condominioId", condominioId)
			.getResultList().isEmpty();
	}
	
	public List<Unidade> lista() {
		return em.createQuery("select u from Unidade u where u.condominioId = "
			+ ":condominioId", Unidade.class)
			.setParameter("condominioId", condominioId)
			.getResultList();
	}
	
	private boolean mesmoCondominio(Unidade unidade) {
		if (unidade.getCondominioId().compareTo(condominioId) == 0) {
			return true;
		} else {
			System.out.println("CONDOMÍNIOS DIFERENTES: " + unidade.getCondominioId() + " != " + condominioId);
			return false;
		}
	}

}
