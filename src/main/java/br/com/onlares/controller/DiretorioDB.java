package br.com.onlares.controller;

import java.net.URI;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.model.Arquivo;

public class DiretorioDB {
	
	private final EntityManager em;
	
	@Inject
	public DiretorioDB(EntityManager em) {
		this.em = em;
	}
	
	@Deprecated
	public DiretorioDB() {
		this(null); // para uso do CDI
	}

	//@Override
	public URI grava(Arquivo arquivo) {
		System.out.println("GRAVA"); // TODO remover
		em.getTransaction().begin();
		em.persist(arquivo);
		em.getTransaction().commit();
		return URI.create("bd://" + arquivo.getId());
	}

	//@Override
	public Arquivo recupera(URI chave) {
		return null;
	}
}