package com.asitec.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asitec.api.request.CoordinadorRequest;
import com.asitec.service.CoordinadorService;
import com.asitec.util.ApiOutResponse;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/coordinador")
public class CoordinadorController {

	public static final Logger logger = LoggerFactory.getLogger(TipdocumentoController.class);
	
	@Autowired
	private CoordinadorService coordinadorService;
	

	@PostMapping("/registrar")
	public ApiOutResponse registrar(@RequestBody CoordinadorRequest coordinador) {
		return coordinadorService.registrar(coordinador);
	}
	
	

}
