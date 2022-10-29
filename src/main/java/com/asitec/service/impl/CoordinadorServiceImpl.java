package com.asitec.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asitec.api.request.CoordinadorRequest;
import com.asitec.dao.CoordinadorDAO;
import com.asitec.service.CoordinadorService;
import com.asitec.util.ApiOutResponse;

@Service
public class CoordinadorServiceImpl implements CoordinadorService {

	@Autowired
	CoordinadorDAO coordinadorDAO;

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
	public ApiOutResponse registrar(CoordinadorRequest coordinador) {
		ApiOutResponse rpta = new ApiOutResponse();
		try {
			rpta = coordinadorDAO.registrar(coordinador);
		} catch (Exception e) {
			mensajeExcepcion(rpta, e, "registro");
		}
		return rpta;
	}

}
