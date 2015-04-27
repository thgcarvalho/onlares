package br.com.onlares.controller;

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
import br.com.onlares.dao.ReservaDao;
import br.com.onlares.model.Reserva;

@Controller
public class AdminReservaController {

	private final ReservaDao reservaDao;
	private final Validator validator;
	private final Result result;

	@Inject
	public AdminReservaController(UsuarioLogado usuarioLogado, ReservaDao reservaDao, Validator validator, Result result) {
		this.reservaDao = reservaDao;
		this.validator = validator;
		this.result = result;
	}
	
	public AdminReservaController() {
		this(null, null, null, null);
	}
	
	@Admin
	@Get("/adminReserva/lista")
	public void lista() {
		result.include("reservaList", reservaDao.lista());
	}
	
	@Admin
	@Get("/adminReserva/novo")
	public void novo() {
		Reserva reserva = new Reserva();
		reserva.setPermitirPosterior(true);
		reserva.setPermitirSemReserva(true);
		result.include("reserva", reserva);
	}
	
	@Admin
	@Post("/adminReserva/")
	public void adiciona(Reserva reserva) {
		if (checkNull(reserva.getDescricao()).equals("")) {
			validator.add(new I18nMessage("reserva.adiciona", "campo.obrigatorio", "Descrição"));
		}
		if (reserva.getAntecedenciaMaximaParaReservar() < reserva.getAntecedenciaMinimaParaReservar()) {
			validator.add(new SimpleMessage("reserva.adiciona", 
					"'Máximo de dias de antecedência para reservar'" +
					" deve ser maior que " +
					"'Mínimo de dias de antecedência para reservar'"));
		}
		
		validator.onErrorUsePageOf(this).novo();
	
		reservaDao.adiciona(reserva);
		result.include("notice", "Veículo adicionado com sucesso!");
		result.redirectTo(this).lista();
	}
	
	@Admin
	@Get("/adminReserva/edita/{reservaId}")
	public void edita(Long reservaId) {
		Reserva reserva = reservaDao.buscaReserva(reservaId);
		if (reserva == null) {
			result.notFound();
		} else {
			result.include("reserva", reserva);
		}
	}
	
	@Admin
	@Put("/adminReserva")
	public void altera(Reserva reserva) {
		if (checkNull(reserva.getDescricao()).equals("")) {
			validator.add(new I18nMessage("reserva.edita", "campo.obrigatorio", "Descrição"));
		}
		if (reserva.getAntecedenciaMaximaParaReservar() < reserva.getAntecedenciaMinimaParaReservar()) {
			validator.add(new SimpleMessage("reserva.adiciona", 
					"'Máximo de dias de antecedência para reservar'" +
					" deve ser maior que " +
					"'Mínimo de dias de antecedência para reservar'"));
		}
		
		validator.onErrorUsePageOf(this).edita(reserva.getId());
		
		reservaDao.altera(reserva);
		result.include("notice", "Veículo atualizado com sucesso!");
		result.redirectTo(this).lista();
	}
	
	@Admin
	@Delete("/adminReserva/{reservaId}")
	public void remove(Long reservaId){
		System.out.println("Veículo = " + reservaId + " FOI REMOVIDO!");
		Reserva reserva = reservaDao.buscaReserva(reservaId);
		reservaDao.removeReserva(reserva);
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
