package com.asitec.dao;

import com.asitec.api.request.SolicitudRequest;
import com.asitec.util.ApiOutResponse;

public interface SolicitudDAO {

	 ApiOutResponse buscarSolicitud(SolicitudRequest solicitud);
}
