package br.com.onlares.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.download.ByteArrayDownload;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Public;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.Arquivo;
import br.com.onlares.model.Usuario;

@Controller
public class LoginController {
	
	private final UsuarioDao dao;
	private final Validator validator;
	private final Result result;
	private final UsuarioLogado usuarioLogado;
	private final DiretorioDB diretorioDB;
	
	@Inject
	public LoginController(UsuarioDao dao, Validator validator, Result result, UsuarioLogado usuarioLogado, DiretorioDB diretorioDB) {
		this.dao = dao;
		this.validator = validator;
		this.result = result;
		this.usuarioLogado = usuarioLogado;
		this.diretorioDB = diretorioDB;
	}
	
	@Deprecated
	public LoginController() {
		this(null, null, null, null, null);
	}

	@Get("/login")
	@Public
	public void login() { }

	@Post("/auth")
	@Public
	public void auth(Usuario usuario) {
		try {
			if (!dao.loginValido(usuario)) {
				validator.add(new I18nMessage("login", "login.invalido"));
				validator.onErrorUsePageOf(this).login();
			}
		} catch (Exception e) {
			e.printStackTrace();
			validator.add(new SimpleMessage("login", e.getMessage()));
			validator.onErrorUsePageOf(this).login();
		} 
		Usuario usuarioDB = dao.buscaPorEmail(usuario.getEmail());
		adicionaFotoDoPerfilEmMemoria(usuarioDB);
		System.out.println("+++++++++usuarioDB.getFotoDownload()" + usuarioDB.getFotoDownload());
		usuarioLogado.setUsuario(usuarioDB);
		result.redirectTo(HomeController.class).index();
	}
	
	@Get("/logout")
	public void sair() {
		usuarioLogado.logout();
		result.redirectTo(this).login();
	}

	public UsuarioLogado getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void adicionaFotoDoPerfilEmMemoria(Usuario usuario) {
		Arquivo foto = diretorioDB.recupera(usuario.getFoto());
		System.out.println("AdicionaFotoDoPerfilEmMemoria foto " + foto.getNome());
		Download fotoDownload = new ByteArrayDownload(foto.getConteudo(), foto.getContentType(), foto.getNome());
		usuario.setFotoDownload(fotoDownload);
	}

}
