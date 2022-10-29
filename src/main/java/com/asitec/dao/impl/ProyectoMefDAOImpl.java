package com.asitec.dao.impl;

import java.math.BigDecimal;
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

import com.asitec.api.request.ProyectoMefRequest;
import com.asitec.api.response.ProyectoMefResponse;
import com.asitec.api.response.ReniecResponse;
import com.asitec.dao.ProyectoMefDAO;
import com.asitec.util.ApiOutResponse;
import com.asitec.util.Constantes;

@Repository
public class ProyectoMefDAOImpl implements ProyectoMefDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final Log log = LogFactory.getLog(getClass());
	
	@Override
	public ApiOutResponse buscarProyectoMef(ProyectoMefRequest proyectoMefRequest) {
		log.info("[DAO buscarProyectoMef] INICIO  ");
		ApiOutResponse outResponse = new ApiOutResponse();
		List <ProyectoMefResponse>lista = new ArrayList <ProyectoMefResponse>();
		int codigoRpta=0;
		String mensajeRpta="";
		
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			jdbcCall.withProcedureName("PaProyectoMefBuscar")
			.declareParameters(
					
					new SqlParameter("pSnip", Types.VARCHAR),
					new SqlParameter("pCui",   Types.VARCHAR),
					new SqlOutParameter("pResultado", 	Types.INTEGER),
					new SqlOutParameter("pMensaje", 	Types.VARCHAR)
			);
			
			MapSqlParameterSource parametros = new MapSqlParameterSource();
		
			parametros.addValue("pSnip",proyectoMefRequest.getCodSnip());
			parametros.addValue("pCui",proyectoMefRequest.getCui());
						
			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			
			codigoRpta=Integer.parseInt(results.get("pResultado").toString());
			mensajeRpta=results.get("pMensaje").toString();
			 
			if (codigoRpta!=0) {//VERIFICACION DE ERROR EN LA BD
				log.error("[buscarProyectoMef] Ocurrio un error en la operacion >>"+mensajeRpta);
				
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {

						ProyectoMefResponse response =  new ProyectoMefResponse();
						response.setSnip(map.get("codigoSnip")!=null ? map.get("codigoSnip").toString() : "");						
						response.setCui(map.get("cui")!=null ? map.get("cui").toString() : "");
						
						response.setIndicaBancoMef(map.get("indicaBancoMef")!=null ? map.get("indicaBancoMef").toString() : "");
						response.setNombreProyecto(map.get("nombreProyectoMef")!=null ? map.get("nombreProyectoMef").toString() : "");
						response.setMontoViabilidad(map.get("montoViabilidad")!=null ? (BigDecimal) map.get("montoViabilidad") : BigDecimal.ZERO);
						response.setMontoActualizado(map.get("montoActualizado")!=null ? (BigDecimal) map.get("montoActualizado") : BigDecimal.ZERO);
						response.setFechaViabilidad(map.get("fechaViabilidad")!=null ? map.get("fechaViabilidad").toString() : "");
						response.setEstadoProyecto(map.get("EstadoProyecto")!=null ? map.get("EstadoProyecto").toString() : "");
						response.setSituacionProyecto(map.get("SituacionProyecto")!=null ? map.get("SituacionProyecto").toString() : "");
						
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
			log.error("DAO buscarProyectoMef>>>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(codigoRpta);
			outResponse.setMsgResultado( this.getClass().getName());
			
		}
		log.info("[DAO buscarProyectoMef] FIN  ");
		return outResponse;
	}

	

	
}
