package br.com.onlares.dao;

import java.net.URI;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.model.Foto;

public class FotoDao {
	
	private final EntityManager em;
	
	@Inject
	public FotoDao(EntityManager em) {
		this.em = em;
	}
	
	@Deprecated
	public FotoDao() {
		this(null); // para uso do CDI
	}

	public URI grava(Foto arquivo) {
		em.getTransaction().begin();
		em.persist(arquivo);
		em.getTransaction().commit();
		return getURI(arquivo);
	}
	
	public URI getURI(Foto arquivo) {
		return URI.create("db://" + arquivo.getId());
	}
	
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
	
	public void deleta(Foto arquivo) {
		em.getTransaction().begin();
		em.remove(arquivo);
		em.getTransaction().commit();
	}
	
}