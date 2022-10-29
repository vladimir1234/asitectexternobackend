package com.asitec.service;

import com.asitec.api.request.UnidadEjecutoraRequest;
import com.asitec.util.ApiOutResponse;

public interface UnidadEjecutoraService {

	ApiOutResponse buscarUnidadEjecutora(UnidadEjecutoraRequest unidadEjecutoraRequest);
}
