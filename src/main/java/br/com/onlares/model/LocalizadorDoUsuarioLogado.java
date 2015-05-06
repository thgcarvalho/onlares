package br.com.onlares.model;

import br.com.onlares.controller.UsuarioLogado;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class LocalizadorDoUsuarioLogado {
	
	/**
	 * Método obter o usuario atual a por meio do UsuarioLogado
	 * 
	 * @author Thiago Carvalho - tcarvalho@grandev.com.br
	 * @param usuarioLogado UsuarioLogado
	 * @return Long - usuarioId
	 */
	public static Long getUsuarioIdAtual(UsuarioLogado usuarioLogado) {
		if (usuarioLogado != null && usuarioLogado.getLocalizadorAtual() != null
				&& usuarioLogado.getLocalizadorAtual().getUsuario()  != null) {
			return usuarioLogado.getLocalizadorAtual().getUsuario().getId();
		} else {
			return Constantes.USUARIO_INEXISTENTE_ID;
		}
	}

	/**
	 * Método obter o condominio atual a por meio do UsuarioLogado
	 * 
	 * @author Thiago Carvalho - tcarvalho@grandev.com.br
	 * @param usuarioLogado UsuarioLogado
	 * @return Long - condominioId
	 */
	public static Long getCondominioIdAtual(UsuarioLogado usuarioLogado) {
		if (usuarioLogado != null && usuarioLogado.getLocalizadorAtual() != null
				&& usuarioLogado.getLocalizadorAtual().getCondominio() != null) {
			return usuarioLogado.getLocalizadorAtual().getCondominio().getId();
		} else {
			return Constantes.CONDOMINIO_INEXISTENTE_ID;
		}
	}
	
	/**
	 * Método obter a unidade atual a por meio do UsuarioLogado
	 * 
	 * @author Thiago Carvalho - tcarvalho@grandev.com.br
	 * @param usuarioLogado UsuarioLogado
	 * @return Long - unidadeId
	 */
	public static Long getUnidadeIdAtual(UsuarioLogado usuarioLogado) {
		if (usuarioLogado != null && usuarioLogado.getLocalizadorAtual() != null
				&& usuarioLogado.getLocalizadorAtual().getUnidade()  != null) {
			return usuarioLogado.getLocalizadorAtual().getUnidade().getId();
		} else {
			return Constantes.UNIDADE_NAO_RELACIONADA_ID;
		}
	}

}
