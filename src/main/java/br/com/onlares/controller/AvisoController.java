package br.com.onlares.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.onlares.dao.AvisoDao;
import br.com.onlares.model.Aviso;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class AvisoController {
	
	private final AvisoDao avisoDao;
	private final Result result;

	@Inject
	public AvisoController(AvisoDao avisoDao, Result result) {
		this.avisoDao = avisoDao;
		this.result = result;
	}
	
	public AvisoController() {
		this(null, null);
	}
	
	@Get("/aviso/lista")
	public void lista() {
		result.include("avisoList", avisoDao.listaSemTexto());
	}
	
	@Get("/aviso/visualiza/{id}")
	public void visualiza(Long id) {
		Aviso aviso = avisoDao.busca(id);
		if (aviso == null) {
			result.notFound();
		} else {
			result.include("aviso", aviso);
		}
	}
	
}