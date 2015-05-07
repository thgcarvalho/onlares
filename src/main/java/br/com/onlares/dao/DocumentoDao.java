package br.com.onlares.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.onlares.controller.UsuarioLogado;
import br.com.onlares.model.Condominio;
import br.com.onlares.model.Documento;
import br.com.onlares.model.LocalizadorDoUsuarioLogado;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class DocumentoDao {

	private final EntityManager em;
	private final Long condominioId;
	
	@Inject
	public DocumentoDao(EntityManager em, UsuarioLogado usuarioLogado) {
		this.em = em;
		this.condominioId = LocalizadorDoUsuarioLogado.getCondominioIdAtual(usuarioLogado);
	}
	
	@Deprecated
	public DocumentoDao() {
		this(null, null); // para uso do CDI
	}
	
	public List<Documento> listaSemArquivo() {
		List<Documento> documentos = new ArrayList<Documento>();
		Documento documento;
		String consulta = "SELECT d.id, d.titulo FROM Documento d";
		TypedQuery<Object[]> query = em.createQuery(consulta, Object[].class);
		List<Object[]> results = query.getResultList();
		
		for (Object[] result : results) {
			documento = new Documento();
			documento.setId((Long)result[0]);
			documento.setTitulo((String)result[1]);
			documentos.add(documento);
		}
		return documentos;
	}
	
	public void grava(Documento documento) {
		Condominio condominio = new Condominio();
		condominio.setId(condominioId);
		documento.setCondominio(condominio);
		em.persist(documento);
	}
	
	public Documento recupera(Long documentoId) {
		Documento documento;
		String strQuery = "SELECT d FROM Documento d"
				+ " WHERE d.id = :documentoId"
				+ " AND d.condominio.id = :condominioId";
		try {
			Query query = em.createQuery(strQuery, Documento.class);
			query.setParameter("documentoId", documentoId);
			query.setParameter("condominioId", condominioId);
			documento = (Documento) query.getSingleResult();
		} catch (NoResultException nrExp) {
			documento = null;
		}
		return documento;
	}
	
	public void deleta(Documento documento) {
		em.remove(documento);
	}

}
