package com.asitec.dao;


import java.util.List;

import com.asitec.model.AstUsuExterno;
import com.asitec.util.JdbcTemplateUtils;

/**
 * 
 * @author Renato Granda
 */
public interface AstUsuExternoDao {
	 
	 
	AstUsuExterno ObtenerPorId(long id); 
	
	AstUsuExterno ObtenerPorNombre(String name);
	
	boolean Insertar(AstUsuExterno registro);
	
	boolean Actualizar(AstUsuExterno registro);
	
	boolean EliminarPorId(long id);

	List<AstUsuExterno> Listar();

	List<AstUsuExterno> Buscar(AstUsuExterno registro);
	
	boolean siExiste(AstUsuExterno registro);	
	
}