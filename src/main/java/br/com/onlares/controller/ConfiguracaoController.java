package br.com.onlares.controller;

import java.io.Serializable;

import javax.inject.Inject;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.simplemail.Mailer;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.dao.AlteraEmailDao;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.AlteraEmail;
import br.com.onlares.model.Status;
import br.com.onlares.model.Usuario;
import br.com.onlares.service.GeradorDeCodigo;

@Controller
public class ConfiguracaoController implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Environment environment;
	private final int TAMANHO_DO_CODIGO = 16;
	private final Mailer mailer;
	private final UsuarioDao usuarioDao;
	private final AlteraEmailDao alteraEmailDao;
	@SuppressWarnings("unused")
	private final UsuarioLogado usuarioLogado;
	private final Validator validator;
	private final Result result;

	@Inject
	public ConfiguracaoController(Mailer mailer, Environment environment, UsuarioDao usuarioDao, AlteraEmailDao alteraEmailDao, UsuarioLogado usuarioLogado, Validator validator, Result result) {
		this.mailer = mailer;
		this.environment = environment;
		this.usuarioDao = usuarioDao;
		this.alteraEmailDao = alteraEmailDao;
		this.usuarioLogado = usuarioLogado;
		this.validator = validator;
		this.result = result;
	}
	
	@Deprecated
	public ConfiguracaoController() {
		this(null, null, null, null, null, null, null);
	}
	
	@Get
	public void email() {
	}
	
	@Put
	public void email(Usuario usuario) {
		if (checkNull(usuario.getEmail()).equals("")) {
			validator.add(new I18nMessage("campo.obrigatorio", "Email"));
			validator.onErrorUsePageOf(this).email();
		}
		
		Long id = usuario.getId();
		Usuario usuarioDB = usuarioDao.buscaPorId(id);
		String emailNovo = usuario.getEmail();
		String emailAntigo = usuarioDB.getEmail();
		
		System.out.println(emailAntigo + " " + emailNovo);
		if (!emailNovo.equals(emailAntigo)) {
			GeradorDeCodigo geradorDeCodigo = new GeradorDeCodigo();
			String codigo = geradorDeCodigo.gerar(TAMANHO_DO_CODIGO);
			try {
				solicitarAlteracaoDeEmail(emailAntigo, emailNovo, codigo);
				enviaConfirmacaoPorEmail(emailNovo, codigo);
			} catch(EmailException eExp) {
				eExp.printStackTrace();	
				validator.add(new SimpleMessage("configuracao.email", "Erro ao enviar email de confirmação!"));
				validator.onErrorUsePageOf(this).email();
			}
			result.include("notice", "Um email de confirmação foi enviado para " + emailNovo + "."
					+ " Verifique em sua caixa de entrada e clique no link para confirmar a alteração.");
		}
		result.redirectTo(this).email();
	}
	
	@Get
	public void notificacoes() {
	}
	
	@Get
	public void senha() {
	}
	
	@Get
	public void conta() {
	}
	
	public void solicitarAlteracaoDeEmail(String emailAntigo, String emailNovo, String codigo) {
		AlteraEmail alteraEmail = new AlteraEmail();
		alteraEmail.setCodigo(codigo);
		alteraEmail.setEmailAntigo(emailAntigo);
		alteraEmail.setEmailNovo(emailNovo);
		alteraEmail.setStatus(Status.PENDENTE.getCodigo());
		alteraEmailDao.adiciona(alteraEmail);
	} 
	
	public void enviaConfirmacaoPorEmail(String emailNovo, String codigo) throws EmailException {
		// TODO adicionar html content e context path
        Email email = new SimpleEmail();
        email.setSubject("Confirmação de Email");
        email.addTo(emailNovo);
        email.setMsg("Clique no link para realizar a alteração: "
        		+ environment.get("context") + "alteraEmail/codigo/" + codigo );
        mailer.send(email);
	
	}
	
	private String checkNull(String value) {
		if (value == null) {
			return ("");
		} else {
			return (value.trim());
		}
	}

}
