package com.asitec.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asitec.api.request.ColegioMineduRequest;
import com.asitec.dao.ColegioMineduDAO;
import com.asitec.service.ColegioMineduService;
import com.asitec.util.ApiOutResponse;

//@Service("ProyectoMefService")
@Service
public class ColegioMineduServiceImpl implements ColegioMineduService{
	
	@Autowired
	ColegioMineduDAO  colegioMineduDAO;

	private final Log log = LogFactory.getLog(getClass());

	
	@Override
	public ApiOutResponse buscarColegioMinedu(ColegioMineduRequest colegioMineduRequest) {
		
		
		ApiOutResponse rpta = new ApiOutResponse();
		try {
			rpta =  colegioMineduDAO.buscarColegioMinedu( colegioMineduRequest);
		} catch (Exception e) {
			mensajeExcepcion(rpta, e, "buscarColegioMinedu");
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
