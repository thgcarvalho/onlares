package br.com.onlares.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
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
	        assertEquals(1, errors.size());
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
	        assertEquals(1, errors.size());
	    }
	}
	
//	@Test
//	public void deveObterUsuarioCadastrado() throws Exception {
//		UsuarioDao daoFalso = mock(UsuarioDao.class);
//		Validator validatorFalso = new MockValidator();
//		Result resultFalso = new MockResult();
//		
//		Usuario usuarioDB = new Usuario("tcarvalho@onlares.com.br", "S3cr3t", "Thiago Carvalho");
//		Usuario usuarioForm = new Usuario("tcarvalho@onlares.com.br", "S3cr3t", null);
//		
//		when(daoFalso.loginValido(usuarioForm)).thenReturn(true);
//		when(daoFalso.buscaPorEmail("tcarvalho@onlares.com.br")).thenReturn(usuarioDB);
//		
//		LoginController loginController = new LoginController(daoFalso, validatorFalso, resultFalso, new UsuarioLogado());
//		loginController.auth(usuarioForm);
//		UsuarioLogado usuarioLogado = loginController.getUsuarioLogado();
//		
//		assertEquals(usuarioLogado.getUsuario(), usuarioDB);
//	}
//	
//	@Test(expected=ValidationException.class)  
//	public void deveDarErroDeValidacaoCasoUsuarioNaoExista() throws NoSuchAlgorithmException {
//		UsuarioDao daoFalso = mock(UsuarioDao.class);
//		Validator validatorFalso = new MockValidator();
//		Result resultFalso = new MockResult();
//		
//		Usuario usuarioForm = new Usuario("naoexiste@onlares.com.br", "S3cr3t", null);
//		
//		when(daoFalso.existe(usuarioForm)).thenReturn(false);
//		
//		LoginController loginController = new LoginController(daoFalso, validatorFalso, resultFalso, new UsuarioLogado());
//		loginController.auth(usuarioForm);
//		UsuarioLogado usuarioLogado = loginController.getUsuarioLogado();
//		
//		assertNull(usuarioLogado.getUsuario());
//	}
//	
//	@Test
//	public void deveRemoverUsuarioLogadoAoRealizarLogout() throws Exception {
//		Result resultFalso = new MockResult();
//		UsuarioLogado usuarioLogado = new UsuarioLogado();
//		
//		Usuario usuario = new Usuario("tcarvalho@onlares.com.br", "S3cr3t", "Thiago Carvalho");
//		usuarioLogado.setUsuario(usuario);
//		
//		LoginController loginController = new LoginController(null, null, resultFalso, usuarioLogado);
//		
//		assertNotNull(loginController.getUsuarioLogado().getUsuario());
//		loginController.sair();
//		assertNull(loginController.getUsuarioLogado().getUsuario());
//	}

}
