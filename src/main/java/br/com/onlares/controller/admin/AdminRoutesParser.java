//package br.com.onlares.controller.admin;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.inject.Specializes;
//
//import br.com.caelum.vraptor.http.route.PathAnnotationRoutesParser;
//import br.com.caelum.vraptor.http.route.Router;
//
///**
// * @author Thiago Carvalho
// * 
// *  Altera a url padrao dos metodos nao anotados dos controllers do vrpator,
// *  adicionado /admin aos controladores do pacote controller.admin
// * 
// */
//@Specializes
//@ApplicationScoped
//public class AdminRoutesParser extends PathAnnotationRoutesParser {
//
//	public AdminRoutesParser(Router router) {
//		super(router);
//	}
//
//	@Override
//	protected String extractControllerNameFrom(Class<?> type) {
//		String controllerName = super.extractControllerNameFrom(type);
//		System.out.println("XXXXX  AdminRoutesParser=" +controllerName);
//		if (type.getPackage().getName().endsWith(".admin")) {
//			System.out.println("XXXXX OK  admin/" + controllerName);
//			return "/admin" + controllerName;
//		}
//		return controllerName;
//	}
//	
////	@Override
////	protected String[] getURIsFor(Method javaMethod, Class<?> type) {
////		String[] uris = super.getURIsFor(javaMethod, type);
////		if (type.getPackage().getName().endsWith(".admin")) {
////			for (int i = 0; i < uris.length; i++) {
////				uris[i] = "/admin" + uris[i];
////			}
////		}
////		return uris;
////	}
//
//}
