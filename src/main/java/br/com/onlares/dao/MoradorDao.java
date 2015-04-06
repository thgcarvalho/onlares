package br.com.onlares.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.controller.UsuarioLogado;
import br.com.onlares.model.Localizador;
import br.com.onlares.model.Usuario;

public class MoradorDao {

	private final EntityManager em;
	private final Long condominioId;
	
	@Inject
	public MoradorDao(EntityManager em, UsuarioLogado usuarioLogado) {
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
	public MoradorDao() {
		this(null, null); // para uso do CDI
	}
	
	public Usuario busca(Usuario usuario) {
		return em.find(Usuario.class, usuario.getId());
	}

	public List<Usuario> listaRegistrados() {
		List<Localizador> identificadores = em.createQuery("select l from Localizador l"
				+ " where l.condominio.id = :condominioId", Localizador.class)
				.setParameter("condominioId", condominioId).getResultList();
		
		List<Usuario> usuariosRegistrados = new ArrayList<Usuario>();
		Usuario usuario1;
		Usuario usuario2;
		String localizacao;
		boolean found = false;
		for (Localizador identificador : identificadores) {
			found = false;
			usuario1 = identificador.getUsuario();
			localizacao = identificador.getUnidade().getDescricao();
			if (usuario1.isRegistrado()) {
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
		return usuariosRegistrados;
	 }

}
