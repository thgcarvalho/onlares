package br.com.onlares.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class UsuarioTest {
	
	private Condominio condominio;
	
	@Before
	public void criaCondominioInexistente() {
		this.condominio = new Condominio();
		this.condominio.setId(Constantes.CONDOMINIO_INEXISTENTE_ID);
	}
	
	@Test
	public void deveConfirmarAutorizacaoDoUsuario() {
		Usuario admin = new Usuario();
		admin.setAutorizacao("A");
		Usuario sindico = new Usuario();
		sindico.setAutorizacao("S");
		Usuario porteiro = new Usuario();
		porteiro.setAutorizacao("P");
		Usuario todos = new Usuario();
		todos.setAutorizacao("A+S+P");
		
		assertTrue(admin.isAdmin());
		assertTrue(sindico.isSindico());
		assertTrue(porteiro.isPorteiro());
		assertTrue(todos.isAdmin());
		assertTrue(todos.isSindico());
		assertTrue(todos.isPorteiro());
	}
	
	@Test
	public void deveNegarAutorizacaoDoUsuario() {
		Usuario admin = new Usuario();
		admin.setAutorizacao("A");
		Usuario sindico = new Usuario();
		sindico.setAutorizacao("S");
		Usuario porteiro = new Usuario();
		porteiro.setAutorizacao("P");
		Usuario usuario = new Usuario();
		usuario.setAutorizacao("");
		
		assertFalse(admin.isSindico());
		assertFalse(admin.isPorteiro());
		assertFalse(sindico.isAdmin());
		assertFalse(sindico.isPorteiro());
		assertTrue(sindico.isSindico());
		assertFalse(porteiro.isAdmin());
		assertFalse(porteiro.isSindico());
		assertFalse(usuario.isAdmin());
		assertFalse(usuario.isSindico());
		assertFalse(usuario.isPorteiro());
	}
	
}
