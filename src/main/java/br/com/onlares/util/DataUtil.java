package br.com.onlares.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**  
* Copyright (c) 2015 GranDev - All rights reserved.
* @author Thiago Carvalho - tcarvalho@grandev.com.br
* 
*/
public class DataUtil {
	
	private static SimpleDateFormat sdfyyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
	private static SimpleDateFormat sdfdmy = new SimpleDateFormat("dd/MM/yyyy"); 
	private static SimpleDateFormat sdfhm = new SimpleDateFormat("HH:mm"); 
	private Date dataAtual;
	
	public DataUtil() {
		this.dataAtual = new Date();
	}
	
	public DataUtil(Date dataAtual) {
		this.dataAtual = dataAtual;
	}
	
	/**
	 * Retorna a data em que o alerta iniciará.
	 * 
	 * @param vencimento 	Data limite
	 * @param alerta 		Número em dias calculado ao vencimento
	 * @return 				Data do alerta
	 * @author 				Thiago Carvalho
	 */
	public Date getDataDoAlerta(Date limite, int alerta) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(limite); 
		calendar.add(Calendar.DAY_OF_MONTH, alerta);
		return calendar.getTime();
	}
	
	/**
	 * Obtém o dia da data limite a partir de uma data.
	 * 
	 * @param vencimento 	Data
	 * @return 				Dia do vencimento
	 * @author 				Thiago Carvalho
	 */
	public int getDiaDaData(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data); 
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * Calcula o número de dias em relação ao vencimento.
	 * 
	 * @param vencimento 	Data do vencimento
	 * @return 				Diferença em dias
	 * @author 				Thiago Carvalho
	 */
	public int getDiasQueFaltam(Date vencimento) {
		Calendar calAtual = Calendar.getInstance();
		calAtual.setTime(dataAtual); 
		
		Calendar calVencimento = Calendar.getInstance();
		calVencimento.setTime(vencimento);
		calVencimento.set(Calendar.HOUR_OF_DAY, calAtual.get(Calendar.HOUR_OF_DAY));
		calVencimento.set(Calendar.MINUTE, calAtual.get(Calendar.MINUTE));
		calVencimento.set(Calendar.SECOND, calAtual.get(Calendar.SECOND));
		calVencimento.set(Calendar.MILLISECOND, calAtual.get(Calendar.MILLISECOND));
		
		// diferença em milisegundos
        long diferenca = calVencimento.getTimeInMillis() - calAtual.getTimeInMillis();
        // Quantidade de milissegundos em um dia
        int tempoDia = 1000 * 60 * 60 * 24;
        // diferença em dias
		return (int) (diferenca / tempoDia);
	}
	
	public Date getDataValida(int dia, int mes, int ano) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1); // seta, mas pode ser alterado
		calendar.set(Calendar.MONTH, mes);
		calendar.set(Calendar.YEAR, ano);
		
		int ultimoDia = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		if (dia > ultimoDia) {
			calendar.set(Calendar.DAY_OF_MONTH, ultimoDia);
		} else {
			calendar.set(Calendar.DAY_OF_MONTH, dia);
		}
		return calendar.getTime();
	}
	
	public Integer getMes(String descMes) {
		if (descMes == null) {
			return null;
		} else if (descMes.equals("JANEIRO")) {
			return 0;
		} else if (descMes.equals("FEVEREIRO")) {
			return 1;
		} else if (descMes.equals("MARÇO")) {
			return 2;
		} else if (descMes.equals("ABRIL")) {
			return 3;
		} else if (descMes.equals("MAIO")) {
			return 4;
		} else if (descMes.equals("JUNHO")) {
			return 5;
		} else if (descMes.equals("JULHO")) {
			return 6;
		} else if (descMes.equals("AGOSTO")) {
			return 7;
		} else if (descMes.equals("SETEMBRO")) {
			return 8;
		} else if (descMes.equals("OUTUBRO")) {
			return 9;
		} else if (descMes.equals("NOVEMBRO")) {
			return 10;
		} else if (descMes.equals("DEZEMBRO")) {
			return 11;
		}
		return null;
	}
	
	public String getDescMes(int mes) {
		if (mes == 0) {
			return "JANEIRO";
		} else if (mes == 1) {
			return "FEVEREIRO";
		} else if (mes == 2) {
			return "MARÇO";
		} else if (mes == 3) {
			return "ABRIL";
		} else if (mes == 4) {
			return "MAIO";
		} else if (mes == 5) {
			return "JUNHO";
		} else if (mes == 6) {
			return "JULHO";
		} else if (mes == 7) {
			return "AGOSTO";
		} else if (mes == 8) {
			return "SETEMBRO";
		} else if (mes == 9) {
			return "OUTUBRO";
		} else if (mes == 10) {
			return "NOVEMBRO";
		} else if (mes == 11) {
			return "DEZEMBRO";
		}
		return null;
	}
	
	public boolean dentroDoMes(Date dataLimite, int mes) {
		Calendar calDataLimite = Calendar.getInstance();
		calDataLimite.setTime(dataLimite); 
		int mesDaDataLimite = calDataLimite.get(Calendar.MONTH);
		if (mesDaDataLimite == mes) {
			return true;
		}
		return false;
	}

	public boolean dentroDoMes(Date dataLimite, int frequencia, int mes) {
		Calendar calDataLimite = Calendar.getInstance();
		calDataLimite.setTime(dataLimite); 
		int mesDaDataLimite = calDataLimite.get(Calendar.MONTH);
		int frequenciaAUX = frequencia;
		int i = 0;
		
		while (i < 12) {
			if (((mesDaDataLimite + frequenciaAUX) % 12) == mes) {
				return true;
			}
			frequenciaAUX += frequencia;
			i++;
		}
		
		return false;
	}
	
	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}
	
	public static void zeraHora(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}
	
	public static String formatarData(Calendar calendar) {
		String dataFormatada;
		try {
			dataFormatada = sdfdmy.format(calendar.getTime());
		} catch (Exception exp) {
			dataFormatada = "";
		}
		return dataFormatada;
	}
	
	public static String formatarHora(Calendar calendar) {
		String horaFormatada;
		try {
			horaFormatada = sdfhm.format(calendar.getTime());
		} catch (Exception exp) {
			horaFormatada = "";
		}
		return horaFormatada;
	}
	
	public static Calendar formatarHoraCalenndar(Calendar calendar) {
		Calendar horaCalendarFormatada = GregorianCalendar.getInstance();
		String strHm = null;
		Date dtHm = null;
		try {
			strHm = sdfhm.format(calendar.getTime());
			dtHm = sdfhm.parse(strHm);
			horaCalendarFormatada.setTime(dtHm);
		} catch (Exception exp) {
			horaCalendarFormatada = null;
		}
		return horaCalendarFormatada;
	}
	
	public static String formatarTudo(Calendar calendar) {
		String tudo;
		try {
			tudo = sdf.format(calendar.getTime());
		} catch (Exception exp) {
			tudo = "";
		}
		return tudo;
	}
	
	public static String formatarTudoInverso(Calendar calendar) {
		String tudoInverso;
		try {
			tudoInverso = sdfyyyyMMddHHmmss.format(calendar.getTime());
		} catch (Exception exp) {
			tudoInverso = "";
		}
		return tudoInverso;
	}
}
