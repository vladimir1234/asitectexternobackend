package com.asitec.api.response;

import lombok.Data;

@Data
public class SolicitudResponse {

	String periodo;
	String snip;
	String cui;
	String nombreProyecto;
	int cantidadIiee;
	int numDiasRestantes;
	String estadoSolicitud;
	String estadoEstudioBasico;
	String estadoEspecialidad;
	String idNotificacion;
	String idSolicitudAt;
	
	   
}
