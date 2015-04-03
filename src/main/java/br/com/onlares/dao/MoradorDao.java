package br.com.onlares.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.controller.UsuarioLogado;
import br.com.onlares.model.Usuario;

public class MoradorDao {

	private final EntityManager em;
	private final Long condominioId;
	
	@Inject
	public MoradorDao(EntityManager em, UsuarioLogado usuarioLogado) {
		// TODO VERIFICAR A NECESSIDADE DE OBTER UsuarioLogado NESSE DAO
		this.em = em;
		if (usuarioLogado != null && usuarioLogado.getUsuario() != null
				&& usuarioLogado.getUsuario().getCondominio() != null) {
			this.condominioId = usuarioLogado.getUsuario().getCondominio().getId();
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
		List<Usuario> usuarios = em.createQuery("select u from Usuario u"
				+ " where u.condominio.id = :condominioId", Usuario.class)
				.setParameter("condominioId", condominioId).getResultList();
		List<Usuario> usuariosRegistrados = new ArrayList<Usuario>();
		for (Usuario usuario : usuarios) {
			if (usuario.isRegistrado()) {
				usuariosRegistrados.add(usuario);
			}
		}
		return usuariosRegistrados;
	 }

}
