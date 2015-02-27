package br.com.onlares.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.onlares.service.ColetorDeAnuncio;

@Controller
public class HomeController {
	
	private final ColetorDeAnuncio coletorDeAnuncio;
	private final Result result;

	@Inject
	public HomeController(ColetorDeAnuncio coletorDeAnuncio, Result result) {
		this.coletorDeAnuncio = coletorDeAnuncio;
		this.result = result;
	}
	
	@Deprecated
	public HomeController() {
		this(null, null);
	}
	
	@Get("/home/index")
	public void index() {
		result.include("anuncioList", coletorDeAnuncio.getAnuncios());
	}
}