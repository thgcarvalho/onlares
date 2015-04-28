package br.com.onlares.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.Message;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.ValidationException;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.dao.EspacoDao;
import br.com.onlares.dao.ReservaDao;
import br.com.onlares.model.Espaco;
import br.com.onlares.model.Reserva;
import br.com.onlares.model.Unidade;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class ReservaControllerTest {
	
	private Unidade unidade;
	private Espaco espaco;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Before
	public void criaUnidade() {
		Unidade unidade = new Unidade();
		unidade.setId(1L);
		unidade.setDescricao("Apartamento 603, Torre 1");
		this.unidade = unidade;
	}
	
	@Before
	public void criaReserva() {
		Espaco reserva = new Espaco();
		reserva.setDescricao("Deck");
		this.espaco = reserva;
	}
	
	@Before
	public void criaReservaController() {
		Espaco reserva = new Espaco();
		reserva.setDescricao("Deck");
		this.espaco = reserva;
	}

	// Reservar
	
	@Test
	public void deveObterUmaMensagemDeSucessoAoTentarReservar() {
		ReservaDao reservaDaoFalso = mock(ReservaDao.class);
		EspacoDao espacoDaoFalso = mock(EspacoDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		Reserva unidadeReserva = new Reserva();
		unidadeReserva.setUnidade(unidade);
		unidadeReserva.setEspaco(espaco);
		Calendar calendar = Calendar.getInstance();
		unidadeReserva.setData(calendar);
		unidadeReserva.setHora(calendar);
		
		when(espacoDaoFalso.buscaEspaco(espaco.getId())).thenReturn(espaco);
		when(reservaDaoFalso.listaReserva(espaco.getId())).thenReturn(new ArrayList<Reserva>());
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, espacoDaoFalso, validatorFalso, resultFalso);
		reservaController.adiciona(unidadeReserva);
		
		assertTrue(resultFalso.included().containsKey("notice"));
	}
	
	@Test 
	public void deveLancarValidationExceptionAoTentarReservarAposDataMáximaPermitida() {
		ReservaDao reservaDaoFalso = mock(ReservaDao.class);
		EspacoDao espacoDaoFalso = mock(EspacoDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		espaco.setAntecedenciaMaximaParaReservar(10);
		
		Reserva unidadeReserva = new Reserva();
		unidadeReserva.setUnidade(unidade);
		unidadeReserva.setEspaco(espaco);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 12);
		unidadeReserva.setData(calendar);
		unidadeReserva.setHora(calendar);
		
		when(espacoDaoFalso.buscaEspaco(espaco.getId())).thenReturn(espaco);
		when(reservaDaoFalso.listaReserva(espaco.getId())).thenReturn(new ArrayList<Reserva>());
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, espacoDaoFalso, validatorFalso, resultFalso);
		
		Calendar antMax = Calendar.getInstance();
		int antecedenciaMaxima = espaco.getAntecedenciaMaximaParaReservar();
		antMax.add(Calendar.DAY_OF_MONTH, antecedenciaMaxima);
		
	    try {
	    	reservaController.adiciona(unidadeReserva);
	    	fail();
		} catch (ValidationException e) {
	        List<Message> errors = e.getErrors();
	        assertTrue(errors.contains(new SimpleMessage("reserva.adiciona", "Data máxima é " + sdf.format(antMax.getTime()))));
	    }
	}
	
	@Test 
	public void deveLancarValidationExceptionAoTentarReservarAnteriorDataPermitida() {
		ReservaDao reservaDaoFalso = mock(ReservaDao.class);
		EspacoDao espacoDaoFalso = mock(EspacoDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		espaco.setAntecedenciaMinimaParaReservar(5);
		
		Reserva unidadeReserva = new Reserva();
		unidadeReserva.setUnidade(unidade);
		unidadeReserva.setEspaco(espaco);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 4);
		unidadeReserva.setData(calendar);
		unidadeReserva.setHora(calendar);
		
		when(espacoDaoFalso.buscaEspaco(espaco.getId())).thenReturn(espaco);
		when(reservaDaoFalso.listaReserva(espaco.getId())).thenReturn(new ArrayList<Reserva>());
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, espacoDaoFalso, validatorFalso, resultFalso);
		
		Calendar antMin = Calendar.getInstance();
		int antecedenciaMinima = espaco.getAntecedenciaMinimaParaReservar();
		antMin.add(Calendar.DAY_OF_MONTH, antecedenciaMinima);
		
	    try {
	    	reservaController.adiciona(unidadeReserva);
	    	fail();
		} catch (ValidationException e) {
	        List<Message> errors = e.getErrors();
	        assertTrue(errors.contains(new SimpleMessage("reserva.adiciona", "Data mínima é " + sdf.format(antMin.getTime()))));
	    }
	}
	
	@Test
	public void deveLancarValidationExceptionAoTentarReservarDataJaReservada() {
		ReservaDao reservaDaoFalso = mock(ReservaDao.class);
		EspacoDao espacoDaoFalso = mock(EspacoDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.DAY_OF_MONTH, 4);
		Reserva unidadeReserva1 = new Reserva();
		unidadeReserva1.setUnidade(unidade);
		unidadeReserva1.setEspaco(espaco);
		unidadeReserva1.setData(calendar1);
		unidadeReserva1.setHora(calendar1);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DAY_OF_MONTH, 8);
		Reserva unidadeReserva2 = new Reserva();
		unidadeReserva2.setUnidade(unidade);
		unidadeReserva2.setEspaco(espaco);
		unidadeReserva2.setData(calendar2);
		unidadeReserva2.setHora(calendar2);
		
		ArrayList<Reserva> unidadeReservas = new ArrayList<Reserva>();
		unidadeReservas.add(unidadeReserva1);
		unidadeReservas.add(unidadeReserva2);
		
		when(espacoDaoFalso.buscaEspaco(espaco.getId())).thenReturn(espaco);
		when(reservaDaoFalso.listaReserva(espaco.getId())).thenReturn(unidadeReservas);
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, espacoDaoFalso, validatorFalso, resultFalso);
		
	    try {
	    	reservaController.adiciona(unidadeReserva2);
	    	fail();
		} catch (ValidationException e) {
	        List<Message> errors = e.getErrors();
	        assertTrue(errors.contains(new SimpleMessage("reserva.adiciona", "Já existe uma reserva na data " + sdf.format(calendar2.getTime()))));
	    }
	}
	
	@Test
	public void deveObterUmaMensagemDeSucessoAoTentarRelizarReservaPosterior() {
		ReservaDao reservaDaoFalso = mock(ReservaDao.class);
		EspacoDao espacoDaoFalso = mock(EspacoDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		espaco.setPermitirPosterior(true);
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.DAY_OF_MONTH, 4);
		Reserva unidadeReserva1 = new Reserva();
		unidadeReserva1.setUnidade(unidade);
		unidadeReserva1.setEspaco(espaco);
		unidadeReserva1.setData(calendar1);
		unidadeReserva1.setHora(calendar1);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DAY_OF_MONTH, 8);
		Reserva unidadeReserva2 = new Reserva();
		unidadeReserva2.setUnidade(unidade);
		unidadeReserva2.setEspaco(espaco);
		unidadeReserva2.setData(calendar2);
		unidadeReserva2.setHora(calendar2);
		
		ArrayList<Reserva> unidadeReservas = new ArrayList<Reserva>();
		unidadeReservas.add(unidadeReserva1);
		
		when(espacoDaoFalso.buscaEspaco(espaco.getId())).thenReturn(espaco);
		when(reservaDaoFalso.listaReserva(espaco.getId())).thenReturn(unidadeReservas);
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, espacoDaoFalso, validatorFalso, resultFalso);
		reservaController.adiciona(unidadeReserva2);
		
		assertTrue(resultFalso.included().containsKey("notice"));
	}
	
	@Test
	public void deveLancarValidationExceptionAoTentarRelizarReservaPosterior() {
		ReservaDao reservaDaoFalso = mock(ReservaDao.class);
		EspacoDao espacoDaoFalso = mock(EspacoDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		espaco.setPermitirPosterior(false);
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.DAY_OF_MONTH, 4);
		Reserva unidadeReserva1 = new Reserva();
		unidadeReserva1.setUnidade(unidade);
		unidadeReserva1.setEspaco(espaco);
		unidadeReserva1.setData(calendar1);
		unidadeReserva1.setHora(calendar1);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DAY_OF_MONTH, 8);
		Reserva unidadeReserva2 = new Reserva();
		unidadeReserva2.setUnidade(unidade);
		unidadeReserva2.setEspaco(espaco);
		unidadeReserva2.setData(calendar2);
		unidadeReserva2.setHora(calendar2);
		
		ArrayList<Reserva> unidadeReservas = new ArrayList<Reserva>();
		unidadeReservas.add(unidadeReserva1);
		
		when(espacoDaoFalso.buscaEspaco(espaco.getId())).thenReturn(espaco);
		when(reservaDaoFalso.listaReserva(espaco.getId())).thenReturn(unidadeReservas);
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, espacoDaoFalso, validatorFalso, resultFalso);
		
		 try {
	    	reservaController.adiciona(unidadeReserva2);
	    	fail();
		} catch (ValidationException e) {
	        List<Message> errors = e.getErrors();
	        assertTrue(errors.contains(new SimpleMessage("reserva.adiciona", "A unidade já possui uma reserva neste espaço")));
	    }
	}
	
	@Test
	public void deveObterUmaMensagemDeSucessoAoTentarRelizarReservaDentroDaQuntidadeDisponivel() {
		ReservaDao reservaDaoFalso = mock(ReservaDao.class);
		EspacoDao espacoDaoFalso = mock(EspacoDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		espaco.setPermitirPosterior(true);
		espaco.setReservasQuantidade(2);
		espaco.setReservasDias(30);
		
		Calendar calendarAntigo = Calendar.getInstance();
		calendarAntigo.add(Calendar.DAY_OF_MONTH, -1);
		Reserva unidadeReservaAntiga = new Reserva();
		unidadeReservaAntiga.setUnidade(unidade);
		unidadeReservaAntiga.setEspaco(espaco);
		unidadeReservaAntiga.setData(calendarAntigo);
		unidadeReservaAntiga.setHora(calendarAntigo);
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.DAY_OF_MONTH, 4);
		Reserva unidadeReserva1 = new Reserva();
		unidadeReserva1.setUnidade(unidade);
		unidadeReserva1.setEspaco(espaco);
		unidadeReserva1.setData(calendar1);
		unidadeReserva1.setHora(calendar1);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DAY_OF_MONTH, 8);
		Reserva unidadeReserva2 = new Reserva();
		unidadeReserva2.setUnidade(unidade);
		unidadeReserva2.setEspaco(espaco);
		unidadeReserva2.setData(calendar2);
		unidadeReserva2.setHora(calendar2);
		
		ArrayList<Reserva> unidadeReservas = new ArrayList<Reserva>();
		unidadeReservas.add(unidadeReservaAntiga);
		unidadeReservas.add(unidadeReserva1);
		
		when(espacoDaoFalso.buscaEspaco(espaco.getId())).thenReturn(espaco);
		when(reservaDaoFalso.listaReserva(espaco.getId())).thenReturn(unidadeReservas);
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, espacoDaoFalso, validatorFalso, resultFalso);
		reservaController.adiciona(unidadeReserva2);
		
		assertTrue(resultFalso.included().containsKey("notice"));
	}
	
	@Test
	public void deveLancarValidationExceptionAoTentarRelizarReservaForaDaQuntidadeDisponivel() {
		ReservaDao reservaDaoFalso = mock(ReservaDao.class);
		EspacoDao espacoDaoFalso = mock(EspacoDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		int reservasQuantidade = 2;
		int reservasDias = 30;
		
		espaco.setPermitirPosterior(true);
		espaco.setReservasQuantidade(reservasQuantidade);
		espaco.setReservasDias(reservasDias);
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.DAY_OF_MONTH, 12);
		Reserva unidadeReserva1 = new Reserva();
		unidadeReserva1.setUnidade(unidade);
		unidadeReserva1.setEspaco(espaco);
		unidadeReserva1.setData(calendar1);
		unidadeReserva1.setHora(calendar1);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DAY_OF_MONTH, 8);
		Reserva unidadeReserva2 = new Reserva();
		unidadeReserva2.setUnidade(unidade);
		unidadeReserva2.setEspaco(espaco);
		unidadeReserva2.setData(calendar2);
		unidadeReserva2.setHora(calendar2);
		
		Calendar calendar3 = Calendar.getInstance();
		calendar3.add(Calendar.DAY_OF_MONTH, 5);
		Reserva unidadeReserva3 = new Reserva();
		unidadeReserva3.setUnidade(unidade);
		unidadeReserva3.setEspaco(espaco);
		unidadeReserva3.setData(calendar3);
		unidadeReserva3.setHora(calendar3);
		
		ArrayList<Reserva> unidadeReservas = new ArrayList<Reserva>();
		unidadeReservas.add(unidadeReserva1);
		unidadeReservas.add(unidadeReserva2);
		
		when(espacoDaoFalso.buscaEspaco(espaco.getId())).thenReturn(espaco);
		when(reservaDaoFalso.listaReserva(espaco.getId())).thenReturn(unidadeReservas);
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, espacoDaoFalso, validatorFalso, resultFalso);
		
		try {
	    	reservaController.adiciona(unidadeReserva3);
	    	fail();
		} catch (ValidationException e) {
	        List<Message> errors = e.getErrors();
	        assertTrue(errors.contains(new SimpleMessage("reserva.adiciona", "A unidade pode realizar no máximo " 
	        		+ reservasQuantidade + " no período de " + reservasDias + " dias")));
	    }
	}
	
}
