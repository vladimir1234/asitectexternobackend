package com.asitec.dao;

import com.asitec.api.request.UnidadEjecutoraRequest;
import com.asitec.util.ApiOutResponse;

public interface UnidadEjecutoraDAO {

	 ApiOutResponse buscarUnidadEjecutora(UnidadEjecutoraRequest unidadEjecutoraRequest);
}
