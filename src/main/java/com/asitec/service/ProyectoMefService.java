package com.asitec.service;

import com.asitec.api.request.ProyectoMefRequest;
import com.asitec.util.ApiOutResponse;

public interface ProyectoMefService {

	ApiOutResponse buscarProyectoMef(ProyectoMefRequest proyectoMefRequest);
}
