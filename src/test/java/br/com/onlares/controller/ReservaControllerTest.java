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
import br.com.onlares.dao.ReservaDao;
import br.com.onlares.model.Reserva;
import br.com.onlares.model.Unidade;
import br.com.onlares.model.UnidadeReserva;

public class ReservaControllerTest {
	
	private Unidade unidade;
	private Reserva reserva;
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
		Reserva reserva = new Reserva();
		reserva.setDescricao("Deck");
		this.reserva = reserva;
	}
	
	@Before
	public void criaReservaController() {
		Reserva reserva = new Reserva();
		reserva.setDescricao("Deck");
		this.reserva = reserva;
	}

	// Reservar
	
	@Test
	public void deveObterUmaMensagemDeSucessoAoTentarReservar() {
		ReservaDao reservaDaoFalso = mock(ReservaDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		UnidadeReserva unidadeReserva = new UnidadeReserva();
		unidadeReserva.setUnidade(unidade);
		unidadeReserva.setReserva(reserva);
		Calendar calendar = Calendar.getInstance();
		unidadeReserva.setData(calendar);
		unidadeReserva.setHora(calendar);
		
		when(reservaDaoFalso.buscaReserva(reserva.getId())).thenReturn(reserva);
		when(reservaDaoFalso.listaUnidadeReserva(reserva.getId())).thenReturn(new ArrayList<UnidadeReserva>());
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, validatorFalso, resultFalso);
		reservaController.adiciona(unidadeReserva);
		
		assertTrue(resultFalso.included().containsKey("notice"));
	}
	
	@Test 
	public void deveLancarValidationExceptionAoTentarReservarAposDataMáximaPermitida() {
		ReservaDao reservaDaoFalso = mock(ReservaDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		reserva.setAntecedenciaMaximaParaReservar(10);
		
		UnidadeReserva unidadeReserva = new UnidadeReserva();
		unidadeReserva.setUnidade(unidade);
		unidadeReserva.setReserva(reserva);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 12);
		unidadeReserva.setData(calendar);
		unidadeReserva.setHora(calendar);
		
		when(reservaDaoFalso.buscaReserva(reserva.getId())).thenReturn(reserva);
		when(reservaDaoFalso.listaUnidadeReserva(reserva.getId())).thenReturn(new ArrayList<UnidadeReserva>());
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, validatorFalso, resultFalso);
		
		Calendar antMax = Calendar.getInstance();
		int antecedenciaMaxima = reserva.getAntecedenciaMaximaParaReservar();
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
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		reserva.setAntecedenciaMinimaParaReservar(5);
		
		UnidadeReserva unidadeReserva = new UnidadeReserva();
		unidadeReserva.setUnidade(unidade);
		unidadeReserva.setReserva(reserva);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 4);
		unidadeReserva.setData(calendar);
		unidadeReserva.setHora(calendar);
		
		when(reservaDaoFalso.buscaReserva(reserva.getId())).thenReturn(reserva);
		when(reservaDaoFalso.listaUnidadeReserva(reserva.getId())).thenReturn(new ArrayList<UnidadeReserva>());
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, validatorFalso, resultFalso);
		
		Calendar antMin = Calendar.getInstance();
		int antecedenciaMinima = reserva.getAntecedenciaMinimaParaReservar();
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
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.DAY_OF_MONTH, 4);
		UnidadeReserva unidadeReserva1 = new UnidadeReserva();
		unidadeReserva1.setUnidade(unidade);
		unidadeReserva1.setReserva(reserva);
		unidadeReserva1.setData(calendar1);
		unidadeReserva1.setHora(calendar1);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DAY_OF_MONTH, 8);
		UnidadeReserva unidadeReserva2 = new UnidadeReserva();
		unidadeReserva2.setUnidade(unidade);
		unidadeReserva2.setReserva(reserva);
		unidadeReserva2.setData(calendar2);
		unidadeReserva2.setHora(calendar2);
		
		ArrayList<UnidadeReserva> unidadeReservas = new ArrayList<UnidadeReserva>();
		unidadeReservas.add(unidadeReserva1);
		unidadeReservas.add(unidadeReserva2);
		
		when(reservaDaoFalso.buscaReserva(reserva.getId())).thenReturn(reserva);
		when(reservaDaoFalso.listaUnidadeReserva(reserva.getId())).thenReturn(unidadeReservas);
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, validatorFalso, resultFalso);
		
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
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		reserva.setPermitirPosterior(true);
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.DAY_OF_MONTH, 4);
		UnidadeReserva unidadeReserva1 = new UnidadeReserva();
		unidadeReserva1.setUnidade(unidade);
		unidadeReserva1.setReserva(reserva);
		unidadeReserva1.setData(calendar1);
		unidadeReserva1.setHora(calendar1);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DAY_OF_MONTH, 8);
		UnidadeReserva unidadeReserva2 = new UnidadeReserva();
		unidadeReserva2.setUnidade(unidade);
		unidadeReserva2.setReserva(reserva);
		unidadeReserva2.setData(calendar2);
		unidadeReserva2.setHora(calendar2);
		
		ArrayList<UnidadeReserva> unidadeReservas = new ArrayList<UnidadeReserva>();
		unidadeReservas.add(unidadeReserva1);
		
		when(reservaDaoFalso.buscaReserva(reserva.getId())).thenReturn(reserva);
		when(reservaDaoFalso.listaUnidadeReserva(reserva.getId())).thenReturn(unidadeReservas);
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, validatorFalso, resultFalso);
		reservaController.adiciona(unidadeReserva2);
		
		assertTrue(resultFalso.included().containsKey("notice"));
	}
	
	@Test
	public void deveLancarValidationExceptionAoTentarRelizarReservaPosterior() {
		ReservaDao reservaDaoFalso = mock(ReservaDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		reserva.setPermitirPosterior(false);
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.DAY_OF_MONTH, 4);
		UnidadeReserva unidadeReserva1 = new UnidadeReserva();
		unidadeReserva1.setUnidade(unidade);
		unidadeReserva1.setReserva(reserva);
		unidadeReserva1.setData(calendar1);
		unidadeReserva1.setHora(calendar1);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DAY_OF_MONTH, 8);
		UnidadeReserva unidadeReserva2 = new UnidadeReserva();
		unidadeReserva2.setUnidade(unidade);
		unidadeReserva2.setReserva(reserva);
		unidadeReserva2.setData(calendar2);
		unidadeReserva2.setHora(calendar2);
		
		ArrayList<UnidadeReserva> unidadeReservas = new ArrayList<UnidadeReserva>();
		unidadeReservas.add(unidadeReserva1);
		
		when(reservaDaoFalso.buscaReserva(reserva.getId())).thenReturn(reserva);
		when(reservaDaoFalso.listaUnidadeReserva(reserva.getId())).thenReturn(unidadeReservas);
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, validatorFalso, resultFalso);
		
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
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		reserva.setPermitirPosterior(true);
		reserva.setReservasQuantidade(2);
		reserva.setReservasDias(30);
		
		Calendar calendarAntigo = Calendar.getInstance();
		calendarAntigo.add(Calendar.DAY_OF_MONTH, -1);
		UnidadeReserva unidadeReservaAntiga = new UnidadeReserva();
		unidadeReservaAntiga.setUnidade(unidade);
		unidadeReservaAntiga.setReserva(reserva);
		unidadeReservaAntiga.setData(calendarAntigo);
		unidadeReservaAntiga.setHora(calendarAntigo);
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.DAY_OF_MONTH, 4);
		UnidadeReserva unidadeReserva1 = new UnidadeReserva();
		unidadeReserva1.setUnidade(unidade);
		unidadeReserva1.setReserva(reserva);
		unidadeReserva1.setData(calendar1);
		unidadeReserva1.setHora(calendar1);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DAY_OF_MONTH, 8);
		UnidadeReserva unidadeReserva2 = new UnidadeReserva();
		unidadeReserva2.setUnidade(unidade);
		unidadeReserva2.setReserva(reserva);
		unidadeReserva2.setData(calendar2);
		unidadeReserva2.setHora(calendar2);
		
		ArrayList<UnidadeReserva> unidadeReservas = new ArrayList<UnidadeReserva>();
		unidadeReservas.add(unidadeReservaAntiga);
		unidadeReservas.add(unidadeReserva1);
		
		when(reservaDaoFalso.buscaReserva(reserva.getId())).thenReturn(reserva);
		when(reservaDaoFalso.listaUnidadeReserva(reserva.getId())).thenReturn(unidadeReservas);
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, validatorFalso, resultFalso);
		reservaController.adiciona(unidadeReserva2);
		
		assertTrue(resultFalso.included().containsKey("notice"));
	}
	
	@Test
	public void deveLancarValidationExceptionAoTentarRelizarReservaForaDaQuntidadeDisponivel() {
		ReservaDao reservaDaoFalso = mock(ReservaDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		int reservasQuantidade = 2;
		int reservasDias = 30;
		
		reserva.setPermitirPosterior(true);
		reserva.setReservasQuantidade(reservasQuantidade);
		reserva.setReservasDias(reservasDias);
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.DAY_OF_MONTH, 12);
		UnidadeReserva unidadeReserva1 = new UnidadeReserva();
		unidadeReserva1.setUnidade(unidade);
		unidadeReserva1.setReserva(reserva);
		unidadeReserva1.setData(calendar1);
		unidadeReserva1.setHora(calendar1);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DAY_OF_MONTH, 8);
		UnidadeReserva unidadeReserva2 = new UnidadeReserva();
		unidadeReserva2.setUnidade(unidade);
		unidadeReserva2.setReserva(reserva);
		unidadeReserva2.setData(calendar2);
		unidadeReserva2.setHora(calendar2);
		
		Calendar calendar3 = Calendar.getInstance();
		calendar3.add(Calendar.DAY_OF_MONTH, 5);
		UnidadeReserva unidadeReserva3 = new UnidadeReserva();
		unidadeReserva3.setUnidade(unidade);
		unidadeReserva3.setReserva(reserva);
		unidadeReserva3.setData(calendar3);
		unidadeReserva3.setHora(calendar3);
		
		ArrayList<UnidadeReserva> unidadeReservas = new ArrayList<UnidadeReserva>();
		unidadeReservas.add(unidadeReserva1);
		unidadeReservas.add(unidadeReserva2);
		
		when(reservaDaoFalso.buscaReserva(reserva.getId())).thenReturn(reserva);
		when(reservaDaoFalso.listaUnidadeReserva(reserva.getId())).thenReturn(unidadeReservas);
		
		ReservaController reservaController = new ReservaController(null, reservaDaoFalso, validatorFalso, resultFalso);
		
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
