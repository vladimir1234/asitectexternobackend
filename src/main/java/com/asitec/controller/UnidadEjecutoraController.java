package com.asitec.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asitec.api.request.UnidadEjecutoraRequest;
import com.asitec.service.UnidadEjecutoraService;
import com.asitec.util.ApiOutResponse;

@RestController
@RequestMapping("/api/unidadEjecutora")
public class UnidadEjecutoraController {

	public static final Logger logger = LoggerFactory.getLogger(TipdocumentoController.class);
	
	@Autowired
	private UnidadEjecutoraService unidadEjecutoraService;
	
	@PostMapping("/buscar")
	public ApiOutResponse buscarUnidadEjecutora(@RequestBody UnidadEjecutoraRequest unidadEjecutoraRequest) {
		
		return unidadEjecutoraService.buscarUnidadEjecutora(unidadEjecutoraRequest);

	}

	
	
	
}
