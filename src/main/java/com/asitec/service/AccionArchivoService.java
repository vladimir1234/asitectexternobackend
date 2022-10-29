package com.asitec.service;

import org.springframework.web.multipart.MultipartFile;

import com.asitec.model.Archivo;
import com.asitec.model.DatoGeneral;
import com.asitec.util.ApiOutResponse;

public interface AccionArchivoService {

	String copiar(MultipartFile archivo, String nomArchivo, String rutaRaiz);

	
	ApiOutResponse registrarArchivo(DatoGeneral dato, Archivo archivo);
	/*
	ApiOutResponse listarArchivo(Long idUsuario, Long idMovimientoProyecto);

	ApiOutResponse eliminarArchivo(Long idUsuario, Long idCodigoArchivo);

	ApiOutResponse aceptarArchivo(Long idUsuario, Long idCodigoArchivo);

	ApiOutResponse listarTipoArchivo();

	String obtenerRutaArchivo(Long idCodigoArchivo);

	ApiOutResponse listarArchivoUpp(Long idUsuario);
	*/
}