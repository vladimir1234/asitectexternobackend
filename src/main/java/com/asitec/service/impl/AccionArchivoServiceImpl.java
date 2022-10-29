package com.asitec.service.impl;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.asitec.api.response.ArchivoResponse;
import com.asitec.dao.AccionArchivoDao;
import com.asitec.model.Archivo;
import com.asitec.model.DatoGeneral;
import com.asitec.service.AccionArchivoService;
import com.asitec.util.ApiOutResponse;
import com.asitec.util.Constantes;
import com.asitec.util.UploadUtility;
import com.asitec.util.Util;
 

@Service
@Transactional(readOnly = true)
public class AccionArchivoServiceImpl implements AccionArchivoService {

	@Autowired
	private AccionArchivoDao accionArchivo;

	private  final Log log = LogFactory.getLog(getClass());

	@Override
	public String copiar(MultipartFile file, String nomArchivo, String rutaRaiz) {

		String ruta_guardar =rutaRaiz+System.getProperty("file.separator") + Util.getObtenerRutaCarpetas() +nomArchivo ;
		
		File rutaFile = new File(rutaRaiz);
		try {

			if(rutaFile.exists()) {
			    File directorio = new File( rutaRaiz+System.getProperty("file.separator") + Util.getObtenerRutaCarpetas() ); 
				directorio.mkdirs();  //CREAR DIRECTORIO
			}
			
			if(! UploadUtility.isEmptyFile(file) ) {//Verificar que el archivo no sea vacio 
				//String ruta = UploadUtility.obtenerRutaServidorArchivo() + System.getProperty("file.separator") + "UPS"+ System.getProperty("file.separator");
				String extension2 = FilenameUtils.getExtension(file.getOriginalFilename());
			
				//ruta_guardar = ruta + nomArchivo;
				File localFile = new File(ruta_guardar);
				FileOutputStream os = null;
				try {
					os = new FileOutputStream(localFile);
					os.write(file.getBytes());
				} catch (IOException e) {
					log.error("Error al guardar archivo en disco>>" + e);
					return "ERROR";
				} finally {
					if (os != null)
						os.close();
				}

			}else {
				return "ERROR";
			}
		} catch (IOException e1) {
			log.error("BUS copiar>>>>" + this.getClass().getName(), e1);
			return "ERROR";
		}

		return "OK";
	}

	
	@Override
	public ApiOutResponse registrarArchivo (DatoGeneral dato, Archivo archivo){
		ApiOutResponse rpta = new ApiOutResponse();
		dato.setIp(Util.getIp());
		try { 
			if (Util.isNull(archivo.getTipoArchivo()) ) {
				 rpta.setMsgResultado("necesita seleccionar el tipo documento");
				 return rpta;
			}
				
						
			rpta = accionArchivo.accionArchivo(dato, archivo,  Constantes.REGISTRAR);
		} catch (DataAccessException e) {
			rpta.setCodResultado(500);
			rpta.setMsgResultado("Error al realizar la consulta en la base de datos!");
			rpta.setTotal(0);
			rpta.setResponse(null);
		} 
		return rpta;
	}
	
	/*
	@Override
	public ApiOutResponse listarArchivo(Long idUsuario, Long idMovimientoProyecto) {
		ApiOutResponse rptaFin = new ApiOutResponse();
		DatoGeneral dato= new DatoGeneral();
		Archivo archivo= new Archivo();
//		dato.setIdProyecto(idMovimientoProyecto);
		dato.setIdMovimientoProyecto(idMovimientoProyecto);
		dato.setIp(Util.getIp());
		try {
			rptaFin = accionArchivo.accionArchivo(dato, archivo, Constantes.LISTAR);
		} catch (DataAccessException e) {
			rptaFin.setCodResultado(500);
			rptaFin.setMsgResultado("Error al realizar la consulta en la base de datos!");
			rptaFin.setTotal(0);
		}
		if (rptaFin.getResponse() == null) {
			rptaFin.setCodResultado(404);
			rptaFin.setMsgResultado("No existen registros.");
			rptaFin.setTotal(0);
		}
		return rptaFin;
	}

	
	@Override
	public ApiOutResponse eliminarArchivo(Long idUsuario, Long  idCodigoArchivo){
		ApiOutResponse rptaFin = new ApiOutResponse();
		DatoGeneral dato= new DatoGeneral();
		Archivo archivo= new Archivo();
		archivo.setIdArchivo(idCodigoArchivo);
		dato.setIp(Util.getIp());
		
		List <ArchivoResponse>lista = new ArrayList <ArchivoResponse>();
		String ruta="";
		try { 
			rptaFin = accionArchivo.accionArchivo(dato, archivo, Constantes.OBTENER_RUTA);// 1. COPIAR EL ARCHIVO AL REPOSITORIO
			lista= (List<ArchivoResponse>) rptaFin.getResponse();
			if(rptaFin.getCodResultado()==1 && lista.size()<2) {
				for (ArchivoResponse archi : lista) {
					 ruta=archi.getNombreArchivo();
				}
			}
			
			
			File fichero=new File(ruta);
			 if (fichero.exists()) {
				 if (fichero.delete()) {//elimina archivo
					     log.info("se elimino el archivo");
					  }
			 }
				
					
			 
			 rptaFin = accionArchivo.accionArchivo(dato, archivo, Constantes.ELIMINAR);//ELIMINAR DE BD
			
		} catch (DataAccessException e) {
			rptaFin.setCodResultado(500);
			rptaFin.setMsgResultado(e.getMessage());
			rptaFin.setTotal(0);
		}
		
		return rptaFin;
	}

	@Override
	public ApiOutResponse listarTipoArchivo() {
		ApiOutResponse rpta = new ApiOutResponse();
		try {
			rpta = accionArchivo.listarTipoArchivo(Constantes.CID_CODIGO_UNIDAD);
		} catch (DataAccessException e) {
			rpta.setCodResultado(500);
			rpta.setMsgResultado("Error al realizar la consulta en la base de datos!");
			rpta.setTotal(0);
			return rpta;
		}
		
		return rpta;
	}

	
	@Override
	public String obtenerRutaArchivo(Long idCodigoArchivo) {
		ApiOutResponse outResponse = new ApiOutResponse();
		String rutaFile ="";
		DatoGeneral dato= new DatoGeneral();
		Archivo archivo=new Archivo();
		List <ArchivoResponse>lista = new ArrayList <ArchivoResponse>();
		try {
			archivo.setIdArchivo(idCodigoArchivo);
			outResponse=accionArchivo.accionArchivo(dato, archivo, Constantes.OBTENER_RUTA);
			
			lista= (List<ArchivoResponse>) outResponse.getResponse();
			if(outResponse.getCodResultado()==1 && lista.size()<2) {
				for (ArchivoResponse archi : lista) {
					rutaFile=archi.getNombreArchivo();
				}
			}
			
		} catch (Exception e1) {
			log.error("BUS copiar>>>>" + this.getClass().getName(), e1);
			return "ERROR";
		}

		return rutaFile;
	}

	@Override
	public ApiOutResponse listarArchivoUpp(Long idUsuario) {
		ApiOutResponse rptaFin = new ApiOutResponse();
		DatoGeneral dato= new DatoGeneral();
		Archivo archivo= new Archivo();
		dato.setIp(Util.getIp());
		try {
			rptaFin = accionArchivo.accionArchivo(dato, archivo, Constantes.LISTAR_ARCHIVO_UPP);
		} catch (DataAccessException e) {
			rptaFin.setCodResultado(500);
			rptaFin.setMsgResultado("Error al realizar la consulta en la base de datos!");
			rptaFin.setTotal(0);
		}
		if (rptaFin.getResponse() == null) {
			rptaFin.setCodResultado(404);
			rptaFin.setMsgResultado("No existen registros.");
			rptaFin.setTotal(0);
		}
		return rptaFin;
	}

	@Override
	public ApiOutResponse aceptarArchivo(Long idUsuario, Long idCodigoArchivo) {
		
		ApiOutResponse rptaFin = new ApiOutResponse();
		DatoGeneral dato= new DatoGeneral();
		Archivo archivo= new Archivo();
		archivo.setIdArchivo(idCodigoArchivo);
		dato.setIp(Util.getIp());
		
		try {
			 rptaFin = accionArchivo.accionArchivo(dato, archivo, Constantes.ACEPTAR);//ACEPTAR DE BD
		} catch (DataAccessException e) {
			rptaFin.setCodResultado(500);
			rptaFin.setMsgResultado(e.getMessage());
			rptaFin.setTotal(0);
		}
		
		return rptaFin;
	}
	*/
	
}
