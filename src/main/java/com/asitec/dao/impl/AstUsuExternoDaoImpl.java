package com.asitec.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asitec.dao.AstUsuExternoDao;
import com.asitec.model.AstUsuExterno;
import com.asitec.util.JdbcTemplateUtils;

/**
 * 
 * @author kELLY dextre
 */
@Component
@Repository("AstUsuExternoDao")
public class AstUsuExternoDaoImpl implements AstUsuExternoDao {

	private static final Logger log = LoggerFactory.getLogger(AstUsuExternoDaoImpl.class);	
	
	@Autowired
    private JdbcTemplateUtils jdbcUtils;

	 @Override
	public AstUsuExterno ObtenerPorId(long id) {

			 Map<String,Object> params=new HashMap<>();       
	         params.put("id",id);    
	         Map<String, Object> map = jdbcUtils.callStoreProcedure("paUsuExternoOntenerPorId",params);
	         List<AstUsuExterno> lista = new ArrayList((map.values()));
	         return (AstUsuExterno) lista.get(0);  	        
	        

	}
	@Override
	public AstUsuExterno ObtenerPorNombre(String name) {

		 Map<String,Object> params=new HashMap<>();       
         params.put("nombre",name);    
         Map<String, Object> map= jdbcUtils.callStoreProcedure("paUsuExternoOntenerPorNombre",params);
         List<AstUsuExterno> lista = new ArrayList((map.values()));
         return (AstUsuExterno) lista.get(0);  	

	}

	@Override
	public boolean Insertar(AstUsuExterno Registro){
		Map<String,Object> params=new HashMap<>();
		try {
			params.put("id",Registro.getIdeUsuario().toString());
	        jdbcUtils.callStoreProcedure("paUsuExternoInsertar",params);	        
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
		return true;
	}	
	@Override	
	public boolean Actualizar(AstUsuExterno Registro) {
		Map<String,Object> params=new HashMap<>();
		try {
			params.put("id",Registro.getIdeUsuario().toString());
	        jdbcUtils.callStoreProcedure("paUsuExternoActualizar",params);	        
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
		return true;	
	}
	
	@Override
	public boolean EliminarPorId(long id) {
		
		Map<String,Object> params=new HashMap<>();
		try {
			params.put("id",id);
	        jdbcUtils.callStoreProcedure("paUsuExternoEliminar",params);	        
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
		return true;	
	}

	@Override
	@ResponseBody 	
    public List<AstUsuExterno> Listar() {
		log.info("Carga de Listar DAO {}");
		 Map<String,Object> params=new HashMap<>(); 
		 Map<String, Object> map=   jdbcUtils.callStoreProcedure("LISTARPERIDOS",params);
         List<AstUsuExterno> lista = new ArrayList((map.values()));
         
         return lista;  	
        
	}

	
	@Override
	public List<AstUsuExterno> Buscar(AstUsuExterno Registro) {

		 Map<String,Object> params=new HashMap<>(); 
			params.put("id",Registro.getIdeUsuario().toString());
	        
         Map<String, Object> map = jdbcUtils.callStoreProcedure("paUsuExternoBuscar",params);
         List<AstUsuExterno> lista = new ArrayList((map.values()));
         return lista;  	
        
	}
		
	
	@Override	
	public boolean siExiste(AstUsuExterno registro) {
	
		Map<String,Object> params=new HashMap<>();
		try {
			params.put("id",registro.getIdeUsuario());
	        jdbcUtils.callStoreProcedure("existe_product",params);	        
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
		return true;
	}

}