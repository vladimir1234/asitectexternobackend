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

import com.asitec.api.request.PeriodoRequest;
import com.asitec.api.response.InstitucionEducativaResponse;
import com.asitec.api.response.PeriodoResponse;
import com.asitec.dao.PeriodoDAO;
import com.asitec.util.ApiOutResponse;
import com.asitec.util.Constantes;

@Repository
public class PeriodoDAOImpl implements PeriodoDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall jdbcCall;
	private final Log log = LogFactory.getLog(getClass());
	
	
	public ApiOutResponse errorDataBase(ApiOutResponse outResponse,Map<String, Object> results ) {
		outResponse.setCodResultado(Integer.parseInt(results.get(Constantes.COD_RESULTADO).toString()));
		outResponse.setTotal(0);
		outResponse.setResponse(Constantes.ERROR_EN_LA_BD);
		outResponse.setMsgResultado(results.get(Constantes.MSG_RESULTADO).toString());
		return outResponse;
	}
	
	@Override
	public ApiOutResponse buscarPeriodo(PeriodoRequest reniecRequest) {
		return null;
	}

	@Override
	public ApiOutResponse registrarPeriodo(PeriodoRequest periodo) {

		jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
		ApiOutResponse outResponse = new ApiOutResponse();
		int codigoRpta = 0;
		

		try {
			jdbcCall.withProcedureName("paPeriodoInsertar")
			.declareParameters(
					new SqlParameter("pFechaInicioAdmision", Types.VARCHAR), 
					new SqlParameter("pFechaFinAdmision", Types.VARCHAR),					
					new SqlParameter("pAnio", Types.VARCHAR),
					new SqlParameter("pNumero", Types.VARCHAR),
					
					new SqlParameter("pUsuarioCreacion", Types.VARCHAR),
					new SqlParameter("pIpEquipo", Types.VARCHAR),
					new SqlParameter("pProgramaAuditoria", Types.VARCHAR),
					// RETORNO
					new SqlOutParameter(Constantes.COD_RESULTADO, Types.VARCHAR),
					new SqlOutParameter(Constantes.MSG_RESULTADO, Types.VARCHAR)				
					
			);

			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("pFechaInicioAdmision", periodo.getFechaInicio());
			parametros.addValue("pFechaFinAdmision", periodo.getFechaFin());
			parametros.addValue("pAnio", periodo.getAnio());
			parametros.addValue("pNumero", periodo.getNumero());
			parametros.addValue("pUsuarioCreacion", "user01");
			parametros.addValue("pIpEquipo", "1.2.4.5");
			parametros.addValue("pProgramaAuditoria", "busqueda periodo");
			

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
			outResponse.setCodResultado(codigoRpta);
			outResponse.setMsgResultado(Constantes.ERROR_EN_LA_BASE_DE_DATOS);
		}

		return outResponse;
	}
	
	@Override
	public ApiOutResponse listarPeriodo() {
		log.info("[DAO listarPeriodo] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		List<PeriodoResponse> listado = new ArrayList<>();
		
		int codigoRpta=0;
		String mensajeRpta="";
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
	
			jdbcCall.withProcedureName("paListarPeriodos")
			.declareParameters(
					new SqlOutParameter("pResultado", 	Types.INTEGER),
					new SqlOutParameter("pMensaje", 	Types.VARCHAR)
				);
			
			Map<String, Object> results = jdbcCall.execute();
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			
			codigoRpta=Integer.parseInt(results.get("pResultado").toString());
			mensajeRpta=results.get("pMensaje").toString();
			
			if (results.isEmpty()||results.size()<1) {
				log.info("¡No existen registros!");
				outResponse.setCodResultado(2);
				outResponse.setMsgResultado("No existen registros");
				return  outResponse;
			}
			
			if (codigoRpta!=0) {//VERIFICACION DE ERROR EN LA BD
				log.error("[listarPeriodo] Ocurrio un error en la operacion del Procedimiento almacenado listarPeriodo : "+mensajeRpta);
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {
						PeriodoResponse  objeto = new PeriodoResponse();
						
						objeto.setIdPeriodo(map.get("idPeriodo")!=null ? Long.parseLong(map.get("idPeriodo").toString()):0);
						objeto.setOrden(map.get("orden")!=null ? Integer.parseInt(map.get("orden").toString()):0);
						objeto.setFechaInicioAdmision(map.get("FechaInicioAdmision")!=null ? map.get("FechaInicioAdmision").toString() : "");
						objeto.setFechaFinAdmision(map.get("FechaFinAdmision")!=null ? map.get("FechaFinAdmision").toString() : "");
						objeto.setPeriodo(map.get("periodo")!=null ? map.get("periodo").toString() : "");
						
						listado.add(objeto);
					}
					outResponse.setResponse(listado);
					outResponse.setCodResultado(codigoRpta); 
					outResponse.setMsgResultado(mensajeRpta);
				}else {
					outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
					outResponse.setTotal(0);
					outResponse.setResponse("");
					outResponse.setMsgResultado("¡No existen registros!");
				}
			}
			
		} catch (Exception e) {
			log.error("DAO listarPeriodo>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(codigoRpta);
			outResponse.setMsgResultado( "Error en la Base de datos!.");
		}
			
		log.info("[DAO listarPeriodo] FIN  ");
		return outResponse;
	}

	

	
}
