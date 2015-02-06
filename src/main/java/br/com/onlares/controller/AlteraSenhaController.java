package br.com.onlares.controller;

import javax.inject.Inject;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.simplemail.Mailer;
import br.com.caelum.vraptor.validator.Severity;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Public;
import br.com.onlares.dao.AlteraSenhaDao;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.AlteraSenha;
import br.com.onlares.model.Usuario;

/**
 * @author Thiago Carvalho
 * 
 */
@Controller
public class AlteraSenhaController {
	
	private static final String STATUS_SENHA_NAO_ALTERADA = "0";
	private static final String STATUS_SENHA_ALTERADA = "1";
	
	//private final Mailer mailer;
	private final UsuarioDao usuarioDao;
	private final AlteraSenhaDao alteraSenhaDao;
	private final Validator validator;
	private final Result result;

	@Inject
	public AlteraSenhaController(UsuarioDao usuarioDao, AlteraSenhaDao alteraSenhaDao, Validator validator, Result result) {
		//this.mailer = mailer;
		this.usuarioDao = usuarioDao;
		this.alteraSenhaDao = alteraSenhaDao;
		this.validator = validator;
		this.result = result;
	}
	
	@Deprecated
	public AlteraSenhaController() {
		this(null, null, null, null);
	}
	
    @Path("/alteraSenha/enviar")
    @Post
    public void enviar() throws EmailException {
        Email email = new SimpleEmail();
        email.setSubject("Your new password");
        email.addTo("thg.exe@gmail.com");
        email.setMsg("TESTE");
       // mailer.send(email); // Hostname, port and security settings are made by the Mailer
    }
	
	@Get("/alteraSenha")
	@Public
	public void index() { 
	}
	
	@Get("/alteraSenha/codigo/{codigo}")
	@Public
	public void codigo(String codigo) { 
		if (!checkValidPatern(codigo)) {
			validator.add(new SimpleMessage("alterasenha.codigo", "Código inválido!", Severity.ERROR));
			validator.onErrorUsePageOf(this).index();
		}
		result.forwardTo((this)).form(codigo);
	}
	
	@Get("/alteraSenha/form")
	@Public
	public void form(String codigo) { 
		result.include("codigo", codigo);
	}

	@Post("/alteraSenha/altera")
	@Public
	public void altera(AlteraSenha alteraSenha) {
		if (alteraSenha.getCodigo() == null) {
			validator.add(new SimpleMessage("alterasenha.altera", "Código inválido!", Severity.ERROR));
			validator.onErrorUsePageOf(this).index();
		}
		
		String codigo = alteraSenha.getCodigo();
		String novaSenha = alteraSenha.getNovaSenha();
		String confirmacaoDeNovaSenha = alteraSenha.getConfirmacaoDeNovaSenha();
		
		if (!novaSenha.equals(confirmacaoDeNovaSenha)) {
			result.include("codigo", codigo);
			validator.add(new SimpleMessage("alterasenha.altera", "Senhas não conferem!", Severity.ERROR));
			validator.onErrorUsePageOf(this).form(codigo);
		}
		
		AlteraSenha alteraSenhaDB;
		Usuario usuarioDB;
		String email;
		try {
			alteraSenhaDB = alteraSenhaDao.buscaPorCodigo(codigo);
			if (alteraSenhaDB == null) {
				result.include("codigo", codigo);
				validator.add(new SimpleMessage("alterasenha.altera", "Código não localizado!", Severity.ERROR));
				validator.onErrorUsePageOf(this).form(codigo);
			} else if (alteraSenhaDB.getStatus().equals("1")){
				result.include("codigo", codigo);
				validator.add(new SimpleMessage("alterasenha.altera", "Solicitação já realizada!", Severity.ERROR));
				validator.onErrorUsePageOf(this).form(codigo);
			} else {
				email = alteraSenhaDB.getEmail();
				usuarioDB = usuarioDao.buscaPorEmail(email);
				// Atualiza usuario
				usuarioDB.setSenha(novaSenha); // TODO adicionar MD5
				usuarioDao.edita(usuarioDB);
				// Atualiza alteraSenha
				alteraSenhaDB.setStatus(STATUS_SENHA_ALTERADA);
				alteraSenhaDao.edita(alteraSenhaDB);
			}
		} catch (Exception e) {
			result.include("codigo", codigo);
			validator.add(new SimpleMessage("alterasenha.altera", "Solicitação inválida!", Severity.ERROR));
			validator.onErrorUsePageOf(this).form(codigo);
		}
		
		result.include("notice", "Senha alterada com sucesso!");
		result.redirectTo(LoginController.class).login();
	}
	
	private boolean checkValidPatern(String codigo) {
		boolean valid = true;
		if (codigo == null || codigo.length() != 8) {
			valid = false;
		}
		return valid;
	}
	
}
