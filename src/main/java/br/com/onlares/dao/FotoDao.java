package br.com.onlares.dao;

import java.net.URI;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.model.Foto;
import br.com.onlares.model.Diretorio;

public class FotoDao implements Diretorio {
	
	private final EntityManager em;
	
	@Inject
	public FotoDao(EntityManager em) {
		this.em = em;
	}
	
	@Deprecated
	public FotoDao() {
		this(null); // para uso do CDI
	}

	@Override
	public URI grava(Foto arquivo) {
		System.out.println("GRAVA"); // TODO remover
		em.getTransaction().begin();
		em.persist(arquivo);
		em.getTransaction().commit();
		return URI.create("db://" + arquivo.getId());
	}

	@Override
	public Foto recupera(URI chave) {
		if (chave == null) {
			return null;
		}
		// scheme é o protocolo. No caso de db:// é o db
		if (!chave.getScheme().equals("db")) {
			throw new IllegalArgumentException(chave + " não é uma URI de banco de dados");
		}
		// authority é o que vem depois do db://
		Long id = Long.valueOf(chave.getAuthority());
		return em.find(Foto.class, id);
	}
	
}