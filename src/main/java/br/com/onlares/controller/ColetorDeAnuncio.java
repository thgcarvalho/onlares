package br.com.onlares.controller;

import java.io.Serializable;
import java.util.ArrayList;
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
	private List<Anuncio> anuncios = new ArrayList<Anuncio>();
	
	public List<Anuncio> getAnuncios() {
		return anuncios;
	}
	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}
	
	public List<Anuncio> getVisualidados() {
		List<Anuncio> visualizados = new ArrayList<Anuncio>(anuncios);
		Collections.shuffle(visualizados);
		//System.out.println("visualizados size (" + visualizados.size() + ")");
		return visualizados.subList(0, (visualizados.size() >= 6 ? 6 : visualizados.size()));
	}
	
	public void limpa() {
		this.anuncios.clear();
	}
}
