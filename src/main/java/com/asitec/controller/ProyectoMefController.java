package com.asitec.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asitec.api.request.ProyectoMefRequest;
import com.asitec.service.ProyectoMefService;
import com.asitec.util.ApiOutResponse;

@RestController
@RequestMapping("/api/proyectoMef")
public class ProyectoMefController {

	public static final Logger logger = LoggerFactory.getLogger(TipdocumentoController.class);
	
	@Autowired
	private ProyectoMefService proyectoMefService;
	
	@PostMapping("/buscar")
	public ApiOutResponse buscarProyectoMef(@RequestBody ProyectoMefRequest proyectoMefRequest) {
		
		return proyectoMefService.buscarProyectoMef(proyectoMefRequest);

	}

	
	
	
}
