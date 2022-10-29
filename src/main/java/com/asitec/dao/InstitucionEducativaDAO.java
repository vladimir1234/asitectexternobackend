package com.asitec.dao;

import com.asitec.api.request.InstitucionEducativaRequest;
import com.asitec.util.ApiOutResponse;

public interface InstitucionEducativaDAO {

	ApiOutResponse buscarInstitucionEducativa(InstitucionEducativaRequest nstitucionEducativaRequest);

	ApiOutResponse insertar(InstitucionEducativaRequest institucionEducativaRequest);
}


