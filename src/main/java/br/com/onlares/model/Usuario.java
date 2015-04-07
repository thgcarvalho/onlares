package br.com.onlares.model;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Thiago Carvalho
 * 
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String email;
	private String senha;
	private String nome;
	private String profissao;
	private String aniversario;
	@Column(name="fone_residencial")
	private String foneResidencial;
	@Column(name="fone_celular")
	private String foneCelular;
	@Column(name="fone_comercial")
	private String foneComercial;
	private String autorizacao;
	@Column(name="alertas_por_email")
	private boolean alertasPorEmail;
	@Transient
	private List<Unidade> unidades;
	@Transient
	private String localizacoes;
	private String foto;
	@Transient
	private String fotoTemp;

	@Deprecated
	public Usuario() {
		this(null, null, null); // para uso do CDI
	}
	
	public Usuario(String email, String senha, String nome) {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
	}
	
	public boolean isAdmin() {
		return (this.autorizacao != null && this.autorizacao.equals("ADMIN"));
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = (email != null ? email.toLowerCase() : email);
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public String getAniversario() {
		return aniversario;
	}
	public void setAniversario(String aniversario) {
		this.aniversario = aniversario;
	}
	public String getFoneResidencial() {
		return foneResidencial;
	}
	public void setFoneResidencial(String foneResidencial) {
		this.foneResidencial = foneResidencial;
	}
	public String getFoneCelular() {
		return foneCelular;
	}
	public void setFoneCelular(String foneCelular) {
		this.foneCelular = foneCelular;
	}
	public String getFoneComercial() {
		return foneComercial;
	}
	public void setFoneComercial(String foneComercial) {
		this.foneComercial = foneComercial;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAutorizacao() {
		return autorizacao;
	}
	public void setAutorizacao(String autorizacao) {
		this.autorizacao = autorizacao;
	}
	public boolean isAlertasPorEmail() {
		return alertasPorEmail;
	}
	public void setAlertasPorEmail(boolean alertasPorEmail) {
		this.alertasPorEmail = alertasPorEmail;
	}
	
	public URI getFoto() {
		if (foto == null) {
			return null;
		}
		return URI.create(foto);
	}

	public void setFoto(URI foto) {
		this.foto = foto == null ? null : foto.toString();
	}
	
	public URI getFotoTemp() {
		if (fotoTemp == null) {
			return null;
		}
		return URI.create(fotoTemp);
	}

	public void setFotoTemp(URI fotoTemp) {
		this.fotoTemp = fotoTemp == null ? null : fotoTemp.toString();
	}
	
	public List<Unidade> getUnidades() {
		return unidades;
	}
	public void setUnidades(List<Unidade> unidades) {
		this.unidades = unidades;
	}
	public String getLocalizacoes() {
		return localizacoes;
	}
	public void setLocalizacoes(String localizacoes) {
		this.localizacoes = localizacoes;
	}
	public boolean contemUnidade(Long id) {
		System.out.println("contemUnidade(" + id + ") " + unidades);
		if (unidades != null) {
			for (Unidade unidade : unidades) {
				if (unidade.getId().equals(id)) {
					System.out.println("CONTEM(" + id + ")");
					return true;
				}
			}
		}
		return false;
	}
	
	@Transient
	public int getNotificacoes() {
		return 0;
	}
	
	@Transient
	public int getMensagens() {
		return 0;
	}
	
	public boolean isRegistrado() {
		return (!checkNull(senha).equals(""));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "(" + this.id + ") " + this.email + " - " + this.nome;
	}
	
	private String checkNull(String value) {
		if (value == null) {
			return ("");
		} else {
			return (value.trim());
		}
	}
	
	
}