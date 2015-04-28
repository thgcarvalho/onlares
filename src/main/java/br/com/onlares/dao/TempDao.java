package br.com.onlares.dao;

import java.net.URI;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.model.Temp;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class TempDao {
	
	private final EntityManager em;
	
	@Inject
	public TempDao(EntityManager em) {
		this.em = em;
	}
	
	@Deprecated
	public TempDao() {
		this(null); // para uso do CDI
	}

	public URI grava(Temp arquivo) {
		em.persist(arquivo);
		return URI.create("temp://" + arquivo.getId());
	}
	
	public Temp recupera(URI chave) {
		if (chave == null) {
			return null;
		}
		// scheme é o protocolo. No caso temp://
		if (!chave.getScheme().equals("temp")) {
			throw new IllegalArgumentException(chave + " não é uma URI de banco de dados");
		}
		// authority é o que vem depois do foto://
		Long id = Long.valueOf(chave.getAuthority());
		return em.find(Temp.class, id);
	}
	
	public void deleta(URI chave) {
		Temp arquivo = recupera(chave);
		em.remove(arquivo);
	}
	
}