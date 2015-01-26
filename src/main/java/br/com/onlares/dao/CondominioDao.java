package br.com.onlares.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.onlares.model.Condominio;

public class CondominioDao {

	private final EntityManager em;

	public CondominioDao(EntityManager em) {
		this.em = em;
	}
	
	public void adiciona(Condominio condominio) {
		em.persist(condominio);
	}

	public void remove(Condominio condominio) {
		em.remove(busca(condominio));
	}

	public Condominio busca(Condominio condominio) {
		return em.find(Condominio.class, condominio.getId());
	}

	@SuppressWarnings("unchecked")
	public List<Condominio> lista() {
		return em.createQuery("select c from Condominio c").getResultList();
	}

}