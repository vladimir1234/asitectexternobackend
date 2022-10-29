package com.asitec.controller;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.asitec.model.Archivo;
import com.asitec.model.DatoGeneral;
import com.asitec.model.Tipdocumento;
import com.asitec.service.AccionArchivoService;
import com.asitec.service.TipdocumentoService;
import com.asitec.util.ApiOutResponse;
import com.asitec.util.CustomErrorType;
import com.asitec.util.UploadUtility;
import com.asitec.util.Util;



@CrossOrigin(origins = { "http://localhost:4200", "http://vulcano.pais.gob.pe", "http://backend.pais.gob.pe", "http://app.pais.gob.pe" })



@RestController
@RequestMapping("/api/Tipdocumento")
public class TipdocumentoController {

	public static final Logger logger = LoggerFactory.getLogger(TipdocumentoController.class);

	@Autowired
	private TipdocumentoService servicio;
	
	@Autowired
	private AccionArchivoService accionArchivoBus;
	
	@Value("${rutaArchivoWindows}")
	private String rutaRaiz;

	@PostMapping("/Insertar")
	public ApiOutResponse Insertar(@RequestBody Tipdocumento registro) {
		logger.info("Creando Registro {}", registro.getIdeTipDocumento());
		if (servicio.siExiste(registro)) {
			logger.error("Insercion Incompleta. A registro with name {} already exist", registro.getIdeTipDocumento());

			ApiOutResponse response = new ApiOutResponse();
			response.setCodResultado(-1);
			response.setMsgResultado("ya existe el registro  " + registro.getIdeTipDocumento() + ".");
			return response;
		} else {
			return servicio.Insertar(registro);
		}

	}

	@PostMapping("/Actualizar")
	public ApiOutResponse Actualizar(@RequestBody Tipdocumento registro) {
		logger.info("Actualizando Registro {}", registro.getIdeTipDocumento());
		if (servicio.siExiste(registro)) {
			return servicio.Actualizar(registro);

		} else {
			logger.error("Actualizacion Incompleta. A registro with name {} already exist",
					registro.getIdeTipDocumento());
			ApiOutResponse response = new ApiOutResponse();
			response.setCodResultado(-1);
			response.setMsgResultado("no existe registro  " + registro.getIdeTipDocumento() + ".");

			return response;
		}

	}

	@PostMapping("/Eliminar/{ideTipDocumento}")
	public ApiOutResponse Eliminar(@PathVariable("ideTipDocumento") long id) {
		logger.info("Eliminando Registro {}", id);
		Tipdocumento registro = new Tipdocumento();
		registro.setIdeTipDocumento(id);
		if (servicio.siExiste(registro)) {
			return servicio.EliminarPorId(id);

		} else {
			logger.error("Eliminacion Incompleta de registro con id {} ", id);
			ApiOutResponse response = new ApiOutResponse();
			response.setCodResultado(-1);
			response.setMsgResultado("no existe registro  " + id + ".");

			return response;
		}

	}

	@GetMapping("/Listar")
	public ApiOutResponse listarUbigeo() {
		return servicio.Listar();

	}

	@RequestMapping(value = "/Existe/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> siExiste(@RequestBody Tipdocumento registro) {
		logger.info("Existe registro with id { }", registro.getIdeTipDocumento());

		if (servicio.siExiste(registro)) {
			return new ResponseEntity<Tipdocumento>(registro, HttpStatus.OK);

		} else {
			logger.error("No existe.registro with id { }  not found.", registro.getIdeTipDocumento());
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to upate.registro with id " + registro.getIdeTipDocumento() + " not found."),
					HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping("/SubirArchivo")
	public ApiOutResponse subirArchivo(@RequestHeader(name = "idUsuario", required = true) Long idUsuario,
			@RequestParam("file") MultipartFile file, @RequestParam("nomArchivo") String nomArchivo,
			@RequestParam("idMovimientoProyecto") Long idProyecto, @RequestParam("descripcion") String descripcion,
			@RequestParam("idTipoArchivo") String idTipoArchivo) {

		ApiOutResponse response = new ApiOutResponse();
		Archivo archivo = new Archivo();
		DatoGeneral dato = new DatoGeneral();
		String estadoCopiado = "";

		// String ruta = UploadUtility.obtenerRutaServidorArchivo() +
		// System.getProperty("file.separator") + "UPS"+
		// System.getProperty("file.separator");
		String ruta = rutaRaiz + System.getProperty("file.separator") + Util.getObtenerRutaCarpetas();

		String rutaCompleta = ruta + nomArchivo;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		String nombreSinExtension = nomArchivo.replace("." + extension, "");

		archivo.setExtension(extension);
		archivo.setNombre(nombreSinExtension);
		archivo.setRuta(ruta);
		archivo.setDescripcion(descripcion);
		archivo.setTipoArchivo(idTipoArchivo);

		// dato.setIdProyecto(idProyecto);
		dato.setIdMovimientoProyecto(idProyecto);
		dato.setIdUsuario(idUsuario);

		estadoCopiado = accionArchivoBus.copiar(file, nomArchivo, rutaRaiz);// 1. COPIAR EL ARCHIVO AL REPOSITORIO
		if (!estadoCopiado.equals("OK")) { // 2. VERIFICAR SI EXISTE ERROR AL COPIAR EL ARCHIVO
			response.setCodResultado(500);
			response.setMsgResultado("Error al copiar al archivo al server file, por favor vuelva a intentar");
			return response;
		}

		if (UploadUtility.existeFileEnRuta(rutaCompleta)) { // 3. VERIFICACION DE LA EXISTENCIA DEL ARCHIVO EN LA RUTA
			accionArchivoBus.registrarArchivo(dato, archivo);
			response.setCodResultado(1);
			response.setMsgResultado("OK");
		} else {
			response.setCodResultado(404);
			response.setMsgResultado("Error, el archivo no se copiÃ³ correctamente, por favor vuelva a intentar");
		}

		return response;
	}
	
	
	
}
