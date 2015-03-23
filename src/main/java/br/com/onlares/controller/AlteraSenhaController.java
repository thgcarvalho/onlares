package br.com.onlares.controller;

import javax.inject.Inject;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.simplemail.Mailer;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Severity;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Public;
import br.com.onlares.dao.AlteraSenhaDao;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.AlteraSenha;
import br.com.onlares.model.Status;
import br.com.onlares.model.Usuario;
import br.com.onlares.service.GeradorDeCodigo;
import br.com.onlares.util.MD5Hashing;

/**
 * @author Thiago Carvalho
 * 
 */
@Controller
public class AlteraSenhaController {
	
	private final int TAMANHO_DO_CODIGO = 8;
	
	private final Mailer mailer;
	private final UsuarioDao usuarioDao;
	private final AlteraSenhaDao alteraSenhaDao;
	private final Validator validator;
	private final Result result;

	@Inject
	public AlteraSenhaController(Mailer mailer, UsuarioDao usuarioDao, AlteraSenhaDao alteraSenhaDao, 
			Validator validator, Result result) {
		this.mailer = mailer;
		this.usuarioDao = usuarioDao;
		this.alteraSenhaDao = alteraSenhaDao;
		this.validator = validator;
		this.result = result;
	}
	
	@Deprecated
	public AlteraSenhaController() {
		this(null, null, null, null, null);
	}
	
	@Get("/esqueci")
	@Public
	public void esqueci() { }
	
	@Public
	@Post("/alteraSenha/enviaCodigo")
    public void enviaCodigo(String emailDoUsuario) {
		if (checkNull(emailDoUsuario).equals("")) {
			validator.add(new I18nMessage("usuario.adiciona", "campo.obrigatorio", "Email"));
			validator.onErrorUsePageOf(this).esqueci();
		}
		Usuario usuario = usuarioDao.buscaPorEmail(emailDoUsuario);
		System.out.println("Usuario " + usuario);
		if (usuario == null) {
			validator.add(new SimpleMessage("alterasenha.solicita", "Email não cadastrado!"));
			validator.onErrorUsePageOf(this).esqueci();
		}
		
		GeradorDeCodigo geradorDeCodigo = new GeradorDeCodigo();
		String codigo = geradorDeCodigo.gerar(TAMANHO_DO_CODIGO);
		// Salva novo registro 
		AlteraSenha alteraSenha = new AlteraSenha();
		alteraSenha.setCodigo(codigo);
		alteraSenha.setEmail(emailDoUsuario);
		alteraSenha.setStatus(Status.PENDENTE.getCodigo());
		alteraSenhaDao.adiciona(alteraSenha);
		// Envia email
		try {
			// TODO adicionar html content e context path
	        Email email = new SimpleEmail();
	        email.setSubject("Instruções para Nova Senha");
	        email.addTo(emailDoUsuario);
	        email.setMsg("Clique no link para realizar a alteração: "
	        		+ "http://www.grandev.me/onlares/alteraSenha/codigo/" + codigo );
	        mailer.send(email);
		} catch(EmailException eExp) {
			eExp.printStackTrace();	
			validator.add(new SimpleMessage("alterasenha.solicita", "Erro ao enviar email!", Severity.ERROR));
			validator.onErrorUsePageOf(this).esqueci();
		}
		
        result.include("notice", "Um email com as instruções foi enviado para " + emailDoUsuario);
		result.redirectTo(this).esqueci();
    }
	
	@Public
	@Get("/alteraSenha")
	public void index() { 
	}
	
	@Public
	@Get("/alteraSenha/codigo/{codigo}")
	public void codigo(String codigo) { 
		if (!checkValidPatern(codigo)) {
			validator.add(new SimpleMessage("alterasenha.codigo", "Código inválido!", Severity.ERROR));
			validator.onErrorUsePageOf(this).index();
		}
		result.forwardTo(this).form(codigo);
	}
	
	@Public
	@Get("/alteraSenha/form")
	public void form(String codigo) { 
		result.include("codigo", codigo);
	}

	@Public
	@Post("/alteraSenha/altera")
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
				usuarioDB.setSenha(MD5Hashing.convertStringToMd5(novaSenha));
				usuarioDao.altera(usuarioDB);
				// Atualiza alteraSenha
				alteraSenhaDB.setStatus(Status.ALTERADO.getCodigo());
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
		if (codigo == null || codigo.length() != TAMANHO_DO_CODIGO) {
			valid = false;
		}
		return valid;
	}
	
	private String checkNull(String value) {
		if (value == null) {
			return ("");
		} else {
			return (value.trim());
		}
	}
	
}
