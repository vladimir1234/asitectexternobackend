package com.asitec.model;

import lombok.Data;

@Data
public class Tipdocumento
{
	private long ideTipDocumento;
	private String nombre;
	private String estadoAuditoria;
	private String usuarioCreacionAuditoria;
	private String fechaCreacionAuditoria;
	private String usuarioModificacionAuditoria;
	private String fechaModificacionAuditoria;
	private String ipeEquipoAuditoria;
	private String programaAuditoria;

	

}