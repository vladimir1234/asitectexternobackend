package com.asitec.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asitec.api.request.PeriodoRequest;
import com.asitec.dao.PeriodoDAO;
import com.asitec.service.PeriodoService;
import com.asitec.util.ApiOutResponse;

@Service
public class periodoServiceImpl implements PeriodoService{
	
	@Autowired
	PeriodoDAO  periodoDAO;

	private final Log log = LogFactory.getLog(getClass());

	ApiOutResponse mensajeExcepcion(ApiOutResponse rpta, Exception e, String metodo) {
		rpta.setCodResultado(1);
		rpta.setMsgResultado(e.toString());
		rpta.setTotal(0);
		rpta.setResponse(null);
		log.error("Ocurrio un error en " + metodo + "]: " + e.toString());
		return rpta;
	}
	
	
	@Override
	public ApiOutResponse buscarPeriodo(PeriodoRequest periodo) {
		ApiOutResponse rpta = new ApiOutResponse();
		try {
			rpta =  periodoDAO.buscarPeriodo( periodo);
		} catch (Exception e) {
			mensajeExcepcion(rpta, e, "buscarReniec");
		}
		return rpta;
	}


	@Override
	public ApiOutResponse registrarPeriodo(PeriodoRequest periodo) {
		ApiOutResponse rpta = new ApiOutResponse();
		try {
			rpta = periodoDAO.registrarPeriodo(periodo);
		} catch (Exception e) {
			mensajeExcepcion(rpta, e, "registro");
		}
		return rpta;
	}

	
	
	@Override
	public ApiOutResponse listarPeriodo() {
		ApiOutResponse rpta = new ApiOutResponse();
		try {
			rpta = periodoDAO.listarPeriodo();
		} catch (Exception e) {
			mensajeExcepcion(rpta, e, "registro");
		}
		return rpta;
	}
		
	
	

}
