package br.com.onlares.comparador;

import java.util.Comparator;

import br.com.onlares.model.Autorizacao;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class ComparadorAutorizacao implements Comparator<Autorizacao> {

	@Override
	public int compare(Autorizacao a1, Autorizacao a2) {
		return -a1.getData().compareTo(a2.getData());
	}

}