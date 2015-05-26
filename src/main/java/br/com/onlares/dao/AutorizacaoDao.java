package br.com.onlares.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.onlares.bean.UsuarioLogado;
import br.com.onlares.model.Autorizacao;
import br.com.onlares.model.LocalizadorDoUsuarioLogado;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class AutorizacaoDao {

	private final EntityManager em;
	private final Long condominioId;
	private final Long unidadeId;
	
	@Inject
	public AutorizacaoDao(EntityManager em, UsuarioLogado usuarioLogado) {
		this.em = em;
		this.condominioId = LocalizadorDoUsuarioLogado.getCondominioIdAtual(usuarioLogado);
		this.unidadeId = LocalizadorDoUsuarioLogado.getUnidadeIdAtual(usuarioLogado);
	}
	
	@Deprecated
	public AutorizacaoDao() {
		this(null, null); // para uso do CDI
	}
	
	public List<Autorizacao> listaAutorizacao(Long tipoDeAutorizacaoId) {
		List<Autorizacao> unidadeAutorizacaos = em.createQuery("SELECT r FROM Autorizacao r"
				+ " where r.tipoDeAutorizacao.id = :tipoDeAutorizacaoId", Autorizacao.class)
				.setParameter("tipoDeAutorizacaoId", tipoDeAutorizacaoId).getResultList();
		return unidadeAutorizacaos;
	}
	
	public void adiciona(Autorizacao autorizacao) {
		em.persist(autorizacao);
	}
	
	public Autorizacao buscaAutorizacao(Long autorizacaoId) {
		Autorizacao autorizacao;
		String strQuery = "SELECT r FROM Autorizacao r"
				+ " WHERE r.id = :autorizacaoId"
				+ " AND r.tipoDeAutorizacao.condominio.id = :condominioId";
		try {
			Query query = em.createQuery(strQuery, Autorizacao.class);
			query.setParameter("autorizacaoId", autorizacaoId);
			query.setParameter("condominioId", condominioId);
			autorizacao = (Autorizacao) query.getSingleResult();
		} catch (NoResultException nrExp) {
			autorizacao = null;
		}
		return autorizacao;
	}
	
	public List<Autorizacao> listaDaAutorizacao(Long tipoDeAutorizacaoId) {
		List<Autorizacao> unidadeAutorizacao;
		Calendar hoje = Calendar.getInstance();
		try {
			unidadeAutorizacao = em.createQuery("select r from Autorizacao r"
					+ " where r.tipoDeAutorizacao.id = :tipoDeAutorizacaoId"
					+ " and r.data >= :hoje", Autorizacao.class)
					.setParameter("tipoDeAutorizacaoId", tipoDeAutorizacaoId)
					.setParameter("hoje", hoje).getResultList();
		} catch (EntityNotFoundException e) {
			unidadeAutorizacao = new ArrayList<Autorizacao>();
		}
		return unidadeAutorizacao;
	}
	
	public List<Autorizacao> listaDaUnidade() {
		List<Autorizacao> autorizacoes;
		try {
			autorizacoes = em.createQuery("select r from Autorizacao r"
					+ " where r.unidade.id = :unidadeId", Autorizacao.class)
					.setParameter("unidadeId", unidadeId).getResultList();
		} catch (EntityNotFoundException e) {
			autorizacoes = new ArrayList<Autorizacao>();
		}
		return autorizacoes;
	}
	
	public List<Autorizacao> listaDoCondominio() {
		List<Autorizacao> autorizacoes;
		try {
			autorizacoes = em.createQuery("select r from Autorizacao r"
					+ " where r.tipoDeAutorizacao.condominio.id = :condominioId", Autorizacao.class)
					.setParameter("condominioId", condominioId).getResultList();
		} catch (EntityNotFoundException e) {
			autorizacoes = new ArrayList<Autorizacao>();
		}
		return autorizacoes;
	}
	
	public void removeAutorizacao(Autorizacao unidadeAutorizacao) {
		em.remove(unidadeAutorizacao);
	}

}
