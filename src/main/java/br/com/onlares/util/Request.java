package br.com.onlares.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class Request {

	public static void print(HttpServletRequest request){
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			System.out.println("name=" + paramName);
			String[] paramValues = request.getParameterValues(paramName);
			for (int i = 0; i < paramValues.length; i++) {
				String paramValue = paramValues[i];
				System.out.println("\tvalue=" + paramValue);
			}
		}
	}
}
