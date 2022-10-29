package com.asitec.dao.impl;

import java.sql.Types;
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

import com.asitec.api.request.CoordinadorRequest;
import com.asitec.dao.CoordinadorDAO;
import com.asitec.util.ApiOutResponse;
import com.asitec.util.Constantes;

@Repository
public class CoordinadorDAOImpl implements CoordinadorDAO {

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
	public ApiOutResponse registrar(CoordinadorRequest coordinador) {

		jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
		ApiOutResponse outResponse = new ApiOutResponse();
		int codigoRpta = 0;
		

		try {
			jdbcCall.withProcedureName("paIntegranteEquipoInsertar")
			.declareParameters(
					new SqlParameter("pIdSolicitud", Types.INTEGER), 
					new SqlParameter("pNumeroDni", Types.VARCHAR),					
					new SqlParameter("pNumeroTelefono", Types.VARCHAR),
					new SqlParameter("pCorreo", Types.VARCHAR),					
					new SqlParameter("pCip", Types.VARCHAR),
					new SqlParameter("pCap", Types.VARCHAR),
					new SqlParameter("pNumeroColegiatura", Types.VARCHAR),
					new SqlParameter("pIndicaCoordinador", Types.VARCHAR),
					
					new SqlParameter("pUsuarioCreacion", Types.VARCHAR),
					new SqlParameter("pIpEquipo", Types.VARCHAR),
					new SqlParameter("pProgramaAuditoria", Types.VARCHAR),
					// RETORNO
					new SqlOutParameter(Constantes.COD_RESULTADO, Types.VARCHAR),
					new SqlOutParameter(Constantes.MSG_RESULTADO, Types.VARCHAR)				
					
			);

			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("pIdSolicitud", coordinador.getIdSolicitud());
			parametros.addValue("pNumeroDni", coordinador.getNumeroDni());
			parametros.addValue("pNumeroTelefono", coordinador.getNumeroTelefono());
			parametros.addValue("pCorreo", coordinador.getCorreo());
			if(coordinador.getIdTipColegiatura()=="1") { //validar el codigo del tipo de colegiatura y actualizar aqui
				parametros.addValue("pCip", coordinador.getNumeroColegiatura());
				parametros.addValue("pCap", "-");
			}else if(coordinador.getIdTipColegiatura()=="2"){
				parametros.addValue("pCap", coordinador.getNumeroColegiatura());
				parametros.addValue("pCip", "-");
			}			
			
			parametros.addValue("pNumeroColegiatura", coordinador.getNumeroColegiatura());
			parametros.addValue("pIndicaCoordinador", "1");
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

	
}
