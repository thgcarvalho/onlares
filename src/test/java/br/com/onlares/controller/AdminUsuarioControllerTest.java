package br.com.onlares.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
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
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.Usuario;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class AdminUsuarioControllerTest {
	
	// ADICIONA
	
	@Test
	public void deveObterUmaMensagemDeSucessoAoTentarAdicionarUsuario() {
		UsuarioDao usuarioDaoFalso = mock(UsuarioDao.class);
		UnidadeDao unidadeDaoFalso = mock(UnidadeDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		Usuario usuarioForm = new Usuario(null, null, null);
		usuarioForm.setEmail("tcarvalho@onlares.com.br");
		usuarioForm.setNomeCompleto("Thiago Carvalho");
		List<Long> unidades = new ArrayList<Long>();
		unidades.add(1L);
	    
		when(usuarioDaoFalso.existe(usuarioForm)).thenReturn(false);
		
		AdminUsuarioController adminUsuarioController = new AdminUsuarioController(usuarioDaoFalso, unidadeDaoFalso, validatorFalso, resultFalso);
		adminUsuarioController.adiciona(usuarioForm, unidades);
		
		assertTrue(resultFalso.included().containsKey("notice"));
	}
	
	@Test
	public void deveLancarValidationExceptionAoTentarAdicionarUsuarioSemNome() {
		List<String> nomes = new ArrayList<String>();
		nomes.add(null);
		nomes.add(" ");
		nomes.add("");
		
		I18nMessage expectedMessage = new I18nMessage("usuario.adiciona", "campo.obrigatorio", "Nome");
		expectedMessage.setBundle(new SafeResourceBundle(ResourceBundle.getBundle("messages"))); 
		
		for (String nome : nomes) {
			try {
				UsuarioDao usuarioDaoFalso = mock(UsuarioDao.class);
				UnidadeDao unidadeDaoFalso = mock(UnidadeDao.class);
				Validator validatorFalso = new MockValidator();
				Result resultFalso = new MockResult();
				AdminUsuarioController adminUsuarioController = new AdminUsuarioController(usuarioDaoFalso, unidadeDaoFalso, validatorFalso, resultFalso);
				Usuario usuarioForm = new Usuario(null, null, null);
				usuarioForm.setNomeCompleto(nome);
				usuarioForm.setEmail("email@notnull.com");
				List<Long> unidades = new ArrayList<Long>();
				unidades.add(1L);
				adminUsuarioController.adiciona(usuarioForm, unidades);
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
					fail("Does not contains the message: " + expectedMessage.getMessage() + " for " + nome);
				}
			}
		}
	}
	
	@Test
	public void deveLancarValidationExceptionAoTentarAdicionarUsuarioSemEmail() {
		List<String> emails = new ArrayList<String>();
		emails.add(null);
		emails.add(" ");
		emails.add("");
		
		I18nMessage expectedMessage = new I18nMessage("usuario.adiciona", "campo.obrigatorio", "Email");
		expectedMessage.setBundle(new SafeResourceBundle(ResourceBundle.getBundle("messages"))); 

		for (String email : emails) {
			try {
				UsuarioDao usuarioDaoFalso = mock(UsuarioDao.class);
				UnidadeDao unidadeDaoFalso = mock(UnidadeDao.class);
				Validator validatorFalso = new MockValidator();
				Result resultFalso = new MockResult();
				AdminUsuarioController adminUsuarioController = new AdminUsuarioController(usuarioDaoFalso, unidadeDaoFalso, validatorFalso, resultFalso);
				Usuario usuarioForm = new Usuario(null, null, null);
				usuarioForm.setEmail(email);
				List<Long> unidades = new ArrayList<Long>();
				unidades.add(1L);
				adminUsuarioController.adiciona(usuarioForm, unidades);
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
					fail("Does not contains the message: " + expectedMessage.getMessage() + " for " + email);
				}
			}
		}
	}
	
//	@Test
//	public void deveLancarValidationExceptionAoTentarAdicionarUsuarioSemUnidade() {
//		UsuarioDao usuarioDaoFalso = mock(UsuarioDao.class);
//		UnidadeDao unidadeDaoFalso = mock(UnidadeDao.class);
//		Validator validatorFalso = new MockValidator();
//		Result resultFalso = new MockResult();
//		Usuario usuarioForm = null;
//		AdminUsuarioController adminUsuarioController = new AdminUsuarioController(usuarioDaoFalso, unidadeDaoFalso, validatorFalso, resultFalso);
//		
//	    try {
//	    	usuarioForm = new Usuario(null, null, null);
//			usuarioForm.setEmail("tcarvalho@onlares.com.br");
//			usuarioForm.setNomeCompleto("Thiago Carvalho");
//			List<Long> unidades = new ArrayList<Long>();
//	    	adminUsuarioController.adiciona(usuarioForm, unidades);
//	        fail();
//	    } catch (ValidationException e) {
//	        List<Message> errors = e.getErrors();
//	        assertTrue(errors.contains(new SimpleMessage("usuario.adiciona", "Selecione alguma unidade")));
//	    }
//	}
	
	@Test
	public void deveLancarValidationExceptionAoTentarAdicionarUsuarioJaExistente() {
		UsuarioDao usuarioDaoFalso = mock(UsuarioDao.class);
		UnidadeDao unidadeDaoFalso = mock(UnidadeDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		Usuario usuarioForm = null;
		AdminUsuarioController adminUsuarioController = new AdminUsuarioController(usuarioDaoFalso, unidadeDaoFalso, validatorFalso, resultFalso);
		
	    try {
	    	usuarioForm = new Usuario(null, null, null);
			usuarioForm.setEmail("tcarvalho@onlares.com.br");
			usuarioForm.setNomeCompleto("Thiago Carvalho");
			
			when(usuarioDaoFalso.existe(usuarioForm)).thenReturn(true);
			
			List<Long> unidades = new ArrayList<Long>();
			unidades.add(1L);
	    	adminUsuarioController.adiciona(usuarioForm, unidades);
	        fail();
	    } catch (ValidationException e) {
	        List<Message> errors = e.getErrors();
	        assertTrue(errors.contains(new SimpleMessage("usuario.adiciona", "Email já cadastrado")));
	    }
	}
	
	// ALTERA
	
	
	@Test
	public void deveObterUmaMensagemDeSucessoAoTentarAlterarUsuarioMantendoMesmoEmail() {
		UsuarioDao usuarioDaoFalso = mock(UsuarioDao.class);
		UnidadeDao unidadeDaoFalso = mock(UnidadeDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		Usuario usuarioDB = new Usuario(null, null, null);
		usuarioDB.setId(1L);
		usuarioDB.setEmail("tcarvalho@onlares.com.br");
		usuarioDB.setNomeCompleto("Thiago Carvalho");
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(usuarioDB);
		
		Usuario usuarioForm = new Usuario(null, null, null);
		usuarioForm.setId(1L);
		usuarioForm.setEmail("tcarvalho@onlares.com.br");
		usuarioForm.setNomeCompleto("Thiago de Oliveira Carvalho");
		
		when(usuarioDaoFalso.lista()).thenReturn(usuarios);
		when(usuarioDaoFalso.busca(usuarioForm)).thenReturn(usuarioDB);
		
		List<Long> unidades = new ArrayList<Long>();
		unidades.add(1L);
		unidades.add(2L);
		
		AdminUsuarioController adminUsuarioController = new AdminUsuarioController(usuarioDaoFalso, unidadeDaoFalso, validatorFalso, resultFalso);
		adminUsuarioController.altera(usuarioForm, unidades);
		
		assertTrue(resultFalso.included().containsKey("notice"));
	}
	
	@Test
	public void deveObterUmaMensagemDeSucessoAoTentarAlterarUsuarioAlterandoEmail() {
		UsuarioDao usuarioDaoFalso = mock(UsuarioDao.class);
		UnidadeDao unidadeDaoFalso = mock(UnidadeDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		Usuario usuarioDB = new Usuario(null, null, null);
		usuarioDB.setId(1L);
		usuarioDB.setEmail("tcarvalho@onlares.com");
		usuarioDB.setNomeCompleto("Thiago Carvalho");
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(usuarioDB);
		
		Usuario usuarioForm = new Usuario(null, null, null);
		usuarioForm.setId(1L);
		usuarioForm.setEmail("tcarvalho@grandev.com.br");
		usuarioForm.setNomeCompleto("Thiago de Oliveira Carvalho");
		
		when(usuarioDaoFalso.lista()).thenReturn(usuarios);
		when(usuarioDaoFalso.busca(usuarioForm)).thenReturn(usuarioDB);
		
		List<Long> unidades = new ArrayList<Long>();
		unidades.add(1L);
		unidades.add(2L);
		
		AdminUsuarioController adminUsuarioController = new AdminUsuarioController(usuarioDaoFalso, unidadeDaoFalso, validatorFalso, resultFalso);
		adminUsuarioController.altera(usuarioForm, unidades);
		
		assertTrue(resultFalso.included().containsKey("notice"));
	}
	
	@Test
	public void deveLancarValidationExceptionAoTentarAlterarUsuarioSemNome() {
		List<String> nomes = new ArrayList<String>();
		nomes.add(null);
		nomes.add(" ");
		nomes.add("");
		
		I18nMessage expectedMessage = new I18nMessage("usuario.edita", "campo.obrigatorio", "Nome");
		expectedMessage.setBundle(new SafeResourceBundle(ResourceBundle.getBundle("messages"))); 
		
		for (String nome : nomes) {
			try {
				UsuarioDao usuarioDaoFalso = mock(UsuarioDao.class);
				UnidadeDao unidadeDaoFalso = mock(UnidadeDao.class);
				Validator validatorFalso = new MockValidator();
				Result resultFalso = new MockResult();
				AdminUsuarioController adminUsuarioController = new AdminUsuarioController(usuarioDaoFalso, unidadeDaoFalso, validatorFalso, resultFalso);
				Usuario usuarioForm = new Usuario(null, null, null);
				usuarioForm.setNomeCompleto(nome);
				usuarioForm.setEmail("email@notnull.com");
				List<Long> unidades = new ArrayList<Long>();
				unidades.add(1L);
				adminUsuarioController.altera(usuarioForm, unidades);
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
					fail("Does not contains the message: " + expectedMessage.getMessage() + " for " + nome);
				}
			}
		}
	}
	
	@Test
	public void deveLancarValidationExceptionAoTentarAlterarUsuarioSemEmail() {
		List<String> emails = new ArrayList<String>();
		emails.add(null);
		emails.add(" ");
		emails.add("");
		
		I18nMessage expectedMessage = new I18nMessage("usuario.edita", "campo.obrigatorio", "Email");
		expectedMessage.setBundle(new SafeResourceBundle(ResourceBundle.getBundle("messages"))); 

		for (String email : emails) {
			try {
				UsuarioDao usuarioDaoFalso = mock(UsuarioDao.class);
				UnidadeDao unidadeDaoFalso = mock(UnidadeDao.class);
				Validator validatorFalso = new MockValidator();
				Result resultFalso = new MockResult();
				AdminUsuarioController adminUsuarioController = new AdminUsuarioController(usuarioDaoFalso, unidadeDaoFalso, validatorFalso, resultFalso);
				Usuario usuarioForm = new Usuario(null, null, null);
				usuarioForm.setEmail(email);
				List<Long> unidades = new ArrayList<Long>();
				unidades.add(1L);
				adminUsuarioController.altera(usuarioForm, unidades);
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
					fail("Does not contains the message: " + expectedMessage.getMessage() + " for " + email);
				}
			}
		}
	}
	
//	@Test
//	public void deveLancarValidationExceptionAoTentarAlterarUsuarioSemUnidade() {
//		UsuarioDao usuarioDaoFalso = mock(UsuarioDao.class);
//		UnidadeDao unidadeDaoFalso = mock(UnidadeDao.class);
//		Validator validatorFalso = new MockValidator();
//		Result resultFalso = new MockResult();
//		Usuario usuarioForm = null;
//		AdminUsuarioController adminUsuarioController = new AdminUsuarioController(usuarioDaoFalso, unidadeDaoFalso, validatorFalso, resultFalso);
//		
//	    try {
//	    	usuarioForm = new Usuario(null, null, null);
//			usuarioForm.setEmail("tcarvalho@onlares.com.br");
//			usuarioForm.setNomeCompleto("Thiago Carvalho");
//			List<Long> unidades = new ArrayList<Long>();
//	    	adminUsuarioController.altera(usuarioForm, unidades);
//	        fail();
//	    } catch (ValidationException e) {
//	        List<Message> errors = e.getErrors();
//	        assertTrue(errors.contains(new SimpleMessage("usuario.edita", "Selecione alguma unidade")));
//	    }
//	}
	
	@Test
	public void deveLancarValidationExceptionAoTentarAlterarUsuarioParaUmJaExistente() {
		UsuarioDao usuarioDaoFalso = mock(UsuarioDao.class);
		UnidadeDao unidadeDaoFalso = mock(UnidadeDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		AdminUsuarioController alteraUsuarioController = new AdminUsuarioController(usuarioDaoFalso, unidadeDaoFalso, validatorFalso, resultFalso);
		
		List<Usuario> usuariosDB = new ArrayList<Usuario>();
		Usuario usuarioDB1 = new Usuario("tcarvalho@onlares.com.br", "S3cr3t", "Thiago Carvalho");
		usuarioDB1.setId(1L);
		Usuario usuarioDB2 = new Usuario("fulano@onlares.com.br", "P455w0rd", "Fulando de Tal");
		usuarioDB2.setId(2L);
		usuariosDB.add(usuarioDB1);
		usuariosDB.add(usuarioDB2);
		Usuario usuarioForm = new Usuario("fulano@onlares.com.br", "S3cr3t", "Thiago Carvalho");
		usuarioForm.setId(1L);
		
	    try {
			when(usuarioDaoFalso.listaTodos()).thenReturn(usuariosDB);
			List<Long> unidades = new ArrayList<Long>();
			unidades.add(1L);
			alteraUsuarioController.altera(usuarioForm, unidades);
	        fail();
	    } catch (ValidationException e) {
	        List<Message> errors = e.getErrors();
	        for (Message message : errors) {
				System.out.println(message);
			}
	        assertTrue(errors.contains(new SimpleMessage("usuario.edita", "Email usado por outro usuário")));
	    }
	}
	
}
