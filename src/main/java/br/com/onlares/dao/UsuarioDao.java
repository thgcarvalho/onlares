package br.com.onlares.dao;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.onlares.controller.UsuarioLogado;
import br.com.onlares.model.Usuario;
import br.com.onlares.util.MD5Hashing;

public class UsuarioDao {

	private final EntityManager em;
	private final Long condominioId;
	
	@Inject
	public UsuarioDao(EntityManager em, UsuarioLogado usuarioLogado) {
		this.em = em;
		this.condominioId = 1L;
		//this.condominioId = (usuarioLogado != null ? usuarioLogado.getUsuario().getCondominio().getId() : null);
		if (usuarioLogado != null) {
			System.out.println("1="+usuarioLogado.getUsuario());	
			if (usuarioLogado.getUsuario() != null) {
				System.out.println("2="+usuarioLogado.getUsuario());	
				if (usuarioLogado.getUsuario().getCondominio() != null) {
					System.out.println("3="+usuarioLogado.getUsuario());	
				}
			}
		}
	}
	
	@Deprecated
	public UsuarioDao() {
		this(null, null); // para uso do CDI
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> lista() {
		return em.createQuery("select u from Usuario u").getResultList();
	}
	
	public Usuario busca(Usuario usuario) {
		return em.find(Usuario.class, usuario.getId());
	}
	
	public Usuario buscaPorEmail(String email) {
		Usuario usuario;
		String strQuery = "SELECT u FROM Usuario u WHERE u.email = :email";
		try {
			Query query = em.createQuery(strQuery, Usuario.class);
			query.setParameter("email", email);
			usuario = (Usuario) query.getSingleResult();
		} catch (NoResultException nrExp) {
			usuario = null;
		}
		return usuario;
	}
	
	public boolean loginValido(Usuario usuario) throws NoSuchAlgorithmException {
		return !em.createQuery("select u from Usuario u where u.email = "
			+ ":email and u.senha = :senha", Usuario.class)
			.setParameter("email", usuario.getEmail())
			.setParameter("senha", MD5Hashing.convertStringToMd5(usuario.getSenha()))
			.getResultList().isEmpty();
	}
	
	public boolean existe(Usuario usuario) {
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
	
	public void altera(Usuario usuario) {
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
