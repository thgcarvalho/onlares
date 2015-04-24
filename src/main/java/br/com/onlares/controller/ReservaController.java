package br.com.onlares.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import br.com.onlares.dao.ReservaDao;
import br.com.onlares.model.Reserva;
import br.com.onlares.model.UnidadeReserva;

@SuppressWarnings("unused")
@Controller
public class ReservaController {

	private final ReservaDao dao;
	private final Validator validator;
	private final Result result;
	private final Long unidadeId;

	@Inject
	public ReservaController(UsuarioLogado usuarioLogado, ReservaDao dao, Validator validator, Result result) {
		this.dao = dao;
		this.validator = validator;
		this.result = result;
		if (usuarioLogado != null && usuarioLogado.getLocalizadorAtual().getUnidade() != null) {
			this.unidadeId = usuarioLogado.getLocalizadorAtual().getUnidade().getId();
		} else {
			this.unidadeId = 0L;
		}
	}
	
	public ReservaController() {
		this(null, null, null, null);
	}
	
	@Get("/reserva/index")
	public void index() {
		result.include("reservaList", dao.lista());
	}
	
	@Get("/reserva/lista")
	public void lista() {
		result.include("reservaUnidadeList", dao.listaDaUnidade(this.unidadeId));
	}
	
	@Get("/reserva/novo/{reservaId}")
	public void novo(Long reservaId) {
		Reserva reserva = dao.busca(reservaId);
		if (reserva == null) {
			result.notFound();
		} else {
			result.include("reserva", reserva);
		}
	}
	
//	@Get("/reserva/novo")
//	public void novo() {
//	}
	
	@Post("/reserva/")
	public void adiciona(UnidadeReserva unidadeReserva) {
//		System.out.println(unidadeReserva.getUnidade());
//		System.out.println(unidadeReserva.getReserva());
//		System.out.println(unidadeReserva.getData());
//		System.out.println(unidadeReserva.getHora());
		if (unidadeReserva.getData() == null) {
			validator.add(new I18nMessage("reserva.adiciona", "campo.obrigatorio", "Data"));
		}
		if (unidadeReserva.getHora() == null) {
			validator.add(new I18nMessage("reserva.adiciona", "campo.obrigatorio", "Hora"));
		}
		
		validator.onErrorRedirectTo(this).novo(unidadeReserva.getReserva().getId());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar hoje = GregorianCalendar.getInstance();
		Calendar calSel = unidadeReserva.getData();
		Calendar antMax = Calendar.getInstance();
		Calendar antMin = Calendar.getInstance();
		
		Reserva reserva = dao.busca(unidadeReserva.getReserva().getId());
		antMax.add(Calendar.DAY_OF_MONTH, reserva.getAntecedenciaMaximaParaReservar());
		antMin.add(Calendar.DAY_OF_MONTH, reserva.getAntecedenciaMinimaParaReservar());
		
		if (calSel.after(antMax)) {
			validator.add(new SimpleMessage("reserva.adiciona", "Data máxima é " + sdf.format(antMax.getTime())));
		}
		if (calSel.before(antMin)) {
			validator.add(new SimpleMessage("reserva.adiciona", "Data mínima é " + sdf.format(antMin.getTime())));
		}
		
		validator.onErrorRedirectTo(this).novo(unidadeReserva.getReserva().getId());
		
		List<UnidadeReserva> unidadeReservas = dao.listaUnidadeReserva(unidadeReserva.getReserva().getId());
		for (UnidadeReserva uniRes : unidadeReservas) {
			if (calSel.equals(uniRes.getData())) {
				validator.add(new SimpleMessage("reserva.adiciona", "Já existe uma reserva na data " + sdf.format(calSel.getTime())));
				break;
			}
		}
		
		validator.onErrorRedirectTo(this).novo(unidadeReserva.getReserva().getId());
		
		dao.reserva(unidadeReserva);
		result.include("notice", "Reserva realizada com sucesso!");
		result.redirectTo(this).lista();
	}
	
	@Get("/reserva/edita/{reservaId}")
	public void edita(Long reservaId) {
//		Reserva reserva = dao.buscaNaUnidade(this.unidadeId, reservaId);
//		if (reserva == null) {
//			result.notFound();
//		} else {
//			result.include("reserva", reserva);
//		}
	}
	
	@Put("/reserva")
	public void altera(Reserva reserva) {
//		if (checkNull(reserva.getTipo()).equals("")) {
//			validator.add(new I18nMessage("reserva.edita", "campo.obrigatorio", "Tipo"));
//		}
//		
//		validator.onErrorUsePageOf(this).edita(reserva.getId());
//		
//		reserva.setTipo(reserva.getTipo().toUpperCase());
//		dao.altera(reserva);
//		result.include("notice", "Reserva atualizado com sucesso!");
//		result.redirectTo(this).lista();
	}
	
	@Delete("/reserva/{reservaId}")
	public void remove(Long reservaId){
//		System.out.println("Reserva = " + reservaId + " FOI REMOVIDA!");
//		Reserva usuario = dao.buscaNaUnidade(this.unidadeId, reservaId);
//		dao.remove(usuario);
//		result.nothing();
	}
	
	private String checkNull(String value) {
		if (value == null) {
			return ("");
		} else {
			return (value.trim());
		}
	}
	
}
