package br.com.onlares.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class CondominioLogado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long condominioID;

	public long getCondominioID() {
		return condominioID;
	}

	public void setCondominio(long condominioID) {
		System.out.println(" CondominioLogado Condominio LOGADO = " + condominioID);
		this.condominioID = condominioID;
	}
	
	
}