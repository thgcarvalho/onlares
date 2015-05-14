package br.com.onlares.controller;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Admin;
import br.com.onlares.bean.UsuarioLogado;
import br.com.onlares.comparador.ComparadorReserva;
import br.com.onlares.dao.EspacoDao;
import br.com.onlares.dao.ReservaDao;
import br.com.onlares.model.Espaco;
import br.com.onlares.model.Reserva;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class AdminReservaController {

	private final ReservaDao reservaDao;
	private final EspacoDao espacoDao;
	private final Validator validator;
	private final Result result;

	@Inject
	public AdminReservaController(UsuarioLogado usuarioLogado, EspacoDao espacoDao, ReservaDao reservaDao, Validator validator, Result result) {
		this.reservaDao = reservaDao;
		this.espacoDao = espacoDao;
		this.validator = validator;
		this.result = result;
	}
	
	public AdminReservaController() {
		this(null, null, null, null, null);
	}
	
	@Admin
	@Get("/adminReserva/lista")
	public void lista() {
		result.include("espacoList", espacoDao.lista());
	}
	
	@Admin
	@Get("/adminReserva/listaDoCondominio")
	public void listaDoCondominio() {
		List<Reserva> reservas= reservaDao.listaDoCondominio();
		ComparadorReserva comparadorReserva = new ComparadorReserva();
		Collections.sort(reservas, comparadorReserva);
		result.include("reservaList", reservas);
	}
	
	@Admin
	@Get("/adminReserva/novo")
	public void novo() {
		Espaco espaco = new Espaco();
		espaco.setPermitirPosterior(true);
		espaco.setPermitirSemReserva(true);
		result.include("espaco", espaco);
	}
	
	@Admin
	@Post("/adminReserva/")
	public void adiciona(Espaco espaco) {
		if (checkNull(espaco.getDescricao()).equals("")) {
			validator.add(new I18nMessage("espaco.adiciona", "campo.obrigatorio", "Descrição"));
		}
		if (espaco.getAntecedenciaMaximaParaReservar() < espaco.getAntecedenciaMinimaParaReservar()) {
			validator.add(new SimpleMessage("espaco.adiciona", 
					"'Máximo de dias de antecedência para reservar'" +
					" deve ser maior que " +
					"'Mínimo de dias de antecedência para reservar'"));
		}
		
		validator.onErrorUsePageOf(this).novo();
		espacoDao.adiciona(espaco);
		result.include("notice", "Espaço adicionado com sucesso!");
		result.redirectTo(this).lista();
	}
	
	@Admin
	@Get("/adminReserva/edita/{espacoId}")
	public void edita(Long espacoId) {
		Espaco espaco = espacoDao.buscaEspaco(espacoId);
		if (espaco == null) {
			result.notFound();
		} else {
			result.include("espaco", espaco);
		}
	}
	
	@Admin
	@Put("/adminReserva")
	public void altera(Espaco espaco) {
		if (checkNull(espaco.getDescricao()).equals("")) {
			validator.add(new I18nMessage("espaco.edita", "campo.obrigatorio", "Descrição"));
		}
		if (espaco.getAntecedenciaMaximaParaReservar() < espaco.getAntecedenciaMinimaParaReservar()) {
			validator.add(new SimpleMessage("reserva.adiciona", 
					"'Máximo de dias de antecedência para reservar'" +
					" deve ser maior que " +
					"'Mínimo de dias de antecedência para reservar'"));
		}
		
		validator.onErrorUsePageOf(this).edita(espaco.getId());
		
		espacoDao.altera(espaco);
		result.include("notice", "Espaço atualizado com sucesso!");
		result.redirectTo(this).lista();
	}
	
	@Admin
	@Delete("/adminReserva/{espacoId}")
	public void remove(Long espacoId){
		System.out.println("Espaco = " + espacoId + " FOI REMOVIDO!");
		Espaco espaco = espacoDao.buscaEspaco(espacoId);
		espacoDao.removeEspaco(espaco);
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
