package br.com.onlares.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.bean.UsuarioLogado;
import br.com.onlares.model.Anuncio;
import br.com.onlares.model.LocalizadorDoUsuarioLogado;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class AnuncioDao {

	private final EntityManager em;
	private Long condominioId;
	
	@Inject
	public AnuncioDao(EntityManager em, UsuarioLogado usuarioLogado) {
		this.em = em;
		this.condominioId = LocalizadorDoUsuarioLogado.getCondominioIdAtual(usuarioLogado);
	}
	
	@Deprecated
	public AnuncioDao() {
		this(null, null); // para uso do CDI
	}
	
	public Anuncio buscaPorId(Long id) {
		return em.find(Anuncio.class, id);
	}
	
//	public Anuncio buscaPorId(Long anuncioId) {
//		Anuncio anuncio;
//		String strQuery = "SELECT a FROM Anuncio a"
//				+ " WHERE a.id = :anuncioId and a.condominioId = :condominioId";
//		try {
//			Query query = em.createQuery(strQuery, Calendario.class);
//			query.setParameter("anuncioId", anuncioId);
//			query.setParameter("condominioId", condominioId);
//			anuncio = (Anuncio) query.getSingleResult();
//		} catch (NoResultException nrExp) {
//			anuncio = null;
//		}
//		return anuncio;
//	}
	
	/**
	 * Usado somente quando ainda não existe um usuarioLogado setado
	 * no momento da execução do construtor atual.
	 * 
	 * @author Thiago Carvalho - tcarvalho@grandev.com.br
	 * @param condominioId Long
	 * @return void
	 */
	public void setCondominioId(Long condominioId) {
		this.condominioId = condominioId;
	}
	
	public List<Anuncio> lista() {
		return em.createQuery("select a from Anuncio a"
			+ " where a.condominioId = :condominioId", Anuncio.class)
			.setParameter("condominioId", this.condominioId).getResultList();
	}

}
