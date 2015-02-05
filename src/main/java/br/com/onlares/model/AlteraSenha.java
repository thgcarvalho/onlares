package br.com.onlares.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Thiago Carvalho
 * 
 */
@Entity
@Table(name = "altera_senha")
public class AlteraSenha {

	@Id
	private String codigo;
	private String email;
	private String status;
	@Transient
	private String novaSenha;
	@Transient
	private String confirmacaoDeNovaSenha;
	
	public AlteraSenha() {
	}
	
	
	public AlteraSenha(String codigo, String email, String status) {
		this.codigo = codigo;
		this.email = email;
		this.status = status;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
}
