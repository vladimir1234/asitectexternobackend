package com.asitec.util;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
//import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

public class Util {

	public static String cleanString(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return texto;
    }
	
	public static int getRandomNumberInts(int min, int max) {
		Random random;
		try {
			random = SecureRandom.getInstanceStrong();
			return random.ints(min, (max + 1)).findFirst().getAsInt();
		} catch (NoSuchAlgorithmException e) {
			LoggerCustom.errorApp("", "", e);
			return 0;
		}
		
	}

//	public static String asJsonString(final Object obj) {
//		try {
//			return new ObjectMapper().writeValueAsString(obj);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}

	@Value("${jwt.originUrl}")
//	public static String originUrl;
	
	public static String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

	
	
	public static String getIp() {
		String ipRemoto =null;
		try {
			ipRemoto= InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			LoggerCustom.errorApp("", "", e);
		}
		return ipRemoto;
    }
	
	@Value("${websocket.url}")
//	public static String wsUrl;
	
	public static void ejecutarWS() {
		
		HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("http://www.pais.gob.pe:3000/emitting");
        // HttpPost post = new HttpPost(wsUrl + "/emitting");

        // Create some NameValuePair for HttpPost parameters
        List<NameValuePair> arguments = new ArrayList<>(1);
        arguments.add(new BasicNameValuePair("channel", "reload-atenciones"));

        try {
            post.setEntity(new UrlEncodedFormEntity(arguments));
            HttpResponse response = client.execute(post);

        } catch (IOException e) {
        	LoggerCustom.errorApp("", "", e);
        }
	}
	
	
	public static boolean  estaVacio(Object valor) {
		if(toStr(valor).trim().equals("")){
			return true;
		}else{
			return false;
		}

	}


	public static boolean  isEqualNoCaseSentivive(Object valor1, Object valor2) {
		if(toStrUpperCase(valor1).equals(toStrUpperCase(valor2))){
			return true;
		}else{
			return false;
		}	
	}

	public static boolean  isEqualCaseSentivive(Object valor1, Object valor2) {
		if(toStrTrim(valor1).equals(toStrTrim(valor2))){
			return true;
		}else{
			return false;
		}	
	}

	public static boolean  isStrNumero1MenorIgualQueStrNumero2(String strNum1, String strNum2) {
		if(isEmpty(strNum1)){
			return false;
		}

		if(isEmpty(strNum2)){
			return false;
		}

		if(toInteger(strNum1)<=toInteger(strNum2)){
			return true;
		}else{
			return false;
		}

	}

	public static int  toInteger(String valor) {
		int numero =  Integer.parseInt(valor);
		return numero;
	}

	public static boolean  isEmpty(Object valor) {
		return estaVacio(valor);
	}

	public static String  toStrTrim(Object valor) {
		if(valor==null){
			return "";
		}

		return valor.toString().trim();

	}

	public static String  toStr(Object valor) {
		if(valor==null){
			return "";
		}

		return valor.toString();

	}

	public static boolean  toBooleanValue(Boolean valor) {
		if(valor==null){
			return false;
		}

		return valor.booleanValue();

	}

	public static String  toStrUpperCase(Object valor) {

		return toStr(valor).toUpperCase();

	}

	public static String  toStrUpperCaseTrim(Object valor) {

		return toStr(valor).toUpperCase().trim();

	}

	public static String  toStrUpperCaseTrimWithoutExtraSpaces(Object valor) {
		String str = toStr(valor).toUpperCase().trim();
		String regex = "\\s{2,}";
		str = str.replaceAll(regex, " "); 

		return str;

	}


	public static String  toStrLowerCase(Object valor) {

		return toStr(valor).toLowerCase();

	}

	public static String  toStrLowerCaseTrim(Object valor) {

		return toStr(valor).toLowerCase().trim();

	}
	
	
	
	public static String getParam(Object campo) {
		String parametro = getString(campo);
		if (isNullOrEmpty(parametro)) {
			return Constantes.CADENA_VACIO;
		}		
		return parametro;
	}
	
	public static Integer getParamInt(Integer campo) {
		if (Util.isNullInteger(campo)) {
			return Constantes.ZERO_INT;
		}		
		return campo;
	}
	
	public static BigDecimal getParamBigDecimal(BigDecimal campo) {
		if (Util.isNullBigDecimal(campo)) {
			return BigDecimal.ZERO;// Constantes.ZERO_BIG_DECIMAL;
		}		
		return campo;
	}
	
	public static boolean isNullInteger(Integer campo) {
		if (!isNull(campo) && campo > 0) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isNullBigDecimal(BigDecimal campo) {
		if (!isNull(campo) ) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean isNull(Object campo) {
		if (campo == null) {
			return true;
		} else {
			return false;
		}
	}	

	public static Integer getInteger(String campo) {
		Integer valor = null;
		if (!isNullOrEmpty(campo)) {
			valor =	Integer.parseInt(campo);	
		}
		return valor; 	
	}
	
	public static String getString(Object campo) {
		if (campo != null) {
			if (campo instanceof Integer) {
				return String.valueOf((Integer) campo);
			} else if (campo instanceof Long) {
				return String.valueOf((Long) campo);
			} else if (campo instanceof BigDecimal) {
				return String.valueOf((BigDecimal) campo);
			} else {
				return (String) campo;
			}
		}
		return Constantes.EMPTY; 	
	}
	
	public static String reemplazarCaracterFecha(String cadena) {
		String newCadena ;
		if(isNullOrEmpty(cadena) ) {
			
			newCadena =cadena;
		}else {
			newCadena=cadena.replace("/", "-");
		}
		return newCadena;
	}
	
	public static boolean isNullOrEmpty(String campo) { 
	    return campo == null || campo.trim().length() == 0 || campo.equals("null") ;
	}
	
	public static int getAnioActual(){
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public static int getMesActual(){
		return Calendar.getInstance().get(Calendar.MONTH)+1;
	}
	
	public static int getDiaActual(){
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}
	
	
	public static String getObtenerRutaCarpetas(){
		return  "documento"+ System.getProperty("file.separator")+ Util.getAnioActual() 
     	+ System.getProperty("file.separator")+ Util.getMesActual()  + System.getProperty("file.separator") + Util.getDiaActual() +System.getProperty("file.separator");
	}
	
	public static String getObtenerRutaUPSArchivosXls(Long idProyecto){
		return  "documento"
				+ System.getProperty("file.separator")+"UPS"
				+ System.getProperty("file.separator")+ Util.getAnioActual() 
				+ System.getProperty("file.separator")+ Util.getMesActual()  
				+ System.getProperty("file.separator") + Util.getDiaActual()
				+ System.getProperty("file.separator")+ idProyecto.toString()
				+System.getProperty("file.separator");
	}
	
	public static Date getFechaActual() {
		Calendar calendar = Calendar.getInstance();
		java.util.Date temp = calendar.getTime();
		Date hoy = new Date(temp.getTime());
		return hoy;
	}
	
	public static long getMilisegundos() {
		Calendar calendar = Calendar.getInstance();
		long hoy = calendar.getTimeInMillis();

		return hoy;
	}
	
	public static Date getFecha(Date f1, int totalDias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(f1);
		calendar.add(Calendar.DAY_OF_MONTH, totalDias);
		Date f2 = new Date(calendar.getTime().getTime());
		return f2;
	}
	
	public static String ensureDir(String path) {
		File f_out = new File(path);
		if (!f_out.exists()) {
			f_out.mkdirs();
		}
		return f_out.getAbsolutePath();
	}

	public static String ensureUserDir(String path) {
		String outPath = System.getProperty("user.dir") + File.separator + path;
		File f_out = new File(outPath);
		if (!f_out.exists()) {
			f_out.mkdirs();
		}
		return f_out.getAbsolutePath();
	}

	public static String getUniqueID() {
		String r = "";
		try {
			MessageDigest salt = MessageDigest.getInstance("SHA-256");
			salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
			r = bytesToHex(salt.digest());
		} catch (Exception ex) {
			LoggerCustom.errorApp("", "", ex);
		}
		return r;
	}

	public static byte[] _bytesToHex(byte[] data) {
		String encoded = javax.xml.bind.DatatypeConverter.printHexBinary(data);
		return encoded.getBytes();
	}

	public static String bytesToHex(byte[] data) {
		return new String(_bytesToHex(data));
	}

	public static HSSFColor setColor(HSSFWorkbook workbook, byte r, byte g, byte b) {
		HSSFPalette palette = workbook.getCustomPalette();
		HSSFColor hssfColor = null;
		try {
			hssfColor= palette.findColor(r, g, b); 
			if (hssfColor == null) {
			    palette.setColorAtIndex(HSSFColor.LAVENDER.index, r, g,b);
			    hssfColor = palette.getColor(HSSFColor.LAVENDER.index);
			}
		 } catch (Exception e) {
			 LoggerCustom.errorApp("", "", e);
		 }
		 return hssfColor;
	}
	
}
