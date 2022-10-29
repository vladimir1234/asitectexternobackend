package com.asitec.model;

public class DatoGeneral {

	long idUsuario;
	long idPerfil;
	long idProyecto;
	long idMovimientoProyecto;
	String ip;
	String nombreArchivo;
	String rutaArchivo;
	long idTipoModalidad;
	String fechaRd;
	String presupuesto;

	public String getFechaRd() {
		return fechaRd;
	}

	public void setFechaRd(String fechaRd) {
		this.fechaRd = fechaRd;
	}

	public String getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(String presupuesto) {
		this.presupuesto = presupuesto;
	}

	public long getIdTipoModalidad() {
		return idTipoModalidad;
	}

	public void setIdTipoModalidad(long idTipoModalidad) {
		this.idTipoModalidad = idTipoModalidad;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public long getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(long idProyecto) {
		this.idProyecto = idProyecto;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public long getIdMovimientoProyecto() {
		return idMovimientoProyecto;
	}

	public void setIdMovimientoProyecto(long idMovimientoProyecto) {
		this.idMovimientoProyecto = idMovimientoProyecto;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

}
