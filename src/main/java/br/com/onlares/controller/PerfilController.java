package br.com.onlares.controller;

import java.io.IOException;
import java.net.URI;
import java.util.Calendar;

import javax.inject.Inject;

import com.google.common.io.ByteStreams;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.Arquivo;
import br.com.onlares.model.Diretorio;
import br.com.onlares.model.Usuario;

@Controller
public class PerfilController {

	private final UsuarioDao usuarioDao;
	@SuppressWarnings("unused")
	private final Validator validator;
	private final Result result;
	private final DiretorioDB imagens;

	@Inject
	public PerfilController(UsuarioDao usuarioDao, Validator validator, Result result, DiretorioDB imagens) {
		this.usuarioDao = usuarioDao;
		this.validator = validator;
		this.result = result;
		this.imagens = imagens;
	}
	
	public PerfilController() {
		this(null, null, null, null);
	}
	
	@Get("/perfil/edita")
	public void edita() {
		System.out.println("public void edita() {");
		
	}
	
	@Post("/perfil/foto")
	public void foto(Usuario usuario, UploadedFile capa) throws IOException {
		System.out.println("public void foto(Usuario usuario, UploadedFile capa) {");
		System.out.println("getFileName =" + (capa == null ? "NULL" : capa.getFileName()));
		
		if (capa != null) {
			URI imagemCapa = imagens.grava(new Arquivo(
					capa.getFileName(), 
					ByteStreams.toByteArray(capa.getFile()), 
					capa.getContentType(), Calendar.getInstance()));
			//livro.setCapa(imagemCapa);
			System.out.println("FOTO=" + imagemCapa); // TODO remover
		}
		
		result.forwardTo(this).edita();
	}
	
	@Get("/perfil/visualiza/{email}")
	public void visualiza(String email) {
		Usuario usuario = usuarioDao.buscaPorEmail(email);
		if (usuario == null) {
			result.notFound();
		} else {
			result.include("usuario", usuario);
		}
	}
	
}
