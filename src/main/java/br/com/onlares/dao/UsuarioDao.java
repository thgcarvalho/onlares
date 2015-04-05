package br.com.onlares.dao;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.onlares.controller.UsuarioLogado;
import br.com.onlares.model.Condominio;
import br.com.onlares.model.Identificador;
import br.com.onlares.model.Usuario;
import br.com.onlares.util.MD5Hashing;

public class UsuarioDao {

	private final EntityManager em;
	private final Long condominioId;
	
	@Inject
	public UsuarioDao(EntityManager em, UsuarioLogado usuarioLogado) {
		// TODO VERIFICAR A NECESSIDADE DE OBTER UsuarioLogado NESSE DAO
		this.em = em;
		if (usuarioLogado != null && usuarioLogado.getUsuario() != null
				&& usuarioLogado.getIdentificadorAtual().getCondominio() != null) {
			this.condominioId = usuarioLogado.getIdentificadorAtual().getCondominio().getId();
		} else {
			this.condominioId = -1L;
		}
	}
	
	@Deprecated
	public UsuarioDao() {
		this(null, null); // para uso do CDI
	}
	
	public List<Usuario> lista() {
		List<Identificador> identificadores = em.createQuery("select i from Identificador i"
				+ " where i.condominio.id = :condominioId", Identificador.class)
				.setParameter("condominioId", condominioId).getResultList();
		
		List<Usuario> usuariosAgrupados = new ArrayList<Usuario>();
		Usuario usuario1;
		Usuario usuario2;
		String localizacao;
		boolean found = false;
		for (Identificador identificador : identificadores) {
			found = false;
			usuario1 = identificador.getUsuario();
			localizacao = identificador.getUnidade().getLocalizacao();
			// agrupa por unidade
			for (Iterator<Usuario> iterator = usuariosAgrupados.iterator(); iterator.hasNext();) {
				usuario2 = iterator.next();
				if (usuario2.equals(usuario1)) {
					found = true;
					usuario2.setLocalizacoes(usuario2.getLocalizacoes() + " | " + localizacao);
				}
			}
			if (!found) {
				usuario1.setLocalizacoes(localizacao);
				usuariosAgrupados.add(usuario1);
			}
		}
		return usuariosAgrupados;
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
			+ " where u.email = :email", Usuario.class)
			.setParameter("email", usuario.getEmail())
			.getResultList().isEmpty();
	}

	public void adiciona(Usuario usuario) {
		Condominio condominio = new Condominio();
		condominio.setId(condominioId);
		// TODO Implementar insert no identificador
		// usuario.setCondominio(condominio);
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
	}
	
	public void registra(Usuario usuario, String nickname, String senha) {
		em.getTransaction().begin();
		usuario.setNome(nickname);
		usuario.setSenha(senha);
		em.merge(usuario);
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
