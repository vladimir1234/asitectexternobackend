package com.asitec.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.asitec.api.request.ReniecRequest;
import com.asitec.api.response.ReniecResponse;
import com.asitec.api.response.UnidadEjecutoraResponse;
import com.asitec.dao.ReniecDAO;
import com.asitec.util.ApiOutResponse;
import com.asitec.util.Constantes;

@Repository
public class ReniecDAOImpl implements ReniecDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final Log log = LogFactory.getLog(getClass());
	
	@Override
	public ApiOutResponse buscarReniec(ReniecRequest reniecRequest) {
		log.info("[DAO buscarReniec] INICIO  ");
		ApiOutResponse outResponse = new ApiOutResponse();
		List <ReniecResponse>lista = new ArrayList <ReniecResponse>();
		int codigoRpta=0;
		String mensajeRpta="";
		
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			jdbcCall.withProcedureName("paDataReniecBuscar")
			.declareParameters(
					
					new SqlParameter("pNumeroDni", Types.VARCHAR),
					new SqlOutParameter("pResultado", 	Types.INTEGER),
					new SqlOutParameter("pMensaje", 	Types.VARCHAR)
			);
			
			MapSqlParameterSource parametros = new MapSqlParameterSource();
		
			parametros.addValue("pNumeroDni",reniecRequest.getNumeroDni());
						
			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			
			codigoRpta=Integer.parseInt(results.get("pResultado").toString());
			mensajeRpta=results.get("pMensaje").toString();
			 
			if (codigoRpta!=0) {//VERIFICACION DE ERROR EN LA BD
				log.error("[buscarReniec] Ocurrio un error en la operacion >>"+mensajeRpta);
				
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {

						ReniecResponse response =  new ReniecResponse();
						response.setDni(map.get("Dni")!=null ? map.get("Dni").toString() : "");						
						response.setApellidoPaterno(map.get("ApellidoPaterno")!=null ? map.get("ApellidoPaterno").toString() : "");
						response.setApellidoMaterno(map.get("ApellidoMaterno")!=null ? map.get("ApellidoMaterno").toString() : "");
						response.setNombres(map.get("Nombres")!=null ? map.get("Nombres").toString() : "");
						
						lista.add(response);
					
					}
					outResponse.setResponse(lista);
					outResponse.setCodResultado( codigoRpta);
					outResponse.setMsgResultado( mensajeRpta!=null ? mensajeRpta: "No se encontraron datos!" );
				}else {
					outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
					outResponse.setTotal(0);
					outResponse.setResponse("");
					outResponse.setMsgResultado("¡No existen registros!");
				}
			}else {
			outResponse.setCodResultado(codigoRpta);
			outResponse.setMsgResultado( mensajeRpta!=null ? mensajeRpta: " " );
			}
		} catch (Exception e) {
			log.error("DAO buscarReniec>>>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(codigoRpta);
			outResponse.setMsgResultado( mensajeRpta);
			
		}
		log.info("[DAO buscarReniec] FIN  ");
		return outResponse;
	}

	

	
}
