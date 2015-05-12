package br.com.onlares.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.junit.Test;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.ValidationException;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.dao.AnuncioDao;
import br.com.onlares.dao.LocalizadorDao;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.Localizador;
import br.com.onlares.model.Usuario;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class LoginControllerTest {

	@Test
	public void deveObterUsuarioCadastrado() throws Exception {
		UsuarioDao usuarioDaoFalso = mock(UsuarioDao.class);
		LocalizadorDao localizadorDaoFalso = mock(LocalizadorDao.class);
		AnuncioDao anuncioDaoFalso = mock(AnuncioDao.class);
		Validator validatorFalso = new MockValidator();
		Result resultFalso = new MockResult();
		
		Usuario usuarioDB = new Usuario("tcarvalho@onlares.com.br", "S3cr3t", "Thiago Carvalho");
		Usuario usuarioForm = new Usuario("tcarvalho@onlares.com.br", "S3cr3t", null);
		
		when(usuarioDaoFalso.loginValido(usuarioForm)).thenReturn(true);
		when(usuarioDaoFalso.buscaPorEmail("tcarvalho@onlares.com.br")).thenReturn(usuarioDB);
		when(localizadorDaoFalso.lista(usuarioDB.getId())).thenReturn(new ArrayList<Localizador>());
		
		LoginController loginController = new LoginController(usuarioDaoFalso, localizadorDaoFalso, anuncioDaoFalso, validatorFalso, resultFalso, new UsuarioLogado(), new ColetorDeAnuncio());
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
		
		LoginController loginController = new LoginController(daoFalso, null, null, validatorFalso, resultFalso, new UsuarioLogado(), new ColetorDeAnuncio());
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
		
		LoginController loginController = new LoginController(null, null, null, null, resultFalso, usuarioLogado, new ColetorDeAnuncio());
		
		assertNotNull(loginController.getUsuarioLogado().getUsuario());
		loginController.sair();
		assertNull(loginController.getUsuarioLogado().getUsuario());
	}
	
}
