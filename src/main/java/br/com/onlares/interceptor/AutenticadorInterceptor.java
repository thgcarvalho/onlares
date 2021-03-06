package br.com.onlares.interceptor;

import javax.inject.Inject;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.onlares.annotations.Public;
import br.com.onlares.bean.UsuarioLogado;
import br.com.onlares.controller.LoginController;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
@Intercepts
public class AutenticadorInterceptor { 

	@Inject
	private UsuarioLogado usuarioLogado;
	@Inject
	private Result result;
	@Inject
	private ControllerMethod controllerMethod;
	
	@Accepts
	public boolean accepts(){
		return !controllerMethod.containsAnnotation(Public.class);
	}

	@AroundCall
	public void intercepta(SimpleInterceptorStack stack) {
		if (usuarioLogado.getUsuario() == null) {
			result.redirectTo(LoginController.class).login();
			return;
		}
		stack.next();
	}
	
}
