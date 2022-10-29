package com.asitec.api.response;

import lombok.Data;

@Data
public class EspecialidadResponse {

	String dni;
	String apellidoPaterno;
	String apellidoMaterno;
	String nombres;
	String nombreCompleto;
	String fechaNacimiento;
	int sexo;
	String ubigeo;
	String discapacidad;
	String fallecido;
	String numeroMesa;
	String gradoInstruccion;
	String restriccion;
	String tipoDocumento;
	String fecNacimientoTexto;
	String numeroMesaAnt;
	String numeroCambioUbigeo;	
	int numeroPadronPk;
	String digitoVerificacion;

}
