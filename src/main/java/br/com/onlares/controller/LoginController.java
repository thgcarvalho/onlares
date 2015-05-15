package br.com.onlares.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.onlares.annotations.Public;
import br.com.onlares.bean.ColetorDeAnuncio;
import br.com.onlares.bean.UsuarioLogado;
import br.com.onlares.dao.AnuncioDao;
import br.com.onlares.dao.AvisoVisualizadoDao;
import br.com.onlares.dao.LocalizadorDao;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.Localizador;
import br.com.onlares.model.LocalizadorDoUsuarioLogado;
import br.com.onlares.model.Usuario;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class LoginController {
	
	private final UsuarioDao usuariodao;
	private final LocalizadorDao localizadorDao;
	private final AnuncioDao anuncioDao;
	private final AvisoVisualizadoDao avisoUsuarioDao;
	private final Validator validator;
	private final Result result;
	private final UsuarioLogado usuarioLogado;
	private final ColetorDeAnuncio coletorDeAnuncio;
	
	@Inject
	public LoginController(UsuarioDao usuariodao, LocalizadorDao localizadorDao, AnuncioDao anuncioDao, AvisoVisualizadoDao avisoUsuarioDao, Validator validator, Result result, UsuarioLogado usuarioLogado, ColetorDeAnuncio coletorDeAnuncio) {
		this.usuariodao = usuariodao;
		this.localizadorDao = localizadorDao;
		this.anuncioDao = anuncioDao;
		this.avisoUsuarioDao = avisoUsuarioDao;
		this.validator = validator;
		this.result = result;
		this.usuarioLogado = usuarioLogado;
		this.coletorDeAnuncio = coletorDeAnuncio;
	}
	
	@Deprecated
	public LoginController() {
		this(null, null, null, null, null, null, null, null);
	}

	@Get("/login")
	@Public
	public void login() { }

	@Post("/auth")
	@Public
	public void auth(Usuario usuario) {
		try {
			if (!usuariodao.loginValido(usuario)) {
				validator.add(new I18nMessage("login", "login.invalido"));
				validator.onErrorUsePageOf(this).login();
			}
		} catch (Exception e) {
			e.printStackTrace();
			validator.add(new SimpleMessage("login", e.getMessage()));
			validator.onErrorUsePageOf(this).login();
		} 
		
		// UsuarioLogado
		Usuario usuarioDB = usuariodao.buscaPorEmail(usuario.getEmail());
		List<Localizador> localizadores = localizadorDao.lista(usuarioDB.getId());
		usuarioLogado.setUsuario(usuarioDB);
		usuarioLogado.setLocalizadores(localizadores);
		// Anuncios
		Long condominioId = LocalizadorDoUsuarioLogado.getCondominioIdAtual(usuarioLogado);
		anuncioDao.setCondominioId(condominioId);
		coletorDeAnuncio.setAnuncios(anuncioDao.lista());
		// Avisos
		int quantidadeNaoVisualizada = avisoUsuarioDao.quantidadeNaoVisualizada(usuarioDB.getId(), condominioId);
		usuarioLogado.getUsuario().setQuantidadeDeAvisos(quantidadeNaoVisualizada);
		
		result.redirectTo(HomeController.class).index();
	}
	
	private void limpaUsuarioLogado() {
		usuarioLogado.logout();
	}
	
	private void limpaAnuncios() {
		coletorDeAnuncio.limpa();
	}
	
	@Get("/logout")
	public void sair() {
		limpaUsuarioLogado();
		limpaAnuncios();
		result.redirectTo(this).login();
	}
	
	@Post("/alteraUnidade")
	public void alteraUnidade(String localizador) { 
		Long localizadorId = Long.parseLong(localizador);
		for (Localizador localizadorDB : usuarioLogado.getLocalizadores()) {
			if (localizadorDB.getId().equals(localizadorId)) {
				usuarioLogado.setLocalizadorAtual(localizadorDB);
				coletorDeAnuncio.setAnuncios(anuncioDao.lista());
				break;
			}
		}
		if (Results.referer() != null) {
			result.use(Results.referer()).redirect();
		} else {
			result.redirectTo(HomeController.class).index();
		}
	}
	
	public UsuarioLogado getUsuarioLogado() {
		return usuarioLogado;
	}
	
}
