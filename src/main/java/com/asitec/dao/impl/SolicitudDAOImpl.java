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

import com.asitec.api.request.SolicitudRequest;
import com.asitec.api.response.SolicitudResponse;
import com.asitec.api.response.UnidadEjecutoraResponse;
import com.asitec.dao.SolicitudDAO;
import com.asitec.util.ApiOutResponse;
import com.asitec.util.Constantes;

@Repository
public class SolicitudDAOImpl implements SolicitudDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final Log log = LogFactory.getLog(getClass());
	
	@Override
	public ApiOutResponse buscarSolicitud(SolicitudRequest solicitud) {
		log.info("[DAO buscarSolicitud] INICIO  ");
		ApiOutResponse outResponse = new ApiOutResponse();
		List <SolicitudResponse>lista = new ArrayList <SolicitudResponse>();
		int codigoRpta=0;
		String mensajeRpta="";
		
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			jdbcCall.//withSchemaName("dbo").
			withProcedureName("paSolicitudBuscar")
			.declareParameters(
					
					new SqlParameter("pIdPeriodo", Types.INTEGER),
					new SqlParameter("pSnip", 		Types.VARCHAR),
					new SqlParameter("pIdEstadoSolicitud", 		Types.INTEGER),
					new SqlOutParameter("pResultado", 	Types.INTEGER),
					new SqlOutParameter("pMensaje", 	Types.VARCHAR)
			);
			
			MapSqlParameterSource parametros = new MapSqlParameterSource();
		
			parametros.addValue("pIdPeriodo",solicitud.getIdPeriodo());
			parametros.addValue("pSnip",solicitud.getCodigo());
			parametros.addValue("pIdEstadoSolicitud",solicitud.getIdEstado());
			
			
			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			
			codigoRpta=Integer.parseInt(results.get("pResultado").toString());
			mensajeRpta=results.get("pMensaje").toString();
			 
			if (codigoRpta!=0) {//VERIFICACION DE ERROR EN LA BD
				log.error("[buscarSolicitud] Ocurrio un error en la operacion >>"+mensajeRpta);
				
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {

						SolicitudResponse response =  new SolicitudResponse();
						response.setPeriodo(map.get("nombrePeriodo")!=null ? map.get("nombrePeriodo").toString() : "");
						response.setSnip(map.get("Snip")!=null ? map.get("Snip").toString() : "");
						response.setCui(map.get("cui")!=null ? map.get("cui").toString() : "");
						response.setNombreProyecto(map.get("nombreProyecto")!=null ? map.get("nombreProyecto").toString() : "");
						response.setCantidadIiee(map.get("cantidadInstitucionesEducativas")!=null ? Integer.parseInt(map.get("cantidadInstitucionesEducativas").toString()) : 0);
						response.setNumDiasRestantes(map.get("diasRestantesEvaluacion")!=null ? Integer.parseInt(map.get("diasRestantesEvaluacion").toString()) : 0);
						response.setEstadoSolicitud(map.get("nombreEstadoSolicitud")!=null ? map.get("nombreEstadoSolicitud").toString() : "");
						response.setEstadoEstudioBasico(map.get("estadoEstudioBasico")!=null ? map.get("estadoEstudioBasico").toString() : "");
						response.setEstadoEspecialidad(map.get("estadoEspecialidad")!=null ? map.get("estadoEspecialidad").toString() : "");
						response.setIdNotificacion(map.get("idNotificacion")!=null ? map.get("idNotificacion").toString() : "");
						response.setIdSolicitudAt(map.get("idSolicitudAT")!=null ? map.get("idSolicitudAT").toString() : "");
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
			log.error("DAO buscarSolicitud>>>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(1);
			outResponse.setMsgResultado( this.getClass().getName());			
		}
		log.info("[DAO buscarSolicitud] FIN  ");
		return outResponse;
	}

	

	
}
