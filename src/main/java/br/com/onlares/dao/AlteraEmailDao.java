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
		em.persist(alteraEmail);
	}

	public void remove(AlteraEmail alteraEmail) {
		em.remove(busca(alteraEmail));
	}

	public AlteraEmail busca(AlteraEmail alteraEmail) {
		return em.find(AlteraEmail.class, alteraEmail.getCodigo());
	}

}
