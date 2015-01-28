package br.com.onlares.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.model.Usuario;

public class MoradorDao {

	private final EntityManager em;
	
	@Inject
	public MoradorDao(EntityManager em) {
		this.em = em;
	}
	
	@Deprecated
	public MoradorDao() {
		this(null); // para uso do CDI
	}
	
	public void adiciona(Usuario usuario) {
		em.persist(usuario);
	}

	public void remove(Usuario usuario) {
		em.remove(busca(usuario));
	}

	public Usuario busca(Usuario usuario) {
		return em.find(Usuario.class, usuario.getId());
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> lista() {
		return em.createQuery("select u from Usuario u").getResultList();
	}

}
