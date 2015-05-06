package br.com.onlares.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.onlares.dao.CalendarioDao;
import br.com.onlares.model.Calendario;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class CalendarioController {
	
	private final CalendarioDao calendarioDao;
	private final Result result;

	@Inject
	public CalendarioController(CalendarioDao calendarioDao, Result result) {
		this.calendarioDao = calendarioDao;
		this.result = result;
	}
	
	@Deprecated
	public CalendarioController() {
		this(null, null);
	}
	
	@Get("/calendario/index")
	public void index() {
	}
	
	@Get("/calendario/load.json")
	public void loadJson() {
		List<Calendario> calendarios = calendarioDao.lista();
		result.use(json()).withoutRoot().from(calendarios).serialize();
	}
	
}