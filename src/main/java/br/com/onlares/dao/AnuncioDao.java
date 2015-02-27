package br.com.onlares.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.controller.UsuarioLogado;
import br.com.onlares.model.Anuncio;

public class AnuncioDao {

	private final EntityManager em;
	private final Long condominioId;
	
	@Inject
	public AnuncioDao(EntityManager em, UsuarioLogado usuarioLogado) {
		this.em = em;
		this.condominioId = usuarioLogado.getUsuario().getCondominio().getId();
	}
	
	@Deprecated
	public AnuncioDao() {
		this(null, null); // para uso do CDI
	}
	
	public Anuncio busca(Anuncio anuncio) {
		return em.find(Anuncio.class, anuncio.getId());
	}

	public List<Anuncio> lista() {
		return em.createQuery("select a from Anuncio a"
			+ " where a.condominioId = :condominioId", Anuncio.class)
			.setParameter("condominioId", condominioId).getResultList();
	}

}
