package br.com.onlares.model;

import java.io.Serializable;
import java.net.URI;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.caelum.vraptor.observer.download.Download;

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
	@Column(name="fone_residencial")
	private String foneResidencial;
	@Column(name="fone_celular")
	private String foneCelular;
	@Column(name="fone_comercial")
	private String foneComercial;
	private String tipo;
	@Column(name="alertas_por_email")
	private boolean alertasPorEmail;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "condominio_id", referencedColumnName = "id", insertable = true, updatable = true)
	private Condominio condominio;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "unidade_id", referencedColumnName = "id", insertable = true, updatable = true)
	private Unidade unidade;
	private String foto;
	@Transient
	private Download fotoDownload;
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
		return (this.tipo != null && this.tipo.equals("ADMIN"));
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
		this.email = email;
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public boolean isAlertasPorEmail() {
		return alertasPorEmail;
	}
	public void setAlertasPorEmail(boolean alertasPorEmail) {
		this.alertasPorEmail = alertasPorEmail;
	}
	public Condominio getCondominio() {
		return condominio;
	}
	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
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
	
	@Deprecated // chamada no header
	public Download getFotoDownload() {
		System.out.println("@Deprecated USUARIO GET FOTO DOWNLOAD " + fotoDownload);
		return fotoDownload;
	}
	
	@Deprecated // chamada em LoginController.adicionaFotoDoPerfilEmMemoria
	public void setFotoDownload(Download fotoDownload) {
		System.out.println("@Deprecated USUARIO SET FOTO DOWNLOAD " + fotoDownload);
		this.fotoDownload = fotoDownload;
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
		result = prime * result + ((condominio == null) ? 0 : condominio.hashCode());
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