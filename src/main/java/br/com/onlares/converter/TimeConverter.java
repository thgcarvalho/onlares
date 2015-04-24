//package br.com.onlares.converter;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.util.Date;
//import java.util.ResourceBundle;
//
//import br.com.caelum.vraptor.Convert;
//import br.com.caelum.vraptor.converter.Converter;
//
//@Convert(Date.class)  
//public class TimeConverter implements Converter<Date> {  
//  
//    public TimeConverter(LocaleBasedDateConverter delegate) {  
//         this.delegate = delegate;  
//    }  
//  
//    public Date convert(String value, Class<? extends Date> type, ResourceBundle bundle) {  
//        if (value == null || value.equals("")) {  
//            return null;  
//        }  
//        DateFormat format = DateFormat.getTimeInstance();  
//        try {  
//            return format.parse(value);  
//        } catch (ParseException e) {  
//            return delegate.convert(value, type, bundle);  
//        }  
//    }
//
//	@Override
//	public Date convert(String value, Class<? extends Date> type) {
//		// TODO Auto-generated method stub
//		return null;
//	}  
//}  