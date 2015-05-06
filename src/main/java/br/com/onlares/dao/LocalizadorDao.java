package br.com.onlares.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.onlares.model.Localizador;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class LocalizadorDao {

	private final EntityManager em;
	
	@Inject
	public LocalizadorDao(EntityManager em) {
		this.em = em;
	}
	
	@Deprecated
	public LocalizadorDao() {
		this(null); // para uso do CDI
	}
	
	public List<Localizador> lista(Long usuarioId) {
		return em.createQuery("select l from Localizador l"
				+ " where l.usuario.id = :usuarioId", Localizador.class)
				.setParameter("usuarioId", usuarioId).getResultList();
	}
	
	public Localizador busca(Localizador identificador) {
		return buscaPorId(identificador.getId());
	}
	
	public Localizador buscaPorId(Long id) {
		return em.find(Localizador.class, id);
	}
	
	public Localizador buscaPorChave(Long usuarioId, Long condominioId, Long unidadeId) {
		Localizador identificador;
		String strQuery = "SELECT l FROM Localizador l WHERE "
				+ "l.usuarioId = :usuarioId"
				+ "l.condominio.id = :condominioId"
				+ "l.unidade.id = :unidadeId";
		try {
			Query query = em.createQuery(strQuery, Localizador.class);
			query.setParameter("usuarioId", usuarioId);
			query.setParameter("condominioId", condominioId);
			query.setParameter("unidadeId", unidadeId);
			identificador = (Localizador) query.getSingleResult();
		} catch (NoResultException nrExp) {
			identificador = null;
		}
		return identificador;
	}
	
}
