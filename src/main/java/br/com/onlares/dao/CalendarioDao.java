package br.com.onlares.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.onlares.controller.UsuarioLogado;
import br.com.onlares.model.Calendario;
import br.com.onlares.model.Condominio;
import br.com.onlares.model.LocalizadorDoUsuarioLogado;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class CalendarioDao {

	private final EntityManager em;
	private final Long condominioId;
	
	@Inject
	public CalendarioDao(EntityManager em, UsuarioLogado usuarioLogado) {
		this.em = em;
		this.condominioId = LocalizadorDoUsuarioLogado.getCondominioIdAtual(usuarioLogado);
	}
	
	@Deprecated
	public CalendarioDao() {
		this(null, null); // para uso do CDI
	}
	
	public Calendario busca(Calendario calendario) {
		return em.find(Calendario.class, calendario.getId());
	}

	public List<Calendario> lista() {
		List<Calendario> calendarios = em.createQuery("select c from Calendario c"
				+ " where c.condominio.id = :condominioId", Calendario.class)
				.setParameter("condominioId", condominioId).getResultList();
		return calendarios;
	}
	
	public void adiciona(Calendario calendario) {
		Condominio condominio = new Condominio();
		condominio.setId(condominioId);
		calendario.setCondominio(condominio);
		em.persist(calendario);
	}
	
	public void altera(Calendario calendario) {
		Condominio condominio = new Condominio();
		condominio.setId(condominioId);
		calendario.setCondominio(condominio);
		em.merge(calendario);
	}
	
	public Calendario busca(Long calendarioId) {
		Calendario calendario;
		// TODO deveria obter somente do condominio em questao
		String strQuery = "SELECT c FROM Calendario c"
				+ " WHERE c.id = :calendarioId";
		try {
			Query query = em.createQuery(strQuery, Calendario.class);
			query.setParameter("calendarioId", calendarioId);
			calendario = (Calendario) query.getSingleResult();
		} catch (NoResultException nrExp) {
			calendario = null;
		}
		return calendario;
	}
	
	public void remove(Calendario calendario) {
		em.remove(busca(calendario));
	}

}
