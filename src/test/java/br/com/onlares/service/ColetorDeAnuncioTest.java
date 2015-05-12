package br.com.onlares.service;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.onlares.controller.ColetorDeAnuncio;
import br.com.onlares.model.Anuncio;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class ColetorDeAnuncioTest {

	@Test
	public void deveEmbaralharListaDeAnuncios() {
		List<Anuncio> anunciosDB = new ArrayList<Anuncio>();
		Anuncio anuncioDB;
		for (int i = 0; i < 100; i++) {
			anuncioDB = new Anuncio();
			anuncioDB.setTitulo("Anúncio" + i);
			anunciosDB.add(anuncioDB);
		}
		ColetorDeAnuncio coletorDeAnuncio = new ColetorDeAnuncio();
		coletorDeAnuncio.setAnuncios(anunciosDB);
		List<Anuncio> anuncios = coletorDeAnuncio.getVisualidados();
	
		boolean diferente = false;
		
		for (int i = 0; i < anuncios.size(); i++) {
			if (!anuncios.get(i).getTitulo().equals(anunciosDB.get(i).getTitulo())) {
				diferente = true;
				break;
			}
		}
		
		if (!diferente) {
			fail("Não houve diferença entre a sequência das duas listas.");
		}
		
	}

}
