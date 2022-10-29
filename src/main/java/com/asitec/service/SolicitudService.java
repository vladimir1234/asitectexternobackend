package com.asitec.service;

import com.asitec.api.request.SolicitudRequest;
import com.asitec.util.ApiOutResponse;

public interface SolicitudService {

	ApiOutResponse buscarSolicitud(SolicitudRequest solicitud);

}
