package br.com.onlares.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

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
}
