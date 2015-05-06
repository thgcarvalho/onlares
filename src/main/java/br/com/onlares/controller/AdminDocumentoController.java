package br.com.onlares.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.onlares.annotations.Admin;
import br.com.onlares.dao.DocumentoDao;
import br.com.onlares.model.Documento;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class AdminDocumentoController {
	
	private final DocumentoDao documentoDao;
	private final Result result;

	@Inject
	public AdminDocumentoController(DocumentoDao documentoDao, Result result) {
		this.documentoDao = documentoDao;
		this.result = result;
	}
	
	@Deprecated
	public AdminDocumentoController() {
		this(null, null);
	}
	
	@Admin
	@Get("/adminDocumento/lista")
	public void lista() {
		result.include("documentoList", documentoDao.lista());
	}
	
	@Admin
	@Get("/adminDocumento/novo")
	public void novo() {
	}
	
	@Admin
	@Post("/adminDocumento/")
	public void adiciona(Documento docuemnto) {
//		if (checkNull(veiculo.getTipo()).equals("")) {
//			validator.add(new I18nMessage("veiculo.adiciona", "campo.obrigatorio", "Tipo"));
//		}
//		validator.onErrorUsePageOf(this).novo();
//	
//		veiculo.setTipo(veiculo.getTipo().toUpperCase());
//		if (veiculo.getPlaca() != null) {
//			veiculo.setPlaca(veiculo.getPlaca().toUpperCase());
//		}
//		veiculoDao.adiciona(veiculo);
		result.include("notice", "Documento adicionado com sucesso!");
		result.redirectTo(this).lista();
	}
	
	
}