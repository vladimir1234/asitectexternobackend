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

import com.asitec.api.request.UnidadEjecutoraRequest;
import com.asitec.api.response.UnidadEjecutoraResponse;
import com.asitec.dao.UnidadEjecutoraDAO;
import com.asitec.util.ApiOutResponse;
import com.asitec.util.Constantes;

@Repository
public class UnidadEjecutoraDAOImpl implements UnidadEjecutoraDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final Log log = LogFactory.getLog(getClass());
	
	@Override
	public ApiOutResponse buscarUnidadEjecutora(UnidadEjecutoraRequest unidadEjecutoraRequest) {
		log.info("[DAO buscarUnidadEjecutora] INICIO  ");
		ApiOutResponse outResponse = new ApiOutResponse();
		List <UnidadEjecutoraResponse>lista = new ArrayList <UnidadEjecutoraResponse>();
		int codigoRpta=0;
		String mensajeRpta="";
		
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			jdbcCall.withProcedureName("PaUnidadEjecutoraBuscar")
			.declareParameters(
					
					new SqlParameter("nombreUnidadEjecutora", Types.VARCHAR),
					new SqlParameter("regionUnidadEjecutora", 		Types.VARCHAR),
					new SqlOutParameter("pResultado", 	Types.INTEGER),
					new SqlOutParameter("pMensaje", 	Types.VARCHAR)
			);
			
			MapSqlParameterSource parametros = new MapSqlParameterSource();
		
			parametros.addValue("nombreUnidadEjecutora",unidadEjecutoraRequest.getNombreUnidadEjecutora());
			parametros.addValue("regionUnidadEjecutora",unidadEjecutoraRequest.getRegionUnidadEjecutora());
			
			
			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			
			codigoRpta=Integer.parseInt(results.get("pResultado").toString());
			mensajeRpta=results.get("pMensaje").toString();
			 
			if (codigoRpta!=0) {//VERIFICACION DE ERROR EN LA BD
				log.error("[buscarUnidadEjecutora] Ocurrio un error en la operacion >>"+mensajeRpta);
				
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {

						UnidadEjecutoraResponse response =  new UnidadEjecutoraResponse();
						response.setCodUbigeo(map.get("codUbigeo")!=null ? map.get("codUbigeo").toString() : "");
						response.setIdUnidadEjecutora(map.get("idUnidadEjecutora")!=null ? Long.parseLong(map.get("idUnidadEjecutora").toString()) : 0);
						response.setNombreDepartamento(map.get("nombreDepartamento")!=null ? map.get("nombreDepartamento").toString() : "");
						response.setNombreProvincia(map.get("nombreProvincia")!=null ? map.get("nombreProvincia").toString() : "");
						response.setNombreRegion(map.get("nombreRegion")!=null ? map.get("nombreRegion").toString() : "");
						response.setNombreUnidadEjecutora(map.get("nombreUnidadEjecutora")!=null ? map.get("nombreUnidadEjecutora").toString() : "");
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
			log.error("DAO buscarUnidadEjecutora>>>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(codigoRpta);
			outResponse.setMsgResultado( mensajeRpta);
			
		}
		log.info("[DAO buscarUnidadEjecutora] FIN  ");
		return outResponse;
	}

	

	
}
