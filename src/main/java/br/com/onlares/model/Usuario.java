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
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
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
	private String cadastro;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String cidade;
	private String uf;
	private String cep;
	private String profissao;
	private String aniversario;
	@Column(name="email_secundario")
	private String emailSecundario;
	@Column(name="fone_residencial")
	private String foneResidencial;
	@Column(name="fone_celular")
	private String foneCelular;
	@Column(name="fone_comercial")
	private String foneComercial;
	@Column(name="nome_completo")
	private String nomeCompleto;
	private String autorizacao;
	@Column(name="alertas_por_email")
	private boolean alertasPorEmail;
	@Transient
	private List<Unidade> unidades;
	@Transient
	private String localizacoes;
	@Transient
	private int quantidadeDeAvisos;
	@Transient
	private String fotoTemp;
	private String foto;

	public Usuario() {
		this(null, null, null);
	}
	
	public Usuario(String email, String senha, String nome) {
		this.alertasPorEmail = true;
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
	public String getCadastro() {
		return cadastro;
	}
	public void setCadastro(String cadastro) {
		if (cadastro != null) {
			this.cadastro = cadastro;
		}
	}
	/*
	 * getters e setters cpf e cnpj auxiliam para inserir na colula (cadastro)
	 * no db somente o campo preenchido e permite a separacao desse campo
	 * quando se obtem o (cadastro) no db
	 */
	public String getCpf() {
		if (getIsPF()) {
			return getCadastro();
		}
		return "";
	}
	public void setCpf(String cpf) {
		if (getCnpj().equals("")) {
			setCadastro(cpf);
		}
	}
	public String getCnpj() {
		if (getIsPJ()) {
			return getCadastro();
		}
		return "";
	}
	public void setCnpj(String cnpj) {
		if (getCpf().equals("")) {
			setCadastro(cnpj);
		}
	}
	public boolean getIsPF() {
		if (getCadastro() != null && getCadastro().length() == 14) {
			return true;
		}
		return false;
	}
	public boolean getIsPJ() {
		if (getCadastro() != null && getCadastro().length() == 18) {
			return true;
		}
		return false;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
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
	public String getEmailSecundario() {
		return emailSecundario;
	}
	public void setEmailSecundario(String emailSecundario) {
		this.emailSecundario = emailSecundario;
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
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
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
		if (unidades != null) {
			for (Unidade unidade : unidades) {
				if (unidade.getId().equals(id)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public int getQuantidadeDeAvisos() {
		return quantidadeDeAvisos;
	}
	
	public void setQuantidadeDeAvisos(int quantidadeDeAvisos) {
		this.quantidadeDeAvisos = quantidadeDeAvisos;
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