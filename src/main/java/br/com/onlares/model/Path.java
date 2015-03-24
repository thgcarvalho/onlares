//package br.com.onlares.model;
//
//import javax.inject.Inject;
//
//import br.com.caelum.vraptor.environment.Environment;
//
///**
// * @author Thiago Carvalho
// * 
// */
//public class Path {
//
//	public final String domain;
//	public final String context;
//	public boolean test = true;
//
//	@Inject
//	public Path(Environment environment) {
//		System.out.println(environment.getName());
//		System.out.println(environment.isDevelopment());
//		System.out.println(environment.isProduction());
//		System.out.println(environment.get("context"));
//		if (environment.isProduction()) {
//			domain = "http://www.grandev.me";
//			context = "onlares";
//		} else {
//			domain = "http://localhost:8080";
//			context = "onlares";
//		}
//	}
//
//	@Deprecated
//	public Path() {
//		this(null);
//	}
//
//	public String getDomain() {
//		return domain;
//	}
//
//	public String getContext() {
//		return context;
//	}
//	
//	public String getSeparador(){
//		return "/";
//	}
//	
//}