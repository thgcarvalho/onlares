package br.com.onlares.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Severity;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.onlares.annotations.Public;
import br.com.onlares.dao.AlteraSenhaDao;
import br.com.onlares.dao.UsuarioDao;
import br.com.onlares.model.AlteraSenha;
import br.com.onlares.model.Status;
import br.com.onlares.model.Usuario;
import br.com.onlares.util.MD5Hashing;

/**
 * @author Thiago Carvalho
 * 
 */
@Controller
@RequestScoped
public class AlteraSenhaController {

	private AlteraSenha alteraSenha;
	private Usuario usuario;
	
	private String novaSenha = null;
	private String confirmacaoDeNovaSenha = null;
	
	private String codigo;
	private String errorMessage;
	
	private final UsuarioDao usuarioDao;
	private final AlteraSenhaDao alteraSenhaDao;
	private final Validator validator;
	private final Result result;

	@Inject
	public AlteraSenhaController(UsuarioDao usuarioDao, AlteraSenhaDao alteraSenhaDao, Validator validator, Result result) {
		this.usuarioDao = usuarioDao;
		this.alteraSenhaDao = alteraSenhaDao;
		this.validator = validator;
		this.result = result;
	}
	
	@Deprecated
	public AlteraSenhaController() {
		this(null, null, null, null);
	}
	
	@Get("/alteraSenha")
	@Public
	public void index() { 
	}
	
	@Get("/alteraSenha/codigo/{codigo}")
	@Public
	public void codigo(String codigo) { 
		this.codigo = codigo;
		this.errorMessage = null;
		
		System.out.println("Verificação por email - Cod=" + this.codigo);
		
		if (!checkValidPatern(this.codigo)) {
			System.out.println("Código inválido!");
			validator.add(new SimpleMessage("alterasenha.codigo", "Código inválido!", Severity.ERROR));
			validator.onErrorUsePageOf(this).index();
		}
		
		if (this.errorMessage == null) {
			if (!checkCodigo(this.codigo)) {
				System.out.println("Código inexistente!");
				validator.add(new SimpleMessage("alterasenha.codigo", "Código inválido!", Severity.ERROR));
				validator.onErrorUsePageOf(this).index();
			}
		}
		
		result.forwardTo((this)).form();
	}
	
	@Get("/alteraSenha/form")
	@Public
	public void form() { 
		System.out.println("1codigo=" + codigo);
		AlteraSenha alteraSenha = new AlteraSenha();
		alteraSenha.setCodigo(codigo);
		alteraSenha.setEmail("tal");
		result.include("alteraSenha", alteraSenha);
	}

	@Post("/alteraSenha/altera")
	@Public
	public void altera(AlteraSenha alteraSenha) {
		System.out.println("alteraSenha=" + alteraSenha.getCodigo());
		System.out.println("alteraSenha=" + alteraSenha.getEmail());
		System.out.println("alteraSenha=" + alteraSenha.getNovaSenha());
//		if (!usuarioDao.existe(usuario)) {
//			validator.add(new I18nMessage("login", "login.invalido"));
//			validator.onErrorUsePageOf(this).login();
//		} 
//		Usuario usuarioDB = usuarioDao.buscaPorEmail(usuario.getEmail());
//		usuarioLogado.setUsuario(usuarioDB);
//		result.redirectTo(DashboardController.class).index();
		validator.add(new SimpleMessage("alterasenha.sucesso", "Senha alterado com sucesso!", Severity.INFO));
		result.redirectTo(LoginController.class).login();
	}
	
	private boolean checkValidPatern(String codigo) {
		boolean valid = true;
		if (codigo == null || codigo.length() != 8) {
			valid = false;
		}
		return valid;
	}
	
	public boolean checkCodigo(String codigo) {
		boolean valid = true;
		String email = null; // TODO precisa?
		
		try {
			alteraSenha = alteraSenhaDao.buscaPorCodigo(codigo);
			if (alteraSenha == null) {
				System.out.println("Código não localizado!");
				this.errorMessage = "Código não localizado!";
				valid = false;
			} else {
				email = alteraSenha.getEmail();
				System.out.println("Código localizado! - email = " + email);
			}
			
		} catch (NoResultException e) {
			this.errorMessage = "Solicitaçaoo inválida!";
			valid = false;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			valid = false;
			e.printStackTrace();
		}
		return valid;
	}
	
	public void alterarSenha() {
		if (alteraSenha != null) {
			String email = alteraSenha.getEmail();
			try {
				alteraSenha = alteraSenhaDao.buscaPorCodigo(codigo);
				usuario.setEmail(email);
				usuario.setSenha(MD5Hashing.convertStringToMd5(this.novaSenha));
				usuarioDao.edita(usuario);
				System.out.println("User alterado");
			} catch (NoResultException e) {
				this.errorMessage = "Solicitação inválida!";
				e.printStackTrace();
			} catch (Exception e) {
				this.errorMessage = e.getMessage();
				e.printStackTrace();
			}
		
			// AlteraEmail
			if (this.errorMessage == null) {
				alteraSenha.setStatus(Status.ALTERADO.getCodigo());
			} else {
				alteraSenha.setStatus(Status.FALHA.getCodigo());
			}
			try {
				alteraSenhaDao.edita(alteraSenha);
			} catch (Exception e) {
				this.errorMessage = e.getMessage();
				e.printStackTrace();
			}
		} else {
			System.out.println("Nada foi alterado");
		}
	}
	
	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	
	public String getConfirmacaoDeNovaSenha() {
		return confirmacaoDeNovaSenha;
	}

	public void setConfirmacaoDeNovaSenha(String confirmacaoDeNovaSenha) {
		this.confirmacaoDeNovaSenha = confirmacaoDeNovaSenha;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
}
