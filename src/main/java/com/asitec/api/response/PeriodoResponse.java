package com.asitec.api.response;

import lombok.Data;

@Data
public class PeriodoResponse {

	long idPeriodo;
	int orden;
	String fechaInicioAdmision;
	String fechaFinAdmision;
	String periodo;
	   
}
