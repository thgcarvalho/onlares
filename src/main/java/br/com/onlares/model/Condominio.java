package br.com.onlares.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Thiago Carvalho
 * 
 */
@Entity
@Table(name = "condominio")
public class Condominio implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cnpj;
	private String fone1;
	private String fone2;
	private String site;
	private String email;
	private String endereco;
	private String numero;
	private String complemento;
	private String cep;
	private String bairro;
	private String cidade;
	private String sindico;
	private String fone1Sindico;
	private String fone2Sindico;
	private String emailSindico;
	private String administradora;
	private String fone1Administradora;
	private String fone2Administradora;
	private String emailAdministradora;
	private String obs;
	private String status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getFone1() {
		return fone1;
	}

	public void setFone1(String fone1) {
		this.fone1 = fone1;
	}

	public String getFone2() {
		return fone2;
	}

	public void setFone2(String fone2) {
		this.fone2 = fone2;
	}
	
	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getCep() {
		return cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getSindico() {
		return sindico;
	}

	public void setSindico(String sindico) {
		this.sindico = sindico;
	}

	public String getFone1Sindico() {
		return fone1Sindico;
	}

	public void setFone1Sindico(String fone1Sindico) {
		this.fone1Sindico = fone1Sindico;
	}

	public String getFone2Sindico() {
		return fone2Sindico;
	}

	public void setFone2Sindico(String fone2Sindico) {
		this.fone2Sindico = fone2Sindico;
	}

	public String getEmailSindico() {
		return emailSindico;
	}

	public void setEmailSindico(String emailSindico) {
		this.emailSindico = emailSindico;
	}
	
	public String getAdministradora() {
		return administradora;
	}

	public void setAdministradora(String administradora) {
		this.administradora = administradora;
	}
	
	public String getFone1Administradora() {
		return fone1Administradora;
	}

	public void setFone1Administradora(String fone1Administradora) {
		this.fone1Administradora = fone1Administradora;
	}

	public String getFone2Administradora() {
		return fone2Administradora;
	}

	public void setFone2Administradora(String fone2Administradora) {
		this.fone2Administradora = fone2Administradora;
	}

	public String getEmailAdministradora() {
		return emailAdministradora;
	}

	public void setEmailAdministradora(String emailAdministradora) {
		this.emailAdministradora = emailAdministradora;
	}

	public String getFones() {
		String fones = "";
		if (fone1 != null && !fone1.equals("")) {
			fones += fone1;
			if (fone2 != null && !fone2.equals("")) {
				fones += " / ";
				fones += fone2;
			}
		} else if (fone2 != null && !fone2.equals("")) {
			fones += fone2;
		}
		return fones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Condominio other = (Condominio) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
