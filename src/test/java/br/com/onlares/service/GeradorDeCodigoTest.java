package br.com.onlares.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.onlares.service.GeradorDeCodigo;

public class GeradorDeCodigoTest {

	@Test
	public void deveGerarUmCodigoComTamhanhoEspecifico() {
		String codigo;
		GeradorDeCodigo geradorDeCodigo = new GeradorDeCodigo();
		
		codigo = geradorDeCodigo.gerar(8);
		assertEquals(8, codigo.length());

		codigo = geradorDeCodigo.gerar(16);
		assertEquals(16, codigo.length());
	}

}
