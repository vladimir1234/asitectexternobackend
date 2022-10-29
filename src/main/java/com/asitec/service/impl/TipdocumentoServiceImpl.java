package com.asitec.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asitec.model.Tipdocumento;
import com.asitec.dao.TipdocumentoDao;
import com.asitec.service.TipdocumentoService;
import com.asitec.util.ApiOutResponse;

@Service("TipdocumentoService")
public class TipdocumentoServiceImpl implements TipdocumentoService{
	
	 private static final Logger log = LoggerFactory.getLogger(TipdocumentoServiceImpl.class);
	
	private static List<Tipdocumento> Registros;
	private static Tipdocumento Registro;

	@Autowired 
	TipdocumentoDao Dao;
	
	public Tipdocumento ObtenerPorId(long  id) {
		Registro = Dao.ObtenerPorId(id);
		if(Registro.getIdeTipDocumento() == id){
			return Registro;
		}
		return null;
	}
	
	public Tipdocumento ObtenerPorNombre(String name) {
		Registro = Dao.ObtenerPorNombre(name);
		if(Registro.toString().equalsIgnoreCase(name)){
			return Registro;
		}
		return null;
	}
	
	public ApiOutResponse Insertar(Tipdocumento registro) {
		return Dao.Insertar(registro);
	}

	public ApiOutResponse Actualizar(Tipdocumento registro) {
		return Dao.Actualizar(registro);
	}

	public ApiOutResponse EliminarPorId(long id) {
		return Dao.EliminarPorId(id);
	}

	public ApiOutResponse Listar() {
		return Dao.Listar();
	}	
	
	public List<Tipdocumento> Buscar(Tipdocumento registro) {
		return Dao.Buscar(registro);
	}	
	
	public boolean siExiste(Tipdocumento registro) {
		return Dao.siExiste(registro);
	}

}



