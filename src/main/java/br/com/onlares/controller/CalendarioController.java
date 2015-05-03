package br.com.onlares.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.onlares.dao.CalendarioDao;
import br.com.onlares.model.Calendario;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class CalendarioController {
	
	@SuppressWarnings("unused")
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
		System.out.println("loadJson");
		List<Calendario> calendario = new ArrayList<Calendario>();

		Calendario c1 = new Calendario();
		c1.setTitle("Task C1");
		c1.setStart("2015-05-28");
		c1.setEnd("2015-05-29");
		c1.setClassName("label-important");

		Calendario c2 = new Calendario();
		c2.setTitle("Task C2");
		c2.setStart("2015-05-06");
		c2.setEnd("2015-05-06");
		c2.setClassName("label-info");
		
		Calendario c3 = new Calendario();
		c3.setTitle("Task C3");
		c3.setStart("2015-05-16");
		c3.setEnd("2015-05-16");
		c3.setClassName("/label-info/");

		calendario.add(c1);
		calendario.add(c2);
		calendario.add(c3);

		result.use(json()).withoutRoot().from(calendario).serialize();
	}
	
}