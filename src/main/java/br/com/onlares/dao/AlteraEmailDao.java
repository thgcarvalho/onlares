package br.com.onlares.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.model.AlteraEmail;


/**
 * @author Thiago Carvalho
 * 
 */
public class AlteraEmailDao {

	private final EntityManager em;
	
	@Inject
	public AlteraEmailDao(EntityManager em) {
		this.em = em;
	}
	
	@Deprecated
	public AlteraEmailDao() {
		this(null); // para uso do CDI
	}
	
	public void adiciona(AlteraEmail alteraEmail) {
		em.getTransaction().begin();
		em.persist(alteraEmail);
		em.getTransaction().commit();
	}

	public void remove(AlteraEmail alteraEmail) {
		em.getTransaction().begin();
		em.remove(busca(alteraEmail));
		em.getTransaction().commit();
	}
	
	public void altera(AlteraEmail alteraEmail) {
		em.getTransaction().begin();
		em.merge(alteraEmail);
		em.getTransaction().commit();
	}

	public AlteraEmail busca(AlteraEmail alteraEmail) {
		return buscaPorCodigo(alteraEmail.getCodigo());
	}
	
	public AlteraEmail buscaPorCodigo(String codigo) {
		return em.find(AlteraEmail.class, codigo);
	}

}
