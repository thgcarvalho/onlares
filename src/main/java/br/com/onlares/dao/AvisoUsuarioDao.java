package br.com.onlares.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.bean.UsuarioLogado;
import br.com.onlares.model.AvisoUsuario;
import br.com.onlares.model.LocalizadorDoUsuarioLogado;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class AvisoUsuarioDao {

	private final EntityManager em;
	@SuppressWarnings("unused")
	private final Long condominioId;
	
	@Inject
	public AvisoUsuarioDao(EntityManager em, UsuarioLogado usuarioLogado) {
		this.em = em;
		this.condominioId = LocalizadorDoUsuarioLogado.getCondominioIdAtual(usuarioLogado);
	}
	
	@Deprecated
	public AvisoUsuarioDao() {
		this(null, null); // para uso do CDI
	}
	
//	public List<AvisoUsuario> listaDoAviso(Long avisoId) {
//		List<AvisoUsuario> unidadeAvisoUsuarios = em.createQuery("SELECT au FROM AvisoUsuario au"
//				+ " where au.aviso.id = :avisoId", AvisoUsuario.class)
//				.setParameter("avisoId", avisoId).getResultList();
//		return unidadeAvisoUsuarios;
//	}
	
	public List<AvisoUsuario> listaDoUsuario(Long usuarioId) {
		List<AvisoUsuario> unidadeAvisoUsuarios = em.createQuery("SELECT au FROM AvisoUsuario au"
				+ " where au.usuario.id = :usuarioId", AvisoUsuario.class)
				.setParameter("usuarioId", usuarioId).getResultList();
		return unidadeAvisoUsuarios;
	}
	
//	public List<AvisoUsuario> listaDoCondominio() {
//		List<AvisoUsuario> autorizacoes;
//		try {
//			autorizacoes = em.createQuery("select au from AvisoUsuario at"
//					+ " where au.aviso.condominio.id = :condominioId", AvisoUsuario.class)
//					.setParameter("condominioId", condominioId).getResultList();
//		} catch (EntityNotFoundException e) {
//			autorizacoes = new ArrayList<AvisoUsuario>();
//		}
//		return autorizacoes;
//	}
	
	public int quantidadeNaoVisualizada(Long usuarioId) {
		Number number = em.createQuery("SELECT COUNT(au) FROM AvisoUsuario au"
				+ " WHERE au.usuario.id = :usuarioId"
				+ " AND au.visualizado = :visualizado", Number.class)
				.setParameter("usuarioId", usuarioId)
				.setParameter("visualizado", false).getSingleResult();
		return number.intValue();
	}
	
	
}
