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
	public void criaReservaController() {
		Espaco espaco = new Espaco();
		espaco.setDescricao("Deck");
		this.espaco = espaco;
	}

	// Reservar
	
	@Test
	public void deveObterUmaMensagemDeSucessoAoTentarReservar() {
		ReservaDao reservaDaoFalso = mock(ReservaDao.class);
		EspacoDao espacoDaoFalso = mock(EspacoDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		Reserva reserva = new Reserva();
		reserva.setUnidade(unidade);
		reserva.setEspaco(espaco);
		Calendar calendar = Calendar.getInstance();
		reserva.setData(calendar);
		reserva.setHora(calendar);
		
		when(espacoDaoFalso.buscaEspaco(espaco.getId())).thenReturn(espaco);
		when(reservaDaoFalso.listaReserva(espaco.getId())).thenReturn(new ArrayList<Reserva>());
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, espacoDaoFalso, validatorFalso, resultFalso);
		reservaController.adiciona(reserva);
		
		assertTrue(resultFalso.included().containsKey("notice"));
	}
	
	@Test 
	public void deveLancarValidationExceptionAoTentarReservarAposDataMáximaPermitida() {
		ReservaDao reservaDaoFalso = mock(ReservaDao.class);
		EspacoDao espacoDaoFalso = mock(EspacoDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		espaco.setAntecedenciaMaximaParaReservar(10);
		
		Reserva reserva = new Reserva();
		reserva.setUnidade(unidade);
		reserva.setEspaco(espaco);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 12);
		reserva.setData(calendar);
		reserva.setHora(calendar);
		
		when(espacoDaoFalso.buscaEspaco(espaco.getId())).thenReturn(espaco);
		when(reservaDaoFalso.listaReserva(espaco.getId())).thenReturn(new ArrayList<Reserva>());
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, espacoDaoFalso, validatorFalso, resultFalso);
		
		Calendar antMax = Calendar.getInstance();
		int antecedenciaMaxima = espaco.getAntecedenciaMaximaParaReservar();
		antMax.add(Calendar.DAY_OF_MONTH, antecedenciaMaxima);
		
	    try {
	    	reservaController.adiciona(reserva);
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
		
		Reserva reserva = new Reserva();
		reserva.setUnidade(unidade);
		reserva.setEspaco(espaco);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 4);
		reserva.setData(calendar);
		reserva.setHora(calendar);
		
		when(espacoDaoFalso.buscaEspaco(espaco.getId())).thenReturn(espaco);
		when(reservaDaoFalso.listaReserva(espaco.getId())).thenReturn(new ArrayList<Reserva>());
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, espacoDaoFalso, validatorFalso, resultFalso);
		
		Calendar antMin = Calendar.getInstance();
		int antecedenciaMinima = espaco.getAntecedenciaMinimaParaReservar();
		antMin.add(Calendar.DAY_OF_MONTH, antecedenciaMinima);
		
	    try {
	    	reservaController.adiciona(reserva);
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
		Reserva reserva1 = new Reserva();
		reserva1.setUnidade(unidade);
		reserva1.setEspaco(espaco);
		reserva1.setData(calendar1);
		reserva1.setHora(calendar1);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DAY_OF_MONTH, 8);
		Reserva reserva2 = new Reserva();
		reserva2.setUnidade(unidade);
		reserva2.setEspaco(espaco);
		reserva2.setData(calendar2);
		reserva2.setHora(calendar2);
		
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		reservas.add(reserva1);
		reservas.add(reserva2);
		
		when(espacoDaoFalso.buscaEspaco(espaco.getId())).thenReturn(espaco);
		when(reservaDaoFalso.listaReserva(espaco.getId())).thenReturn(reservas);
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, espacoDaoFalso, validatorFalso, resultFalso);
		
	    try {
	    	reservaController.adiciona(reserva2);
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
		Reserva reserva1 = new Reserva();
		reserva1.setUnidade(unidade);
		reserva1.setEspaco(espaco);
		reserva1.setData(calendar1);
		reserva1.setHora(calendar1);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DAY_OF_MONTH, 8);
		Reserva reserva2 = new Reserva();
		reserva2.setUnidade(unidade);
		reserva2.setEspaco(espaco);
		reserva2.setData(calendar2);
		reserva2.setHora(calendar2);
		
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		reservas.add(reserva1);
		
		when(espacoDaoFalso.buscaEspaco(espaco.getId())).thenReturn(espaco);
		when(reservaDaoFalso.listaReserva(espaco.getId())).thenReturn(reservas);
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, espacoDaoFalso, validatorFalso, resultFalso);
		reservaController.adiciona(reserva2);
		
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
		Reserva reserva1 = new Reserva();
		reserva1.setUnidade(unidade);
		reserva1.setEspaco(espaco);
		reserva1.setData(calendar1);
		reserva1.setHora(calendar1);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DAY_OF_MONTH, 8);
		Reserva reserva2 = new Reserva();
		reserva2.setUnidade(unidade);
		reserva2.setEspaco(espaco);
		reserva2.setData(calendar2);
		reserva2.setHora(calendar2);
		
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		reservas.add(reserva1);
		
		when(espacoDaoFalso.buscaEspaco(espaco.getId())).thenReturn(espaco);
		when(reservaDaoFalso.listaReserva(espaco.getId())).thenReturn(reservas);
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, espacoDaoFalso, validatorFalso, resultFalso);
		
		 try {
	    	reservaController.adiciona(reserva2);
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
		Reserva reservaAntiga = new Reserva();
		reservaAntiga.setUnidade(unidade);
		reservaAntiga.setEspaco(espaco);
		reservaAntiga.setData(calendarAntigo);
		reservaAntiga.setHora(calendarAntigo);
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.DAY_OF_MONTH, 4);
		Reserva reserva1 = new Reserva();
		reserva1.setUnidade(unidade);
		reserva1.setEspaco(espaco);
		reserva1.setData(calendar1);
		reserva1.setHora(calendar1);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DAY_OF_MONTH, 8);
		Reserva reserva2 = new Reserva();
		reserva2.setUnidade(unidade);
		reserva2.setEspaco(espaco);
		reserva2.setData(calendar2);
		reserva2.setHora(calendar2);
		
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		reservas.add(reservaAntiga);
		reservas.add(reserva1);
		
		when(espacoDaoFalso.buscaEspaco(espaco.getId())).thenReturn(espaco);
		when(reservaDaoFalso.listaReserva(espaco.getId())).thenReturn(reservas);
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, espacoDaoFalso, validatorFalso, resultFalso);
		reservaController.adiciona(reserva2);
		
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
		Reserva reserva1 = new Reserva();
		reserva1.setUnidade(unidade);
		reserva1.setEspaco(espaco);
		reserva1.setData(calendar1);
		reserva1.setHora(calendar1);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DAY_OF_MONTH, 8);
		Reserva reserva2 = new Reserva();
		reserva2.setUnidade(unidade);
		reserva2.setEspaco(espaco);
		reserva2.setData(calendar2);
		reserva2.setHora(calendar2);
		
		Calendar calendar3 = Calendar.getInstance();
		calendar3.add(Calendar.DAY_OF_MONTH, 5);
		Reserva reserva3 = new Reserva();
		reserva3.setUnidade(unidade);
		reserva3.setEspaco(espaco);
		reserva3.setData(calendar3);
		reserva3.setHora(calendar3);
		
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		reservas.add(reserva1);
		reservas.add(reserva2);
		
		when(espacoDaoFalso.buscaEspaco(espaco.getId())).thenReturn(espaco);
		when(reservaDaoFalso.listaReserva(espaco.getId())).thenReturn(reservas);
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, espacoDaoFalso, validatorFalso, resultFalso);
		
		try {
	    	reservaController.adiciona(reserva3);
	    	fail();
		} catch (ValidationException e) {
	        List<Message> errors = e.getErrors();
	        assertTrue(errors.contains(new SimpleMessage("reserva.adiciona", "A unidade pode realizar no máximo " 
	        		+ reservasQuantidade + " no período de " + reservasDias + " dias")));
	    }
	}
	
	@Test
	public void deveLancarValidationExceptionAoTentarCancelarReservaDepoisDoPrazo() {
		ReservaDao reservaDaoFalso = mock(ReservaDao.class);
		EspacoDao espacoDaoFalso = mock(EspacoDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		espaco.setAntecedenciaMinimaParaCancelar(7);
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 5);
		Reserva reserva = new Reserva();
		reserva.setUnidade(unidade);
		reserva.setEspaco(espaco);
		reserva.setData(calendar);
		reserva.setHora(calendar);
		
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		reservas.add(reserva);
		
		when(reservaDaoFalso.buscaReserva(reserva.getId())).thenReturn(reserva);
		when(espacoDaoFalso.buscaEspaco(espaco.getId())).thenReturn(espaco);
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, espacoDaoFalso, validatorFalso, resultFalso);
		
		 try {
	    	reservaController.remove(reserva.getId());
	    	fail();
		} catch (ValidationException e) {
	        List<Message> errors = e.getErrors();
	        assertTrue(errors.contains(new SimpleMessage("reserva.cancela", "Não é mais possível cancelar essa reserva")));
		}
	}
	
}
