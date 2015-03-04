package br.com.onlares.controller;

import java.net.URI;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.model.Arquivo;
import br.com.onlares.model.Diretorio;

public class DiretorioDB implements Diretorio{
	
	private final EntityManager em;
	
	@Inject
	public DiretorioDB(EntityManager em) {
		this.em = em;
	}
	
	@Deprecated
	public DiretorioDB() {
		this(null); // para uso do CDI
	}

	@Override
	public URI grava(Arquivo arquivo) {
		System.out.println("GRAVA"); // TODO remover
		em.getTransaction().begin();
		em.persist(arquivo);
		em.getTransaction().commit();
		return URI.create("db://" + arquivo.getId());
	}

	@Override
	public Arquivo recupera(URI chave) {
		if (chave == null) {
			return null;
		}
		// scheme é o protocolo. No caso de db:// é o db
		System.out.println("chave.getScheme()"+chave.getScheme());
		if (!chave.getScheme().equals("db")) {
			throw new IllegalArgumentException(chave + " não é uma URI de banco de dados");
		}
		// authority é o que vem depois do db://
		Long id = Long.valueOf(chave.getAuthority());
		return em.find(Arquivo.class, id);
	}
	
}