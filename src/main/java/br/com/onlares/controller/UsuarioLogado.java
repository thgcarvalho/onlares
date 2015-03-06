package br.com.onlares.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.onlares.model.Usuario;

@SessionScoped
@Named
public class UsuarioLogado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		System.out.println(" UsuarioLogado USUARIO LOGADO = " +usuario.getNome());
		this.usuario = usuario;
	}
	
	public void logout() {
		this.usuario = null;
	}
	
	@Get
	public Download getFotoDownload() {
		System.out.println(" XXXXXXX UsuarioLogado GET FOTO DOWNLOAD " + usuario.getFotoDownload());
		return usuario.getFotoDownload();
	}
	
}