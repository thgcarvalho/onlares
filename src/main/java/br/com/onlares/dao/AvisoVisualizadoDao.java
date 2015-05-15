package br.com.onlares.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.bean.UsuarioLogado;
import br.com.onlares.model.Aviso;
import br.com.onlares.model.AvisoVisualizado;
import br.com.onlares.model.LocalizadorDoUsuarioLogado;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class AvisoVisualizadoDao {

	private final EntityManager em;
	private final Long usuarioId;
	
	@Inject
	public AvisoVisualizadoDao(EntityManager em, UsuarioLogado usuarioLogado) {
		this.em = em;
		this.usuarioId = LocalizadorDoUsuarioLogado.getUsuarioIdAtual(usuarioLogado);
	}
	
	@Deprecated
	public AvisoVisualizadoDao() {
		this(null, null); // para uso do CDI
	}
	
	public void adiciona(Long avisoId) {
		AvisoVisualizado avisoVisualizado = new AvisoVisualizado();
		avisoVisualizado.setAvisoId(avisoId);
		avisoVisualizado.setUsuarioId(usuarioId);
		em.persist(avisoVisualizado);
	}
	
	public boolean foiVisualizado(Long avisoId) {
		List<AvisoVisualizado> avisosVisualizados = em.createQuery("SELECT av FROM AvisoVisualizado av"
			+ " where av.usuarioId = :usuarioId", AvisoVisualizado.class)
			.setParameter("usuarioId", this.usuarioId).getResultList();
		for (AvisoVisualizado avisoVisualizado : avisosVisualizados) {
			if (avisoId.compareTo(avisoVisualizado.getAvisoId()) == 0) {
				return true;
			}
		}
		return false;
	}
	
	public void setaVisualizacao(List<Aviso> avisos) {
		List<AvisoVisualizado> avisosVisualizados = em.createQuery("SELECT av FROM AvisoVisualizado av"
			+ " where av.usuarioId = :usuarioId", AvisoVisualizado.class)
			.setParameter("usuarioId", this.usuarioId).getResultList();
		for (Aviso aviso : avisos) {
			aviso.setVisualizado(false);
			for (AvisoVisualizado avisoVisualizado : avisosVisualizados) {
				if (aviso.getId().compareTo(avisoVisualizado.getAvisoId()) == 0) {
					aviso.setVisualizado(true);
					break;
				}
			}
		}
	}
	
	public int quantidadeNaoVisualizada(Long usuarioId, Long condominioId) {
		/* Avisos */
		List<Long> avisosIds = em.createQuery("SELECT a.id FROM Aviso a"
				+ " WHERE a.condominio.id = :condominioId", Long.class)
				.setParameter("condominioId", condominioId).getResultList();
		/* AvisosUsuario */
		List<AvisoVisualizado> avisosVisualizados = em.createQuery("SELECT av FROM AvisoVisualizado av"
				+ " where av.usuarioId = :usuarioId", AvisoVisualizado.class)
				.setParameter("usuarioId", usuarioId).getResultList();
		int quantidadeNaoVisualizada = avisosIds.size();
		for (Long avisoId : avisosIds) {
			for (AvisoVisualizado avisoVisualizado : avisosVisualizados) {
				if (avisoId.compareTo(avisoVisualizado.getAvisoId()) == 0) {
					quantidadeNaoVisualizada--;
					break;
				}
			}
		}
		
		return quantidadeNaoVisualizada;
	}
	
}
