package com.asitec.service;

import com.asitec.api.request.InstitucionEducativaRequest;
import com.asitec.util.ApiOutResponse;

public interface InstitucionEducativaService {

	ApiOutResponse buscarInstitucionEducativa(InstitucionEducativaRequest institucionEducativaRequest);

	ApiOutResponse insertar(InstitucionEducativaRequest institucionEducativaRequest);
}
