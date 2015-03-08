package br.com.onlares.controller;

import java.io.IOException;
import java.net.URI;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.download.ByteArrayDownload;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Admin;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.Arquivo;
import br.com.onlares.model.Usuario;

import com.google.common.io.ByteStreams;

@Controller
public class PerfilController {

	private final UsuarioDao usuarioDao;
	private final UsuarioLogado usuarioLogado;
	private final Validator validator;
	private final Result result;
	private final DiretorioDB imagens;

	@Inject
	public PerfilController(UsuarioDao usuarioDao, UsuarioLogado usuarioLogado, Validator validator, Result result, DiretorioDB imagens) {
		this.usuarioDao = usuarioDao;
		this.usuarioLogado = usuarioLogado;
		this.validator = validator;
		this.result = result;
		this.imagens = imagens;
	}
	
	public PerfilController() {
		this(null, null, null, null, null);
	}
	
	@Get("/perfil/edita")
	public void edita() {
	}
	
	@Get
	public Download fotoDownload() {
		System.out.println("   PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP PerfilController GET FOTO DOWNLOAD " + usuarioLogado.getUsuario().getNome());
		return usuarioLogado.getUsuario().getFotoDownload();
	}
	
	@Get("/perfil/{email}/foto")
	public Download foto(String email) {
		Usuario usuario = usuarioDao.buscaPorEmail(email);
		Arquivo foto = imagens.recupera(usuario.getFoto());
		return new ByteArrayDownload(foto.getConteudo(), foto.getContentType(), foto.getNome());
	}
	
	@Post("/perfil/foto")
	public void armazenaFoto(UploadedFile foto) throws IOException {
		System.out.println("public void foto(Usuario usuario, UploadedFile capa) {");
		System.out.println("getFileName =" + (foto == null ? "NULL" : foto.getFileName()));
		//Usuario usuarioDB = usuarioDao.busca(usuario);
		if (foto != null) {
			URI imagemURI = imagens.grava(new Arquivo(
					foto.getFileName(), 
					ByteStreams.toByteArray(foto.getFile()), 
					foto.getContentType(), Calendar.getInstance()));
			System.out.println("ID=" + usuarioLogado.getUsuario().getId()); // TODO remover
			System.out.println("NOME=" + usuarioLogado.getUsuario().getNome()); // TODO remover
			usuarioLogado.getUsuario().setFoto(imagemURI);
			usuarioDao.altera(usuarioLogado.getUsuario());
			System.out.println("FOTO=" + imagemURI); // TODO remover
		}
		
		result.forwardTo(this).edita();
	}
	
	@Admin
	@Put("/perfil/{email}")
	public void altera(Usuario usuario) {
		if (checkNull(usuario.getNome()).equals("")) {
			validator.add(new I18nMessage("perfil.edita", "campo.obrigatorio", "Nome"));
		}
		if (checkNull(usuario.getEmail()).equals("")) {
			validator.add(new I18nMessage("perfil.edita", "campo.obrigatorio", "Email"));
			validator.onErrorUsePageOf(this).edita();
		}
		
		List<Usuario> usuarios = usuarioDao.listaTodos();
		for (Usuario usuarioCadastrado : usuarios) {
			if (usuarioCadastrado.getEmail().equals(usuario.getEmail())) {
				if (!usuarioCadastrado.getId().equals(usuario.getId())) {
					validator.add(new SimpleMessage("usuario.edita", "Email usado por outro usuário"));
					break;
				}
			}
		}
		
		validator.onErrorUsePageOf(this).edita();
		
		usuarioDao.altera(usuario);
		result.include("notice", "Perfil atualizado com sucesso!");
		result.redirectTo(this).edita();
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
	
	private String checkNull(String value) {
		if (value == null) {
			return ("");
		} else {
			return (value.trim());
		}
	}
	
}
