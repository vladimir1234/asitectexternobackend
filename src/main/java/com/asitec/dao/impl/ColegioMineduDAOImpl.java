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

import com.asitec.api.request.ColegioMineduRequest;
import com.asitec.api.response.ColegioMineduResponse;
import com.asitec.dao.ColegioMineduDAO;
import com.asitec.util.ApiOutResponse;
import com.asitec.util.Constantes;

@Repository
public class ColegioMineduDAOImpl implements ColegioMineduDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final Log log = LogFactory.getLog(getClass());
	
	@Override
	public ApiOutResponse buscarColegioMinedu(ColegioMineduRequest colegioMineduRequest) {
		log.info("[DAO buscarColegioMinedu] INICIO  ");
		ApiOutResponse outResponse = new ApiOutResponse();
		List <ColegioMineduResponse>lista = new ArrayList <ColegioMineduResponse>();
		int codigoRpta=0;
		String mensajeRpta="";
		
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			jdbcCall.withProcedureName("PaColegioMineduBuscar")
			.declareParameters(
					
					new SqlParameter("codLocal", Types.VARCHAR),
					new SqlParameter("codModular",   Types.VARCHAR),
					new SqlParameter("codRegion", Types.VARCHAR),
					
					new SqlOutParameter("pResultado", 	Types.INTEGER),
					new SqlOutParameter("pMensaje", 	Types.VARCHAR)
			);
			
			MapSqlParameterSource parametros = new MapSqlParameterSource();
		
			parametros.addValue("codLocal",colegioMineduRequest.getCodLocal());
			parametros.addValue("codModular",colegioMineduRequest.getCodModular());
			parametros.addValue("codRegion",colegioMineduRequest.getCodRegion());
						
			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			
			codigoRpta=Integer.parseInt(results.get("pResultado").toString());
			mensajeRpta=results.get("pMensaje").toString();
			 
			if (codigoRpta!=0) {//VERIFICACION DE ERROR EN LA BD
				log.error("[buscarColegioMinedu] Ocurrio un error en la operacion >>"+mensajeRpta);
				
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {

						ColegioMineduResponse response =  new ColegioMineduResponse();
						response.setCodLocal(map.get("codLocal")!=null ? map.get("codLocal").toString() : "");						
						response.setCodModular(map.get("codModular")!=null ? map.get("codModular").toString() : "");
						response.setCodRegion(map.get("codRegion")!=null ? map.get("codRegion").toString() : "");
						response.setCanEstudiantes(map.get("nivAcademico")!=null ? map.get("nivAcademico").toString() : "");
						response.setCanEstudiantes(map.get("nombreInstitucion")!=null ? map.get("nombreInstitucion").toString() : "");
						response.setCanEstudiantes(map.get("canEstudiantes")!=null ? map.get("canEstudiantes").toString() : "");
						response.setNombreRegion(map.get("nombreRegion")!=null ? map.get("nombreRegion").toString() : "");
						response.setNombreProvincia(map.get("nombreProvincia")!=null ? map.get("nombreProvincia").toString() : "");
						response.setNombreDistrito(map.get("nombreDistrito")!=null ? map.get("nombreDistrito").toString() : "");
						
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
			log.error("DAO buscarColegioMinedu>>>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(codigoRpta);
			outResponse.setMsgResultado( "Error en la BD");
			
		}
		log.info("[DAO buscarColegioMinedu] FIN  ");
		return outResponse;
	}

	

	
}
