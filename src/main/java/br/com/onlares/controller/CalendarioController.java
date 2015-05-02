package br.com.onlares.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.onlares.model.Calendario;
import br.com.onlares.model.Reserva;
import br.com.onlares.service.ColetorDeAnuncio;

import com.google.gson.Gson;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class CalendarioController {
	
	private final ColetorDeAnuncio coletorDeAnuncio;
	private final Result result;

	@Inject
	public CalendarioController(ColetorDeAnuncio coletorDeAnuncio, Result result) {
		this.coletorDeAnuncio = coletorDeAnuncio;
		this.result = result;
	}
	
	@Deprecated
	public CalendarioController() {
		this(null, null);
	}
	
	@Get("/calendario/index")
	public void index() {
		result.include("anuncioList", coletorDeAnuncio.getAnuncios());
	}
	
	@Get("/calendario/read.json")
	public void readJson() {
//	[
//	  {
//		title: 'All Day Event',
//		start: new Date(y, m, 1),
//		className: 'label-important'
//	  },
//	  {
//		title: 'Long Event',
//		start: moment().subtract(5, 'days').format('YYYY-MM-DD'),
//		end: moment().subtract(1, 'days').format('YYYY-MM-DD'),
//		className: 'label-success'
//	  },
//	  {
//		title: 'Some Event',
//		start: new Date(y, m, d-3, 16, 0),
//		allDay: false,
//		className: 'label-info'
//	  }
//	]
	//		result.use(json()).from(calendario).serialize();
	}
	
	@Get("/calendario/load.json")
	public void loadJson() {
		System.out.println("testeVraptor");
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
	
//	protected void doGet(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		List l = new ArrayList();
//
//		CalendarDTO c = new CalendarDTO();
//		c.setId(1);
//		c.setStart("2013-07-28");
//		c.setEnd("2013-07-29");
//		c.setTitle("Task in Progress");
//
//		CalendarDTO d = new CalendarDTO();
//		c.setId(2);
//		c.setStart("2013-07-26");
//		c.setEnd("2013-08-28");
//		c.setTitle("Task in Progress");
//
//		l.add(c);
//		l.add(d);
//
//		response.setContentType("application/json");
//		response.setCharacterEncoding("UTF-8");
//		PrintWriter out = response.getWriter();
//		out.write(new Gson().toJson(l));
//	}
}