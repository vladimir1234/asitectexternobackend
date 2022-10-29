package com.asitec.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asitec.api.request.SolicitudRequest;
import com.asitec.dao.SolicitudDAO;
import com.asitec.service.SolicitudService;
import com.asitec.util.ApiOutResponse;

@Service("SolicitudService")
public class SolicitudServiceImpl implements SolicitudService{
	
	@Autowired
	SolicitudDAO  solicitudDAO;

	private final Log log = LogFactory.getLog(getClass());

	
	@Override
	public ApiOutResponse buscarSolicitud(SolicitudRequest solicitudRequest) {
		
		
		ApiOutResponse rpta = new ApiOutResponse();
		try {
			rpta =  solicitudDAO.buscarSolicitud( solicitudRequest);
		} catch (Exception e) {
			mensajeExcepcion(rpta, e, "buscarSolicitud");
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
