package br.com.onlares.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.onlares.dao.AnuncioDao;
import br.com.onlares.model.Anuncio;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class AnuncioController {

	private final AnuncioDao anuncioDao;
	private final Result result;

	@Inject
	public AnuncioController(AnuncioDao anuncioDao, Result result) {
		this.anuncioDao = anuncioDao;
		this.result = result;
	}
	
	public AnuncioController() {
		this(null, null);
	}

	@Get
	public void lista() {
		result.include("anuncioList", anuncioDao.lista());
	}
	
	@Get("/anuncio/visualiza/{anuncioId}")
	public void visualiza(Long anuncioId) {
		Anuncio anuncio = anuncioDao.buscaPorId(anuncioId);
		if (anuncio == null) {
			result.notFound();
		} else {
			result.include("anuncio", anuncio);
		}
	}
}
