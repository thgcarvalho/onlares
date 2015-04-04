package br.com.onlares.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.onlares.model.Identificador;
import br.com.onlares.model.Usuario;

@SessionScoped
@Named
public class UsuarioLogado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	private List<Identificador> identificadores;
	private Identificador identificadorAtual;

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		System.out.println("USUARIO LOGADO = " + usuario.getNome());
		this.usuario = usuario;
	}
	public List<Identificador> getIdentificadores() {
		return identificadores;
	}
	public void setIdentificadores(List<Identificador> identificadores) {
		setIdentificadorAtual(identificadores.get(0));
		for (Identificador identificador : identificadores) {
			System.out.println("      IDENTIFICADOR = "
					+ identificador.getUsuario().getNome() + " - "
					+ identificador.getCondominio().getNome() + " - "
					+ identificador.getUnidade().getLocalizacao());
		}
		this.identificadores = identificadores;
	}
	public Identificador getIdentificadorAtual() {
		return identificadorAtual;
	}
	public void setIdentificadorAtual(Identificador identificadorAtual) {
		this.identificadorAtual = identificadorAtual;
	}
	
	public void logout() {
		this.usuario = null;
	}



	
}