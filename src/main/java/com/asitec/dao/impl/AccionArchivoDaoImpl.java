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

import com.asitec.api.response.ArchivoResponse;
import com.asitec.dao.AccionArchivoDao;
import com.asitec.model.Archivo;
import com.asitec.model.DatoGeneral;
import com.asitec.model.Item;
import com.asitec.util.ApiOutResponse;
import com.asitec.util.Constantes;
import com.asitec.util.Util;

@Repository
//public class ProyectoDaoImpl extends JdbcDaoSupport implements ProyectoDao{
public class AccionArchivoDaoImpl implements AccionArchivoDao {

	private final Log log = LogFactory.getLog(getClass());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall jdbcCall;
	
	@Override
	public ApiOutResponse accionArchivo(DatoGeneral dato, Archivo archivo, String opcion) {
		log.info("[DAO accionArchivo] INICIO  ");
		ApiOutResponse outResponse = new ApiOutResponse();
		List<ArchivoResponse> lista = new ArrayList<ArchivoResponse>();
		int codigoRpta = 0;
		String mensajeRpta = "";
		try {
			jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			jdbcCall.withSchemaName("UPS").withProcedureName("accionesArchivo").declareParameters(

					new SqlParameter("POPCION", Types.VARCHAR), new SqlParameter("PCID_NOMBRE", Types.VARCHAR),
					new SqlParameter("PCID_EXTENSION", Types.VARCHAR), new SqlParameter("PCID_RUTA", Types.VARCHAR),
					new SqlParameter("PFID_MOVIMIENTO_PROYECTO", Types.INTEGER),
					new SqlParameter("PFID_TIPO_ARCHIVO", Types.VARCHAR),
					new SqlParameter("PFID_ID_USUARIO_REG", Types.INTEGER),
					new SqlParameter("PTXT_IPMAQ_REG", Types.VARCHAR),
					new SqlParameter("PCID_DESCRIPCION", Types.VARCHAR),
					new SqlParameter("PFID_CODIGO_ARCHIVO", Types.INTEGER),
					new SqlOutParameter("COD_RESULTADO", Types.INTEGER),
					new SqlOutParameter("MSG_RESULTADO", Types.VARCHAR));

			MapSqlParameterSource parametros = new MapSqlParameterSource();

			parametros.addValue("POPCION", opcion);
			parametros.addValue("PCID_NOMBRE", Util.getParam(archivo.getNombre()));
			parametros.addValue("PCID_EXTENSION", Util.getParam(archivo.getExtension()));
			parametros.addValue("PCID_RUTA", Util.getParam(archivo.getRuta()));
			parametros.addValue("PFID_MOVIMIENTO_PROYECTO", dato.getIdMovimientoProyecto()); // dato.getIdProyecto()
			parametros.addValue("PFID_TIPO_ARCHIVO",
					(archivo.getTipoArchivo() != null) ? archivo.getTipoArchivo().toString() : "");
			parametros.addValue("PFID_ID_USUARIO_REG", dato.getIdUsuario());
			parametros.addValue("PTXT_IPMAQ_REG", Util.getParam(dato.getIp()));
			parametros.addValue("PCID_DESCRIPCION", Util.getParam(archivo.getDescripcion()));
			parametros.addValue("PFID_CODIGO_ARCHIVO", archivo.getIdArchivo());

			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);

			codigoRpta = Integer.parseInt(results.get("COD_RESULTADO").toString());
			mensajeRpta = results.get("MSG_RESULTADO").toString();

			if (codigoRpta != 1) {// VERIFICACION DE ERROR EN LA BD
				log.error("[accionArchivo] Ocurrio un error en la operacion " + opcion + " >>" + mensajeRpta);

				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}

			if (rs != null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {

						ArchivoResponse archivoResponse = new ArchivoResponse();

						archivoResponse.setIdCodigoArchivo(
								map.get("ID_CODIGO") != null ? Long.parseLong(map.get("ID_CODIGO").toString()) : 0L);
						archivoResponse.setNombreArchivo(
								map.get("CID_NOMBRE") != null ? map.get("CID_NOMBRE").toString().trim() : "");
						archivoResponse.setTipoArchivo(
								map.get("TIPO_ARCHIVO") != null ? map.get("TIPO_ARCHIVO").toString().trim() : "");
						archivoResponse.setFechaRegistro(
								map.get("FEC_REG") != null ? map.get("FEC_REG").toString().trim() : "");
						archivoResponse.setDescripcion(
								map.get("CID_DESCRIPCION") != null ? map.get("CID_DESCRIPCION").toString() : "");
						archivoResponse.setNombreEstado(
								map.get("NOMBRE_ESTADO") != null ? map.get("NOMBRE_ESTADO").toString() : "");
						archivoResponse.setFidProyecto(
								map.get("FID_PROYECTO") != null ? Long.parseLong(map.get("FID_PROYECTO").toString())
										: 0);
						archivoResponse.setCidNombreEstado(
								map.get("CID_NOMBRE_ESTADO") != null ? map.get("CID_NOMBRE_ESTADO").toString() : "");
						archivoResponse.setEstadoCarga(
								map.get("FLG_ESTADO_CARGA") != null ? map.get("FLG_ESTADO_CARGA").toString() : "");
						archivoResponse.setCidTipoArchivo(
								map.get("CID_TIPO_ARCHIVO") != null ? map.get("CID_TIPO_ARCHIVO").toString() : "");

						lista.add(archivoResponse);

					}
					outResponse.setResponse(lista);
					outResponse.setCodResultado(codigoRpta);
					outResponse.setMsgResultado(mensajeRpta != null ? mensajeRpta : "No se ecnontraron datos!");
				} else {
					outResponse.setCodResultado(2);// Codigo para indicar que no existen registros en la BD
					outResponse.setTotal(0);
					outResponse.setResponse("");
					outResponse.setMsgResultado("¡No existen registros!");
				}
			} else {
				outResponse.setCodResultado(codigoRpta);
				outResponse.setMsgResultado(mensajeRpta != null ? mensajeRpta : " ");
			}
		} catch (Exception e) {
			log.error("DAO accionArchivo>>>>" + opcion + " >>" + this.getClass().getName(), e);
			outResponse.setCodResultado(codigoRpta);
			outResponse.setMsgResultado(mensajeRpta);

		}
		log.info("[DAO accionArchivo] FIN  ");
		return outResponse;
	}

	/*
	public ApiOutResponse listarTipoArchivo(String unidad) {
		log.info("[DAO listarTipoArchivo] INICIO  ");
		ApiOutResponse outResponse = new ApiOutResponse();
		List<Item> lista = new ArrayList<>();
		int codigoRpta = 0;
		String mensajeRpta = "";
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			jdbcCall.withSchemaName("UPS").withProcedureName("listarTipoArchivoUnidad").declareParameters(
					new SqlParameter("PFID_UNIDAD", Types.VARCHAR), new SqlOutParameter("COD_RESULTADO", Types.VARCHAR),
					new SqlOutParameter("MSG_RESULTADO", Types.VARCHAR));

			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("PFID_UNIDAD", unidad);

			Map<String, Object> results = jdbcCall.execute(parametros);

			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);

			codigoRpta = Integer.parseInt(results.get("COD_RESULTADO").toString());
			mensajeRpta = results.get("MSG_RESULTADO").toString();
			if (codigoRpta != 1) {// VERIFICACION DE ERROR EN LA BD
				log.error(
						"[listarTipoArchivo] Ocurrio un error en la operacion del Procedimiento almacenado listadoArchivoExpediente : "
								+ mensajeRpta);
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}

			if (rs != null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {

						Item item = new Item();
						item.setIdCodigo(Long.parseLong(map.get("ID_CODIGO").toString()));
						item.setCidCodigo(map.get("CID_CODIGO") != null ? map.get("CID_CODIGO").toString() : "");
						item.setCidNombre(map.get("CID_NOMBRE") != null ? map.get("CID_NOMBRE").toString() : "");

						lista.add(item);
					}
					outResponse.setResponse(lista);
					outResponse.setCodResultado(codigoRpta);
					outResponse.setMsgResultado(mensajeRpta);
				} else {
					outResponse.setCodResultado(2);// Codigo para indicar que no existen registros en la BD
					outResponse.setTotal(0);
					outResponse.setResponse("");
					outResponse.setMsgResultado("¡No existen registros!");
				}
			}

		} catch (Exception e) {
			log.error("ERROR DAO listarTipoArchivo>>>>" + this.getClass().getName(), e);
			outResponse.setCodResultado(codigoRpta);
			outResponse.setMsgResultado(mensajeRpta);
		}
		log.info("[DAO listarTipoArchivo] FIN  ");
		return outResponse;
	}

	*/
}
