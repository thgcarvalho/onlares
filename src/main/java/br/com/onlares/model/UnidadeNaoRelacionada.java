package br.com.onlares.model;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class UnidadeNaoRelacionada extends Unidade {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String descricao;

	public UnidadeNaoRelacionada() {
		this.id = Constantes.UNIDADE_NAO_RELACIONADA_ID;
		this.descricao = "Não relacionada";
	}

	public Long getId() {
		return id;
	}
	public String getDescricao() {
		return descricao;
	}

}
