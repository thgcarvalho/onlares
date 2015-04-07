package br.com.onlares.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.junit.Test;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.SafeResourceBundle;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Message;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.ValidationException;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.dao.UnidadeDao;
import br.com.onlares.exception.RestricaoDeIntegridadeException;
import br.com.onlares.model.Unidade;

public class AdminUnidadeControllerTest {
	
	// Adicionar
	
	@Test
	public void deveObterUmaMensagemDeSucessoAoTentarAdicionar() {
		UnidadeDao unidadeDaoFalso = mock(UnidadeDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		Unidade unidadeForm = new Unidade();
		unidadeForm.setDescricao("Apartamento 603, Torre 1");
		
		when(unidadeDaoFalso.existe(unidadeForm)).thenReturn(false);
		
		AdminUnidadeController adminUnidadeController = new AdminUnidadeController(unidadeDaoFalso, validatorFalso, resultFalso);
		adminUnidadeController.adiciona(unidadeForm);
		
		assertTrue(resultFalso.included().containsKey("notice"));
	}
	
	@Test 
	public void deveLancarValidationExceptionAoTentarAdicionarUnidadeSemLocalizacao() {
		List<String> localizacoes = new ArrayList<String>();
		localizacoes.add(null);
		localizacoes.add(" ");
		localizacoes.add("");
		
		I18nMessage expectedMessage = new I18nMessage("unidade.adiciona", "campo.obrigatorio", "Descrição");
		expectedMessage.setBundle(new SafeResourceBundle(ResourceBundle.getBundle("messages"))); 
		
		for (String localizacao : localizacoes) {
			try {
				UnidadeDao unidadeDaoFalso = mock(UnidadeDao.class);
				Validator validatorFalso = new MockValidator();
				Result resultFalso = new MockResult();
				AdminUnidadeController adminUnidadeController = new AdminUnidadeController(unidadeDaoFalso, validatorFalso, resultFalso);
				Unidade unidadeForm = new Unidade();
				unidadeForm.setDescricao(localizacao);
				adminUnidadeController.adiciona(unidadeForm);
				fail();
			} catch (ValidationException e) {
				List<Message> errors = e.getErrors();
				boolean found = false;
				for (Message message : errors) {
					message.setBundle(ResourceBundle.getBundle("messages"));
					if (expectedMessage.getMessage().equals(message.getMessage())) {
						found = true;
						break;
					}
				}
				if (!found) {
					fail("Does not contains the message: " + expectedMessage.getMessage() + " for " + localizacao);
				}
			}
		}
	}
	
	@Test
	public void deveLancarValidationExceptionAoTentarAdicionarUnidadeJaExistente() {
		UnidadeDao unidadeDaoFalso = mock(UnidadeDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		Unidade unidadeForm = null;
		AdminUnidadeController adminUnidadeController = new AdminUnidadeController(unidadeDaoFalso, validatorFalso, resultFalso);
		
	    try {
	    	unidadeForm = new Unidade();
	    	unidadeForm.setDescricao("Apartamento 603, Torre 1");
			
			when(unidadeDaoFalso.existe(unidadeForm)).thenReturn(true);
			
	    	adminUnidadeController.adiciona(unidadeForm);
	        fail();
	    } catch (ValidationException e) {
	        List<Message> errors = e.getErrors();
	        assertTrue(errors.contains(new SimpleMessage("unidade.adiciona", "Unidade já cadastrada")));
	    }
	}
	
	// ALTERA
	
	@Test
	public void deveObterUmaMensagemDeSucessoAoTentarAlterar() {
		UnidadeDao unidadeDaoFalso = mock(UnidadeDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		Unidade unidadeForm = new Unidade();
		unidadeForm.setId(0L);
		unidadeForm.setDescricao("Apartamento 603, Torre 1");
		
		when(unidadeDaoFalso.lista()).thenReturn(new ArrayList<Unidade>());
		
		AdminUnidadeController adminUnidadeController = new AdminUnidadeController(unidadeDaoFalso, validatorFalso, resultFalso);
		adminUnidadeController.altera(unidadeForm);
		
		assertTrue(resultFalso.included().containsKey("notice"));
	}
	
	@Test 
	public void deveLancarValidationExceptionAoTentarAlterarUnidadeSemLocalizacao() {
		List<String> localizacoes = new ArrayList<String>();
		localizacoes.add(null);
		localizacoes.add(" ");
		localizacoes.add("");
		
		I18nMessage expectedMessage = new I18nMessage("unidade.adiciona", "campo.obrigatorio", "Descrição");
		expectedMessage.setBundle(new SafeResourceBundle(ResourceBundle.getBundle("messages"))); 
		
		for (String localizacao : localizacoes) {
			try {
				UnidadeDao unidadeDaoFalso = mock(UnidadeDao.class);
				Validator validatorFalso = new MockValidator();
				Result resultFalso = new MockResult();
				AdminUnidadeController adminUnidadeController = new AdminUnidadeController(unidadeDaoFalso, validatorFalso, resultFalso);
				Unidade unidadeForm = new Unidade();
				unidadeForm.setDescricao(localizacao);
				adminUnidadeController.altera(unidadeForm);
				fail();
			} catch (ValidationException e) {
				List<Message> errors = e.getErrors();
				boolean found = false;
				for (Message message : errors) {
					message.setBundle(ResourceBundle.getBundle("messages"));
					if (expectedMessage.getMessage().equals(message.getMessage())) {
						found = true;
						break;
					}
				}
				if (!found) {
					fail("Does not contains the message: " + expectedMessage.getMessage() + " for " + localizacao);
				}
			}
		}
	}
	
	@Test
	public void deveLancarValidationExceptionAoTentarAlterarUnidadeParaUmJaExistente() {
		UnidadeDao unidadeDaoFalso = mock(UnidadeDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		AdminUnidadeController alteraUnidadeController = new AdminUnidadeController(unidadeDaoFalso, validatorFalso, resultFalso);
		
		List<Unidade> unidadesDB = new ArrayList<Unidade>();
		Unidade unidadeDB1 = new Unidade();
		unidadeDB1.setDescricao("Apartamento 603, Torre 1");
		Unidade unidadeDB2 = new Unidade();
		unidadeDB2.setDescricao("Apartamento 111, Torre 2");
		unidadesDB.add(unidadeDB1);
		unidadesDB.add(unidadeDB2);
		Unidade unidadeForm = new Unidade();
		
	    try {
	    	unidadeForm = new Unidade();
	    	unidadeForm.setDescricao("Apartamento 603, Torre 1");
			
			when(unidadeDaoFalso.lista()).thenReturn(unidadesDB);
			
			alteraUnidadeController.altera(unidadeForm);
	        fail();
	    } catch (ValidationException e) {
	        List<Message> errors = e.getErrors();
	        assertTrue(errors.contains(new SimpleMessage("unidade.edita", "Unidade já existente")));
	    }
	}
	
	// Remover
	
	@Test
	public void deveLancarValidationExceptionAoTentarRemoverUnidadeComUsuarioRelacionado() {
		UnidadeDao unidadeDaoFalso = mock(UnidadeDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		AdminUnidadeController alteraUnidadeController = new AdminUnidadeController(unidadeDaoFalso, validatorFalso, resultFalso);
		
		Unidade unidadeDB = new Unidade();
		unidadeDB.setId(1L);
		unidadeDB.setDescricao("Apartamento 603, Torre 1");
		
	    try {
	    	when(unidadeDaoFalso.buscaPorId(1L)).thenReturn(unidadeDB);
			doThrow(new RestricaoDeIntegridadeException("")).when(unidadeDaoFalso).verificaIntegridade(1L);
			alteraUnidadeController.remove(1L);
			verify(unidadeDaoFalso, never()).remove(unidadeDB);
		} catch (RestricaoDeIntegridadeException e) {
			System.out.println("Lançou");
			assertTrue(e.getMessage() != null);
		}
	}
	
	
}
