package br.com.onlares.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.onlares.annotations.Admin;
import br.com.onlares.dao.CalendarioDao;
import br.com.onlares.model.Calendario;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class AdminCalendarioController {
	
	private final CalendarioDao calendarioDao;
	private final Result result;

	@Inject
	public AdminCalendarioController(CalendarioDao calendarioDao, Result result) {
		this.calendarioDao = calendarioDao;
		this.result = result;
	}
	
	@Deprecated
	public AdminCalendarioController() {
		this(null, null);
	}
	
	@Admin
	@Get("/adminCalendario/index")
	public void index() {
	}
	
	@Admin
	@Get("/adminCalendario/load.json")
	public void loadJson() {
		System.out.println("loadJson");
		List<Calendario> calendarios = calendarioDao.lista();
		result.use(json()).withoutRoot().from(calendarios).serialize();
	}
	
	@Admin
	@Post("/adminCalendario/adicionar")
	public void adicionar(String title, String start, String end) {
		Long lStart = Long.parseLong(start);
		Long lEnd = Long.parseLong(end);
		System.out.println("ADICIONAR");
		System.out.println("title=" + title + " end=" + end + " start=" + start);
//		Calendar start = Calendar.getInstance();
//		start.setTime(new Date());
		SimpleDateFormat sdfyyyyMMdd = new SimpleDateFormat("yyyy-MM-dd"); 
		Date dStart = new Date(lStart);
		Date dEnd = new Date(lEnd);
		
		Calendario calendario = new Calendario();
		calendario.setTitle(title);
		calendario.setStart(sdfyyyyMMdd.format(dStart));
		calendario.setEnd(sdfyyyyMMdd.format(dEnd));
		calendarioDao.adiciona(calendario);
		result.include("notice", "Evento cadastrado com sucesso!");
		result.nothing();
	}
	
//	@Get("/adminCalendario/read.json")
//	public void readJson() {
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
//	}
	
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