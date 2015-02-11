package br.com.onlares.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

import br.com.caelum.vraptor.http.route.PathAnnotationRoutesParser;
import br.com.caelum.vraptor.http.route.Router;

@Specializes
@ApplicationScoped
public class AdminRoutesParser extends PathAnnotationRoutesParser {

	/**
	 * @deprecated CDI eyes only
	 */
	
	protected AdminRoutesParser() {
		this(null);
	}

	@Inject
	public AdminRoutesParser(Router router) {
		super(router);
	}

	@Override
	protected String extractControllerNameFrom(Class<?> type) {
		String controllerName = super.extractControllerNameFrom(type);
		if (type.getPackage().getName().endsWith(".admin")) {
			return "admin/" + controllerName;
		}
		return controllerName;
	}

}
