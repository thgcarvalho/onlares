package br.com.onlares.comparador;

import java.util.Comparator;

import br.com.onlares.model.Unidade;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class ComparadorUnidade implements Comparator<Unidade> {

	@Override
	public int compare(Unidade u1, Unidade u2) {
		return u1.getDescricao().compareTo(u2.getDescricao());
	}

}