package com.asitec.service;


import java.util.List;

import com.asitec.model.Tipdocumento;
import com.asitec.util.ApiOutResponse;

public interface TipdocumentoService {
	
	Tipdocumento ObtenerPorId(long id);
	
	Tipdocumento ObtenerPorNombre(String name);
	
	ApiOutResponse Insertar(Tipdocumento Registro);
	
	ApiOutResponse Actualizar(Tipdocumento Registro);
	
	ApiOutResponse EliminarPorId(long id);

	ApiOutResponse Listar();
	
	
	List<Tipdocumento> Buscar(Tipdocumento registro);

	boolean siExiste(Tipdocumento registro);
	
}



