package br.com.onlares.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.model.Usuario;

public class UsuarioDao {

	private final EntityManager em;
	
	@Inject
	public UsuarioDao(EntityManager em) {
		this.em = em;
	}
	
	@Deprecated
	public UsuarioDao() {
		this(null); // para uso do CDI
	}
	
	public boolean existe(Usuario usuario) {
		System.out.println(usuario.getEmail());
		System.out.println(usuario.getSenha());
		return !em.createQuery("select u from Usuario u where u.email = "
			+ ":email and u.senha = :senha", Usuario.class)
			.setParameter("email", usuario.getEmail())
			.setParameter("senha", usuario.getSenha())
			.getResultList().isEmpty();
	}

	public void salva(Usuario usuario) {
		em.persist(usuario);
	}

}
