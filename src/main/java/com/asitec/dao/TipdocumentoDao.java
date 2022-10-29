package com.asitec.dao;


import java.util.List;

import com.asitec.model.Tipdocumento;
import com.asitec.util.ApiOutResponse;
import com.asitec.util.JdbcTemplateUtils;

/**
 * 
 * @author Renato Granda
 */
public interface TipdocumentoDao {
	 
	 
	Tipdocumento ObtenerPorId(long id); 
	
	Tipdocumento ObtenerPorNombre(String name);
	
	ApiOutResponse Insertar(Tipdocumento registro);
	
	ApiOutResponse Actualizar(Tipdocumento registro);
	
	ApiOutResponse EliminarPorId(long id);

	ApiOutResponse Listar();

	List<Tipdocumento> Buscar(Tipdocumento registro);
	
	boolean siExiste(Tipdocumento registro);	
	
}
