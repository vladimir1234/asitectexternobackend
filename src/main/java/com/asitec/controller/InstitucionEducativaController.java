package com.asitec.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asitec.api.request.InstitucionEducativaRequest;
import com.asitec.service.InstitucionEducativaService;
import com.asitec.util.ApiOutResponse;

@RestController
@RequestMapping("/api/institucionEducativa")
public class InstitucionEducativaController {

	public static final Logger logger = LoggerFactory.getLogger(InstitucionEducativaController.class);
	
	@Autowired
	private InstitucionEducativaService institucionEducativaService;
	
	@PostMapping("/buscar")
	public ApiOutResponse buscarInstitucionEducativa(@RequestBody InstitucionEducativaRequest institucionEducativaRequest) {
		
		return institucionEducativaService.buscarInstitucionEducativa(institucionEducativaRequest);

	}

		
	@PostMapping("/insertar")
	public ApiOutResponse insertar(@RequestBody InstitucionEducativaRequest institucionEducativaRequest) {
		return institucionEducativaService.insertar(institucionEducativaRequest);
	}
	
	
}
