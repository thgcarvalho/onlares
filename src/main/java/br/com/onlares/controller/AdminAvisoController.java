package br.com.onlares.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Admin;
import br.com.onlares.dao.AvisoDao;
import br.com.onlares.model.Aviso;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class AdminAvisoController {
	
	private final AvisoDao avisoDao;
	private final Validator validator;
	private final Result result;

	@Inject
	public AdminAvisoController(AvisoDao avisoDao, Validator validator, Result result) {
		this.avisoDao = avisoDao;
		this.validator = validator;
		this.result = result;
	}
	
	public AdminAvisoController() {
		this(null, null, null);
	}
	
	@Admin
	@Get("/adminAviso/lista")
	public void lista() {
		result.include("avisoList", avisoDao.listaSemTexto());
	}
	
	@Admin
	@Get("/adminAviso/novo")
	public void novo() {
	}
	
	@Admin
	@Post("/adminAviso/")
	public void adiciona(Aviso aviso) {
        if (checkNull(aviso.getTitulo()).equals("")) {
   			validator.add(new I18nMessage("aviso.adiciona", "campo.obrigatorio", "Título"));
   		}
        if (checkNull(aviso.getTexto()).equals("")) {
   			validator.add(new I18nMessage("aviso.adiciona", "campo.obrigatorio", "Texto"));
   		}
        
		validator.onErrorUsePageOf(this).novo();
		
		avisoDao.adiciona(aviso);
		result.include("notice", "Aviso adicionado com sucesso!");
		result.redirectTo(this).lista();
	}
	
	@Admin
	@Get("/adminAviso/visualiza/{id}")
	public void visualiza(Long id) {
		Aviso aviso = avisoDao.busca(id);
		if (aviso == null) {
			result.notFound();
		} else {
			result.include("aviso", aviso);
		}
	}
	
	@Admin
	@Delete("/adminAviso/{id}")
	public void remove(Long id) {
		Aviso aviso = avisoDao.busca(id);
		avisoDao.remove(aviso);
		result.nothing();
	}
	
	private String checkNull(String value) {
		if (value == null) {
			return ("");
		} else {
			return (value.trim());
		}
	}
}