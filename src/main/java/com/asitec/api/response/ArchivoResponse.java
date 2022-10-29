package com.asitec.api.response;

import java.io.Serializable;

public class ArchivoResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idCodigoArchivo;
	private String nombreArchivo;
	private String tipoArchivo;
	private String fechaRegistro;
	private String descripcion;
	private String nombreEstado;
	private Long fidProyecto;
	private String cidNombreEstado;
	private String estadoCarga;
	private String cidTipoArchivo;
	private String ruta;

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getCidTipoArchivo() {
		return cidTipoArchivo;
	}

	public void setCidTipoArchivo(String cidTipoArchivo) {
		this.cidTipoArchivo = cidTipoArchivo;
	}

	public String getEstadoCarga() {
		return estadoCarga;
	}

	public void setEstadoCarga(String estadoCarga) {
		this.estadoCarga = estadoCarga;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCidNombreEstado() {
		return cidNombreEstado;
	}

	public void setCidNombreEstado(String cidNombreEstado) {
		this.cidNombreEstado = cidNombreEstado;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	public Long getFidProyecto() {
		return fidProyecto;
	}

	public void setFidProyecto(Long fidProyecto) {
		this.fidProyecto = fidProyecto;
	}

	public Long getIdCodigoArchivo() {
		return idCodigoArchivo;
	}

	public void setIdCodigoArchivo(Long idCodigoArchivo) {
		this.idCodigoArchivo = idCodigoArchivo;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getTipoArchivo() {
		return tipoArchivo;
	}

	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
