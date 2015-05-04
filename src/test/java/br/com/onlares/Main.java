package br.com.onlares;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import br.com.onlares.util.DataUtil;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author  Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class Main {
	public static void main(String[] args) {
		TimeZone timeZone = TimeZone.getTimeZone("UTC");
		Calendar calendar = Calendar.getInstance(timeZone);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EE MMM dd HH:mm:ss zzz yyyy", Locale.CANADA);
		simpleDateFormat.setTimeZone(timeZone);

		System.out.println("Time zone: " + timeZone.getID());
		System.out.println("default time zone: " + TimeZone.getDefault().getID());
		System.out.println();

		System.out.println("UTC:     " + simpleDateFormat.format(calendar.getTime()));
		System.out.println("Default: " + calendar.getTime());
		
		//title=7 as 3 end=1431010800000 start=1430982000000
		Date dStart = new Date(1430982000000L);
		Date dEnd = new Date(1431010800000L);
		Calendar cStart = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		cStart.setTime(dStart);
		Calendar cEnd = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		cEnd.setTime(dEnd);
		System.out.println("START=" + DataUtil.formatarTudo(cStart));
		System.out.println("END=" + DataUtil.formatarTudo(cEnd));
		
		Calendar tzCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		System.out.println("T1: " + tzCal.getTime());
		
		TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");  
        TimeZone.setDefault(tz);  
        Calendar ca = GregorianCalendar.getInstance(tz);  
          
        System.out.println ("T2: " + ca.getTime());  
	}
}
