package com.asitec.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asitec.api.request.PeriodoRequest;
import com.asitec.service.PeriodoService;
import com.asitec.util.ApiOutResponse;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/periodo")
public class PeriodoController {

	public static final Logger logger = LoggerFactory.getLogger(TipdocumentoController.class);
	
	@Autowired
	private PeriodoService periodoService;
	
	@PostMapping("/buscar")
	public ApiOutResponse buscarSolicitud(@RequestBody PeriodoRequest solicitud) {
		
		return  periodoService.buscarPeriodo(solicitud);

	}

	@PostMapping("/registrar")
	public ApiOutResponse registrar(@RequestBody PeriodoRequest solicitud) {
		return periodoService.registrarPeriodo(solicitud);
	}
	
	
	@GetMapping("/listar")
	public ApiOutResponse listarPeriodo() {
		return periodoService.listarPeriodo();

	}

}
