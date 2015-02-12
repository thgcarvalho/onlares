//package br.com.onlares.controller.admin;
//package br.com.onlares.controller;
//
//import javax.enterprise.inject.Specializes;
//
//import br.com.caelum.vraptor.controller.ControllerMethod;
//import br.com.caelum.vraptor.http.FormatResolver;
//import br.com.caelum.vraptor.view.DefaultPathResolver;
//
///**
// * @author Thiago Carvalho
// * 
// *  DEVERIA altera a padrao de diretórios dos arquivos jsps do vrpator,
// *  adicionado /admin aos controladores do pacote controller.admin
// * 
// */
//@Specializes
//public class AdminPathResolver extends DefaultPathResolver {
//
//	public AdminPathResolver(FormatResolver resolver) {
//		super(resolver);
//	}
//	
////	@Override
////	public String pathFor(ControllerMethod method) {
////		String path = super.pathFor(method);
////		if (method.getMethod().get.getName().endsWith(".admin")) {
////			System.out.println("XXXXX OK  admin/" + controllerName);
////			return "/admin" + controllerName;
////		}
////		return path;
////	}
//
//
////	@Override
////	protected String extractControllerFromName(String baseName) {
////		System.out.println("YYYYYY extractControllerFromName=" +baseName);
////		String controllerName = super.extractControllerNameFrom(type);
////		System.out.println("XXXXX  AdminRoutesParser=" +controllerName);
////		if (type.getPackage().getName().endsWith(".admin")) {
////			System.out.println("XXXXX OK  admin/" + controllerName);
////			return "/admin" + controllerName;
////		}
////		return baseName;
////	}
//
//}
