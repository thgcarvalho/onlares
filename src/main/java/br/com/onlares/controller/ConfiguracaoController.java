package br.com.onlares.controller;

import java.io.Serializable;
import java.util.List;

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
import br.com.onlares.bean.UsuarioLogado;
import br.com.onlares.dao.AlteraEmailDao;
import br.com.onlares.dao.ExcluiContaDao;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.AlteraEmail;
import br.com.onlares.model.ExcluiConta;
import br.com.onlares.model.Status;
import br.com.onlares.model.Usuario;
import br.com.onlares.service.GeradorDeCodigo;
import br.com.onlares.util.MD5Hashing;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Controller
public class ConfiguracaoController implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Environment environment;
	private final int TAMANHO_DO_CODIGO = 8;
	private final Mailer mailer;
	private final UsuarioDao usuarioDao;
	private final AlteraEmailDao alteraEmailDao;
	private final ExcluiContaDao excluiContaDao;
	private final UsuarioLogado usuarioLogado;
	private final Validator validator;
	private final Result result;

	@Inject
	public ConfiguracaoController(Mailer mailer, Environment environment, UsuarioDao usuarioDao, ExcluiContaDao excluiContaDao, AlteraEmailDao alteraEmailDao, UsuarioLogado usuarioLogado, Validator validator, Result result) {
		this.mailer = mailer;
		this.environment = environment;
		this.usuarioDao = usuarioDao;
		this.alteraEmailDao = alteraEmailDao;
		this.excluiContaDao = excluiContaDao;
		this.usuarioLogado = usuarioLogado;
		this.validator = validator;
		this.result = result;
	}
	
	@Deprecated
	public ConfiguracaoController() {
		this(null, null, null, null, null, null, null, null);
	}
	
	// EMAIL
	
	@Get
	public void email() {
	}
	
	@Put
	public void email(Usuario usuario) {
		if (checkNull(usuario.getEmail()).equals("")) {
			validator.add(new I18nMessage("campo.obrigatorio", "Email"));
		} else {
			List<Usuario> usuarios = usuarioDao.listaTodos();
			for (Usuario usuarioCadastrado : usuarios) {
				if (usuarioCadastrado.getEmail().equals(usuario.getEmail())) {
					if (!usuarioCadastrado.getId().equals(usuario.getId())) {
						validator.add(new SimpleMessage("email.edita", "Email usado por outro usuário"));
						break;
					}
				}
			}
		}
		
		validator.onErrorUsePageOf(this).email();
		
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
				enviaConfirmacaoDeAlteracaoPorEmail(emailNovo, codigo);
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
	
	public void solicitarAlteracaoDeEmail(String emailAntigo, String emailNovo, String codigo) {
		AlteraEmail alteraEmail = new AlteraEmail();
		alteraEmail.setCodigo(codigo);
		alteraEmail.setEmailAntigo(emailAntigo);
		alteraEmail.setEmailNovo(emailNovo);
		alteraEmail.setStatus(Status.PENDENTE.getCodigo());
		alteraEmailDao.adiciona(alteraEmail);
	} 
	
	public void enviaConfirmacaoDeAlteracaoPorEmail(String emailNovo, String codigo) throws EmailException {
		// TODO adicionar html content e context path
        Email email = new SimpleEmail();
        email.setSubject("Confirmação de Email");
        email.addTo(emailNovo);
        email.setMsg("Clique no link para realizar a alteração: "
        		+ environment.get("context") + "alteraEmail/codigo/" + codigo );
        mailer.send(email);
	}
	
	// NOTIFICACOES
	
	@Get
	public void notificacoes() {
	}
	
	@Put
	public void notificacoes(Usuario usuario) {
		Long id = usuario.getId();
		boolean alertasPorEmail = usuario.isAlertasPorEmail();
		Usuario usuarioDB = usuarioDao.buscaPorId(id);
		usuarioDB.setAlertasPorEmail(alertasPorEmail);
		usuarioDao.altera(usuarioDB);
		usuarioLogado.setUsuario(usuarioDB);
		result.redirectTo(this).notificacoes();
	}
	
	// SENHA
	
	@Get
	public void senha() {
	}
	
	@Put
	public void senha(Usuario usuario) {
		Long id = usuario.getId();
		String novaSenha = usuario.getSenha();
		String confirmacaoDeNovaSenha = usuario.getSenha();
		System.out.println(id + " " + usuario.getSenha());
		if (!novaSenha.equals(confirmacaoDeNovaSenha)) {
			//result.include("codigo", codigo);
			validator.add(new SimpleMessage("configuracao.senha", "Senhas não conferem!"));
			validator.onErrorUsePageOf(this).senha();
		}
		try {
			Usuario usuarioDB = usuarioDao.buscaPorId(id);
			usuarioDB.setSenha(MD5Hashing.convertStringToMd5(novaSenha));
			usuarioDao.altera(usuarioDB);
			usuarioLogado.setUsuario(usuarioDB);
			result.include("notice", "Senha alterada com sucesso.");
			result.redirectTo(this).senha();
		} catch (Exception e) {
			validator.add(new SimpleMessage("configuracao.senha", "Solicitação inválida!"));
			validator.onErrorUsePageOf(this).senha();
		}
	}
	
	// CONTA
	
	@Get
	public void conta() {
	}
	
	@Put
	public void conta(Usuario usuario) {
		Long id = usuario.getId();
		Usuario usuarioDB = usuarioDao.buscaPorId(id);
		String emailDeConfirmacao = usuarioDB.getEmail();
		GeradorDeCodigo geradorDeCodigo = new GeradorDeCodigo();
		String codigo = geradorDeCodigo.gerar(TAMANHO_DO_CODIGO);
		solicitarExclusaoDeConta(emailDeConfirmacao, codigo);
		try {
			enviaConfirmacaoDeExclusaoPorEmail(emailDeConfirmacao, codigo);
		} catch(EmailException eExp) {
			eExp.printStackTrace();	
			validator.add(new SimpleMessage("configuracao.email", "Erro ao enviar email de confirmação!"));
			validator.onErrorUsePageOf(this).email();
		}
		result.include("notice", "Um email de confirmação foi enviado para " + emailDeConfirmacao + "."
				+ " Verifique em sua caixa de entrada e clique no link para confirmar a alteração.");
		result.redirectTo(this).email();
	}
	
	public void solicitarExclusaoDeConta(String emailDeConfirmacao, String codigo) {
		ExcluiConta excluiConta = new ExcluiConta();
		excluiConta.setCodigo(codigo);
		excluiConta.setEmail(emailDeConfirmacao);
		excluiConta.setStatus(Status.PENDENTE.getCodigo());
		excluiContaDao.adiciona(excluiConta);
	} 
	
	public void enviaConfirmacaoDeExclusaoPorEmail(String emailDeConfirmacao, String codigo) throws EmailException {
		// TODO adicionar html content e context path
        Email email = new SimpleEmail();
        email.setSubject("Confirmação de exclusão de conta");
        email.addTo(emailDeConfirmacao);
        email.setMsg("Clique no link para realizar a excluão: "
        		+ environment.get("context") + "excluiConta/codigo/" + codigo );
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
