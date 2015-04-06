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
	
	@Get("/perfil/{email}/foto")
	public Download foto(String email, ServletContext context) throws FileNotFoundException {
		Usuario usuario = usuarioDao.buscaPorEmail(email);
		Foto foto = null;
		URI fotoTempURI = usuarioLogado.getUsuario().getFotoTemp();
		if (fotoTempURI != null) {
			Temp temp = tempDao.recupera(fotoTempURI); 
			foto = new Foto(temp.getNome(), temp.getConteudo(), temp.getContentType(), temp.getDataModificacao());
		} else {
			foto = fotoDao.recupera(usuario.getFoto());
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
		if (foto != null) {
			try {
				URI imagemURI = tempDao.grava(new Temp(
						foto.getFileName(), 
						ByteStreams.toByteArray(foto.getFile()), 
						foto.getContentType(), Calendar.getInstance()));
				usuarioLogado.getUsuario().setFotoTemp(imagemURI);
			} catch (IOException e) {
				e.printStackTrace();
			}
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
		
		Usuario usuarioDB = usuarioDao.busca(usuario);
		usuarioDB.setNome(usuario.getNome());
		usuarioDB.setProfissao(usuario.getProfissao());
		usuarioDB.setAniversario(usuario.getAniversario());
		usuarioDB.setFoneResidencial(usuario.getFoneResidencial());
		usuarioDB.setFoneCelular(usuario.getFoneCelular());
		usuarioDB.setFoneComercial(usuario.getFoneComercial());
		
		URI fotoTemp = usuarioLogado.getUsuario().getFotoTemp();
		if (fotoTemp != null) {
			URI fotoAntigaURI = usuarioDB.getFoto();
			// recupera foto temp
			Temp temp = tempDao.recupera(fotoTemp); 
			Foto foto = new Foto(temp.getNome(), temp.getConteudo(), temp.getContentType(), temp.getDataModificacao());
			// grava foto
			URI imagemURI = fotoDao.grava(foto);
			usuarioDB.setFoto(imagemURI);
			// deleta foto temp
			tempDao.deleta(fotoTemp);
			// deleta foto antiga
			if (fotoAntigaURI != null) {
				fotoDao.deleta(fotoAntigaURI);
			}
		}
		usuarioLogado.getUsuario().setFotoTemp(null);
		
		//if (mesmoCondominio(usuarioDB)) {
			usuarioDao.altera(usuarioDB);
		//}
		usuarioLogado.setUsuario(usuarioDB);
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
	
//  TODO Implementar e descomentar chamada acima
//	private boolean mesmoCondominio(Usuario usuario) {
//		if (usuario.getCondominio().getId().compareTo(usuarioLogado.getIdentificadorAtual().getCondominio().getId()) == 0) {
//			return true;
//		} else {
//			System.out.println("CONDOMÍNIOS DIFERENTES: " + usuario.getCondominio().getId() 
//					+ " != " + usuarioLogado.getIdentificadorAtual().getCondominio().getId());
//			return false;
//		}
//	}
	
	private String checkNull(String value) {
		if (value == null) {
			return ("");
		} else {
			return (value.trim());
		}
	}
	
	
}
