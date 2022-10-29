package com.asitec.service;


import java.util.List;

import com.asitec.model.AstUsuExterno;

public interface AstUsuExternoService {
	
	AstUsuExterno ObtenerPorId(long id);
	
	AstUsuExterno ObtenerPorNombre(String name);
	
	boolean Insertar(AstUsuExterno Registro);
	
	boolean Actualizar(AstUsuExterno Registro);
	
	boolean EliminarPorId(long id);

	List<AstUsuExterno> Listar();
	
	List<AstUsuExterno> Buscar(AstUsuExterno registro);

	boolean siExiste(AstUsuExterno registro);
	
}


