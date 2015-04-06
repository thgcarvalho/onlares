package br.com.onlares.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.onlares.model.Localizador;
import br.com.onlares.model.Usuario;

@SessionScoped
@Named
public class UsuarioLogado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	private List<Localizador> localizadores;
	private Localizador localizadorAtual;

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		System.out.println("USUARIO LOGADO = " + usuario.getNome());
		this.usuario = usuario;
	}
	public List<Localizador> getLocalizadores() {
		return localizadores;
	}
	public void setLocalizadores(List<Localizador> localizadores) {
		setIdentificadorAtual(localizadores.get(0));
		for (Localizador localizador : localizadores) {
			System.out.println("      IDENTIFICADOR = "
					+ localizador.getUsuario().getNome() + " - "
					+ localizador.getCondominio().getNome() + " - "
					+ localizador.getUnidade().getDescricao());
		}
		this.localizadores = localizadores;
	}
	public Localizador getLocalizadorAtual() {
		return localizadorAtual;
	}
	public void setIdentificadorAtual(Localizador localizadorAtual) {
		this.localizadorAtual = localizadorAtual;
	}
	
	public void logout() {
		this.usuario = null;
	}



	
}