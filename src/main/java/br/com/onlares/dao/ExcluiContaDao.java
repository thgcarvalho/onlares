package br.com.onlares.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.model.ExcluiConta;


/**
 * @author Thiago Carvalho
 * 
 */
public class ExcluiContaDao {

	private final EntityManager em;
	
	@Inject
	public ExcluiContaDao(EntityManager em) {
		this.em = em;
	}
	
	@Deprecated
	public ExcluiContaDao() {
		this(null); // para uso do CDI
	}
	
	public void adiciona(ExcluiConta excluiConta) {
		em.getTransaction().begin();
		em.persist(excluiConta);
		em.getTransaction().commit();
	}

	public void remove(ExcluiConta excluiConta) {
		em.getTransaction().begin();
		em.remove(busca(excluiConta));
		em.getTransaction().commit();
	}
	
	public void altera(ExcluiConta excluiConta) {
		em.getTransaction().begin();
		em.merge(excluiConta);
		em.getTransaction().commit();
	}

	public ExcluiConta busca(ExcluiConta excluiConta) {
		return buscaPorCodigo(excluiConta.getCodigo());
	}
	
	public ExcluiConta buscaPorCodigo(String codigo) {
		return em.find(ExcluiConta.class, codigo);
	}

}
