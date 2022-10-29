package com.asitec.dao.impl;

import java.util.List;

import javax.sql.DataSource; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import com.asitec.dao.ComunDao;
import com.asitec.model.Periodo;
import com.asitec.model.Ubigeo; 

 @Component
public class ComunDaoImpl implements ComunDao {

	 JdbcTemplate jdbcTemplate;	 
	// private SimpleJdbcCall simpleJdbcCall;
	 
	 private static final Logger log = LoggerFactory.getLogger(ComunDaoImpl.class);
	 
	  @Autowired
	  public ComunDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	  }
	 
	@Override
	public List<Periodo> listarPeridos() {
		String SQL_FIND_PERSON = "select * from AstPeriodo";
		log.info(SQL_FIND_PERSON);
		 List<Periodo> customers = jdbcTemplate.query(
				 SQL_FIND_PERSON,
	                new BeanPropertyRowMapper(Periodo.class));
		return customers;
 
	}

	@Override
	public List<Ubigeo> listarRegion() {
		String SQL_FIND_PERSON = "select codUbigeo,codRegion,region,codProvincia,provincia,codDistrito,distrito from Ubigeo";
		log.info(SQL_FIND_PERSON);
		 List<Ubigeo> lista = jdbcTemplate.query(
				 SQL_FIND_PERSON,
	                new BeanPropertyRowMapper(Ubigeo.class));
		return lista;
	}

	 
}
