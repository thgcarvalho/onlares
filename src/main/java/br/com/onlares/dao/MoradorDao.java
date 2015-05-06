package br.com.onlares.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.comparador.ComparadorUsuarioNome;
import br.com.onlares.controller.UsuarioLogado;
import br.com.onlares.model.Constantes;
import br.com.onlares.model.Localizador;
import br.com.onlares.model.LocalizadorDoUsuarioLogado;
import br.com.onlares.model.Usuario;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class MoradorDao {

	private final EntityManager em;
	private final Long condominioId;
	
	@Inject
	public MoradorDao(EntityManager em, UsuarioLogado usuarioLogado) {
		this.em = em;
		this.condominioId = LocalizadorDoUsuarioLogado.getCondominioIdAtual(usuarioLogado);
	}
	
	@Deprecated
	public MoradorDao() {
		this(null, null); // para uso do CDI
	}
	
	public Usuario busca(Usuario usuario) {
		return em.find(Usuario.class, usuario.getId());
	}
	
	private boolean adminGlobal(Localizador localizador) {
		if (localizador.getUsuario().getEmail().endsWith("@grandev.com.br")) {
			if (localizador.getCondominio().getId().compareTo(1L) != 0) {
				return true;
			}
		}
		return false;
	}

	public List<Usuario> listaRegistrados() {
		List<Localizador> localizadores = em.createQuery("select l from Localizador l"
				+ " where l.condominio.id = :condominioId"
				+ " and l.usuario.id <> :usuarioId"
				+ " and l.unidade.id <> :unidadeId", Localizador.class)
				.setParameter("condominioId", condominioId)
				.setParameter("usuarioId", Constantes.USUARIO_INEXISTENTE_ID)
				.setParameter("unidadeId", Constantes.UNIDADE_NAO_RELACIONADA_ID).getResultList();
		
		List<Usuario> usuariosRegistrados = new ArrayList<Usuario>();
		Usuario usuario1;
		Usuario usuario2;
		String localizacao;
		boolean found = false;
		for (Localizador localizador : localizadores) {
			if (adminGlobal(localizador)) {
				continue;
			}
			found = false;
			usuario1 = localizador.getUsuario();
			if (usuario1.isRegistrado()) {
			 	if (localizador.getUnidade().getId().equals(Constantes.UNIDADE_NAO_RELACIONADA_ID)) {
					localizacao = "";
				} else {
					localizacao = localizador.getUnidade().getDescricao();
				}
				// agrupa por unidade
				for (Iterator<Usuario> iterator = usuariosRegistrados.iterator(); iterator.hasNext();) {
					usuario2 = iterator.next();
					if (usuario2.equals(usuario1)) {
						found = true;
						usuario2.setLocalizacoes(usuario2.getLocalizacoes() + " | " + localizacao);
					}
				}
				if (!found) {
					usuario1.setLocalizacoes(localizacao);
					usuariosRegistrados.add(usuario1);
				}
			}
		}
		
		ComparadorUsuarioNome comparadorUsuarioNome = new ComparadorUsuarioNome();
		Collections.sort(usuariosRegistrados, comparadorUsuarioNome);
		return usuariosRegistrados;
	 }

}
