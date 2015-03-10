package br.com.onlares.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.download.ByteArrayDownload;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.observer.download.FileDownload;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.dao.FotoDao;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.Foto;
import br.com.onlares.model.Usuario;

import com.google.common.io.ByteStreams;

@Controller
public class PerfilController implements Serializable{

	private static final long serialVersionUID = 1L;
	private final UsuarioDao usuarioDao;
	private final FotoDao fotoDao;
	private final UsuarioLogado usuarioLogado;
	private final Validator validator;
	private final Result result;

	@Inject
	public PerfilController(UsuarioDao usuarioDao, FotoDao fotoDao, UsuarioLogado usuarioLogado, Validator validator, Result result) {
		this.usuarioDao = usuarioDao;
		this.fotoDao = fotoDao;
		this.usuarioLogado = usuarioLogado;
		this.validator = validator;
		this.result = result;
	}
	
	public PerfilController() {
		this(null, null, null, null, null);
	}
	
	@Get("/perfil/edita")
	public void edita() {
	}
	
    @Deprecated
	@Get
	public Download fotoDownload() {
		return usuarioLogado.getUsuario().getFotoDownload();
	}
	
	@Get("/perfil/{email}/foto")
	public Download foto(String email, ServletContext context) throws FileNotFoundException {
		Usuario usuario = usuarioDao.buscaPorEmail(email);
		// TODO check antes de recuperar
		Foto foto = null;
		
		UploadedFile uploadedFile = usuarioLogado.getUsuario().getUploadedFile();
		// Verifica se existe upload realizado na alteração de foto do perfil
		if (uploadedFile != null) {
			System.out.println("       uploadedFile != null " + uploadedFile);
			try {
				// Foto carregada somente para vizualização
				foto = new Foto(uploadedFile.getFileName(),
						ByteStreams.toByteArray(uploadedFile.getFile()),
						uploadedFile.getContentType(), Calendar.getInstance());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			foto = fotoDao.recupera(usuario.getFoto());
			System.out.println("               @Get(/perfil/{email}/foto)" + foto);
		}
		
		if (foto != null) {
			return new ByteArrayDownload(foto.getConteudo(), foto.getContentType(), foto.getNome());
		} else {
			File semFoto = new File(context.getRealPath("/resources/images/sem_foto.jpg"));
			return new FileDownload(semFoto, "image/jpg", "sem_foto.jpg");
		}
	}
	
	@Post("/perfil/foto")
	public void armazenaFoto(UploadedFile foto) {
		System.out.println("                             setFileName =" + (foto == null ? "NULL" : foto.getFileName()));
		if (foto != null) {
			usuarioLogado.getUsuario().setUploadedFile(foto);
		}
		result.forwardTo(this).edita();
	}
	
	@Put("/perfil/") 
	public void altera(Usuario usuario) throws IOException {
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
		
		// Obtém a alteração de foto do usuário logado do método armazenaFoto(UploadedFile foto) 
		UploadedFile uploadedFile = usuarioLogado.getUsuario().getUploadedFile();
		if (uploadedFile != null) {
			URI imagemURI = fotoDao.grava(new Foto(
				uploadedFile.getFileName(), 
				ByteStreams.toByteArray(uploadedFile.getFile()), 
				uploadedFile.getContentType(), Calendar.getInstance()));
			System.out.println("                             usuario.setFoto(imagemURI); =" + imagemURI);
			usuario.setFoto(imagemURI);
		}
		usuarioLogado.getUsuario().setUploadedFile(null);
		
		usuarioDao.altera(usuario);
		//usuarioLogado.setUsuario(usuario);
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
