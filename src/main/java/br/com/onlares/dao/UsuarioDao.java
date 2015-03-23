package br.com.onlares.dao;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.onlares.controller.UsuarioLogado;
import br.com.onlares.model.Condominio;
import br.com.onlares.model.Usuario;
import br.com.onlares.util.MD5Hashing;

public class UsuarioDao {

	private final EntityManager em;
	private final Long condominioId;
	
	@Inject
	public UsuarioDao(EntityManager em, UsuarioLogado usuarioLogado) {
		this.em = em;
		if (usuarioLogado != null && usuarioLogado.getUsuario() != null
				&& usuarioLogado.getUsuario().getCondominio() != null) {
			this.condominioId = usuarioLogado.getUsuario().getCondominio().getId();
		} else {
			this.condominioId = -1L;
		}
	}
	
	@Deprecated
	public UsuarioDao() {
		this(null, null); // para uso do CDI
	}
	
	public List<Usuario> lista() {
		return em.createQuery("select u from Usuario u"
				+ " where u.condominio.id = :condominioId", Usuario.class)
				.setParameter("condominioId", condominioId).getResultList();
	}

	public List<Usuario> listaTodos() {
		return em.createQuery("select u from Usuario u", Usuario.class).getResultList();
	}
	
	public Usuario busca(Usuario usuario) {
		return buscaPorId(usuario.getId());
	}
	
	public Usuario buscaPorId(Long id) {
		return em.find(Usuario.class, id);
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
		return !em.createQuery("select u from Usuario u"
			+ " where u.email = :email and u.senha = :senha", Usuario.class)
			.setParameter("email", usuario.getEmail())
			.setParameter("senha", MD5Hashing.convertStringToMd5(usuario.getSenha()))
			.getResultList().isEmpty();
	}
	
	public boolean existe(Usuario usuario) {
		return !em.createQuery("select u from Usuario u"
			+ " where u.email = :email"
			+ " and u.condominio.id = :condominioId", Usuario.class)
			.setParameter("email", usuario.getEmail())
			.setParameter("condominioId", condominioId)
			.getResultList().isEmpty();
	}

	public void adiciona(Usuario usuario) {
		Condominio condominio = new Condominio();
		condominio.setId(condominioId);
		usuario.setCondominio(condominio);
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
	}
	
	public void registra(Usuario usuario, String nome, String senha) {
		em.getTransaction().begin();
		usuario.setNome(nome);
		usuario.setSenha(senha);
		em.merge(usuario);
		em.getTransaction().commit();
	}
	
	public void altera(Usuario usuario) {
		if (mesmoCondominio(usuario)) {
			em.getTransaction().begin();
			em.merge(usuario);
			em.getTransaction().commit();
		}
	}
	
	public void remove(Usuario usuario) {
		if (mesmoCondominio(usuario)) {
			em.getTransaction().begin();
			em.remove(busca(usuario));
			em.getTransaction().commit();
		}
	}
	
	private boolean mesmoCondominio(Usuario usuario) {
		return true; // TODO reavaliar metodo
//		if (usuario.getCondominio().getId().compareTo(condominioId) == 0) {
//			return true;
//		} else {
//			System.out.println("CONDOMÍNIOS DIFERENTES: " + usuario.getCondominio().getId() + " != " + condominioId);
//			return false;
//		}
	}

}
