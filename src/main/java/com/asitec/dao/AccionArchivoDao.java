package com.asitec.dao;

import com.asitec.model.Archivo;
import com.asitec.model.DatoGeneral;
import com.asitec.util.ApiOutResponse;

public interface AccionArchivoDao {

	ApiOutResponse accionArchivo(DatoGeneral dato, Archivo archivo, String opcion);
//	ApiOutResponse listarTipoArchivo(String unidad);
	
}
