package br.com.onlares.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.onlares.dao.DocumentoDao;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class DocumentoController {
	
	private final DocumentoDao documentoDao;
	private final Result result;

	@Inject
	public DocumentoController(DocumentoDao documentoDao, Result result) {
		this.documentoDao = documentoDao;
		this.result = result;
	}
	
	@Deprecated
	public DocumentoController() {
		this(null, null);
	}
	
	@Get("/documento/lista")
	public void lista() {
		result.include("documentoList", documentoDao.lista());
	}
	
	
}