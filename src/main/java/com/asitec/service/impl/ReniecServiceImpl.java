package com.asitec.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asitec.api.request.ReniecRequest;
import com.asitec.api.request.UnidadEjecutoraRequest;
import com.asitec.dao.ReniecDAO;
import com.asitec.dao.UnidadEjecutoraDAO;
import com.asitec.service.ReniecService;
import com.asitec.service.UnidadEjecutoraService;
import com.asitec.util.ApiOutResponse;

@Service("ReniecService")
public class ReniecServiceImpl implements ReniecService{
	
	@Autowired
	ReniecDAO  reniecDAO;

	private final Log log = LogFactory.getLog(getClass());

	
	@Override
	public ApiOutResponse buscarReniec(ReniecRequest reniecRequest) {
		
		
		ApiOutResponse rpta = new ApiOutResponse();
		try {
			rpta =  reniecDAO.buscarReniec( reniecRequest);
		} catch (Exception e) {
			mensajeExcepcion(rpta, e, "buscarReniec");
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

	

}
