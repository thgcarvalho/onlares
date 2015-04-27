package br.com.onlares.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.UniqueConstraint;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.comparador.ComparadorUnidadeReserva;
import br.com.onlares.dao.ReservaDao;
import br.com.onlares.model.Reserva;
import br.com.onlares.model.UnidadeReserva;
import br.com.onlares.util.DataUtil;

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
	
	@Get("/reserva/lista/{reservaId}")
	public void listaDaReserva(Long reservaId) {
		Reserva reserva = dao.buscaReserva(reservaId);
		if (reserva == null) {
			result.notFound();
		} else {
			List<UnidadeReserva> listaDaReserva = dao.listaDaReserva(reservaId);
			ComparadorUnidadeReserva comparadorUnidadeReserva = new ComparadorUnidadeReserva();
			Collections.sort(listaDaReserva, comparadorUnidadeReserva);
			result.include("unidadeReservaList", listaDaReserva);
			result.include("reserva", reserva);
		}
	}
	
	@Get("/reserva/listaDaUnidade/")
	public void listaDaUnidade() {
		List<UnidadeReserva> listaDaUnidade= dao.listaDaUnidade(this.unidadeId);
		ComparadorUnidadeReserva comparadorUnidadeReserva = new ComparadorUnidadeReserva();
		Collections.sort(listaDaUnidade, comparadorUnidadeReserva);
		result.include("unidadeReservaList", listaDaUnidade);
	}
	
	@Get("/reserva/novo/{reservaId}")
	public void novo(Long reservaId) {
		Reserva reserva = dao.buscaReserva(reservaId);
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
		if (unidadeReserva.getData() == null) {
			validator.add(new I18nMessage("reserva.adiciona", "campo.obrigatorio", "Data"));
		}
		if (unidadeReserva.getHora() == null) {
			validator.add(new I18nMessage("reserva.adiciona", "campo.obrigatorio", "Hora"));
		}
		
		validator.onErrorRedirectTo(this).novo(unidadeReserva.getReserva().getId());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar hoje = Calendar.getInstance();
		DataUtil.zeraHora(hoje);
		Calendar calSel = unidadeReserva.getData();
		Calendar antMax = Calendar.getInstance();
		Calendar antMin = Calendar.getInstance();
		
		Long reservaId = unidadeReserva.getReserva().getId();
		Reserva reserva = dao.buscaReserva(reservaId);
		int antecedenciaMaxima = reserva.getAntecedenciaMaximaParaReservar();
		int antecedenciaMinima = reserva.getAntecedenciaMinimaParaReservar();
		int reservasQuantidade = reserva.getReservasQuantidade();
		int reservasDias = reserva.getReservasDias();
		antMax.add(Calendar.DAY_OF_MONTH, antecedenciaMaxima);
		DataUtil.zeraHora(antMax);
		antMin.add(Calendar.DAY_OF_MONTH, antecedenciaMinima);
		DataUtil.zeraHora(antMin);
		
		if (antecedenciaMaxima > 0 && calSel.after(antMax)) {
			validator.add(new SimpleMessage("reserva.adiciona", "Data máxima é " + sdf.format(antMax.getTime())));
		}
		
		if (antecedenciaMinima > 0 && calSel.before(antMin)) {
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
		
		if (!reserva.isPermitirPosterior()) {
			for (UnidadeReserva uniRes : unidadeReservas) {
				if (uniRes.getUnidade().getId().compareTo(unidadeReserva.getUnidade().getId()) == 0) {
					validator.add(new SimpleMessage("reserva.adiciona", "A unidade já possui uma reserva neste espaço"));
					break;
				}
			}
		}
		
		validator.onErrorRedirectTo(this).novo(unidadeReserva.getReserva().getId());
		
		int quantidadeDeReservas = 0;
		ComparadorUnidadeReserva comparadorUnidadeReserva = new ComparadorUnidadeReserva();
		Collections.sort(unidadeReservas, comparadorUnidadeReserva);
		for (UnidadeReserva uniRes : unidadeReservas) {
			if (uniRes.getUnidade().getId().compareTo(unidadeReserva.getUnidade().getId()) == 0) {
				quantidadeDeReservas++;
			}
			if (quantidadeDeReservas == reservasQuantidade) {
				if (uniRes.getData().compareTo(hoje) >= 0) {
					validator.add(new SimpleMessage("reserva.adiciona", "A unidade pode realizar no máximo " 
							+ reservasQuantidade + " no período de " + reservasDias + " dias"));
				}
				break;
			}
		}
		
		validator.onErrorRedirectTo(this).novo(unidadeReserva.getReserva().getId());
		
		dao.reserva(unidadeReserva);
		result.include("notice", "Reserva realizada com sucesso!");
		result.redirectTo(this).listaDaReserva(reservaId);;
	}
	
	@Delete("/reserva/{unidadeReservaId}")
	public void remove(Long unidadeReservaId){
		System.out.println("UnidadeReserva = " + unidadeReservaId + " FOI REMOVIDA!");
		UnidadeReserva unidadeReserva = dao.buscaUnidadeReserva(unidadeReservaId);
		dao.removeUnidadeReserva(unidadeReserva);
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
