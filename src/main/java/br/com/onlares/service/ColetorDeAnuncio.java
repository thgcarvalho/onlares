package br.com.onlares.service;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import br.com.onlares.dao.AnuncioDao;
import br.com.onlares.model.Anuncio;

public class ColetorDeAnuncio {
	
	private final AnuncioDao dao;
	
	@Inject
	public ColetorDeAnuncio(AnuncioDao dao){
		this.dao = dao;
	}
	
	@Deprecated
	public ColetorDeAnuncio() {
		this(null);
	}

	public List<Anuncio>  getAnuncios() {
		List<Anuncio> anuncios = dao.lista();
		Collections.shuffle(anuncios);
		return anuncios;
	}
}
