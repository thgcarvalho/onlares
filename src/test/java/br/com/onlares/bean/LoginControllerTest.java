package br.com.onlares.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.ValidationException;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.controller.LoginController;
import br.com.onlares.controller.UsuarioLogado;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.Usuario;

public class LoginControllerTest {

	@Test
	public void deveObterUsuarioCadastrado() throws Exception {
		UsuarioDao daoFalso = mock(UsuarioDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		Usuario usuarioDB = new Usuario("tcarvalho@onlares.com.br", "S3cr3t", "Thiago Carvalho");
		Usuario usuarioForm = new Usuario("tcarvalho@onlares.com.br", "S3cr3t", null);
		
		when(daoFalso.loginValido(usuarioForm)).thenReturn(true);
		when(daoFalso.buscaPorEmail("tcarvalho@onlares.com.br")).thenReturn(usuarioDB);
		
		LoginController loginController = new LoginController(daoFalso, validatorFalso, resultFalso, new UsuarioLogado());
		loginController.auth(usuarioForm);
		UsuarioLogado usuarioLogado = loginController.getUsuarioLogado();
		
		assertEquals(usuarioLogado.getUsuario(), usuarioDB);
	}
	
	@Test(expected=ValidationException.class)  
	public void deveDarErroDeValidacaoCasoUsuarioNaoExista() throws NoSuchAlgorithmException {
		UsuarioDao daoFalso = mock(UsuarioDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		Usuario usuarioForm = new Usuario("naoexiste@onlares.com.br", "S3cr3t", null);
		
		when(daoFalso.existe(usuarioForm)).thenReturn(false);
		
		LoginController loginController = new LoginController(daoFalso, validatorFalso, resultFalso, new UsuarioLogado());
		loginController.auth(usuarioForm);
		UsuarioLogado usuarioLogado = loginController.getUsuarioLogado();
		
		assertNull(usuarioLogado.getUsuario());
	}
	
	@Test
	public void deveRemoverUsuarioLogadoAoRealizarLogout() throws Exception {
		Result resultFalso = new MockResult();
		UsuarioLogado usuarioLogado = new UsuarioLogado();
		
		Usuario usuario = new Usuario("tcarvalho@onlares.com.br", "S3cr3t", "Thiago Carvalho");
		usuarioLogado.setUsuario(usuario);
		
		LoginController loginController = new LoginController(null, null, resultFalso, usuarioLogado);
		
		assertNotNull(loginController.getUsuarioLogado().getUsuario());
		loginController.sair();
		assertNull(loginController.getUsuarioLogado().getUsuario());
	}
	
}
