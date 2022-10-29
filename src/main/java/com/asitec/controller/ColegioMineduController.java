package com.asitec.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asitec.api.request.ColegioMineduRequest;
import com.asitec.service.ColegioMineduService;
import com.asitec.util.ApiOutResponse;

@RestController
@RequestMapping("/api/colegioMinedu")
public class ColegioMineduController {

	public static final Logger logger = LoggerFactory.getLogger(TipdocumentoController.class);
	
	@Autowired
	private ColegioMineduService colegioMineduService;
	
	@PostMapping("/buscar")
	public ApiOutResponse buscarColegioMinedu(@RequestBody ColegioMineduRequest colegioMineduRequest) {
		
		return colegioMineduService.buscarColegioMinedu(colegioMineduRequest);

	}

	
	
	
}
