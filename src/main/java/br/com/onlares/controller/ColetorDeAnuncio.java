package br.com.onlares.controller;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.onlares.model.Anuncio;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@SessionScoped
@Named
public class ColetorDeAnuncio implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Anuncio> anuncios;
	
	public List<Anuncio> getAnuncios() {
		return anuncios;
	}
	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}
	
	public List<Anuncio> getVisualidados() {
		Collections.shuffle(anuncios);
		System.out.println("anuncios size (" + anuncios.size() + ")");
		return anuncios.subList(0, (anuncios.size() >= 6 ? 6 : anuncios.size()));
	}
	
	public void limpa() {
		this.anuncios.clear();
	}
}
