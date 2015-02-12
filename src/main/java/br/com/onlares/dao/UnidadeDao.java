package br.com.onlares.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.model.Usuario;

public class UnidadeDao {

	private final EntityManager em;
	
	@Inject
	public UnidadeDao(EntityManager em) {
		this.em = em;
	}
	
	@Deprecated
	public UnidadeDao() {
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
		return em.createQuery("select u from Unidade u").getResultList();
	}

}
