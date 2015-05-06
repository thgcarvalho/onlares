package br.com.onlares.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.onlares.controller.UsuarioLogado;
import br.com.onlares.model.Documento;
import br.com.onlares.model.LocalizadorDoUsuarioLogado;

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
		this.condominioId = LocalizadorDoUsuarioLogado.getCondominioIdAtual(usuarioLogado);
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
