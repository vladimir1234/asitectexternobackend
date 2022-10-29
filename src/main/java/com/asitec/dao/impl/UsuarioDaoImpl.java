package com.asitec.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import com.asitec.dao.UsuarioDao;
import com.asitec.model.Usuario;

@Component
public class UsuarioDaoImpl implements UsuarioDao {
	
	 JdbcTemplate jdbcTemplate;	 
	 private SimpleJdbcCall jdbcCall;
		 
	 private static final Logger log = LoggerFactory.getLogger(UsuarioDaoImpl.class);
		 
	
	
	@Autowired
	  public UsuarioDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	  }

	@Override
	public List<Usuario> buscarInfo(Usuario usuario) {
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			jdbcCall.withProcedureName("paUsuarioExternoBuscar")
			.declareParameters(
					new SqlParameter("NroDocumento", Types.VARCHAR),
					new SqlParameter("TxtNombres", Types.VARCHAR),
					new SqlParameter("TxtApellidos", Types.VARCHAR),
					new SqlParameter("EstAutorizado", Types.NUMERIC),
					new SqlParameter("IdeUei", Types.NUMERIC),
					new SqlParameter("CodRegion", Types.VARCHAR)
					);
			
			 MapSqlParameterSource parametros = new MapSqlParameterSource();
			 
		/*	 parametros.addValue("NroDocumento",("".equals(usuario.getNroDocumento()))?null:usuario.getNroDocumento());
			 parametros.addValue("TxtNombres", ("".equals(usuario.getTxtNombres()))?null:usuario.getTxtNombres());
			 parametros.addValue("TxtApellidos", ("".equals(usuario.getTxtApePaterno()))?null:usuario.getTxtApePaterno());
			 parametros.addValue("EstAutorizado", ("".equals(usuario.getTxtAutoriza()))?null:usuario.getTxtAutoriza());
			 parametros.addValue("IdeUei", ("".equals(usuario.getTxtUnidadEjecutora()))?null:usuario.getTxtUnidadEjecutora());
			 parametros.addValue("CodRegion", ("".equals(usuario.getTxtRegion()))?null:usuario.getTxtRegion());
		*/	 
			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get("#result-set-1");
			if(rs.size()>0) {
				for (Map<String, Object> map : rs) {
					Usuario item  = new Usuario();
		/*			item.setIdeTipDocumento((map.get("IdeTipDocumento")==null)?"":map.get("IdeTipDocumento").toString());
					item.setTxtTipDocumento((map.get("TxtTipDocumento")==null)?"":map.get("TxtTipDocumento").toString());
					item.setNroDocumento((map.get("NroDocumento")==null)?"":map.get("NroDocumento").toString());
					item.setTxtNombres( ((map.get("TxtNombres")==null)?"":map.get("TxtNombres").toString()) + ((map.get("TxtApellidos")==null)?"":map.get("TxtApellidos").toString()));
					item.setTxtApePaterno((map.get("TxtApellidos")==null)?"":map.get("TxtApellidos").toString());
			    	item.setTxtUnidadEjecutora((map.get("TxtNombreUei")==null)?"": map.get("TxtNombreUei").toString());
			    	item.setEstAutorizado((map.get("EstAutorizado")==null)?"": map.get("EstAutorizado").toString());
			*/		lista.add(item);
				}
				
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return lista;
	}

}
