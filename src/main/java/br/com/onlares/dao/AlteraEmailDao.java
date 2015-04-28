package br.com.onlares.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.model.AlteraEmail;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
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
	
	public void altera(AlteraEmail alteraEmail) {
		em.merge(alteraEmail);
	}

	public AlteraEmail busca(AlteraEmail alteraEmail) {
		return buscaPorCodigo(alteraEmail.getCodigo());
	}
	
	public AlteraEmail buscaPorCodigo(String codigo) {
		return em.find(AlteraEmail.class, codigo);
	}

}
