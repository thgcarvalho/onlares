package br.com.onlares.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.comparador.ComparadorReserva;
import br.com.onlares.dao.EspacoDao;
import br.com.onlares.dao.ReservaDao;
import br.com.onlares.model.Espaco;
import br.com.onlares.model.Reserva;
import br.com.onlares.util.DataUtil;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class ReservaController {

	private final ReservaDao reservaDao;
	private final EspacoDao espacoDao;
	private final Validator validator;
	private final Result result;
	private final Long unidadeId;

	@Inject
	public ReservaController(UsuarioLogado usuarioLogado, ReservaDao reservaDao, EspacoDao espacoDao, Validator validator, Result result) {
		this.reservaDao = reservaDao;
		this.espacoDao = espacoDao;
		this.validator = validator;
		this.result = result;
		if (usuarioLogado != null && usuarioLogado.getLocalizadorAtual().getUnidade() != null) {
			this.unidadeId = usuarioLogado.getLocalizadorAtual().getUnidade().getId();
		} else {
			this.unidadeId = 0L;
		}
	}
	
	public ReservaController() {
		this(null, null, null, null, null);
	}
	
	@Get("/reserva/index")
	public void index() {
		result.include("espacoList", espacoDao.lista());
	}
	
	@Get("/reserva/lista/{espacoId}")
	public void listaDaReserva(Long espacoId) {
		Espaco espaco = espacoDao.buscaEspaco(espacoId);
		if (espaco == null) {
			result.notFound();
		} else {
			List<Reserva> listaDaReserva = reservaDao.listaDaReserva(espacoId);
			ComparadorReserva comparadorUnidadeReserva = new ComparadorReserva();
			Collections.sort(listaDaReserva, comparadorUnidadeReserva);
			result.include("reservaList", listaDaReserva);
			result.include("espaco", espaco);
		}
	}
	
	@Get("/reserva/listaDaUnidade/")
	public void listaDaUnidade() {
		List<Reserva> reservas= reservaDao.listaDaUnidade(this.unidadeId);
		ComparadorReserva comparadorReserva = new ComparadorReserva();
		Collections.sort(reservas, comparadorReserva);
		result.include("reservaList", reservas);
	}
	
	@Get("/reserva/novo/{espacoId}")
	public void novo(Long espacoId) {
		Espaco espaco = espacoDao.buscaEspaco(espacoId);
		if (espaco == null) {
			result.notFound();
		} else {
			result.include("espaco", espaco);
		}
	}
	
	@Post("/reserva/")
	public void adiciona(Reserva reserva) {
		if (reserva.getData() == null) {
			validator.add(new I18nMessage("reserva.adiciona", "campo.obrigatorio", "Data"));
		}
		if (reserva.getHora() == null) {
			validator.add(new I18nMessage("reserva.adiciona", "campo.obrigatorio", "Hora"));
		}
		
		validator.onErrorRedirectTo(this).novo(reserva.getEspaco().getId());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar hoje = Calendar.getInstance();
		DataUtil.zeraHora(hoje);
		Calendar calSel = reserva.getData();
		Calendar antMax = Calendar.getInstance();
		Calendar antMin = Calendar.getInstance();
		
		Long espacoId = reserva.getEspaco().getId();
		Espaco espaco = espacoDao.buscaEspaco(espacoId);
		int antecedenciaMaxima = espaco.getAntecedenciaMaximaParaReservar();
		int antecedenciaMinima = espaco.getAntecedenciaMinimaParaReservar();
		int reservasQuantidade = espaco.getReservasQuantidade();
		int reservasDias = espaco.getReservasDias();
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
		
		validator.onErrorRedirectTo(this).novo(reserva.getEspaco().getId());
		
		List<Reserva> unidadeReservas = reservaDao.listaReserva(reserva.getEspaco().getId());
		for (Reserva uniRes : unidadeReservas) {
			if (calSel.equals(uniRes.getData())) {
				validator.add(new SimpleMessage("reserva.adiciona", "Já existe uma reserva na data " + sdf.format(calSel.getTime())));
				break;
			}
		}
		
		validator.onErrorRedirectTo(this).novo(reserva.getEspaco().getId());
		
		if (!espaco.isPermitirPosterior()) {
			for (Reserva uniRes : unidadeReservas) {
				if (uniRes.getUnidade().getId().compareTo(reserva.getUnidade().getId()) == 0) {
					validator.add(new SimpleMessage("reserva.adiciona", "A unidade já possui uma reserva neste espaço"));
					break;
				}
			}
		}
		
		validator.onErrorRedirectTo(this).novo(reserva.getEspaco().getId());
		
		int quantidadeDeReservas = 0;
		ComparadorReserva comparadorUnidadeReserva = new ComparadorReserva();
		Collections.sort(unidadeReservas, comparadorUnidadeReserva);
		for (Reserva uniRes : unidadeReservas) {
			if (uniRes.getUnidade().getId().compareTo(reserva.getUnidade().getId()) == 0) {
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
		
		validator.onErrorRedirectTo(this).novo(reserva.getEspaco().getId());
		
		reservaDao.reserva(reserva);
		result.include("notice", "Reserva realizada com sucesso!");
		result.redirectTo(this).listaDaReserva(espacoId);;
	}
	
	@Delete("/reserva/{reservaId}")
	public void remove(Long reservaId){
		System.out.println("Reserva = " + reservaId + " FOI REMOVIDA!");
		Reserva reserva = reservaDao.buscaReserva(reservaId);
		reservaDao.removeUnidadeReserva(reserva);
		result.nothing();
	}
	
}
