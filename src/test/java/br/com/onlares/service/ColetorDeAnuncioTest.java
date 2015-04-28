package br.com.onlares.service;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.onlares.dao.AnuncioDao;
import br.com.onlares.model.Anuncio;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class ColetorDeAnuncioTest {

	@Test
	public void deveEmbaralharListaDeAnuncios() {
		AnuncioDao daoFalso = mock(AnuncioDao.class);
		List<Anuncio> anunciosDB = new ArrayList<Anuncio>();
		Anuncio anuncioDB;
		for (int i = 0; i < 100; i++) {
			anuncioDB = new Anuncio();
			anuncioDB.setTitulo("Anúncio" + i);
			anunciosDB.add(anuncioDB);
		}
		
		when(daoFalso.lista()).thenReturn(new ArrayList<>(anunciosDB));
		
		ColetorDeAnuncio coletorDeAnuncio = new ColetorDeAnuncio(daoFalso);
		List<Anuncio> anuncios = coletorDeAnuncio.getAnuncios();
	
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
