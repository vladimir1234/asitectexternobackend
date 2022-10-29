package com.asitec.util;


import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class UploadUtility {

	private static final String SERVIDOR_ARCHIVO_LINUX = "/SRVREPOSITORIOS/DATA.01";

	private static final String SERVIDOR_ARCHIVO_WINDOWS = "C:\\SRVREPOSITORIOS";

	// Reincorporacion
	private static final String SERVIDOR_ARCHIVO_WINDOWS_REINCORPORACION = "/SRVREPOSITORIOS/DATA.01/Mantenimiento/Reincorporacion";

	private static String ruta;

	public static String getRuta() {
		return ruta;
	}

	public static void setRuta(String ruta) {
		UploadUtility.ruta = ruta;
	}

	public static boolean isEmptyFile(MultipartFile mpf) {

		if (mpf == null || (mpf != null && (mpf.getSize() == 0 || Util.toStr(mpf.getOriginalFilename()).equals("")))) {
			return true;
		}
		return false;
	}


	public static boolean isValidFile(MultipartFile mpf) {

		if (mpf != null && mpf.getSize() != 0 && !Util.toStr(mpf.getOriginalFilename()).equals("")) {
			return true;
		}
		return false;
	}

	public static MultipartFile getMultipartFileFromRequest(MultipartHttpServletRequest request) {
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = request.getFile(itr.next());
		return mpf;
	}

	public static List<MultipartFile> getLstMultipartFileFromRequest(MultipartHttpServletRequest request) {
		Iterator<String> itr = request.getFileNames();
		List<MultipartFile> lst = new ArrayList<MultipartFile>();

		while (itr.hasNext()) {
			// MultipartFile mpf = request.getFile(itr.next());
			lst.add(request.getFile(itr.next()));
		}

		return lst;
	}
	
	
	
	public static boolean existeFileEnRuta(String rutaArchivo) {

		File fichero = new File(rutaArchivo);

		if (!fichero.exists()) {
			return false;
		}
		return true;
	}
	
	/*
	 * public static void saveUploadedFileToDisk(MultipartFile uploadedFile) throws
	 * Exception {
	 * 
	 * String extension =
	 * FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
	 * System.out.println("extension: " + extension);
	 * if(!verificarExtension(uploadedFile.getTipoDocumento(),extension)){ throw new
	 * Exception("forms.errors.upload.extension"); }
	 * 
	 * File dir = new
	 * File(UploadUtility.calculateDestinationDirectory(uploadedFile.getIdHogar(),
	 * uploadedFile.getProceso(),uploadedFile.getIdMiembro())); if(!dir.exists()) {
	 * dir.mkdirs(); }
	 * setRuta(UploadUtility.calculateDestinationPath(uploadedFile)); //File
	 * multipartFile = new
	 * File(UploadUtilityTim.calculateDestinationPath(uploadedFile)); File
	 * multipartFile = new File(ruta);
	 * 
	 * uploadedFile.transferTo(multipartFile); }
	 * 
	 * 
	 * public static void saveUploadedFileToDiskGeneric(UploadedFileTim
	 * uploadedFile,Object... pathSegmentos) throws Exception {
	 * 
	 * String extension =
	 * FilenameUtils.getExtension(uploadedFile.getFile().getOriginalFilename());
	 * 
	 * if(!verificarExtension(uploadedFile.getTipoDocumento(),extension)){ throw new
	 * Exception("forms.errors.upload.extension"); }
	 * 
	 * String urlDir =
	 * UploadUtility.calculateDestinationDirectoryGeneric(pathSegmentos);
	 * 
	 * File dir = new File(urlDir); if(!dir.exists()) { dir.mkdirs(); }
	 * 
	 * urlDir=appendFileNameToDirectoryPathGeneric(urlDir,uploadedFile.getIdObjeto()
	 * +"."+extension); File multipartFile = new File(urlDir);
	 * System.out.println("URL : "+urlDir);
	 * uploadedFile.getFile().transferTo(multipartFile); }
	 * 
	 * 
	 * 
	 * private static String calculateDestinationPath(UploadedFileTim uploadedFile)
	 * {
	 * 
	 * Date hoy = new Date(); SimpleDateFormat formatter = new
	 * SimpleDateFormat("yyyyMMddHHmmss"); String sFechaHora =
	 * formatter.format(hoy);
	 * 
	 * 
	 * String result =
	 * calculateDestinationDirectory(uploadedFile.getIdHogar(),uploadedFile.
	 * getProceso(),uploadedFile.getIdMiembro()); result +=getSeparator(); if
	 * (uploadedFile.getDni() == null) { result +=
	 * uploadedFile.getTipoDocumento()+"_"+"9999999"+"_"+sFechaHora+"."+
	 * FilenameUtils.getExtension(uploadedFile.getFile().getOriginalFilename()); }
	 * else { result +=
	 * uploadedFile.getTipoDocumento()+"_"+uploadedFile.getDni()+"_"+sFechaHora+"."+
	 * FilenameUtils.getExtension(uploadedFile.getFile().getOriginalFilename()); }
	 * 
	 * //Se agregÃ³ para obtener siempre el mismo nombre de archivo, debido a que el
	 * segundo podrÃ­a variar si se generan en momentos diferentes if
	 * (uploadedFile.getDni() == null) {
	 * uploadedFile.setName(uploadedFile.getTipoDocumento()+"_"+"9999999"+"_"+
	 * sFechaHora+"."+FilenameUtils.getExtension(uploadedFile.getFile().
	 * getOriginalFilename())); } else {
	 * uploadedFile.setName(uploadedFile.getTipoDocumento()+"_"+uploadedFile.getDni(
	 * )+"_"+sFechaHora+"."+FilenameUtils.getExtension(uploadedFile.getFile().
	 * getOriginalFilename())); }
	 * 
	 * return result; }
	 * 
	 */

	public static String calculateDestinationPath(Long idHogar, String proceso, Long idMiembro, String nombreArchivo) {
		String result = calculateDestinationDirectory(idHogar, proceso, idMiembro);
		result += getSeparator();
		result += nombreArchivo;
		return result;
	}

	public static String calculateDestinationPathFolder(Long idHogar, String proceso, String folder,
			String nombreArchivo) {
		String result = calculateDestinationDirectoryFolder(idHogar, proceso, folder);
		result += getSeparator();
		result += nombreArchivo;
		return result;
	}

	public static String calculateDestinationDirectoryGeneric(Object... segmentos) {
		String result = UploadUtility.obtenerRutaServidorArchivo();
		result += getSeparator();

		for (Object object : segmentos) {
			if (!Util.isEmpty(object)) {
				result += object.toString();
				result += getSeparator();
			}
		}
		return result;
	}

	private static String appendFileNameToDirectoryPathGeneric(String directoryPath, Object newFile) {

		if (!Util.isEmpty(newFile)) {
			directoryPath += newFile.toString();
		}

		return directoryPath;
	}

	private static String calculateDestinationDirectory(Long idHogar, String proceso, Long idMiembro) {
		String result = UploadUtility.obtenerRutaServidorArchivo();
		result += getSeparator();
		if (!Util.toStr(idHogar).equals("")) {
			result += idHogar;
			result += getSeparator();
		}
		if (!Util.toStr(proceso).equals("")) {
			result += proceso;
			result += getSeparator();
		}
		if (!Util.toStr(idMiembro).equals("")) {
			result += idMiembro;
			result += getSeparator();
		}
		return result;
	}

	private static String calculateDestinationDirectoryFolder(Long idHogar, String proceso, String folder) {
		String result = UploadUtility.obtenerRutaServidorArchivo();
		result += getSeparator();
		if (!Util.toStr(idHogar).equals("")) {
			result += idHogar;
			result += getSeparator();
		}
		if (!Util.toStr(proceso).equals("")) {
			result += proceso;
			result += getSeparator();
		}
		return result;
	}

	public static String getSeparator() {
		return System.getProperty("file.separator");
	}

	public static String obtenerRutaServidorArchivo() {
		String separador = System.getProperty("file.separator");
		if (separador.equals("/")) // Linux (/)
			return SERVIDOR_ARCHIVO_LINUX;
		else // Windows (\)
			return SERVIDOR_ARCHIVO_WINDOWS;
	}

	public static String obtenerSO() {
		String separador = System.getProperty("file.separator");
		if (separador.equals("/")) // Linux (/)
			return "LINUX";
		else // Windows (\)
			return "WINDOWS";
	}

}
