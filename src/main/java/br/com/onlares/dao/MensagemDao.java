package br.com.onlares.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.onlares.bean.UsuarioLogado;
import br.com.onlares.model.LocalizadorDoUsuarioLogado;
import br.com.onlares.model.Mensagem;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class MensagemDao {

	private final EntityManager em;
	private final Long usuarioId;
	
	@Inject
	public MensagemDao(EntityManager em, UsuarioLogado usuarioLogado) {
		this.em = em;
		this.usuarioId = LocalizadorDoUsuarioLogado.getUsuarioIdAtual(usuarioLogado);
	}
	
	@Deprecated
	public MensagemDao() {
		this(null, null); // para uso do CDI
	}
	
	public List<Mensagem> listaRecebidas() {
		List<Mensagem> recebidas = em.createQuery("SELECT e.mensagem FROM Envio e"
				+ " where e.usuario.id = :usuarioId", Mensagem.class)
				.setParameter("usuarioId", usuarioId).getResultList();
		return recebidas;
	}
	
	public Mensagem buscaRecebida(Long mensagemId) {
		Mensagem mnsagem;
		try {
			mnsagem = em.createQuery("SELECT e.mensagem FROM Envio e"
					+ " WHERE e.mensagem.id = :mensagemId"
					+ " AND e.usuario.id = :usuarioId", Mensagem.class)
					.setParameter("mensagemId", mensagemId)
					.setParameter("usuarioId", usuarioId).getSingleResult();
		} catch (NoResultException nrExp) {
			mnsagem = null;
		}
		return mnsagem;
	}
	
	public Mensagem buscaEnviada(Long mensagemId) {
		Mensagem mnsagem;
		try {
			mnsagem = em.createQuery("SELECT m FROM Mensagem m"
					+ " WHERE m.id = :mensagemId"
					+ " AND m.usuario.id = :usuarioId", Mensagem.class)
					.setParameter("mensagemId", mensagemId)
					.setParameter("usuarioId", usuarioId).getSingleResult();
		} catch (NoResultException nrExp) {
			mnsagem = null;
		}
		return mnsagem;
	}
}
