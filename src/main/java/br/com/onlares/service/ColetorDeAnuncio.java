//package br.com.onlares.service;
//
//import java.util.Collections;
//import java.util.List;
//
//import javax.inject.Inject;
//
//import br.com.onlares.dao.AnuncioDao;
//import br.com.onlares.model.Anuncio;
//
///**  
//* Copyright (c) 2015 GranDev - All rights reserved.
//* @author Thiago Carvalho - tcarvalho@grandev.com.br
//* 
//*/
//public class ColetorDeAnuncio {
//
//	private final AnuncioDao anuncioDao;
//
//	@Inject
//	public ColetorDeAnuncio(AnuncioDao anuncioDao) {
//		this.anuncioDao = anuncioDao;
//	}
//
//	@Deprecated
//	public ColetorDeAnuncio() {
//		this(null);
//	}
//
//	public List<Anuncio> getAnuncios() {
//		List<Anuncio> anuncios = anuncioDao.lista();
//		Collections.shuffle(anuncios);
//		return anuncios.subList(0, (anuncios.size() >= 6 ? 6 : anuncios.size()));
//	}
//}
