package br.com.onlares.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	
	public Usuario buscaPorEmail(String email) {
		String strQuery = "SELECT u FROM Usuario u WHERE u.email = :email";
		Query query = em.createQuery(strQuery, Usuario.class);
		query.setParameter("email", email);
		return (Usuario) query.getSingleResult();
	}
	
	public boolean existe(Usuario usuario) {
		return !em.createQuery("select u from Usuario u where u.email = "
			+ ":email and u.senha = :senha", Usuario.class)
			.setParameter("email", usuario.getEmail())
			.setParameter("senha", usuario.getSenha())
			.getResultList().isEmpty();
	}

	public void salva(Usuario usuario) {
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
	}
	
	public void edita(Usuario usuario) {
		em.getTransaction().begin();
		em.merge(usuario);
		em.getTransaction().commit();
	}

}
