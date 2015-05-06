package br.com.onlares.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.controller.UsuarioLogado;
import br.com.onlares.model.Constantes;
import br.com.onlares.model.Documento;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class DocumentoDao {

	@SuppressWarnings("unused")
	private final EntityManager em;
	@SuppressWarnings("unused")
	private final Long condominioId;
	
	@Inject
	public DocumentoDao(EntityManager em, UsuarioLogado usuarioLogado) {
		this.em = em;
		if (usuarioLogado != null && usuarioLogado.getUsuario() != null
				&& usuarioLogado.getLocalizadorAtual().getCondominio() != null) {
			this.condominioId = usuarioLogado.getLocalizadorAtual().getCondominio().getId();
		} else {
			this.condominioId = Constantes.CONDOMINIO_INEXISTENTE_ID;
		}
	}
	
	@Deprecated
	public DocumentoDao() {
		this(null, null); // para uso do CDI
	}
	
	public List<Documento> lista() {
		List<Documento> documentos = new  ArrayList<Documento>();
		return documentos;
	 }

}
