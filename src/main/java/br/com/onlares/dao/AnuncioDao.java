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
		// TODO VERIFICAR A NECESSIDADE DE OBTER UsuarioLogado NESSE DAO
		this.em = em;
		if (usuarioLogado != null && usuarioLogado.getUsuario() != null
				&& usuarioLogado.getLocalizadorAtual().getCondominio() != null) {
			this.condominioId = usuarioLogado.getLocalizadorAtual().getCondominio().getId();
		} else {
			this.condominioId = -1L;
		}
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
