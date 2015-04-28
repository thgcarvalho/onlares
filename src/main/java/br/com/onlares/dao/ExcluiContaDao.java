package br.com.onlares.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.model.ExcluiConta;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
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
		em.persist(excluiConta);
	}

	public void remove(ExcluiConta excluiConta) {
		em.remove(busca(excluiConta));
	}
	
	public void altera(ExcluiConta excluiConta) {
		em.merge(excluiConta);
	}

	public ExcluiConta busca(ExcluiConta excluiConta) {
		return buscaPorCodigo(excluiConta.getCodigo());
	}
	
	public ExcluiConta buscaPorCodigo(String codigo) {
		return em.find(ExcluiConta.class, codigo);
	}

}
