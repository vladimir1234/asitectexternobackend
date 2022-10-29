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

import com.asitec.api.request.InstitucionEducativaRequest;
import com.asitec.api.response.InstitucionEducativaResponse;
import com.asitec.dao.InstitucionEducativaDAO;
import com.asitec.util.ApiOutResponse;
import com.asitec.util.Constantes;

@Repository
public class InstitucionEducativaDAOImpl  implements InstitucionEducativaDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall jdbcCall;
	
	private final Log log = LogFactory.getLog(getClass());
	
	@Override
	public ApiOutResponse buscarInstitucionEducativa(InstitucionEducativaRequest institucionEducativaRequest) {
		log.info("[DAO buscarInstitucionEducativa] INICIO  ");
		ApiOutResponse outResponse = new ApiOutResponse();
		List <InstitucionEducativaResponse>lista = new ArrayList <InstitucionEducativaResponse>();
		int codigoRpta=0;
		String mensajeRpta="";
		
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			jdbcCall.withProcedureName("PaInstitucionEducativaBuscar")
			.declareParameters(
					
					new SqlParameter("idSolicitud", Types.VARCHAR),
					new SqlOutParameter("pResultado", 	Types.INTEGER),
					new SqlOutParameter("pMensaje", 	Types.VARCHAR)
			);
			
			MapSqlParameterSource parametros = new MapSqlParameterSource();
		
			parametros.addValue("idSolicitud",institucionEducativaRequest.getIdSolicitud());
						
			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			
			codigoRpta=Integer.parseInt(results.get("pResultado").toString());
			mensajeRpta=results.get("pMensaje").toString();
			 
			if (codigoRpta!=0) {//VERIFICACION DE ERROR EN LA BD
				log.error("[buscarInstitucionEducativa] Ocurrio un error en la operacion >>"+mensajeRpta);
				
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {

						InstitucionEducativaResponse response =  new InstitucionEducativaResponse();
						response.setIdInstitucionEducativa(map.get("idInstitucionEducativa")!=null ? map.get("idInstitucionEducativa").toString() : "");						
						response.setNombreInstitucionEducativa(map.get("nombreInstitucionEducativa")!=null ? map.get("nombreInstitucionEducativa").toString() : "");
						response.setCodLocal(map.get("codLocal")!=null ? map.get("codLocal").toString() : "");
						response.setCodModular(map.get("codModular")!=null ? map.get("codModular").toString() : "");
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
			log.error("DAO buscarInstitucionEducativa>>>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(codigoRpta);
			outResponse.setMsgResultado( "Error en la BD");
			
		}
		log.info("[DAO buscarInstitucionEducativa] FIN  ");
		return outResponse;
	}

	

	@Override
	public ApiOutResponse insertar(InstitucionEducativaRequest institucionEducativaRequest) {

		jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
		ApiOutResponse outResponse = new ApiOutResponse();
		int codigoRpta = 0;
		

		try {
			jdbcCall.withProcedureName("PaInstitucionEducativaInsertar")
			.declareParameters(
					new SqlParameter(Constantes.ID_OPCION, Types.VARCHAR), 
					
					new SqlParameter("idSolicitud", Types.BIGINT),
					new SqlParameter("codLocal", Types.VARCHAR),
					// RETORNO
					new SqlOutParameter(Constantes.COD_RESULTADO, Types.VARCHAR),
					new SqlOutParameter(Constantes.MSG_RESULTADO, Types.VARCHAR)
			);

			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("idSolicitud", institucionEducativaRequest.getIdSolicitud());
			parametros.addValue("codLocal", institucionEducativaRequest.getCodLocal());
			

			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);

			codigoRpta = Integer.parseInt(results.get(Constantes.COD_RESULTADO).toString());
			

			if (codigoRpta != 0) {
				this.errorDataBase(outResponse, results);

			}

			if (codigoRpta == 0) {
				outResponse.setTotal(0);
				outResponse.setCodResultado(results.get(Constantes.COD_RESULTADO) != null ? Integer.parseInt(results.get(Constantes.COD_RESULTADO).toString()): 500);
				outResponse.setMsgResultado(results.get(Constantes.MSG_RESULTADO) != null ? results.get(Constantes.MSG_RESULTADO).toString() : "-");
			}

		} catch (Exception e) {
			log.error(">> " + this.getClass().getName(), e);
			outResponse.setCodResultado(500);
			outResponse.setMsgResultado(Constantes.ERROR_EN_LA_BASE_DE_DATOS);
		}

		return outResponse;
	}
	

	
	
	public ApiOutResponse errorDataBase(ApiOutResponse outResponse,Map<String, Object> results ) {
		outResponse.setCodResultado(Integer.parseInt(results.get(Constantes.COD_RESULTADO).toString()));
		outResponse.setTotal(0);
		outResponse.setResponse(Constantes.ERROR_EN_LA_BD);
		outResponse.setMsgResultado(results.get(Constantes.MSG_RESULTADO).toString());
		return outResponse;
	}

	
}
