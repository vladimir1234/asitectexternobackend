package com.asitec.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asitec.api.request.UnidadEjecutoraRequest;
import com.asitec.dao.UnidadEjecutoraDAO;
import com.asitec.service.UnidadEjecutoraService;
import com.asitec.util.ApiOutResponse;

@Service("UnidadEjecutoraService")
public class UnidadEjecutoraServiceImpl implements UnidadEjecutoraService{
	
	@Autowired
	UnidadEjecutoraDAO  unidadEjecutoraDAO;

	private final Log log = LogFactory.getLog(getClass());

	
	@Override
	public ApiOutResponse buscarUnidadEjecutora(UnidadEjecutoraRequest unidadEjecutoraRequest) {
		
		
		ApiOutResponse rpta = new ApiOutResponse();
		try {
			rpta =  unidadEjecutoraDAO.buscarUnidadEjecutora( unidadEjecutoraRequest);
		} catch (Exception e) {
			mensajeExcepcion(rpta, e, "buscarUnidadEjecutora");
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
