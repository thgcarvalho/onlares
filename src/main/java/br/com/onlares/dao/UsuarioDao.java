package br.com.onlares.dao;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.onlares.comparador.ComparadorUsuarioNomeCompleto;
import br.com.onlares.controller.UsuarioLogado;
import br.com.onlares.model.Condominio;
import br.com.onlares.model.Constantes;
import br.com.onlares.model.Localizador;
import br.com.onlares.model.Unidade;
import br.com.onlares.model.Usuario;
import br.com.onlares.util.MD5Hashing;

public class UsuarioDao {

	private final EntityManager em;
	private final Long condominioId;
	
	@Inject
	public UsuarioDao(EntityManager em, UsuarioLogado usuarioLogado) {
		this.em = em;
		if (usuarioLogado != null && usuarioLogado.getUsuario() != null
				&& usuarioLogado.getLocalizadorAtual().getCondominio() != null) {
			this.condominioId = usuarioLogado.getLocalizadorAtual().getCondominio().getId();
		} else {
			this.condominioId = Constantes.CONDOMINIO_INEXISTENTE_ID;
		}
	}
	
	@Deprecated
	public UsuarioDao() {
		this(null, null); // para uso do CDI
	}
	
	public List<Usuario> lista() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		List<Localizador> localizadores = em.createQuery("select l from Localizador l"
				+ " where l.condominio.id = :condominioId"
				+ " and l.usuario.id <> :usuarioId"
				+ " order by l.usuario.nome", Localizador.class)
				.setParameter("condominioId", condominioId)
				.setParameter("usuarioId", Constantes.USUARIO_INEXISTENTE_ID).getResultList();
		usuarios = agrupaUnidades(localizadores);
		ComparadorUsuarioNomeCompleto comparadorUsuarioNomeCompleto = new ComparadorUsuarioNomeCompleto();
		Collections.sort(usuarios, comparadorUsuarioNomeCompleto);
		return usuarios;
	}
	
	private List<Usuario> agrupaUnidades(List<Localizador> localizadores) {
		List<Usuario> usuariosAgrupados = new ArrayList<Usuario>();
		Usuario usuario1;
		Usuario usuario2;
		String localizacao;
		boolean found = false;
		for (Localizador localizador : localizadores) {
			
			if (adminGlobal(localizador)) {
				continue;
			}
			found = false;
			usuario1 = localizador.getUsuario();
           	if (localizador.getUnidade().getId().equals(Constantes.UNIDADE_NAO_RELACIONADA_ID)) {
				localizacao = "";
			} else {
				localizacao = localizador.getUnidade().getDescricao();
			}
			// agrupa por unidade
			for (Iterator<Usuario> iterator = usuariosAgrupados.iterator(); iterator.hasNext();) {
				usuario2 = iterator.next();
				if (usuario2.getEmail().equals(usuario1.getEmail())) {
					found = true;
					usuario2.setLocalizacoes(usuario2.getLocalizacoes() + " | " + localizacao);
					break;
				}
			}
			if (!found) {
				usuario1.setLocalizacoes(localizacao);
				usuariosAgrupados.add(usuario1);
			}
		}
		return usuariosAgrupados;
	}
	
	private boolean adminGlobal(Localizador localizador) {
		if (localizador.getUsuario().getEmail().endsWith("@grandev.com.br")) {
			if (localizador.getCondominio().getId().compareTo(1L) != 0) {
				return true;
			}
		}
		return false;
	}
	
	private void agrupaUnidades(Usuario usuario) {
		// TODO avaliar unidades não relacionadas
		List<Localizador> localizadores = em.createQuery("select l from Localizador l"
				+ " where l.usuario.id = :usuarioId"
				+ " and l.condominio.id = :condominioId", Localizador.class)
				.setParameter("usuarioId", usuario.getId())
				.setParameter("condominioId", condominioId).getResultList();
		
		List<Unidade> unidades = new ArrayList<Unidade>();
		String localizacao;
		for (Localizador localizador : localizadores) {
			localizacao = localizador.getUnidade().getDescricao();
			// agrupa por unidade
			if (usuario.getLocalizacoes() == null) {
				usuario.setLocalizacoes(localizacao);
			} else {
				usuario.setLocalizacoes(usuario.getLocalizacoes() + " | " + localizacao);
			}
			unidades.add(localizador.getUnidade());
		}
		usuario.setUnidades(unidades);
	}
	
	public List<Usuario> listaTodos() {
		return em.createQuery("select u from Usuario u where u.id <> :usuarioId", Usuario.class)
				.setParameter("usuarioId", Constantes.USUARIO_INEXISTENTE_ID).getResultList();
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
			agrupaUnidades(usuario);
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

	public void adiciona(Usuario usuario, List<Long> unidades) {
		Localizador localizador;
		Condominio condominio = new Condominio();
		condominio.setId(condominioId);
		
		em.persist(usuario);
		if (unidades == null || unidades.isEmpty()) {
			Unidade unidade = new Unidade();
			unidade.setId(Constantes.UNIDADE_NAO_RELACIONADA_ID);
			localizador = new Localizador();
			localizador.setCondominio(condominio);
			localizador.setUsuario(usuario);
			localizador.setUnidade(unidade);
			em.persist(localizador);
		} else {
			for (Long unidadeId : unidades) {
				Unidade unidade = new Unidade();
				unidade.setId(unidadeId);
				localizador = new Localizador();
				localizador.setCondominio(condominio);
				localizador.setUsuario(usuario);
				localizador.setUnidade(unidade);
				em.persist(localizador);
			}
		}
	}
	
	public void registra(Usuario usuario, String nome, String senha) {
		usuario.setNome(nome);
		usuario.setSenha(senha);
		em.merge(usuario);
	}
	
	public void altera(Usuario usuario) {
		em.merge(usuario);
	}
	
	@SuppressWarnings("unchecked")
	private List<Localizador> localizadoresDoUsuario(Usuario usuario) {
		return em.createQuery("select l from Localizador l"
				+ " where l.usuario.id = :usuarioId"
				+ " and l.condominio.id = :condominioId")
				.setParameter("usuarioId", usuario.getId())
				.setParameter("condominioId", condominioId).getResultList();
	}
	
	public void altera(Usuario usuario, List<Long> unidades) {
		List<Localizador> localizadoresDB = localizadoresDoUsuario(usuario);
		List<Localizador> insertList = new ArrayList<Localizador>();
		List<Localizador> removeList = new ArrayList<Localizador>();
		Localizador localizador;
		Condominio condominio = new Condominio();
		condominio.setId(condominioId);
		boolean encontrado;
		
		em.merge(usuario);
		
		if (unidades == null) {
			unidades = new ArrayList<Long>();
		}
		
		// Selecionados que não estão no DB (INSERT)
		encontrado = false;
		for (Long unidadeId : unidades) {
			encontrado = false;
			Unidade unidade = new Unidade();
			unidade.setId(unidadeId);
			localizador = new Localizador();
			localizador.setCondominio(condominio);
			localizador.setUsuario(usuario);
			localizador.setUnidade(unidade);
			for (Localizador localizadorDB : localizadoresDB) {
				if (localizadorDB.equals(localizador)) {
					encontrado = true;
					break;
				}
			}
			if (!encontrado) {
				insertList.add(localizador);
			}
		}
		
		// DB que não estão nos Selecionados (DELETE)
		encontrado = false;
		for (Localizador localizadorDB : localizadoresDB) {
			encontrado = false;
			for (Long unidadeId : unidades) {
				Unidade unidade = new Unidade();
				unidade.setId(unidadeId);
				localizador = new Localizador();
				localizador.setCondominio(condominio);
				localizador.setUsuario(usuario);
				localizador.setUnidade(unidade);
				if (localizadorDB.equals(localizador)) {
					encontrado = true;
					break;
				}
			}
			if (!encontrado) {
				removeList.add(localizadorDB);
			}
		}
		for (Localizador locInsert : insertList) {
			em.persist(locInsert);
		}
		for (Localizador locRemove : removeList) {
			em.remove(locRemove);
		}
		// Todos foram removidos - Adiciona unidade nao relacionada
		if ((removeList.size() > 0 && insertList.size() == 0)
				&& (removeList.size() == localizadoresDB.size())) {
//			boolean unidadeNaoRelacionadaJaExistente = false;
//			for (Localizador loc : localizadoresDB) {
//				if (loc.getUnidade().getId().equals(Constantes.UNIDADE_NAO_RELACIONADA_ID)) {
//					unidadeNaoRelacionadaJaExistente = true;
//					break;
//				}
//			} 
//			if (!unidadeNaoRelacionadaJaExistente) {
				Localizador locUnidNaoRelac = new Localizador();
				Unidade unidade = new Unidade();
				unidade.setId(Constantes.UNIDADE_NAO_RELACIONADA_ID);
				locUnidNaoRelac = new Localizador();
				locUnidNaoRelac.setCondominio(condominio);
				locUnidNaoRelac.setUsuario(usuario);
				locUnidNaoRelac.setUnidade(unidade);
				em.persist(locUnidNaoRelac);
//			}
		}
	}
	
	public void remove(Usuario usuario) {
		List<Localizador> localizadores = em.createQuery("select l from Localizador l"
				+ " where l.usuario.id = :usuarioId"
				+ " and l.condominio.id = :condominioId", Localizador.class)
				.setParameter("usuarioId", usuario.getId())
				.setParameter("condominioId", condominioId).getResultList();
		if (mesmoCondominio(usuario)) {
			for (Localizador localizador : localizadores) {
				em.remove(localizador);
			}
			em.remove(busca(usuario));
		}
	}
	
	private boolean mesmoCondominio(Usuario usuario) {
		boolean mesmoCondominio = !em.createQuery("select l.usuario from Localizador l"
				+ " where l.condominio.id = :condominioId", Usuario.class)
				.setParameter("condominioId", condominioId).getResultList().isEmpty();
		if (mesmoCondominio) {
			return true;
		} else {
			System.out.println("CONDOMÍNIOS DIFERENTES: usuario="+ usuario.getNome()
					+ " (" + usuario.getId() + ") != " + condominioId);
			return false;
		}
	}

}
