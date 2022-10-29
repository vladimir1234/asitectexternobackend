package com.asitec.api.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProyectoMefResponse {

	String snip;
	String cui;
	String indicaBancoMef;
	String nombreProyecto;
	BigDecimal montoViabilidad;
	BigDecimal montoActualizado;
	String fechaViabilidad;
	String EstadoProyecto;
	String SituacionProyecto;
//	String existeBancoInvMef;

}
