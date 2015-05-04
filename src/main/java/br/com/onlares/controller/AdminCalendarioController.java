package br.com.onlares.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Admin;
import br.com.onlares.dao.CalendarioDao;
import br.com.onlares.model.Calendario;
import br.com.onlares.util.DataUtil;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class AdminCalendarioController {
	
	private final CalendarioDao calendarioDao;
	private final Result result;
	private final Validator validator;

	@Inject
	public AdminCalendarioController(CalendarioDao calendarioDao, Result result, Validator validator) {
		this.calendarioDao = calendarioDao;
		this.result = result;
		this.validator = validator;
	}
	
	@Deprecated
	public AdminCalendarioController() {
		this(null, null, null);
	}
	
	@Admin
	@Get("/adminCalendario/index")
	public void index() {
	}
	
	@Admin
	@Get("/adminCalendario/load.json")
	public void loadJson() {
		List<Calendario> calendarios = calendarioDao.lista();
		result.use(json()).withoutRoot().from(calendarios).serialize();
	}
	
	@Admin
	@Post("/adminCalendario/adicionar")
	public void adicionar(String title, String start, String end) {
		String startFormatado = null;
		String endFormatado = null;
		String startHoraFormatada = null;
		String endHoraFormatada = null;
		try {
			Long lStart = Long.parseLong(start);
			Long lEnd = Long.parseLong(end);
			Date dStart = new Date(lStart);
			Date dEnd = new Date(lEnd);
			Calendar cStart = Calendar.getInstance();
			Calendar cEnd = Calendar.getInstance();
			cStart.setTime(dStart);
			cEnd.setTime(dEnd);
			cStart.add(Calendar.HOUR_OF_DAY, 3);
			cEnd.add(Calendar.HOUR_OF_DAY, 3);
			startFormatado = DataUtil.formatarTudoInverso(cStart);
			endFormatado = DataUtil.formatarTudoInverso(cEnd);
			startHoraFormatada = DataUtil.formatarHora(cStart);
			endHoraFormatada = DataUtil.formatarHora(cEnd);
		} catch (Exception exp) {
			validator.add(new SimpleMessage("calendario.adiciona", exp.getMessage()));
			exp.printStackTrace();
		}
		Calendario calendario = new Calendario();
		calendario.setTitle(title);
		calendario.setStart(startFormatado);
		calendario.setEnd(endFormatado);
		if (startHoraFormatada.equals("00:00") && endHoraFormatada.equals("00:00")) {
			calendario.setAllDay(true);
		} else {
			calendario.setAllDay(false);
		}
		calendarioDao.adiciona(calendario);
		result.include("notice", "Evento cadastrado com sucesso!");
		result.redirectTo(this).index();
	}
	
	@Admin
	@Delete("/adminCalendario/{calendarioId}")
	public void remove(Long calendarioId){
		System.out.println("CALENDARIO = " + calendarioId + " FOI REMOVIDO!");
		Calendario calendario = calendarioDao.busca(calendarioId);
		calendarioDao.remove(calendario);
		result.nothing();
	}
	
}