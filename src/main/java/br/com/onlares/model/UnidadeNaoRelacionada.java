package br.com.onlares.model;


/**
 * @author Thiago Carvalho
 * 
 */
public class UnidadeNaoRelacionada extends Unidade {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String descricao;

	public UnidadeNaoRelacionada() {
		this.id = 0L;
		this.descricao = "Não relacionada";
	}

	public Long getId() {
		return id;
	}
	public String getDescricao() {
		return descricao;
	}

}
