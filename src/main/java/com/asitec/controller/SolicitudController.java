package com.asitec.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asitec.api.request.SolicitudRequest;
import com.asitec.service.SolicitudService;
import com.asitec.util.ApiOutResponse;

@RestController
@RequestMapping("/api/solicitud")
public class SolicitudController {

	public static final Logger logger = LoggerFactory.getLogger(TipdocumentoController.class);
	
	@Autowired
	private SolicitudService solicitudService;
	
	@PostMapping("/buscar")
	public ApiOutResponse buscarSolicitud(@RequestBody SolicitudRequest solicitud) {
		
		return solicitudService.buscarSolicitud(solicitud);

	}

	
	
	
}
