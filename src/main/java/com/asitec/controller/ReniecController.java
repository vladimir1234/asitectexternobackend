package com.asitec.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asitec.api.request.ReniecRequest;
import com.asitec.service.ReniecService;
import com.asitec.util.ApiOutResponse;

@RestController
@RequestMapping("/api/reniec")
public class ReniecController {

	public static final Logger logger = LoggerFactory.getLogger(TipdocumentoController.class);
	
	@Autowired
	private ReniecService reniecService;
	
	@PostMapping("/buscar")
	public ApiOutResponse buscarReniec(@RequestBody ReniecRequest reniecRequest) {
		
		return reniecService.buscarReniec(reniecRequest);

	}

	
	
	
}
