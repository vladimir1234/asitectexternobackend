package com.asitec.util;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerCustom {
	
	private static final Logger log = LoggerFactory.getLogger(LoggerCustom.class);
	
	public static void error(String aplicacion, Object o, String m, Exception e){
		
		String msg = "";
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss");
		
		if(aplicacion != null && !aplicacion.equals(""))
		msg += "Aplicacion        : " + aplicacion + "\n";
		msg += "Fecha             : " + formatoFecha.format(new java.util.Date()) + "\n";
		msg += "Hora              : " + formatoHora.format(new java.util.Date()) + "\n";
		if(o != null)
		msg += "Clase             : " + o.getClass().getName() + "\n";
		if(m != null && !m.equals(""))
		msg += "Metodo            : " + m + "\n";
		msg += "Tipo de error     : " + e.getClass().getSimpleName() + "\n";
		msg += "Causa del error   : " + e.getMessage() + "\n";
		
		StackTraceElement[] stackTrace = e.getStackTrace();
		
		if(stackTrace != null) 
		{
		msg += "Detalle del error : \n\n";
			StackTraceElement stackTraceElement = null;
			
			msg += e.getClass().getName() + ": " + e.getLocalizedMessage() + "\n";
			
			for (int i = 0; i < stackTrace.length; i++) {
				stackTraceElement = stackTrace[i];
				
				msg +=  "\t" + stackTraceElement.toString() + "\n";
			}
		}
		
		log.error(msg);
	}
	
	public static void errorApp(Object o, String m, Exception e) {
		String APP = "Pais - RSPais";
		
		error(APP, o, m, e);
	}
}
