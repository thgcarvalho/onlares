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
import br.com.onlares.dao.TempDao;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.Foto;
import br.com.onlares.model.Temp;
import br.com.onlares.model.Usuario;

import com.google.common.io.ByteStreams;

@Controller
public class PerfilController implements Serializable{

	private static final long serialVersionUID = 1L;
	private final UsuarioDao usuarioDao;
	private final FotoDao fotoDao;
	private final TempDao tempDao;
	private final UsuarioLogado usuarioLogado;
	private final Validator validator;
	private final Result result;

	@Inject
	public PerfilController(UsuarioDao usuarioDao, FotoDao fotoDao, TempDao tempDao, UsuarioLogado usuarioLogado, Validator validator, Result result) {
		this.usuarioDao = usuarioDao;
		this.fotoDao = fotoDao;
		this.tempDao = tempDao;
		this.usuarioLogado = usuarioLogado;
		this.validator = validator;
		this.result = result;
	}
	
	public PerfilController() {
		this(null, null, null, null, null, null);
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
		Foto foto = null;
		System.out.println("OBTEM");
		URI fotoTempURI = usuarioLogado.getUsuario().getFotoTemp();
		if (fotoTempURI != null) {
			Temp temp = tempDao.recupera(fotoTempURI); 
			foto = new Foto(temp.getNome(), temp.getConteudo(), temp.getContentType(), temp.getDataModificacao());
			System.out.println("       FOTO TEMP= " + fotoTempURI + " =" + foto);
		} else {
			foto = fotoDao.recupera(usuario.getFoto());
			System.out.println("       FOTO REAL=" + foto);
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
		System.out.println("ARMAZENA");
		if (foto != null) {
			try {
				URI imagemURI = tempDao.grava(new Temp(
						foto.getFileName(), 
						ByteStreams.toByteArray(foto.getFile()), 
						foto.getContentType(), Calendar.getInstance()));
				usuarioLogado.getUsuario().setFotoTemp(imagemURI);
				System.out.println("         amarzenafoto  " + imagemURI + " "+ (foto == null ? "NULL" : foto+" "+foto.getFileName()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		result.forwardTo(this).edita();
	}
	
	@Put("/perfil/") 
	public void altera(Usuario usuario) throws IOException {
		System.out.println("SALVA");
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
		
		URI fotoTemp = usuarioLogado.getUsuario().getFotoTemp();
		if (fotoTemp != null) {
			URI fotoAntigaURI = usuario.getFoto();
			System.out.println(" 1 fotoAntigaURI" + fotoAntigaURI);
			// recupera foto temp
			Temp temp = tempDao.recupera(fotoTemp); 
			Foto foto = new Foto(temp.getNome(), temp.getConteudo(), temp.getContentType(), temp.getDataModificacao());
			// grava foto
			URI imagemURI = fotoDao.grava(foto);
			usuario.setFoto(imagemURI);
			// deleta foto temp
			tempDao.deleta(fotoTemp);
			// deleta foto antiga
			System.out.println(" 2 fotoAntigaURI" + fotoAntigaURI);
			if (fotoAntigaURI != null) {
				fotoDao.deleta(fotoAntigaURI);
			}
		}
		usuarioLogado.getUsuario().setFotoTemp(null);
		
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
	
	@Deprecated
	@Get("/perfil/fotos")
	public void fotos() {
	}
	
	@Deprecated
	@Get("/perfil/ver/{id}")
	public Download ver(String id) {
		Foto foto = fotoDao.recupera(URI.create("db://" + id));
		return new ByteArrayDownload(foto.getConteudo(), foto.getContentType(), foto.getNome());
	}
	
}
