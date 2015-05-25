package br.com.onlares.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.onlares.bean.UsuarioLogado;
import br.com.onlares.model.Envio;
import br.com.onlares.model.LocalizadorDoUsuarioLogado;
import br.com.onlares.model.Mensagem;
import br.com.onlares.model.Usuario;

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
		List<Envio> envios = em.createQuery("SELECT e FROM Envio e"
				+ " where e.usuario.id = :usuarioId", Envio.class)
				.setParameter("usuarioId", usuarioId).getResultList();
		List<Mensagem> mensagens = new ArrayList<Mensagem>();
		Mensagem mensagem = null;
		for (Envio envio : envios) {
			mensagem = envio.getMensagem();
			mensagem.setVisualizado(envio.isVisualizado());
			mensagens.add(mensagem);
		}
		
		List<Mensagem> recebidas = em.createQuery("SELECT e.mensagem FROM Envio e"
				+ " where e.usuario.id = :usuarioId", Mensagem.class)
				.setParameter("usuarioId", usuarioId).getResultList();
		return recebidas;
	}
	
	public List<Mensagem> listaEnviadas() {
		List<Mensagem> enviadas = em.createQuery("SELECT m FROM Mensagem m"
				+ " where m.usuario.id = :usuarioId", Mensagem.class)
				.setParameter("usuarioId", usuarioId).getResultList();
		return enviadas;
	}
	
	public Mensagem buscaRecebida(Long mensagemId) {
		Mensagem recebida;
		try {
			recebida = em.createQuery("SELECT e.mensagem FROM Envio e"
					+ " WHERE e.mensagem.id = :mensagemId"
					+ " AND e.usuario.id = :usuarioId", Mensagem.class)
					.setParameter("mensagemId", mensagemId)
					.setParameter("usuarioId", usuarioId).getSingleResult();
		} catch (NoResultException nrExp) {
			recebida = null;
		}
		return recebida;
	}
	
	public Mensagem buscaEnviada(Long mensagemId) {
		Mensagem enviada;
		try {
			enviada = em.createQuery("SELECT m FROM Mensagem m"
					+ " WHERE m.id = :mensagemId"
					+ " AND m.usuario.id = :usuarioId", Mensagem.class)
					.setParameter("mensagemId", mensagemId)
					.setParameter("usuarioId", usuarioId).getSingleResult();
		} catch (NoResultException nrExp) {
			enviada = null;
		}
		return enviada;
	}
	
	public List<Usuario> buscaDestinatarios(Mensagem mensagem) {
		List<Usuario> usuarios;
		try {
			usuarios = em.createQuery("SELECT e.usuario FROM Envio e"
					+ " WHERE e.mensagem.id = :mensagemId"
					+ " AND e.usuario.id = :usuarioId", Usuario.class)
					.setParameter("mensagemId", mensagem.getId())
					.setParameter("usuarioId", usuarioId).getResultList();
		} catch (NoResultException nrExp) {
			usuarios = null;
		}
		return usuarios;
	}
	
	public boolean foiVisualizado(Long mensagemId) {
		Envio envio = em.createQuery("SELECT e FROM Envio e"
				+ " WHERE e.mensagem.id = :mensagemId"
				+ " AND e.usuario.id = :usuarioId", Envio.class)
				.setParameter("mensagemId", mensagemId)
				.setParameter("usuarioId", this.usuarioId).getSingleResult();
		if (envio.isVisualizado()) {
			return true;
		}
		return false;
	}
	
	public void setaVisualizacao(Long mensagemId) {
		Envio envio = em.createQuery("SELECT e FROM Envio e"
				+ " WHERE e.mensagem.id = :mensagemId"
				+ " AND e.usuario.id = :usuarioId", Envio.class)
				.setParameter("mensagemId", mensagemId)
				.setParameter("usuarioId", this.usuarioId).getSingleResult();
		envio.setVisualizado(true);
	}
	
}
