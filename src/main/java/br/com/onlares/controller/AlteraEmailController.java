package br.com.onlares.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Public;
import br.com.onlares.dao.AlteraEmailDao;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.AlteraEmail;
import br.com.onlares.model.Status;
import br.com.onlares.model.Usuario;

/**
 * @author Thiago Carvalho
 * 
 */
@Controller
public class AlteraEmailController {
	
	private final int TAMANHO_DO_CODIGO = 8;
	
	private final UsuarioDao usuarioDao;
	private final AlteraEmailDao alteraEmailDao;
	private final Validator validator;
	private final Result result;

	@Inject
	public AlteraEmailController(UsuarioDao usuarioDao, AlteraEmailDao alteraEmailDao, 
			Validator validator, Result result) {
		this.usuarioDao = usuarioDao;
		this.alteraEmailDao = alteraEmailDao;
		this.validator = validator;
		this.result = result;
	}
	
	@Deprecated
	public AlteraEmailController() {
		this(null, null, null, null);
	}
	
	@Public
	@Get("/alteraEmail")
	public void index() { 
	}
	
	@Public
	@Get("/alteraEmail/codigo/{codigo}")
	public void codigo(String codigo) { 
		if (!checkValidPatern(codigo)) {
			validator.add(new SimpleMessage("alteraemail.codigo", "Código inválido!"));
			validator.onErrorUsePageOf(this).index();
		}
		
		AlteraEmail alteraEmail = alteraEmailDao.buscaPorCodigo(codigo);
		String emailAntigo = null;
		String emailNovo = null;
		Usuario usuario = null;
		if (alteraEmail == null) {
			validator.add(new SimpleMessage("alteraemail.codigo", "Código não encontrado!"));
			validator.onErrorUsePageOf(this).index();
		} 
		
		emailAntigo = alteraEmail.getEmailAntigo();
		emailNovo = alteraEmail.getEmailNovo();
		System.out.println("Código encotrado! - emailAntigo = " + emailAntigo + " emailNovo = " + emailNovo);
		usuario = usuarioDao.buscaPorEmail(emailAntigo);
		if (usuario == null) {
			validator.add(new SimpleMessage("alteraemail.codigo", "Usuário não encontrado!"));
			validator.onErrorUsePageOf(this).index();
		}
		
		// Usuario
		usuario.setEmail(emailNovo);
		usuarioDao.altera(usuario);
		
		// AlteraEmail
		alteraEmail.setStatus(Status.ALTERADO.getCodigo());
		alteraEmailDao.altera(alteraEmail);
		
		result.include("notice", "Email alterado com sucesso!");
		result.redirectTo(this).index();
	}
	
	private boolean checkValidPatern(String codigo) {
		boolean valid = true;
		if (codigo == null || codigo.length() != TAMANHO_DO_CODIGO) {
			valid = false;
		}
		return valid;
	}
	
	@SuppressWarnings("unused")
	private String checkNull(String value) {
		if (value == null) {
			return ("");
		} else {
			return (value.trim());
		}
	}
	
}
