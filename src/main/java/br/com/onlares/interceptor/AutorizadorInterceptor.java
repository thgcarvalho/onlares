package br.com.onlares.interceptor;

import javax.inject.Inject;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.caelum.vraptor.view.Results;
import br.com.onlares.annotations.Admin;
import br.com.onlares.controller.UsuarioLogado;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Intercepts(after=AutenticadorInterceptor.class)
public class AutorizadorInterceptor { 

	@Inject
	private UsuarioLogado usuarioLogado;
	@Inject
	private Result result;
	@Inject
	private ControllerMethod controllerMethod;
	
	@Accepts
	public boolean accepts(){
		return controllerMethod.containsAnnotation(Admin.class);
	}

	@AroundCall
	public void intercepta(SimpleInterceptorStack stack) {
		if (!usuarioLogado.getUsuario().isAdmin()) {
			result.use(Results.http()).sendError(401, "Você não está autorizado!");
			return;
		}
		stack.next();
	}
	
}
