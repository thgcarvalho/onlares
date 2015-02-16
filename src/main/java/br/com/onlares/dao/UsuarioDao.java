package br.com.onlares.dao;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.onlares.model.Usuario;
import br.com.onlares.util.MD5Hashing;

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
	
	@SuppressWarnings("unchecked")
	public List<Usuario> lista() {
		return em.createQuery("select u from Usuario u").getResultList();
	}
	
	public Usuario busca(Usuario usuario) {
		return em.find(Usuario.class, usuario.getId());
	}
	
	public Usuario buscaPorEmail(String email) {
		String strQuery = "SELECT u FROM Usuario u WHERE u.email = :email";
		Query query = em.createQuery(strQuery, Usuario.class);
		query.setParameter("email", email);
		return (Usuario) query.getSingleResult();
	}
	
	public boolean loginValido(Usuario usuario) throws NoSuchAlgorithmException {
		return !em.createQuery("select u from Usuario u where u.email = "
			+ ":email and u.senha = :senha", Usuario.class)
			.setParameter("email", usuario.getEmail())
			.setParameter("senha", MD5Hashing.convertStringToMd5(usuario.getSenha()))
			.getResultList().isEmpty();
	}
	
	public boolean existe(Usuario usuario) throws NoSuchAlgorithmException {
		return !em.createQuery("select u from Usuario u where u.email = "
			+ ":email", Usuario.class)
			.setParameter("email", usuario.getEmail())
			.getResultList().isEmpty();
	}

	public void adiciona(Usuario usuario) {
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
	}
	
	public void edita(Usuario usuario) {
		em.getTransaction().begin();
		em.merge(usuario);
		em.getTransaction().commit();
	}
	
	public void remove(Usuario usuario) {
		em.getTransaction().begin();
		em.remove(busca(usuario));
		em.getTransaction().commit();
	}

}
