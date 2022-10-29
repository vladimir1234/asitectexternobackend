package com.asitec.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asitec.model.AstUsuExterno;
import com.asitec.dao.AstUsuExternoDao;
import com.asitec.service.AstUsuExternoService;

@Service("AstUsuExternoService")
public class AstUsuExternoServiceImpl implements AstUsuExternoService{
	
	 private static final Logger log = LoggerFactory.getLogger(AstUsuExternoServiceImpl.class);
	
	private static List<AstUsuExterno> Registros;
	private static AstUsuExterno Registro;

	@Autowired 
	AstUsuExternoDao Dao;
	
	public AstUsuExterno ObtenerPorId(long  id) {
		Registro = Dao.ObtenerPorId(id);
		if(Registro.getIdeUsuExterno() == id){
			return Registro;
		}
		return null;
	}
	
	public AstUsuExterno ObtenerPorNombre(String name) {
		Registro = Dao.ObtenerPorNombre(name);
		if(Registro.toString().equalsIgnoreCase(name)){
			return Registro;
		}
		return null;
	}
	
	public boolean Insertar(AstUsuExterno registro) {
		return Dao.Insertar(registro);
	}

	public boolean Actualizar(AstUsuExterno registro) {
		return Dao.Actualizar(registro);
	}

	public boolean EliminarPorId(long id) {
		return Dao.EliminarPorId(id);
	}

	public List<AstUsuExterno> Listar() {
		return Dao.Listar();
	}	
	
	public List<AstUsuExterno> Buscar(AstUsuExterno registro) {
		return Dao.Buscar(registro);
	}	
	
	public boolean siExiste(AstUsuExterno registro) {
		return Dao.siExiste(registro);
	}
	




}
