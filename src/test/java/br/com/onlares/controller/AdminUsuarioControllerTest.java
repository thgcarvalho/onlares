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
import br.com.onlares.model.Unidade;
import br.com.onlares.model.Usuario;

public class AdminUsuarioControllerTest {
	
	@Test
	public void deveObterUmaMensagemDeSucesso() {
		UsuarioDao usuarioDaoFalso = mock(UsuarioDao.class);
		UnidadeDao unidadeDaoFalso = mock(UnidadeDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		Usuario usuarioForm = new Usuario(null, null, null);
		usuarioForm.setEmail("tcarvalho@onlares.com.br");
		usuarioForm.setNome("Thiago Carvalho");
		Unidade unidade = new Unidade();
		unidade.setId(1L);
		usuarioForm.setUnidade(unidade);
	    
		when(usuarioDaoFalso.existe(usuarioForm)).thenReturn(false);
		
		AdminUsuarioController loginController = new AdminUsuarioController(usuarioDaoFalso, unidadeDaoFalso, validatorFalso, resultFalso);
		loginController.adiciona(usuarioForm);
		
		assertTrue(resultFalso.included().containsKey("notice"));
	}
	
	@Test
	public void deveLancarValidationExceptionAoTentarCadastrarUsuarioSemNome() {
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
				AdminUsuarioController loginController = new AdminUsuarioController(usuarioDaoFalso, unidadeDaoFalso, validatorFalso, resultFalso);
				Usuario usuarioForm = new Usuario(null, null, null);
				usuarioForm.setNome(nome);
				usuarioForm.setEmail("email@notnull.com");
				loginController.adiciona(usuarioForm);
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
	public void deveLancarValidationExceptionAoTentarCadastrarUsuarioSemEmail() {
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
				AdminUsuarioController loginController = new AdminUsuarioController(usuarioDaoFalso, unidadeDaoFalso, validatorFalso, resultFalso);
				Usuario usuarioForm = new Usuario(null, null, null);
				usuarioForm.setEmail(email);
				loginController.adiciona(usuarioForm);
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
	
	@Test
	public void deveLancarValidationExceptionAoTentarCadastrarUsuarioSemUnidade() {
		UsuarioDao usuarioDaoFalso = mock(UsuarioDao.class);
		UnidadeDao unidadeDaoFalso = mock(UnidadeDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		Usuario usuarioForm = null;
		AdminUsuarioController loginController = new AdminUsuarioController(usuarioDaoFalso, unidadeDaoFalso, validatorFalso, resultFalso);
		
	    try {
	    	usuarioForm = new Usuario(null, null, null);
			usuarioForm.setEmail("tcarvalho@onlares.com.br");
			usuarioForm.setNome("Thiago Carvalho");
	    	loginController.adiciona(usuarioForm);
	        fail();
	    } catch (ValidationException e) {
	        List<Message> errors = e.getErrors();
	        assertTrue(errors.contains(new SimpleMessage("usuario.adiciona", "Selecione a unidade")));
	    }
	}
	
	@Test
	public void deveLancarValidationExceptionAoTentarCadastrarUsuarioJaExistente() {
		UsuarioDao usuarioDaoFalso = mock(UsuarioDao.class);
		UnidadeDao unidadeDaoFalso = mock(UnidadeDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		Usuario usuarioForm = null;
		AdminUsuarioController loginController = new AdminUsuarioController(usuarioDaoFalso, unidadeDaoFalso, validatorFalso, resultFalso);
		
	    try {
	    	usuarioForm = new Usuario(null, null, null);
			usuarioForm.setEmail("tcarvalho@onlares.com.br");
			usuarioForm.setNome("Thiago Carvalho");
			
			when(usuarioDaoFalso.existe(usuarioForm)).thenReturn(true);
			
	    	loginController.adiciona(usuarioForm);
	        fail();
	    } catch (ValidationException e) {
	        List<Message> errors = e.getErrors();
	        assertTrue(errors.contains(new SimpleMessage("usuario.adiciona", "Email já cadastrado")));
	    }
	}
	
	@Test
	public void deveLancarValidationExceptionAoTentarAlterarUsuarioParaUmJaExistente() {
		UsuarioDao usuarioDaoFalso = mock(UsuarioDao.class);
		UnidadeDao unidadeDaoFalso = mock(UnidadeDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		AdminUsuarioController alteraUsuarioController = new AdminUsuarioController(usuarioDaoFalso, unidadeDaoFalso, validatorFalso, resultFalso);
		
		List<Usuario> usuariosDB = new ArrayList<Usuario>();
		Usuario usuarioDB1 = new Usuario("tcarvalho@onlares.com.br", "S3cr3t", "Thiago Carvalho");
		Usuario usuarioDB2 = new Usuario("fulano@onlares.com.br", "P455w0rd", "Fulando de Tal");
		usuariosDB.add(usuarioDB1);
		usuariosDB.add(usuarioDB2);
		Usuario usuarioForm = new Usuario("tcarvalho@onlares.com.br", "S3cr3t", null);
		
	    try {
	    	usuarioForm = new Usuario(null, null, null);
			usuarioForm.setEmail("tcarvalho@onlares.com.br");
			usuarioForm.setNome("Thiago Carvalho");
			
			when(usuarioDaoFalso.lista()).thenReturn(usuariosDB);
			
			alteraUsuarioController.altera(usuarioForm);
	        fail();
	    } catch (ValidationException e) {
	        List<Message> errors = e.getErrors();
	        assertTrue(errors.contains(new SimpleMessage("usuario.edita", "Email usado por outro usuário")));
	    }
	}
	
}
