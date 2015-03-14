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
		return URI.create("foto://" + arquivo.getId());
	}
	
	public Foto recupera(URI chave) {
		if (chave == null || chave.equals("")) {
			return null;
		}
		// scheme é o protocolo. No caso de foto:// ou temp://
		if (!chave.getScheme().equals("foto")) {
			throw new IllegalArgumentException(chave + " não é uma URI de banco de dados");
		}
		// authority é o que vem depois do foto://
		Long id = Long.valueOf(chave.getAuthority());
		return em.find(Foto.class, id);
	}
	
	public void deleta(URI chave) {
		Foto arquivo = recupera(chave);
		em.getTransaction().begin();
		em.remove(arquivo);
		em.getTransaction().commit();
	}
	
}