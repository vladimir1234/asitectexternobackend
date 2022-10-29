package com.asitec.dao;

import com.asitec.api.request.ProyectoMefRequest;
import com.asitec.util.ApiOutResponse;

public interface ProyectoMefDAO {

	ApiOutResponse buscarProyectoMef(ProyectoMefRequest proyectoMefRequest);
}


