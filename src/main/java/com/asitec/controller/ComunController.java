package com.asitec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asitec.model.Periodo;
import com.asitec.model.Ubigeo;
import com.asitec.service.ComunService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ComunController {
	
	@Autowired
	ComunService  comunService;

	@GetMapping("/listarUbigeos")
	 public List<Ubigeo> listarUbigeo(){
		return comunService.listarRegion();
		
	}
}
