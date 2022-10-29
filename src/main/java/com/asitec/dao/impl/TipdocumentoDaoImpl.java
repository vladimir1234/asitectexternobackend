package com.asitec.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asitec.dao.TipdocumentoDao;
import com.asitec.model.Tipdocumento;
import com.asitec.model.Ubigeo;
import com.asitec.util.JdbcTemplateUtils;
import com.asitec.util.ApiOutResponse;
import com.asitec.util.Constantes;

/**
 * 
 * @author kELLY dextre
 */
@Component
@Repository("Tipdocumento")
public class TipdocumentoDaoImpl implements TipdocumentoDao {

	private static final Logger log = LoggerFactory.getLogger(TipdocumentoDaoImpl.class);	
	
	@Autowired
    private JdbcTemplateUtils jdbcUtils;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static final String RETURN_RESULT_SET_PREFIX = "#result-set-1"; 
	
	 @Override
	public Tipdocumento ObtenerPorId(long id) {

			 Map<String,Object> params=new HashMap<>();
	         params.put("id", id);
	         Map<String, Object> map = jdbcUtils.callStoreProcedure("paTipdocumentoObtenerPorId"  ,params);
	         List<Tipdocumento> lista = new ArrayList((map.values()));
	         return (Tipdocumento) lista.get(0);


	}
	@Override
	public Tipdocumento ObtenerPorNombre(String name) {

		 Map<String,Object> params=new HashMap<>();
        params.put("nombre", name);
        Map<String, Object> map = jdbcUtils.callStoreProcedure("paTipdocumentoObtenerPorNombre",params);
         List<Tipdocumento > lista = new ArrayList((map.values()));
         return (Tipdocumento ) lista.get(0); 

	}

	@Override
	public ApiOutResponse Insertar(Tipdocumento Registro){
		
		ApiOutResponse outResponse = new ApiOutResponse();
		try {            
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
            jdbcCall.withProcedureName("PaTipdocumentoInsertar")
			.declareParameters(
					new SqlParameter("Nombre", 	Types.VARCHAR),
					new SqlParameter("EstadoAuditoria", 				Types.VARCHAR),
					new SqlParameter("FechaCreacionAuditoria", 		Types.VARCHAR),
					new SqlParameter("UsuarioCreacionAuditoria", 			Types.VARCHAR),
					new SqlParameter("FechaModificacionAuditoria", 		Types.VARCHAR),
					new SqlParameter("UsuarioModificacionAuditoria", 			Types.VARCHAR),
					new SqlParameter("IPEquipoAuditoria", 			Types.VARCHAR),
					new SqlParameter("ProgramaAuditoria", 			Types.VARCHAR),
				
					new SqlOutParameter("Idetipdocumento", 			    Types.INTEGER),
					new SqlOutParameter("pResultado", 		Types.INTEGER),
					new SqlOutParameter("pMensaje", 		Types.VARCHAR)
					);
			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("Nombre", Registro.getNombre());
			parametros.addValue("EstadoAuditoria", Registro.getEstadoAuditoria());
			parametros.addValue("FechaCreacionAuditoria", Registro.getFechaCreacionAuditoria());
			parametros.addValue("UsuarioCreacionAuditoria", Registro.getUsuarioCreacionAuditoria());	
			parametros.addValue("FechaModificacionAuditoria", Registro.getFechaModificacionAuditoria());
			parametros.addValue("UsuarioModificacionAuditoria", Registro.getUsuarioModificacionAuditoria());	
			parametros.addValue("IPEquipoAuditoria", Registro.getIpeEquipoAuditoria());	
			parametros.addValue("ProgramaAuditoria", Registro.getProgramaAuditoria());
		
			
			Map<String, Object> results = jdbcCall.execute(parametros);
			
			outResponse.setResponse( results.get("Idetipdocumento")!=null ? Integer.parseInt(results.get("Idetipdocumento").toString()) : 1);		
			outResponse.setCodResultado( results.get("pResultado")!=null ? Integer.parseInt(results.get("pResultado").toString()) : 1);
			outResponse.setMsgResultado( results.get("pMensaje")!=null ? results.get("pMensaje").toString() : "-" );
		} catch (Exception e) {
			e.getMessage();
			outResponse.setCodResultado(-1);
			outResponse.setMsgResultado( e.getMessage());
		}
		return outResponse;
	}
	
	
	@Override
	public ApiOutResponse Actualizar(Tipdocumento Registro) {
		
		ApiOutResponse outResponse = new ApiOutResponse();
		try {            
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
            jdbcCall.withProcedureName("paTipdocumentoActualizar")
			.declareParameters(
					
					new SqlParameter("Idetipdocumento", 	Types.INTEGER),
					new SqlParameter("Nombre", 	Types.VARCHAR),
					new SqlParameter("EstadoAuditoria", 				Types.VARCHAR),
					new SqlParameter("FechaCreacionAuditoria", 		Types.VARCHAR),
					new SqlParameter("UsuarioCreacionAuditoria", 			Types.VARCHAR),
					new SqlParameter("FechaModificacionAuditoria", 		Types.VARCHAR),
					new SqlParameter("UsuarioModificacionAuditoria", 			Types.VARCHAR),
					new SqlParameter("IPEquipoAuditoria", 			Types.VARCHAR),
					new SqlParameter("ProgramaAuditoria", 			Types.VARCHAR),				

					new SqlOutParameter("pResultado", 		Types.INTEGER),
					new SqlOutParameter("pMensaje", 		Types.VARCHAR)
					);
			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("Idetipdocumento", Registro.getIdeTipDocumento());
			parametros.addValue("Nombre", Registro.getNombre());
			parametros.addValue("EstadoAuditoria", Registro.getEstadoAuditoria());
			parametros.addValue("FechaCreacionAuditoria", Registro.getFechaCreacionAuditoria());
			parametros.addValue("UsuarioCreacionAuditoria", Registro.getUsuarioCreacionAuditoria());	
			parametros.addValue("FechaModificacionAuditoria", Registro.getFechaModificacionAuditoria());
			parametros.addValue("UsuarioModificacionAuditoria", Registro.getUsuarioModificacionAuditoria());	
			parametros.addValue("IPEquipoAuditoria", Registro.getIpeEquipoAuditoria());	
			parametros.addValue("ProgramaAuditoria", Registro.getProgramaAuditoria());
		
			
			Map<String, Object> results = jdbcCall.execute(parametros);
			
			outResponse.setResponse( Registro.getIdeTipDocumento() );		
			outResponse.setCodResultado( results.get("pResultado")!=null ? Integer.parseInt(results.get("pResultado").toString()) : 1);
			outResponse.setMsgResultado( results.get("pMensaje")!=null ? results.get("pMensaje").toString() : "-" );
		} catch (Exception e) {
			e.getMessage();
			outResponse.setCodResultado(-1);
			outResponse.setMsgResultado( e.getMessage());
		}
		return outResponse;
	}
	
//	@Override
//	public boolean EliminarPorId(long id) {
//		
//		Map<String,Object> params=new HashMap<>();
//		try {
//	         params.put("id", id);
//            jdbcUtils.callStoreProcedure("paTipdocumentoEliminar",params);
//		} catch (Exception e) {
//			e.getMessage();
//			return false;
//		}
//		return true;
//	}

	@Override
	public ApiOutResponse EliminarPorId(long id) {
		
		ApiOutResponse outResponse = new ApiOutResponse();
		try {            
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
            jdbcCall.withProcedureName("paTipdocumentoEliminar")
			.declareParameters(					
					new SqlParameter("Idetipdocumento", 	Types.INTEGER),								

					new SqlOutParameter("pResultado", 		Types.INTEGER),
					new SqlOutParameter("pMensaje", 		Types.VARCHAR)
					);
			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("Idetipdocumento", id);
			
			Map<String, Object> results = jdbcCall.execute(parametros);
			
			outResponse.setResponse( id );		
			outResponse.setCodResultado( results.get("pResultado")!=null ? Integer.parseInt(results.get("pResultado").toString()) : 1);
			outResponse.setMsgResultado( results.get("pMensaje")!=null ? results.get("pMensaje").toString() : "-" );
		} catch (Exception e) {
			e.getMessage();
			outResponse.setCodResultado(-1);
			outResponse.setMsgResultado( e.getMessage());
		}
		return outResponse;
	}
	
//	@Override
//	@ResponseBody
//    public List<Tipdocumento> Listar() {
//		log.info("Carga de Listar DAO { } ");
//		 Map<String,Object> params=new HashMap<>();
//		 Map<String, Object> map=   jdbcUtils.callStoreProcedure("paTipdocumentoListar",params);
//         List<Tipdocumento> lista = new ArrayList((map.values()));
//         
//         return lista;
//        
//	}

	@Override
	public ApiOutResponse Listar() {
		log.info("[DAO listado Tipo documento] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		List<Tipdocumento> lista = new ArrayList<>();
		int codigoRpta=0;
		String mensajeRpta="";
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			jdbcCall.withProcedureName("paTipdocumentoListar")
			.declareParameters(
					new SqlOutParameter("pResultado", 		Types.INTEGER),
					new SqlOutParameter("pMensaje", 		Types.VARCHAR)
				);					
	
			
			Map<String, Object> results = jdbcCall.execute();
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			
			codigoRpta=Integer.parseInt(results.get("pResultado").toString());
			mensajeRpta=results.get("pMensaje").toString();
			
			if (results.isEmpty()||results.size()<1) {
				log.info("Sin registros, verificar el procedimiento listaTrabajadorAsistencia");
				return  new ApiOutResponse();
			}
			
			if (codigoRpta!=0) {//VERIFICACION DE ERROR EN LA BD
				log.error("[listado tipo documento] Ocurrio un error en la operacion del Procedimiento almacenado paTipdocumentoListar : "+mensajeRpta);
				outResponse.setCodResultado(codigoRpta);
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {
						Tipdocumento objeto = new Tipdocumento();
						objeto.setIdeTipDocumento(Long.parseLong(map.get("Idetipdocumento").toString()) );
						objeto.setNombre( map.get("Nombre")!=null ? map.get("Nombre").toString() : "");
						objeto.setEstadoAuditoria(map.get("EstadoAuditoria")!=null ? map.get("EstadoAuditoria").toString() : "" );
						objeto.setUsuarioCreacionAuditoria(map.get("UsuarioCreacionAuditoria")!=null ? map.get("UsuarioCreacionAuditoria").toString() : "" );
						objeto.setFechaCreacionAuditoria(map.get("FechaCreacionAuditoria")!=null ?map.get("FechaCreacionAuditoria").toString():"" );
						objeto.setUsuarioModificacionAuditoria(map.get("UsuarioModificacionAuditoria")!=null ? map.get("UsuarioModificacionAuditoria").toString() : "" );
						objeto.setFechaModificacionAuditoria(map.get("FechaModificacionAuditoria")!=null ?map.get("FechaModificacionAuditoria").toString():"" );
						objeto.setIpeEquipoAuditoria( map.get("IPEquipoAuditoria")!=null ? map.get("IPEquipoAuditoria").toString() : "");
						objeto.setProgramaAuditoria( map.get("ProgramaAuditoria")!=null ? map.get("ProgramaAuditoria").toString() : "");
						
						lista.add(objeto);
					}
					outResponse.setResponse(lista);
					outResponse.setTotal(lista.size());
					outResponse.setCodResultado( codigoRpta);
					outResponse.setMsgResultado(mensajeRpta);
				}else {
					outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
					outResponse.setTotal(0);
					outResponse.setResponse("");
					outResponse.setMsgResultado("¡No existen registros!");
				}
			}
			
		} catch (Exception e) {
			log.error("DAO listadoTrabajadorDiaActual>>>>ERROR:"+this.getClass().getName(), e);
			outResponse.setCodResultado( codigoRpta);
			outResponse.setMsgResultado(mensajeRpta);
		}
		log.info("[DAO listadoTrabajadorDiaActual] FIN  ");	
		return outResponse;
	}

	
	
	@Override
	public List<Tipdocumento> Buscar(Tipdocumento Registro) {

		 Map<String,Object> params=new HashMap<>(); 
			params.put("Idetipdocumento", Long.toString(Registro.getIdeTipDocumento()));
			params.put("Nombre", Registro.getNombre().toString());
//			params.put("Estadoactivo", Registro.getEstadoactivo());
//			params.put("Fechacreacion", Registro.getFechacreacion().toString());
//			params.put("Usuariocreacion", Registro.getUsuariocreacion().toString());
//			params.put("Fechamodificacion", Registro.getFechamodificacion().toString());
//			params.put("Usuariomodificacion", Registro.getUsuariomodificacion().toString());
	        
		 Map<String, Object> map=   jdbcUtils.callStoreProcedure("paTipdocumentoBuscar",params);
         List<Tipdocumento> lista = new ArrayList((map.values()));
         return lista;  	
        
	}
		
	

	
	 public boolean siExiste(Tipdocumento registro) {   
		 Map<String,Object> params=new HashMap<>();

         try {
            params.put("Idetipdocumento", registro.getIdeTipDocumento()); 
            Map<String, Object> map = jdbcUtils.callStoreProcedure("paTipdocumentoObtenerPorId"  ,params);
            Object lista = new ArrayList((map.values()));
            
          		
			int total=Integer.parseInt(map.get("Total").toString());
			
			

         return  ( total>0  )?true:false;
         } catch (Exception e) {
                e.getMessage();
                return false;
         }

   }
	 
}

