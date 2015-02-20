package br.com.onlares.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.model.Unidade;

public class UnidadeDao {

	private final EntityManager em;
	
	@Inject
	public UnidadeDao(EntityManager em) {
		this.em = em;
	}
	
	@Deprecated
	public UnidadeDao() {
		this(null); // para uso do CDI
	}
	
	public void adiciona(Unidade unidade) {
		em.getTransaction().begin();
		em.persist(unidade);
		em.getTransaction().commit();
	}
		
	public void altera(Unidade unidade) {
		em.getTransaction().begin();
		em.merge(unidade);
		em.getTransaction().commit();
	}

	public void remove(Unidade unidade) {
		em.getTransaction().begin();
		em.remove(busca(unidade));
		em.getTransaction().commit();
	}

	public Unidade busca(Unidade unidade) {
		return em.find(Unidade.class, unidade.getId());
	}

	public boolean existe(Unidade unidade) {
		return !em.createQuery("select u from Unidade u where u.localizacao = "
			+ ":localizacao", Unidade.class)
			.setParameter("localizacao", unidade.getLocalizacao())
			.getResultList().isEmpty();
	}
	
	@SuppressWarnings("unchecked")
	public List<Unidade> lista() {
		return em.createQuery("select u from Unidade u").getResultList();
	}

}
