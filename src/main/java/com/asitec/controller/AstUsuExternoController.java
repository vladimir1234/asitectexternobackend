package com.asitec.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.asitec.util.CustomErrorType;

import com.asitec.model.AstUsuExterno;
import com.asitec.service.AstUsuExternoService;

/**
 * 
 * @author HRR (2013)
 */
@RestController
@RequestMapping("api/astUsuExterno")
public class AstUsuExternoController {

	public static final Logger logger = LoggerFactory.getLogger(AstUsuExternoController.class);


	@Autowired
	private AstUsuExternoService servicio;

	@RequestMapping(value = "/ObtenerPorId/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> ObtenerPorId(@PathVariable("id") long id) {
		logger.info("extrayendo Registro  id {}", id);
		AstUsuExterno registro = servicio.ObtenerPorId(id);
		if (registro == null) {
			logger.error("registro with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("registro with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AstUsuExterno>(registro, HttpStatus.OK);
	}

	@RequestMapping(value = "/ObtenerPorNombre/{name}", method = RequestMethod.GET)
	public ResponseEntity<?> ObtenerPorNombre(@PathVariable("name") String name) {
		logger.info("Fetching User with name {}", name);
		AstUsuExterno registro = servicio.ObtenerPorNombre(name);
		if (registro == null) {
			logger.error("registro with name {} not found.",name);
			return new ResponseEntity(new CustomErrorType("registro with name " + name
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AstUsuExterno>(registro, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/Insertar", method = RequestMethod.POST)
	public ResponseEntity<?> Insertar(@RequestBody AstUsuExterno registro, UriComponentsBuilder ucBuilder) {
		logger.info("Creating registro : {}", registro.getIdeUsuExterno());

		if (servicio.siExiste(registro)) {
			logger.error("Insercion Incompleta. A registro with name {} already exist", registro.getIdeUsuExterno());
			return new ResponseEntity(new CustomErrorType("Unable to create. A registro with name " + 
			registro.getIdeUsuExterno() + " already exist."),HttpStatus.CONFLICT);
		}
		servicio.Insertar(registro);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/registro/{id}").buildAndExpand(registro.getIdeUsuExterno()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}


	@RequestMapping(value = "/Actualizar", method = RequestMethod.PUT)
	public ResponseEntity<?> Actualizar(@RequestBody AstUsuExterno registro) {
		logger.info("Actualizando registro with id {}", registro.getIdeUsuExterno());

		if (servicio.siExiste(registro)) {
			servicio.Actualizar(registro);
			return new ResponseEntity<AstUsuExterno>(registro, HttpStatus.OK);
			
		}
		else 
		{
			logger.error("Actualizacion Incompleta. registro with id {} not found.", registro.getIdeUsuExterno());
			return new ResponseEntity(new CustomErrorType("Unable to upate. registro with id " + registro.getIdeUsuExterno() + " not found."),
					HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/Eliminar/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> Eliminar(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting User with id {}", id);

		AstUsuExterno registro = servicio.ObtenerPorId(id);
		if (registro == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		servicio.EliminarPorId(id);
		return new ResponseEntity<AstUsuExterno>(HttpStatus.NO_CONTENT);
		
	}

	@RequestMapping(value = "/Listar", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody  	
	public ResponseEntity<List<AstUsuExterno>> Listar() {
		logger.info("Carga de Listar {}");

		List<AstUsuExterno> registros = servicio.Listar();
		if (registros.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<AstUsuExterno>>(registros, HttpStatus.OK);
	}

	@RequestMapping(value = "/Buscar", method = RequestMethod.GET)
	public ResponseEntity<List<AstUsuExterno>> Buscar(@RequestBody AstUsuExterno registro) {
		List<AstUsuExterno> registros = servicio.Buscar(registro);
		if (registros.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<AstUsuExterno>>(registros, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/Existe/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> siExiste(@RequestBody AstUsuExterno registro) {
		logger.info("Existe registro with id {}", registro.getIdeUsuExterno());

		if (servicio.siExiste(registro)) {
			return new ResponseEntity<AstUsuExterno>(registro, HttpStatus.OK);
			
		}
		else 
		{
			logger.error("No existe. registro with id {} not found.", registro.getIdeUsuExterno());
			return new ResponseEntity(new CustomErrorType("Unable to upate. registro with id " + registro.getIdeUsuExterno() + " not found."),
					HttpStatus.NOT_FOUND);
		}

	}	
	
}