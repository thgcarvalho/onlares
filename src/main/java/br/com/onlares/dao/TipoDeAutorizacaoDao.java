package br.com.onlares.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.onlares.controller.UsuarioLogado;
import br.com.onlares.model.Autorizacao;
import br.com.onlares.model.Condominio;
import br.com.onlares.model.LocalizadorDoUsuarioLogado;
import br.com.onlares.model.TipoDeAutorizacao;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class TipoDeAutorizacaoDao {

	private final EntityManager em;
	private final Long condominioId;
	
	@Inject
	public TipoDeAutorizacaoDao(EntityManager em, UsuarioLogado usuarioLogado) {
		this.em = em;
		this.condominioId = LocalizadorDoUsuarioLogado.getCondominioIdAtual(usuarioLogado);
	}
	
	@Deprecated
	public TipoDeAutorizacaoDao() {
		this(null, null); // para uso do CDI
	}
	
	public List<TipoDeAutorizacao> lista() {
		List<TipoDeAutorizacao> tiposDeAutorizacoes = em.createQuery("SELECT ta FROM TipoDeAutorizacao ta"
				+ " where ta.condominio.id = :condominioId", TipoDeAutorizacao.class)
				.setParameter("condominioId", condominioId).getResultList();
		return tiposDeAutorizacoes;
	}
	
	private List<Autorizacao> listaAutorizacao(Long tipoDeAutorizacaoId) {
		List<Autorizacao> autorizacoes = em.createQuery("SELECT a FROM Autorizacao a"
				+ " where a.tipoDeAutorizacao.id = :tipoDeAutorizacaoId", Autorizacao.class)
				.setParameter("tipoDeAutorizacaoId", tipoDeAutorizacaoId).getResultList();
		return autorizacoes;
	}
	
	public void adiciona(TipoDeAutorizacao tipoDeAutorizacaoId) {
		Condominio condominio = new Condominio();
		condominio.setId(condominioId);
		tipoDeAutorizacaoId.setCondominio(condominio);
		em.persist(tipoDeAutorizacaoId);
	}
	
	public void altera(TipoDeAutorizacao tipoDeAutorizacaoId) {
		Condominio condominio = new Condominio();
		condominio.setId(condominioId);
		tipoDeAutorizacaoId.setCondominio(condominio);
		em.merge(tipoDeAutorizacaoId);
	}
	
	public TipoDeAutorizacao buscaTipoDeAutorizacao(Long tipoDeAutorizacaoId) {
		TipoDeAutorizacao tipoDeAutorizacao;
		String strQuery = "SELECT ta FROM TipoDeAutorizacao ta"
				+ " WHERE ta.id = :tipoDeAutorizacaoId"
				+ " AND ta.condominio.id = :condominioId";
		try {
			Query query = em.createQuery(strQuery, TipoDeAutorizacao.class);
			query.setParameter("tipoDeAutorizacaoId", tipoDeAutorizacaoId);
			query.setParameter("condominioId", condominioId);
			tipoDeAutorizacao = (TipoDeAutorizacao) query.getSingleResult();
		} catch (NoResultException nrExp) {
			tipoDeAutorizacao = null;
		}
		return tipoDeAutorizacao;
	}
	
	public List<Autorizacao> listaDaAutorizacao(Long tipoDeAutorizacaoId) {
		List<Autorizacao> unidadeAutorizacao;
		Calendar hoje = Calendar.getInstance();
		try {
			unidadeAutorizacao = em.createQuery("select a from Autorizacao a"
					+ " where a.tipoDeAutorizacao.id = :tipoDeAutorizacaoId"
					+ " and a.data >= :hoje", Autorizacao.class)
					.setParameter("tipoDeAutorizacaoId", tipoDeAutorizacaoId)
					.setParameter("hoje", hoje).getResultList();
		} catch (EntityNotFoundException e) {
			unidadeAutorizacao = new ArrayList<Autorizacao>();
		}
		return unidadeAutorizacao;
	}
	
	public void removeTipoDeAutorizacao(TipoDeAutorizacao tipoDeAutorizacao) {
		List<Autorizacao> autorizacoes = listaAutorizacao(tipoDeAutorizacao.getId());
		for (Autorizacao autorizacao : autorizacoes) {
			em.remove(autorizacao);
		}
		em.remove(buscaTipoDeAutorizacao(tipoDeAutorizacao.getId()));
	}

}
