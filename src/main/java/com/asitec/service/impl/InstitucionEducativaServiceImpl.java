package com.asitec.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asitec.api.request.InstitucionEducativaRequest;
import com.asitec.dao.InstitucionEducativaDAO;
import com.asitec.service.InstitucionEducativaService;
import com.asitec.util.ApiOutResponse;

@Service
public class InstitucionEducativaServiceImpl implements InstitucionEducativaService{
	
	@Autowired
	InstitucionEducativaDAO  institucionEducativaDAO;

	private final Log log = LogFactory.getLog(getClass());

	
	@Override
	public ApiOutResponse buscarInstitucionEducativa(InstitucionEducativaRequest institucionEducativaRequest) {
		
		
		ApiOutResponse rpta = new ApiOutResponse();
		try {
			rpta =  institucionEducativaDAO.buscarInstitucionEducativa( institucionEducativaRequest);
		} catch (Exception e) {
			mensajeExcepcion(rpta, e, "buscar InstitucionEducativa");
		}
		return rpta;
	}
	

	ApiOutResponse mensajeExcepcion(ApiOutResponse rpta, Exception e, String metodo) {
		rpta.setCodResultado(1);
		rpta.setMsgResultado(e.toString());
		rpta.setTotal(0);
		rpta.setResponse(null);
		log.error("Ocurrio un error en " + metodo + "]: " + e.toString());
		return rpta;
	}


	@Override
	public ApiOutResponse  insertar(InstitucionEducativaRequest institucionEducativaRequest) {

		ApiOutResponse rpta = new ApiOutResponse();
		try {
			rpta = institucionEducativaDAO.insertar(institucionEducativaRequest);
		} catch (Exception e) {
			mensajeExcepcion(rpta, e, "registro");
		}
		return rpta;
	}
	

}
