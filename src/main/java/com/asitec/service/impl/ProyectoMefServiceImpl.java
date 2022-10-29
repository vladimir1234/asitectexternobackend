package com.asitec.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asitec.api.request.ProyectoMefRequest;
import com.asitec.dao.ProyectoMefDAO;
import com.asitec.service.ProyectoMefService;
import com.asitec.util.ApiOutResponse;

@Service("ProyectoMefService")
public class ProyectoMefServiceImpl implements ProyectoMefService{
	
	@Autowired
	ProyectoMefDAO  proyectoMefDAO;

	private final Log log = LogFactory.getLog(getClass());

	
	@Override
	public ApiOutResponse buscarProyectoMef(ProyectoMefRequest proyectoMefRequest) {
		
		
		ApiOutResponse rpta = new ApiOutResponse();
		try {
			rpta =  proyectoMefDAO.buscarProyectoMef( proyectoMefRequest);
		} catch (Exception e) {
			mensajeExcepcion(rpta, e, "buscarProyectoMef");
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
