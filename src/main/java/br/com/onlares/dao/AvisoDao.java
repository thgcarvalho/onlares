package br.com.onlares.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.onlares.bean.UsuarioLogado;
import br.com.onlares.model.Aviso;
import br.com.onlares.model.AvisoUsuario;
import br.com.onlares.model.Condominio;
import br.com.onlares.model.LocalizadorDoUsuarioLogado;
import br.com.onlares.model.Usuario;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class AvisoDao {

	private final EntityManager em;
	private final Long condominioId;
	private final MoradorDao moradorDao;
	
	@Inject
	public AvisoDao(EntityManager em, UsuarioLogado usuarioLogado) {
		this.em = em;
		this.condominioId = LocalizadorDoUsuarioLogado.getCondominioIdAtual(usuarioLogado);
		this.moradorDao = new MoradorDao(em, usuarioLogado);
	}
	
	@Deprecated
	public AvisoDao() {
		this(null, null); // para uso do CDI
	}
	
	public List<Aviso> listaSemTexto() {
		List<Aviso> avisos = new ArrayList<Aviso>();
		Aviso aviso;
		String consulta = "SELECT a.id, a.titulo FROM Aviso a"
				+ " WHERE a.condominio.id = :condominioId";
		TypedQuery<Object[]> query = em.createQuery(consulta, Object[].class);
		query.setParameter("condominioId", condominioId);
		List<Object[]> results = query.getResultList();
		
		for (Object[] result : results) {
			aviso = new Aviso();
			aviso.setId((Long)result[0]);
			aviso.setTitulo((String)result[1]);
			avisos.add(aviso);
		}
		return avisos;
	}
	
	public List<Usuario> listaUsuariosRegistrados() {
		return moradorDao.listaRegistrados();
	}
	
	public void adiciona(Aviso aviso) {
		Condominio condominio = new Condominio();
		condominio.setId(condominioId);
		aviso.setCondominio(condominio);
		em.persist(aviso);
		AvisoUsuario avisoUsuario;
		for (Usuario usuario : listaUsuariosRegistrados()) {
			avisoUsuario = new AvisoUsuario();
			avisoUsuario.setAviso(aviso);
			avisoUsuario.setUsuario(usuario);
			avisoUsuario.setVisualizado(false);
			em.persist(avisoUsuario);
		}
	}
	
	public Aviso busca(Long avisoId) {
		Aviso aviso;
		String strQuery = "SELECT a FROM Aviso a"
				+ " WHERE a.id = :avisoId"
				+ " AND a.condominio.id = :condominioId";
		try {
			Query query = em.createQuery(strQuery, Aviso.class);
			query.setParameter("avisoId", avisoId);
			query.setParameter("condominioId", condominioId);
			aviso = (Aviso) query.getSingleResult();
		} catch (NoResultException nrExp) {
			aviso = null;
		}
		return aviso;
	}
	
	public void remove(Aviso aviso) {
		em.remove(aviso);
	}

}
